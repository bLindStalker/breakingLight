package com.pocket.rocket.broken;

public final class Constants {

    public static final int HEIGHT = 1280;
    public static final int WIDTH = 720;
    public static final int X_CENTER = WIDTH / 2;

    public static final int LAMP_WIDTH = 212;
    public static final int LAMP_HEIGHT = 241;
    public static final int LAMP_GROUP_WIDTH = WIDTH - 60;

    public static final float X_BONUS_SIZE = 42f * 1.3f;
    public static final float Y_BONUS_SIZE = 111f * 1.3f;

    // game logic constants
    public static final int BASIC_SCORE = 10;
    public static final int BASIC_BONUS_SCORE = 25;

    public static final int MAX_ACTIVE_LAMPS = 9;

    public static final float MIN_ANGRY_TIME = 0.95f;
    public static final float MAX_ANGRY_TIME = 1.2f;
    public static final float ANGRY_INTERVAL = 1.8f;

    public static final float MIN_ACTIVE_TIME = 0.8f;
    public static final float MAX_ACTIVE_TIME = 1.2f;
    public static final float ACTIVE_INTERVAL = 1.8f;

    public static final float MIN_NEUTRAL_TIME = 0.6f;
    public static final float MAX_NEUTRAL_TIME = 0.8f;
    public static final float NEUTRAL_INTERVAL = 1.2f;

    public static final int HARD_CORE_TIME = 30;

    public static final int MIN_CLICK_FOR_BONUS = 4;
    public static final int MAX_CLICK_FOR_BONUS = 9;

    public static final int MIN_TIME_FOR_HEART_BONUS = 20;
    public static final int MIN_HEART_BONUS_CHANCE = 30;
    public static final int MAX_HEART_BONUS_CHANCE = 70;
    public static final int CREATED_BONUS_INTERVAL = 7;

    // gallery
    public static final int DOUBLE_BONUS_MAX = 100;//800;
    public static final int LAMP_OPEN_MAX = 100;//1200;
    public static final int HEART_BONUS_TOTAL = 100;//10000;
    public static final int LAMP_OPEN_TOTAL = 100;//15000;

    public static final String[] WORLDS = {"WOW!", "Supper!", "Amazing!", "Well done!", "Very well!"};
    public static final String[] MISSING_WORLDS = {"Miss", "Bad", "Shit", "Blunder", "Boner", "Mistake"};
}
