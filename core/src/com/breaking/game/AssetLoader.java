package com.breaking.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.breaking.game.enums.LightBulbStatus;

public class AssetLoader implements Disposable {
    public static final String BACKGROUND = "background.png";
    public static final String HEARD = "heard.png";
    public static final String TURN_ON = "turnOn.png";
    public static final String TURN_OFF = "turnOff.png";
    public static final String BROKEN = "broken.png";
    private static AssetManager assetManager;

    public static void initialize() {
        assetManager = new AssetManager();

        assetManager.load(BACKGROUND, Texture.class);

        assetManager.load(HEARD, Texture.class);

        assetManager.load(TURN_ON, Texture.class);
        assetManager.load(TURN_OFF, Texture.class);
        assetManager.load(BROKEN, Texture.class);
        assetManager.load("empty.png", Texture.class);

        assetManager.finishLoading();
    }

    public static Texture getBackGround() {
        return assetManager.get(BACKGROUND);
    }

    public static Texture getHeard() {
        return assetManager.get(HEARD);
    }

    public static Texture turnOn() {
        return assetManager.get(TURN_ON);
    }

    public static Texture turnOff() {
        return assetManager.get(TURN_OFF);
    }

    public static Texture broken() {
        return assetManager.get(BROKEN);
    }

    public static Texture empty() {
        return assetManager.get("empty.png");
    }

    public static Texture getLampImage(LightBulbStatus status) {
        switch (status) {
            case TURN_ON:
                return turnOn();
            case TURN_OFF:
                return turnOff();
            case BROKEN:
                return broken();
            default:
                return turnOff();
        }
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
