package com.pocket.rocket.broken;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.pocket.rocket.broken.enums.LightBulbStatus;

import java.util.ArrayList;

import static com.pocket.rocket.broken.Preference.getLampPrefix;
import static java.lang.String.format;

public class AssetLoader implements Disposable {
    public static final int LAMPS_PREFIX_0 = 0;
    public static final int LAMPS_PREFIX_1 = 1;
    public static final int LAMPS_PREFIX_2 = 2;
    public static final int LAMPS_PREFIX_3 = 3;
    public static final Color BUTTON_TEXT_COLOR = new Color(0.38f, 0, 0, 1);

    private static final String TURN_ON = "lamps/%s/turn_on.png";
    private static final String TURN_OFF = "lamps/%s/turn_off.png";
    private static final String BROKEN = "lamps/%s/broken.png";

    private static final String FONT = "font.fnt";
    private static final String BUTTON_UP = "button_up.png";
    private static final String BUTTON_DOWN = "button_down.png";
    private static final String RESULT = "result.png";
    private static final String DIALOG = "dialog.png";

    private static final String LABEL = "labels/logo.png";
    private static final String GAME_OVER_LABEL = "labels/game_over.png";
    private static final String CONGRATULATIONS = "labels/congratulations.png";
    private static final String TUTORIAL = "labels/tutorial.png";

    private static final String ADS = "elements/icons/ads.png";
    private static final String NO_ADS = "elements/icons/no_ads.png";
    private static final String RATE_US = "elements/icons/rate_us.png";
    private static final String SOUND_OFF = "elements/icons/sound_off.png";
    private static final String SOUND_ON = "elements/icons/sound_on.png";
    private static final String SERVICE_ON = "elements/icons/service_on.png";
    private static final String SERVICE_OFF = "elements/icons/service_off.png";
    private static final String HEART_ATLAS = "elements/heart/heart_atlas.atlas";
    private static final String STAR = "elements/star.png";
    private static final String GALLERY = "gallery.png";
    private static final String OK = "elements/ok.png";
    private static final String ROUND = "elements/round.png";
    private static final String BAR = "elements/bar.png";
    private static final String PROGRESS = "elements/progress.png";

    private static AssetManager assetManager;
    private static int defaultPrefix = LAMPS_PREFIX_1;
    private static String background;

    public static void initialize() {
        assetManager = new AssetManager();

        ArrayList<String> backgrounds = new ArrayList<String>();
        backgrounds.add("bg/background1.png");
  /*      backgrounds.add("bg/background2.png");
        backgrounds.add("bg/background3.png");
        backgrounds.add("bg/background4.png");*/

        background = backgrounds.get(MathUtils.random(0, backgrounds.size() - 1));
        assetManager.load(background, Texture.class);
        assetManager.load(RESULT, Texture.class);

        assetManager.load(getLampPath(LAMPS_PREFIX_0, TURN_ON), Texture.class);
        loadLamps(LAMPS_PREFIX_1);
        loadLamps(LAMPS_PREFIX_2);
        loadLamps(LAMPS_PREFIX_3);

        assetManager.load(FONT, BitmapFont.class);

        assetManager.load(BUTTON_UP, Texture.class);
        assetManager.load(BUTTON_DOWN, Texture.class);

        loadLabels();
        loadIcons();

        assetManager.load(GALLERY, Texture.class);
        assetManager.load(DIALOG, Texture.class);
        assetManager.load(BAR, Texture.class);
        assetManager.load(PROGRESS, Texture.class);
        assetManager.load(ROUND, Texture.class);
        assetManager.load(OK, Texture.class);
        assetManager.load(STAR, Texture.class);

        assetManager.load(HEART_ATLAS, TextureAtlas.class);

        assetManager.finishLoading();

        defaultPrefix = getLampPrefix();
    }

    private static void loadLabels() {
        assetManager.load(LABEL, Texture.class);
        assetManager.load(GAME_OVER_LABEL, Texture.class);
        assetManager.load(CONGRATULATIONS, Texture.class);
        assetManager.load(TUTORIAL, Texture.class);
    }

    private static void loadIcons() {
        assetManager.load(ADS, Texture.class);
        assetManager.load(NO_ADS, Texture.class);
        assetManager.load(RATE_US, Texture.class);
        assetManager.load(SOUND_OFF, Texture.class);
        assetManager.load(SOUND_ON, Texture.class);
        assetManager.load(SERVICE_OFF, Texture.class);
        assetManager.load(SERVICE_ON, Texture.class);
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

    public static Texture getResultBg() {
        return assetManager.get(RESULT);
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
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(getButtonUp()));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion((Texture) assetManager.get(BUTTON_DOWN)));
        buttonStyle.font = assetManager.get(FONT);
        buttonStyle.fontColor = BUTTON_TEXT_COLOR;

        return buttonStyle;
    }

    public static Texture getButtonUp() {
        return assetManager.get(BUTTON_UP);
    }

    public static Label.LabelStyle getFont() {

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assetManager.get(FONT);
        labelStyle.fontColor = Color.FIREBRICK;

        return labelStyle;
    }

    public static Label.LabelStyle getFont(Color color) {
        Label.LabelStyle font = getFont();
        font.fontColor = color;
        return font;
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

    private static Texture turnOn() {
        return assetManager.get(getLampPath(defaultPrefix, TURN_ON));
    }

    private static Texture turnOff() {
        return assetManager.get(getLampPath(defaultPrefix, TURN_OFF));
    }

    private static Texture broken() {
        return assetManager.get(getLampPath(defaultPrefix, BROKEN));
    }

    public static Texture getLabel() {
        return assetManager.get(LABEL);
    }

    public static Texture getGameOverLabel() {
        return assetManager.get(GAME_OVER_LABEL);
    }

    public static Texture getCongratulationsLabel() {
        return assetManager.get(CONGRATULATIONS);
    }

    public static Texture getDialog() {
        return assetManager.get(DIALOG);
    }

    public static Texture getBar() {
        return assetManager.get(BAR);
    }

    public static Texture getProgress() {
        return assetManager.get(PROGRESS);
    }

    public static Texture getAds() {
        return assetManager.get(ADS);
    }

    public static Texture getNoAds() {
        return assetManager.get(NO_ADS);
    }

    public static Texture getRateUs() {
        return assetManager.get(RATE_US);
    }

    public static Texture getSoundOff() {
        return assetManager.get(SOUND_OFF);
    }

    public static Texture getSoundOn() {
        return assetManager.get(SOUND_ON);
    }

    public static Texture getServiceOn() {
        return assetManager.get(SERVICE_ON);
    }

    public static Texture getServiceOff() {
        return assetManager.get(SERVICE_OFF);
    }

    public static Texture getTutorial() {
        return assetManager.get(TUTORIAL);
    }

    public static Array<TextureAtlas.AtlasRegion> getHeart() {
        return ((TextureAtlas) assetManager.get(HEART_ATLAS)).findRegions("heart");
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
