package com.pocket.rocket.broken;

public final class Constants {

    public static final int HEIGHT = 1280;
    public static final int WIDTH = 720;
    public static final int X_CENTER = WIDTH / 2;

    public static final int LAMP_WIDTH = 260;
    public static final int LAMP_HEIGHT = 335;
    public static final int Y_LAMP_POSITION = 110;

    public static final int Y_STATUS_POSITION = HEIGHT - 190;

    public static final int TIMER_WIDTH = 130;
    public static final int TIMER_HEIGHT = 100;

    public static final int HEART_SIZE = 90;
    public static final int Y_HEART_POSITION = Y_STATUS_POSITION + TIMER_HEIGHT / 2 - HEART_SIZE / 2;

    public static final float BONUS_SIZE = 110f;

    // game logic constants
    public static final int BASIC_SCORE = 10;
    public static final int BASIC_bonus_SCORE = 20;

    public static final int MAX_ACTIVE_LAMPS = 9;

    public static final float MIN_ACTIVE_LAMP_TIME = 0.95f;
    public static final float MAX_ACTIVE_LAMP_TIME = 1.2f;
    public static final float ACTIVE_LAMP_INTERVAL = 1.8f;

    public static final float MIN_BROKEN_TIME = 0.8f;
    public static final float MAX_BROKEN_TIME = 1.2f;
    public static final float BROKEN_TIME_INTERVAL = 1.8f;

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
