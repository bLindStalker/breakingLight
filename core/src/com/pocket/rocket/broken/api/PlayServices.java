package com.pocket.rocket.broken.api;

public interface PlayServices {
    public void connect();

    public void disconnect();

    public boolean isConnected();

    public void signOut();

    public void unlockAchievement(int n);

    public void unlockIncrementAchievement(int n, int count);

    public void showAchievements();

    public void submitScore(long score);

    public void showLeaderboard();
}
