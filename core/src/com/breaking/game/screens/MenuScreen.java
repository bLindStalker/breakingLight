package com.breaking.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.AssetLoader;
import com.breaking.game.Constants;
import com.breaking.game.Main;
import com.breaking.game.Preference;
import com.breaking.game.actors.ImageActor;
import com.breaking.game.screens.menu.MainMenu;

import java.util.HashMap;
import java.util.Map;

import static com.breaking.game.AssetLoader.LAMPS_PREFIX_0;
import static com.breaking.game.AssetLoader.LAMPS_PREFIX_1;
import static com.breaking.game.AssetLoader.LAMPS_PREFIX_2;
import static com.breaking.game.AssetLoader.LAMPS_PREFIX_3;
import static com.breaking.game.AssetLoader.getCheckround;
import static com.breaking.game.AssetLoader.getFont;
import static com.breaking.game.AssetLoader.getPrefix;
import static com.breaking.game.Constants.HEIGHT;
import static com.breaking.game.Constants.LAMP_HEIGHT;
import static com.breaking.game.Constants.LAMP_OPEN_MAX;
import static com.breaking.game.Constants.LAMP_OPEN_TOTAL;
import static com.breaking.game.Constants.LAMP_WIDTH;
import static com.breaking.game.Constants.WIDTH;
import static com.breaking.game.Preference.getScore;
import static com.breaking.game.Preference.getTotalScore;

public class MenuScreen extends BaseScreen {

    public static final float MENU_SWITCH_TIME = 0.35f;
    private static final float ADVANCED_FONT_SCALE = 0.8f;
    private static final int X_MENU_BUTTON_POSITION = Constants.X_CENTER - 200;
    private static final int Y_MENU_BUTTON = 500;
    private static final int Y_ADVANCED_BUTTON = Y_MENU_BUTTON + 200;
    private static final int MENU_BUTTON_WIDTH = 400;
    private static final int ADVANCED_BUTTON_HEIGHT = 100;
    private static final int ADVANCED_BUTTON_WHITE_SPACE = 25;
    private static final int GALLERY_HEIGHT = HEIGHT / 2;
    private static final int EMPTY = 100;
    private static final int X_ROUND_POSITION = LAMP_WIDTH * 2 + 40;
    private static final int Y_ROUND_POSITION = LAMP_HEIGHT * 2 + 10;

    private final Group menu;
    private final Group gallery;

    private final Map<Integer, Actor> okActorMap;

    public MenuScreen(final Main main, boolean fade) {
        super(main);
        okActorMap = new HashMap<Integer, Actor>();

        menu = new Group();
        menu.addActor(new MainMenu(main, menu));
        menu.addActor(buildAdvanced());

        gallery = buildGallery();
        menu.addActor(gallery);

        addActor(menu);
        menu.addAction(Actions.alpha(0, 0));
        menu.addAction(Actions.alpha(1, .25f));

        if (fade) {
            addAction(Actions.alpha(0, 0));
            addAction(Actions.alpha(1, .5f));
        }
    }

    private Group buildGallery() {
        final Table scrollTable = new Table();
        scrollTable.add(buildGalleryElement(EMPTY, "", false));
        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_1, "", true));
        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_2,
                "total score = " + LAMP_OPEN_TOTAL,
                getTotalScore() >= LAMP_OPEN_TOTAL));

        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_3,
                "max score = " + LAMP_OPEN_MAX,
                getScore() >= LAMP_OPEN_MAX));
        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_0, "coming soon", false));
        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_0, "coming soon", false));
        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_0, "coming soon", false));
        scrollTable.row();

        final ScrollPane scroller = new ScrollPane(scrollTable);

        scroller.setBounds(WIDTH, HEIGHT, WIDTH, HEIGHT + (GALLERY_HEIGHT / 2));
        scroller.layout();
        scroller.setScrollY((getPrefix() - 1) * GALLERY_HEIGHT);
        scroller.updateVisualScroll();

        return scroller;
    }

    private Group buildGalleryElement(final int index, String displayValue, boolean showLamp) {
        Group element = new Group();
        element.setBounds(0, 0, WIDTH - 100, GALLERY_HEIGHT);

        ImageActor background = new ImageActor(0, 0, WIDTH - 100, GALLERY_HEIGHT, AssetLoader.getGallery());
        element.addActor(background);

        if (index != 100) {
            ImageActor lamp = new ImageActor(115, 80, LAMP_WIDTH * 2, LAMP_HEIGHT * 2, AssetLoader.getLampImage(showLamp ? index : LAMPS_PREFIX_0));

            if (index == LAMPS_PREFIX_0 || !showLamp) {
                Label display = new Label(displayValue, getFont(Color.BLACK));
                display.setAlignment(Align.center);
                display.setHeight(35);
                display.setWidth(LAMP_WIDTH * 2);
                display.setPosition(LAMP_WIDTH / 2, 60);
                display.setFontScale(0.7f, 0.7f);
                element.addActor(display);
            } else {
                lamp.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Actor previousOk = okActorMap.get(getPrefix());
                        if (previousOk != null) {
                            previousOk.setVisible(false);
                        }
                        okActorMap.get(index).setVisible(true);
                        AssetLoader.setPrefix(index);
                        Preference.saveLampPrefix(index);

                        gallery.addAction(Actions.moveBy(0, HEIGHT, MENU_SWITCH_TIME));
                    }
                });

                element.addActor(new ImageActor(X_ROUND_POSITION, Y_ROUND_POSITION, 80, 80, getCheckround()));
                ImageActor okActor = new ImageActor(X_ROUND_POSITION + 5, Y_ROUND_POSITION + 15, 100, 100, AssetLoader.getCheckok());
                okActor.setVisible(index == getPrefix());

                okActorMap.put(index, okActor);
                element.addActor(okActor);
            }

            element.addActor(lamp);

        }
        return element;
    }

    private Group buildAdvanced() {
        final Group advancedButtons = new Group();
        advancedButtons.setPosition(WIDTH, 0);

        Label.LabelStyle font = getFont();
        font.fontColor = Color.FIREBRICK;
        Label label = new Label("best players\nbest players\nbest players", font);
        label.setAlignment(Align.center);
        label.setBounds(Constants.X_CENTER - 300, HEIGHT - 300, 600, 250);
        advancedButtons.addActor(label);

        TextButton allPlayersButton = new TextButton("All players", AssetLoader.getButtonStyle());
        allPlayersButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON, MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        allPlayersButton.getLabel().setFontScale(ADVANCED_FONT_SCALE);
        advancedButtons.addActor(allPlayersButton);

        TextButton achievementButton = new TextButton("Achievement", AssetLoader.getButtonStyle());
        achievementButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON - ADVANCED_BUTTON_HEIGHT - ADVANCED_BUTTON_WHITE_SPACE, MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        achievementButton.getLabel().setFontScale(ADVANCED_FONT_SCALE);
        advancedButtons.addActor(achievementButton);

        TextButton galleryButton = new TextButton("Gallery", AssetLoader.getButtonStyle());
        galleryButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON - 2 * (ADVANCED_BUTTON_HEIGHT + ADVANCED_BUTTON_WHITE_SPACE), MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        galleryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gallery.addAction(Actions.moveBy(0, -HEIGHT, MENU_SWITCH_TIME));
            }
        });
        galleryButton.getLabel().setFontScale(ADVANCED_FONT_SCALE);
        advancedButtons.addActor(galleryButton);

        TextButton helpButton = new TextButton("How to play", AssetLoader.getButtonStyle());
        helpButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON - 3 * (ADVANCED_BUTTON_HEIGHT + ADVANCED_BUTTON_WHITE_SPACE), MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menu.addAction(Actions.alpha(0, .2f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        main.setScreen(new GameScreen(main));
                    }
                }, 0.25f);
            }
        });
        helpButton.getLabel().setFontScale(ADVANCED_FONT_SCALE);
        advancedButtons.addActor(helpButton);

        TextButton clearButton = new TextButton("Clear score", AssetLoader.getButtonStyle());
        clearButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON - 4 * (ADVANCED_BUTTON_HEIGHT + ADVANCED_BUTTON_WHITE_SPACE), MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        clearButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preference.reset();
                menu.addAction(Actions.alpha(0, .18f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        main.setScreen(new MenuScreen(main, false));
                    }
                }, .2f);
            }
        });
        clearButton.getLabel().setFontScale(ADVANCED_FONT_SCALE);
        advancedButtons.addActor(clearButton);

        TextButton backToMenuButton = new TextButton("Menu", AssetLoader.getButtonStyle());
        backToMenuButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON - 5 * (ADVANCED_BUTTON_HEIGHT + ADVANCED_BUTTON_WHITE_SPACE), MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        backToMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menu.addAction(Actions.moveTo(0, 0, MENU_SWITCH_TIME));
            }
        });
        backToMenuButton.getLabel().setFontScale(ADVANCED_FONT_SCALE);
        advancedButtons.addActor(backToMenuButton);

        return advancedButtons;
    }
}
