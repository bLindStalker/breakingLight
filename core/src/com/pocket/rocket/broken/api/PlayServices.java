package com.pocket.rocket.broken.api;

import com.pocket.rocket.broken.actors.userData.ScoreData;
import com.pocket.rocket.broken.enums.Achievement;

import java.util.List;

public interface PlayServices {
    void connect();

    void disconnect();

    boolean isConnected();

    void signOut();

    void unlockAchievement(Achievement achievement);

    void unlockIncrementAchievement(Achievement achievement, int count);

    void showAchievements();

    void submitScore(long score);

    void submitTotalScore(long score);

    List<ScoreData> getLeaderboardPlayers();

    void showLeaderboard();

    void rateGame();
}
