package com.pocket.rocket.broken.desktop;

import com.badlogic.gdx.Gdx;
import com.pocket.rocket.broken.actors.userData.ScoreData;
import com.pocket.rocket.broken.api.PlayServices;
import com.pocket.rocket.broken.enums.Achievement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey (cb) Mikheev
 * TutorialGPGS
 * 26.09.2016
 */
public class GPGSImpl implements PlayServices {

    private final String[] ACHEIVEMENT = {
            "Found 3 keys",
            "Reach 3 levels",
            "Half-way pass",
            "Game over!",
            "Game passed 3 times",

            "Wellcome back to game!",
            "New game after game over!",
            "Level better time",
            "Found secret stuff 1",
            "Found secret stuff 2",

            "Found secret stuff 3",
            "Found secret stuff 4",
            "Found secret stuff 5",
            "100 steps to win",
            "250 steps to win",

            "500 steps to win",
            "1000 steps to win",
            "2500 steps to win",
            "Game master. All acheivements reached.",
            "Get 100 keys",

            "Get 250 keys",
            "Get 500 keys",
            "3 levels without zooming",
            "10 levels without zooming",
            "50 levels without zooming",

            "Keep calm and see 5 random labyrints.",
    };

    private final String className = ">>> " + PlayServices.class.getSimpleName();
    private boolean connectingState = false;

    @Override
    public void connect() {
        connectingState = true;
        Gdx.app.debug(className, "connect");
    }

    @Override
    public void disconnect() {
        connectingState = false;
        Gdx.app.debug(className, "disconnect");
    }

    @Override
    public boolean isConnected() {
        return connectingState;
    }

    @Override
    public void signOut() {
        Gdx.app.debug(className, "sign out");
    }

    @Override
    public void unlockAchievement(Achievement achievement) {

    }

    @Override
    public void unlockIncrementAchievement(Achievement achievement, int count) {

    }

    @Override
    public void showAchievements() {
        Gdx.app.debug(className, "show achievements");
    }

    @Override
    public void submitScore(long score) {
        Gdx.app.debug(className, "submit " + score + " scores");
    }

    @Override
    public void submitTotalScore(long score) {
        Gdx.app.debug(className, "submit " + score + " total scores");
    }

    @Override
    public List<ScoreData> getLeaderboardPlayers() {
        return new ArrayList<ScoreData>();
    }

    @Override
    public void showLeaderboard() {
        Gdx.app.debug(className, "show leaderboard");
    }

    @Override
    public void rateGame() {

    }
}
