package com.breaking.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.breaking.game.enums.LampData;
import com.google.gson.Gson;

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
        prefs.flush();
    }

    public static String getConfigs() {
        String config = prefs.getString("config");
        if (config == null || config.isEmpty()) {
            String json = new Gson().toJson(new LampData());
            saveConfigs(json);
            return json;
        } else {
            return config;
        }
    }

    public static void saveConfigs(String config) {
        prefs.putString("config", config);
        prefs.flush();
    }
}
