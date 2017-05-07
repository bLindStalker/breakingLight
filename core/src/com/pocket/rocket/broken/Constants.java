package com.pocket.rocket.broken;

public final class Constants {

    public static final int HEIGHT = 1280;
    public static final int WIDTH = 720;
    public static final int X_CENTER = WIDTH / 2;

    public static final int LAMP_WIDTH = 212;
    public static final int LAMP_HEIGHT = 241;
    public static final int Y_LAMP_POSITION = 150;
    public static final int LAMP_GROUP_WIDTH = WIDTH - 60;

    public static final float X_BONUS_SIZE = 42f;
    public static final float Y_BONUS_SIZE = 111f;

    // game logic constants
    public static final int BASIC_SCORE = 10;
    public static final int BASIC_bonus_SCORE = 20;

    public static final int MAX_ACTIVE_LAMPS = 9;

    public static final float MIN_ACTIVE_LAMP_TIME = 0.95f;
    public static final float MAX_ACTIVE_LAMP_TIME = 1.2f;
    public static final float ACTIVE_LAMP_INTERVAL = 1.8f;

    public static final float MIN_NEUTRAL_TIME = 0.8f;
    public static final float MAX_NEUTRAL_TIME = 1.2f;
    public static final float neutral_TIME_INTERVAL = 1.8f;

    public static final float MIN_TURN_OFF_TIME = 0.6f;
    public static final float MAX_TURN_OFF_TIME = 0.8f;
    public static final float TURN_OFF_TIME_INTERVAL = 1.2f;

    public static final int HARD_CORE_TIME = 30;

    public static final int MIN_CLICK_FOR_bonus = 3;
    public static final int MAX_CLICK_FOR_bonus = 9;

    // gallery
    public static int LAMP_OPEN_MAX = 100; // 1000
    public static int LAMP_OPEN_TOTAL = 100; // 10000
}
