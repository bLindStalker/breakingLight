package com.pocket.rocket.broken.api;

import com.pocket.rocket.broken.actors.userData.ScoreData;

import java.util.List;

public interface PlayServices {
    void connect();

    void disconnect();

    boolean isConnected();

    void signOut();

    void unlockAchievement(int n);

    void unlockIncrementAchievement(int n, int count);

    void showAchievements();

    void submitScore(long score);

    void submitTotalScore(long score);

    List<ScoreData> getLeaderboardPlayers();

    void showLeaderboard();

    void rateGame();
}
