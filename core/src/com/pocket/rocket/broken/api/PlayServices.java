package com.pocket.rocket.broken.api;

public interface PlayServices {
    void connect();

    void disconnect();

    boolean isConnected();

    void signOut();

    void unlockAchievement(int n);

    void unlockIncrementAchievement(int n, int count);

    void showAchievements();

    void submitScore(long score);

    void showLeaderboard();

    public void rateGame();
}
