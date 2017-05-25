package com.pocket.rocket.broken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.pocket.rocket.broken.actors.userData.ScoreData;
import com.pocket.rocket.broken.api.PlayServices;
import com.pocket.rocket.broken.enums.Achievement;

import java.util.ArrayList;
import java.util.List;


public class GPGSImpl implements PlayServices, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private final static String className = GPGSImpl.class.getSimpleName();

    private static final int RC_SIGN_IN = 9001;
    private static final int REQUEST_ACHIEVEMENTS = 9002;
    private static final int REQUEST_LEADERBOARD = 9003;
    private final ArrayList<ScoreData> userScoreData = new ArrayList<>();

    private final String LEADERBOARD_SCORE_ID = "CgkI-L-JwqYVEAIQAQ";
    private final String LEADERBOARD_TOTAL_ID = "CgkI-L-JwqYVEAIQAg";

    private GoogleApiClient client;
    private AndroidLauncher context;
    private boolean userScoreReady = true;

    public void init(AndroidLauncher context) {
        this.context = context;

        client = new GoogleApiClient.Builder(context)
                .addApi(Games.API)
                .build();
        // .addApi( Plus.API ).addScope( Plus.SCOPE_PLUS_LOGIN ) - не нужно
        // .addScope( Games.SCOPE_GAMES ) - тоже не нужно
    }

    @Override
    public void connect() {
        if (isConnected()) {
            return;
        }

        client.registerConnectionCallbacks(this);
        client.registerConnectionFailedListener(this);
        client.connect();

        Log.d(className, "Client: log in");
    }

    @Override
    public void disconnect() {
        if (!isConnected()) {
            return;
        }

        client.unregisterConnectionCallbacks(this);
        client.unregisterConnectionFailedListener(this);
        client.disconnect();

        Log.d(className, "Client: log out");
    }

    @Override
    public void signOut() {
        if (!isConnected()) {
            return;
        }

        Games.signOut(client);

        Log.d(className, "Client: sign out");
    }

    @Override
    public boolean isConnected() {
        return client != null && client.isConnected();
    }

    public void onActivityResult(int request, int response, Intent intent) {
        Log.d(className, "Activity requestCode: " + request);

        if (request == RC_SIGN_IN) {
            Log.d(className, "RC_SIGN_IN, responseCode=" + response + ", intent=" + intent);
            if (response == Activity.RESULT_OK) {
                client.connect();
            } else {
                int error = R.string.connect_error;
                BaseGameUtils.showActivityResultError(context, request, response, error);
            }
        }
    }

    @Override
    public void unlockAchievement(Achievement achievement) {
        if (!isConnected()) {
            return;
        }

        Games.Achievements.unlock(client, achievement.getHash());
    }

    @Override
    public void unlockIncrementAchievement(Achievement achievement, int count) {
        if (!isConnected()) {
            return;
        }

        Games.Achievements.increment(client, achievement.getHash(), count);
    }

    @Override
    public void showAchievements() {
        if (!isConnected()) {
            connect();
            return;
        }

        Intent intent = Games.Achievements.getAchievementsIntent(client);
        context.startActivityForResult(intent, REQUEST_ACHIEVEMENTS);
    }

    @Override
    public void submitScore(long score) {
        if (!isConnected()) {
            return;
        }

        Games.Leaderboards.submitScore(client, LEADERBOARD_SCORE_ID, score);
    }

    @Override
    public void submitTotalScore(long score) {
        if (!isConnected()) {
            return;
        }

        Games.Leaderboards.submitScore(client, LEADERBOARD_TOTAL_ID, score);
    }

    @Override
    public List<ScoreData> getLeaderboardPlayers() {
        if (!isConnected()) {
            return userScoreData;
        }

        Games.Leaderboards.loadPlayerCenteredScores(client, LEADERBOARD_SCORE_ID, LeaderboardVariant.TIME_SPAN_ALL_TIME,
                LeaderboardVariant.COLLECTION_PUBLIC, 5)
                .setResultCallback(new ResultCallback<Leaderboards.LoadScoresResult>() {

                    @Override
                    public void onResult(@NonNull Leaderboards.LoadScoresResult result) {
                        int size = result.getScores().getCount();

                        for (int i = 0; i < size; i++) {

                            LeaderboardScore lbs = result.getScores().get(i);

                            String name = lbs.getScoreHolderDisplayName();
                            String score = lbs.getDisplayScore();
                            String id = lbs.getScoreHolder().getPlayerId();

                            userScoreData.add(new ScoreData(id, name, score));
                            userScoreReady = true;
                        }
                    }
                });

        return userScoreData;
    }

    @Override
    public void showLeaderboard() {
        if (!isConnected()) {
            connect();
            return;
        }

        // Intent intent = Games.Leaderboards.getAllLeaderboardsIntent(client/*, LEADERBOARD_SCORE_ID*/);
        Intent intent = Games.Leaderboards.getLeaderboardIntent(client, LEADERBOARD_SCORE_ID);
        context.startActivityForResult(intent, REQUEST_LEADERBOARD);
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d(className, "Client: Success connected xD");
    }

    @Override
    public void onConnectionSuspended(int i) {
        client.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(className, "Connection Failed, result: " + connectionResult);

        String error = connectionResult.getErrorMessage();
        BaseGameUtils.resolveConnectionFailure(context, client, connectionResult,
                RC_SIGN_IN, error);
    }

    @Override
    public void rateGame() {
    }
}
