package com.pocket.rocket.broken;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import static com.pocket.rocket.broken.AssetLoader.LAMPS_PREFIX_1;
import static com.pocket.rocket.broken.AssetLoader.setPrefix;

public class Preference {
    private static Preferences prefs = Gdx.app.getPreferences("user_preference");

    public static void saveScore(int score) {
        if (getScore() < score) {
            prefs.putInteger("score", score);
        }
        prefs.putInteger("totalScore", getTotalScore() + score);
        prefs.flush();
    }

    public static int getScore() {
        return prefs.getInteger("score");
    }

    public static int getTotalScore() {
        return prefs.getInteger("totalScore");
    }

    public static void reset() {
        prefs.putInteger("score", 0);
        prefs.putInteger("totalScore", 0);
        prefs.putInteger("lampPrefix", LAMPS_PREFIX_1);
        setPrefix(LAMPS_PREFIX_1);

        prefs.putBoolean("doubleBonusActivated", false);
        prefs.putBoolean("bonusActivatedHeart", false);
        prefs.putBoolean("lamp2", false);
        prefs.putBoolean("lamp3", false);
        prefs.putInteger("language", -1);
        prefs.putLong("playTimes", 0L);
        prefs.putLong("bonusCount", 0);
        prefs.putBoolean("rate", false);
        prefs.putInteger("rateCount", 0);

        prefs.flush();
    }

    public static int getLampPrefix() {
        return prefs.getInteger("lampPrefix", LAMPS_PREFIX_1);
    }

    public static void saveLampPrefix(int prefix) {
        prefs.putInteger("lampPrefix", prefix);
        prefs.flush();
    }

    public static boolean soundStatus() {
        return prefs.getBoolean("sound", true);
    }

    public static void setSoundStatus() {
        prefs.putBoolean("sound", !soundStatus());
        prefs.flush();
    }

    public static int getLanguage() {
        return prefs.getInteger("language", -1);
    }

    public static void saveLanguage(int index) {
        prefs.putInteger("language", index);
        prefs.flush();
    }

    public static void activateDoubleBonus() {
        prefs.putBoolean("doubleBonusActivated", true);
        prefs.flush();
    }

    public static boolean doubleBonusActivated() {
        return prefs.getBoolean("doubleBonusActivated", false);
    }

    public static void setHeartBonus() {
        prefs.putBoolean("bonusActivatedHeart", true);
        prefs.flush();
    }

    public static boolean bonusActivatedHeart() {
        return prefs.getBoolean("bonusActivatedHeart", false);
    }

    public static boolean lamp2Open() {
        return prefs.getBoolean("lamp2", false);
    }

    public static void setLamp2Open() {
        prefs.putBoolean("lamp2", true);
        prefs.flush();
    }

    public static boolean lamp3Open() {
        return prefs.getBoolean("lamp3", false);
    }

    public static void setLamp3Open() {
        prefs.putBoolean("lamp3", true);
        prefs.flush();
    }

    public static void saveBonusCount(int bonusCount) {
        prefs.putLong("bonusCount", getBonusCount() + bonusCount);
        prefs.flush();
    }

    public static Long getBonusCount() {
        return prefs.getLong("bonusCount", 0);
    }

    public static void updatePlayTimes() {
        prefs.putLong("playTimes", getPlayTimes() + 1L);
        prefs.flush();
    }

    public static Long getPlayTimes() {
        return prefs.getLong("playTimes", 0);
    }

    public static void setRated() {
        prefs.putBoolean("rate", true);
        prefs.flush();
    }

    public static boolean isRated() {
        return prefs.getBoolean("rate", false);
    }

    public static void updateRateCount() {
        prefs.putInteger("rateCount", getRateCount() + 1);
        prefs.flush();
    }

    public static int getRateCount() {
        return prefs.getInteger("rateCount", 0);
    }

    public static void resetRateCount() {
        prefs.putInteger("rateCount", 0);
        prefs.flush();
    }
}
