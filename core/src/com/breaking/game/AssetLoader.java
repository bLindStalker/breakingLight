package com.breaking.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.breaking.game.enums.LightBulbStatus;

public class AssetLoader implements Disposable {
    private static final String BACKGROUND = "background.png";
    private static final String HEARD = "heard.png";
    private static final String TURN_ON = "turnOn.png";
    private static final String TURN_OFF = "turnOff.png";
    private static final String BROKEN = "broken.png";
    private static final String FONT = "font.fnt";
    private static final String BUTTON_UP = "buttonUp.png";
    private static final String BUTTON_DOWN = "buttonDown.png";
    private static final String RESULT = "result.png";
    private static final String STAR = "star.png";

    private static AssetManager assetManager;

    public static void initialize() {
        assetManager = new AssetManager();

        assetManager.load(BACKGROUND, Texture.class);
        assetManager.load(RESULT, Texture.class);

        assetManager.load(HEARD, Texture.class);

        assetManager.load(TURN_ON, Texture.class);
        assetManager.load(TURN_OFF, Texture.class);
        assetManager.load(BROKEN, Texture.class);

        assetManager.load(FONT, BitmapFont.class);

        assetManager.load(BUTTON_UP, Texture.class);
        assetManager.load(BUTTON_DOWN, Texture.class);
        assetManager.load(STAR, Texture.class);

        assetManager.finishLoading();
    }

    public static Texture getBackGround() {
        return assetManager.get(BACKGROUND);
    }

    public static Texture getResult() {
        return assetManager.get(RESULT);
    }

    public static Texture getHeard() {
        return assetManager.get(HEARD);
    }

    public static Texture getStar() {
        return assetManager.get(STAR);
    }

    private static Texture turnOn() {
        return assetManager.get(TURN_ON);
    }

    private static Texture turnOff() {
        return assetManager.get(TURN_OFF);
    }

    private static Texture broken() {
        return assetManager.get(BROKEN);
    }

    public static TextButtonStyle getButton() {
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion((Texture) assetManager.get(BUTTON_UP)));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion((Texture) assetManager.get(BUTTON_DOWN)));
        buttonStyle.font = assetManager.get(FONT);

        return buttonStyle;
    }

    public static Label.LabelStyle getFont() {

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assetManager.get(FONT);
        labelStyle.fontColor = Color.FIREBRICK;

        return labelStyle;
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
