package com.breaking.game;

public final class Constants {

    public static final int HEIGHT = 1280;
    public static final int WIDTH = 720;

    public static final int LIGHT_WIDTH = 200;
    public static final int LIGHT_HEIGHT = 250; //283
    public static final int LAMPS_SPACE = 10;

    public static final int X_CENTER_LAMP_POSITION = WIDTH / 2;
    public static final int Y_LAMP_POSITION = 150;

    public static final int X_STATUS_POSITION = WIDTH / 4;
    public static final int Y_STATUS_POSITION = HEIGHT - 250;

    public static final int TIMER_WIDTH = X_STATUS_POSITION - 40;
    public static final int TIMER_HEIGHT = 120;

    public static final int HEARD_SIZE = 70;

    public static final int Y_LIFE_POSITION = Y_STATUS_POSITION + TIMER_HEIGHT / 2 - HEARD_SIZE / 2;

    // game logic constants
    public static final int TIME = 30; // in seconds
    public static final int BASIC_SCORE = 10;
    public static final int BASIC_STAR_SCORE = 15;

    public static final int MAX_ACTIVE_LAMPS = 9;

    public static final float MIN_ACTIVE_LAMP_TIME = 1f;
    public static final float MAX_ACTIVE_LAMP_TIME = 1.2f;
    public static final float ACTIVE_LAMP_INTERVAL = 1.4f;

    public static final float MIN_BROKEN_TIME = 0.8f;
    public static final float MAX_BROKEN_TIME = 1.2f;
    public static final float BROKEN_TIME_INTERVAL = 1.4f;

    public static final float MIN_TURN_OFF_TIME = 0.6f;
    public static final float MAX_TURN_OFF_TIME = 0.8f;
    public static final float TURN_OFF_TIME_INTERVAL = 0.8f;

    public static final int HARD_CORE_TIME = 15;

    public static final float STAR_SIZE = 90f;
    public static final int MIN_CLICK_FOR_STAR = 2;
    public static final int MAX_CLICK_FOR_STAR = 6;
}
