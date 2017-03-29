package com.breaking.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.breaking.game.enums.LightBulbStatus;

import java.util.ArrayList;

import static com.breaking.game.Preference.getLampPrefix;
import static java.lang.String.format;

public class AssetLoader implements Disposable {
    public static final int LAMPS_PREFIX_0 = 0;
    public static final int LAMPS_PREFIX_1 = 1;
    public static final int LAMPS_PREFIX_2 = 2;
    public static final int LAMPS_PREFIX_3 = 3;
    private static final String HEARD = "heard.png";
    private static final String TURN_ON = "lamps/%s/turnOn.png";
    private static final String TURN_OFF = "lamps/%s/turnOff.png";
    private static final String BROKEN = "lamps/%s/broken.png";
    private static final String FONT = "font.fnt";
    private static final String BUTTON_UP = "buttonUp.png";
    private static final String BUTTON_DOWN = "buttonDown.png";
    private static final String RESULT = "result.png";
    private static final String STAR = "star.png";
    private static final String GALLERY = "gallery.png";
    private static final String OK = "ok.png";
    private static final String ROUND = "round.png";
    public static AssetManager assetManager;
    private static int defaultPrefix = LAMPS_PREFIX_1;
    private static String background;

    public static void initialize() {
        assetManager = new AssetManager();

        ArrayList<String> backgrounds = new ArrayList<String>();
        backgrounds.add("bg/background1.png");
        backgrounds.add("bg/background2.png");
        backgrounds.add("bg/background3.png");
        backgrounds.add("bg/background4.png");

        background = backgrounds.get(MathUtils.random(0, backgrounds.size() - 1));
        assetManager.load(background, Texture.class);
        assetManager.load(RESULT, Texture.class);

        assetManager.load(HEARD, Texture.class);

        assetManager.load(getLampPath(LAMPS_PREFIX_0, TURN_ON), Texture.class);
        loadLamps(LAMPS_PREFIX_1);
        loadLamps(LAMPS_PREFIX_2);
        loadLamps(LAMPS_PREFIX_3);

        assetManager.load(FONT, BitmapFont.class);

        assetManager.load(BUTTON_UP, Texture.class);
        assetManager.load(BUTTON_DOWN, Texture.class);
        assetManager.load(STAR, Texture.class);

        assetManager.load(GALLERY, Texture.class);

        assetManager.load(ROUND, Texture.class);
        assetManager.load(OK, Texture.class);

        assetManager.finishLoading();

        defaultPrefix = getLampPrefix();
    }

    private static void loadLamps(int prefix) {
        assetManager.load(getLampPath(prefix, TURN_ON), Texture.class);
        assetManager.load(getLampPath(prefix, TURN_OFF), Texture.class);
        assetManager.load(getLampPath(prefix, BROKEN), Texture.class);
    }

    private static String getLampPath(int prefix, String fileName) {
        return format(fileName, prefix);
    }

    public static Texture getBackGround() {
        return assetManager.get(background);
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

    public static Texture getGallery() {
        return assetManager.get(GALLERY);
    }


    public static Texture getCheckround() {
        return assetManager.get(ROUND);
    }

    public static Texture getCheckok() {
        return assetManager.get(OK);
    }

    public static TextButtonStyle getButtonStyle() {
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

    public static Texture getLampImage(int prefix) {
        return assetManager.get(getLampPath(prefix, TURN_ON));
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

    public static int getPrefix() {
        return defaultPrefix;
    }

    public static void setPrefix(int prefix) {
        defaultPrefix = prefix == LAMPS_PREFIX_0 ? LAMPS_PREFIX_1 : prefix;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    private static Texture turnOn() {
        return assetManager.get(getLampPath(defaultPrefix, TURN_ON));
    }

    private static Texture turnOff() {
        return assetManager.get(getLampPath(defaultPrefix, TURN_OFF));
    }

    private static Texture broken() {
        return assetManager.get(getLampPath(defaultPrefix, BROKEN));
    }
}
