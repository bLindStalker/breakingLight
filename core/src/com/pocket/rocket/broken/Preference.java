package com.pocket.rocket.broken;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

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
        prefs.putInteger("lampPrefix", AssetLoader.LAMPS_PREFIX_1);

        prefs.putBoolean("doubleBonusActivated", false);
        prefs.putBoolean("bonusActivatedHeart", false);
        prefs.putBoolean("lamp2", false);
        prefs.putBoolean("lamp3", false);
        prefs.flush();
    }

    public static int getLampPrefix() {
        return prefs.getInteger("lampPrefix", AssetLoader.LAMPS_PREFIX_1);
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
}
