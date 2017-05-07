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
import static com.pocket.rocket.broken.screens.menu.Settings.EN_INDEX;
import static com.pocket.rocket.broken.screens.menu.Settings.RU_INDEX;
import static com.pocket.rocket.broken.screens.menu.Settings.UA_INDEX;
import static java.lang.String.format;

public class AssetLoader implements Disposable {
    public static final int LAMPS_PREFIX_0 = 0;
    public static final int LAMPS_PREFIX_1 = 1;
    public static final int LAMPS_PREFIX_2 = 2;
    public static final int LAMPS_PREFIX_3 = 3;

    private static final String ANGRY = "lamps/%s/angry.png";
    private static final String NEUTRAL = "lamps/%s/neutral.png";
    private static final String ACTIVE = "lamps/%s/active.png";

    private static final String FONT = "font.fnt";
    private static final String BUTTON_UP = "button_up.png";
    private static final String BUTTON_DOWN = "button_down.png";
    private static final String DIALOG = "dialog.png";
    private static final String HEADER = "header.png";

    private static final String LOGO_LABEL = "labels/logo.png";
    private static final String GAME_OVER_LABEL = "labels/game_over.png";

    private static final String ADS = "elements/icons/ads.png";
    private static final String NO_ADS = "elements/icons/no_ads.png";
    private static final String RATE_US = "elements/icons/rate_us.png";
    private static final String ACHIEVEMENTS = "elements/icons/achievements.png";
    private static final String TOP = "elements/icons/top.png";

    private static final String BACK = "elements/back.png";
    private static final String BONUS = "elements/bonus.png";
    private static final String CHECKED = "elements/checked.png";
    private static final String BAR = "elements/bar.png";
    private static final String PROGRESS = "elements/progress.png";
    private static final String HEART = "elements/heart.png";
    private static final String LINE = "elements/line.png";
    private static final String UA = "elements/ua.png";
    private static final String RU = "elements/ru.png";
    private static final String EN = "elements/en.png";
    private static final String TOGLE1 = "elements/togle1.png";
    private static final String TOGLE2 = "elements/togle2.png";
    private static final String TOGLE3 = "elements/togle3.png";
    private static final String ACTIVE_LANGUAGE = "elements/active_language.png";

    private static final String GALLERY = "gallery.png";
    private static final String HEART_ATLAS = "elements/heart_animation/heart_atlas.atlas";

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

        assetManager.load(getLampPath(LAMPS_PREFIX_0, ACTIVE), Texture.class);
        loadLamps(LAMPS_PREFIX_1);
        loadLamps(LAMPS_PREFIX_2);
        loadLamps(LAMPS_PREFIX_3);

        assetManager.load(FONT, BitmapFont.class);

        assetManager.load(BUTTON_UP, Texture.class);
        assetManager.load(BUTTON_DOWN, Texture.class);

        loadLabels();
        loadIcons();

        assetManager.load(HEADER, Texture.class);
        assetManager.load(BACK, Texture.class);
        assetManager.load(GALLERY, Texture.class);
        assetManager.load(DIALOG, Texture.class);
        assetManager.load(BAR, Texture.class);
        assetManager.load(PROGRESS, Texture.class);
        assetManager.load(CHECKED, Texture.class);
        assetManager.load(BONUS, Texture.class);
        assetManager.load(LINE, Texture.class);
        assetManager.load(UA, Texture.class);
        assetManager.load(RU, Texture.class);
        assetManager.load(EN, Texture.class);
        assetManager.load(ACTIVE_LANGUAGE, Texture.class);
        assetManager.load(TOGLE1, Texture.class);
        assetManager.load(TOGLE2, Texture.class);
        assetManager.load(TOGLE3, Texture.class);

        assetManager.load(HEART, Texture.class);

        assetManager.load(HEART_ATLAS, TextureAtlas.class);

        assetManager.finishLoading();

        defaultPrefix = getLampPrefix();
    }

    private static void loadLabels() {
        assetManager.load(LOGO_LABEL, Texture.class);
        assetManager.load(GAME_OVER_LABEL, Texture.class);
    }

    private static void loadIcons() {
        assetManager.load(ADS, Texture.class);
        assetManager.load(NO_ADS, Texture.class);
        assetManager.load(RATE_US, Texture.class);
        assetManager.load(ACHIEVEMENTS, Texture.class);
        assetManager.load(TOP, Texture.class);
    }

    private static void loadLamps(int prefix) {
        assetManager.load(getLampPath(prefix, ANGRY), Texture.class);
        assetManager.load(getLampPath(prefix, NEUTRAL), Texture.class);
        assetManager.load(getLampPath(prefix, ACTIVE), Texture.class);
    }

    private static String getLampPath(int prefix, String fileName) {
        return format(fileName, prefix);
    }

    public static Texture getBackGround() {
        return assetManager.get(background);
    }

    public static Texture getBonus() {
        return assetManager.get(BONUS);
    }

    public static Texture getGallery() {
        return assetManager.get(GALLERY);
    }

    public static Texture getChecked() {
        return assetManager.get(CHECKED);
    }

    public static TextButtonStyle getButtonStyle() {
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(getButtonUp()));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion((Texture) assetManager.get(BUTTON_DOWN)));
        buttonStyle.font = assetManager.get(FONT);
        buttonStyle.fontColor = Color.BLACK;

        return buttonStyle;
    }

    public static Texture getButtonUp() {
        return assetManager.get(BUTTON_UP);
    }

    public static Label.LabelStyle getFont() {

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assetManager.get(FONT);

        return labelStyle;
    }

    public static Label.LabelStyle getFont(Color color) {
        Label.LabelStyle font = getFont();
        font.fontColor = color;
        return font;
    }

    public static Texture getLampImage(int prefix) {
        return assetManager.get(getLampPath(prefix, ANGRY));
    }

    public static Texture getLampImage(LightBulbStatus status) {
        switch (status) {
            case ANGRY:
                return angry();
            case NEUTRAL:
                return neutral();
            case ACTIVE:
                return active();
            default:
                return neutral();
        }
    }

    public static int getPrefix() {
        return defaultPrefix;
    }

    public static void setPrefix(int prefix) {
        defaultPrefix = prefix == LAMPS_PREFIX_0 ? LAMPS_PREFIX_1 : prefix;
    }

    private static Texture angry() {
        return assetManager.get(getLampPath(defaultPrefix, ANGRY));
    }

    private static Texture neutral() {
        return assetManager.get(getLampPath(defaultPrefix, NEUTRAL));
    }

    private static Texture active() {
        return assetManager.get(getLampPath(defaultPrefix, ACTIVE));
    }

    public static Texture getLogoLabel() {
        return assetManager.get(LOGO_LABEL);
    }

    public static Texture getGameOverLabel() {
        return assetManager.get(GAME_OVER_LABEL);
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

    public static Array<TextureAtlas.AtlasRegion> getHeart() {
        return ((TextureAtlas) assetManager.get(HEART_ATLAS)).findRegions("heart");
    }

    public static Texture getAchievements() {
        return assetManager.get(ACHIEVEMENTS);
    }

    public static Texture getTop() {
        return assetManager.get(TOP);
    }

    public static Texture getHeader() {
        return assetManager.get(HEADER);
    }

    public static Texture getBack() {
        return assetManager.get(BACK);
    }

    public static Texture getLine() {
        return assetManager.get(LINE);
    }

    public static Texture getTogle() {
        return assetManager.get(TOGLE1);
    }

    public static Texture getTogleRoundOn() {
        return assetManager.get(TOGLE2);
    }

    public static Texture getTogleRoundOff() {
        return assetManager.get(TOGLE3);
    }

    public static Texture getLanguage(int index) {
        switch (index) {
            case RU_INDEX:
                return assetManager.get(RU);
            case UA_INDEX:
                return assetManager.get(UA);
            case EN_INDEX:
                return assetManager.get(EN);
            default:
                return assetManager.get(EN);
        }
    }

    public static Texture getActiveLanguage() {
        return assetManager.get(ACTIVE_LANGUAGE);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
