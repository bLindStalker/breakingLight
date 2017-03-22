package com.breaking.game;

public final class Constants {

    public static final int HEIGHT = 1280;
    public static final int WIDTH = 720;

    public static final int LIGHT_WIDTH = 200;
    public static final int LIGHT_HEIGHT = 250;
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
    public static final int TIME = 400; // in seconds
    public static final int BASIC_SCORE = 10;
    public static final int BASIC_STAR_SCORE = 15;

    public static final int MAX_ACTIVE_LAMPS = 6;

    public static final float MIN_ACTIVE_LAMP_TIME = 2f;
    public static final float MAX_ACTIVE_LAMP_TIME = 2f;
    //public static final float ACTIVE_LAMP_INTERVAL = 0.5f;

    public static final float MIN_BROKEN_TIME = 3f;
    public static final float MAX_BROKEN_TIME = 3f;

    public static final float MAX_TURN_OFF_TIME = 1.3f;
    public static final float MIN_TURN_OFF_TIME = 1.3f;

    public static final float HARD_CORE_TIME = 20;
    public static final float UPDATE_DIFFICULTY = 41;
}
