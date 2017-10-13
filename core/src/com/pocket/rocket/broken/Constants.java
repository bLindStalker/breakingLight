package com.pocket.rocket.broken;

public final class Constants {

    public static final int HEIGHT = 1280;
    public static final int WIDTH = 720;
    public static final int X_CENTER = WIDTH / 2;
    public static final int Y_CENTER = HEIGHT / 2;

    public static final int LAMP_WIDTH = 212;
    public static final int LAMP_HEIGHT = 241;
    public static final int LAMP_GROUP_WIDTH = WIDTH - 60;

    public static final float BONUS_WIDTH = 42f * 1.3f;
    public static final float BONUS_HEIGHT = 111f * 1.3f;

    // game logic constants
    public static final int BASIC_SCORE = 10;
    public static final int BASIC_BONUS_SCORE = 25;

    public static final int MAX_ACTIVE_LAMPS = 7;

    public static final float MIN_ANGRY_TIME = 0.8f;
    public static final float MAX_ANGRY_TIME = 1.25f;
    public static final float ANGRY_INTERVAL = 1.8f;

    public static final float MIN_ACTIVE_TIME = 0.55f;
    public static final float MAX_ACTIVE_TIME = 1.1f;
    public static final float ACTIVE_INTERVAL = 1.8f;

    public static final float MIN_NEUTRAL_TIME = 0.3f;
    public static final float MAX_NEUTRAL_TIME = 0.8f;
    public static final float NEUTRAL_INTERVAL = 1.25f;

    public static final int HARD_CORE_TIME = 30;

    public static final int MIN_CLICK_FOR_BONUS = 4;
    public static final int MAX_CLICK_FOR_BONUS = 9;

    public static final int MIN_TIME_FOR_HEART_BONUS = 20;
    public static final int MIN_HEART_BONUS_CHANCE = 45;
    public static final int MAX_HEART_BONUS_CHANCE = 80;

    public static final int MIN_TIME_FOR_FREEZE_BONUS = 15;
    public static final int MIN_FREEZE_BONUS_CHANCE = 35;
    public static final int MAX_FREEZE_BONUS_CHANCE = 80;
    public static final int HEARD_CREATED_BONUS_INTERVAL = 7;
    public static final int FREEZE_CREATED_BONUS_INTERVAL = 8;

    public static final float FREEZE_TIME = 8;
    public static final float FREEZE_POWER = 2.5f;

    public static final int RATE_US_SHOW_TIMES = 20;

    // gallery
    public static final int COLLECT_BONUSES = 50;
    public static final int PLAY_TIMES_HEART = 5;
    public static final int LAMP_OPEN_MAX = 2000;
    public static final int LAMP_OPEN_TOTAL = 20000;
    public static final int MAX_TIME_FOR_FREEZE_BONUS = 60;
}
