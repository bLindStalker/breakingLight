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
    public static final int TIME = 30;
    public static final int BASIC_SCORE = 100;

    public static final int MAX_ACTIVE_LAMPS = 5;
    public static final float MAX_ACTIVE_LAMP_TIME = 3;
    public static final float BROKEN_TIME = 2.5f;
    public static final float TURN_OFF_TIME = BROKEN_TIME + 0.1f;
}
