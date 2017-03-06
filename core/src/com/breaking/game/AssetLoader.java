package com.breaking.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.breaking.game.enums.LightBulbStatus;

public class AssetLoader implements Disposable {
    public static final String BACKGROUND = "background.png";
    public static final String HEARD = "heard.png";
    public static final String TURN_ON = "turnOn.png";
    public static final String TURN_OFF = "turnOff.png";
    public static final String BROKEN = "broken.png";
    public static final String FONT = "font.fnt";
    private static AssetManager assetManager;

    public static void initialize() {
        assetManager = new AssetManager();

        assetManager.load(BACKGROUND, Texture.class);

        assetManager.load(HEARD, Texture.class);

        assetManager.load(TURN_ON, Texture.class);
        assetManager.load(TURN_OFF, Texture.class);
        assetManager.load(BROKEN, Texture.class);
        assetManager.load(FONT, BitmapFont.class);

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

    public static Label.LabelStyle getFont() {
        Label.LabelStyle label1Style = new Label.LabelStyle();
        label1Style.font = assetManager.get(FONT);
        label1Style.fontColor = Color.RED;
        return  label1Style;
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
