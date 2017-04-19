package com.pocket.rocket.broken.screens.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.screens.MenuScreen;
import com.pocket.rocket.broken.screens.tutorial.TutorialScreen;

import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_SWITCH_TIME;
import static com.pocket.rocket.broken.screens.MenuScreen.X_MENU_BUTTON_POSITION;
import static com.pocket.rocket.broken.screens.MenuScreen.Y_MENU_BUTTON;

public class AdvancedMenu extends Group {
    private static final float ADVANCED_FONT_SCALE = 0.8f;
    private static final int Y_ADVANCED_BUTTON = Y_MENU_BUTTON + 240;
    private static final int ADVANCED_BUTTON_HEIGHT = 120;
    private static final int ADVANCED_BUTTON_WHITE_SPACE = 30;

    private final Main main;
    private final Group menuGroup;
    private final Group gallery;

    public AdvancedMenu(final Main main, final Group menuGroup, Group gallery) {
        this.main = main;
        this.menuGroup = menuGroup;
        this.gallery = gallery;

        addActor(buildAdvanced());
    }

    private Group buildAdvanced() {
        final Group advancedButtons = new Group();
        advancedButtons.setPosition(WIDTH, 0);

        Label.LabelStyle font = AssetLoader.getFont();
        font.fontColor = Color.FIREBRICK;
        Label label = new Label("best players\nbest players\nbest players", font);
        label.setAlignment(Align.center);
        label.setBounds(X_CENTER - 300, HEIGHT - 300, 600, 250);
        advancedButtons.addActor(label);

        TextButton achievementButton = new TextButton("Achievement", AssetLoader.getButtonStyle());
        achievementButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON, MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        achievementButton.getLabel().setFontScale(ADVANCED_FONT_SCALE);
        advancedButtons.addActor(achievementButton);

        TextButton galleryButton = new TextButton("Gallery", AssetLoader.getButtonStyle());
        galleryButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON - (ADVANCED_BUTTON_HEIGHT + ADVANCED_BUTTON_WHITE_SPACE), MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        galleryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gallery.addAction(Actions.moveBy(0, -HEIGHT, MENU_SWITCH_TIME));
            }
        });
        galleryButton.getLabel().setFontScale(ADVANCED_FONT_SCALE);
        advancedButtons.addActor(galleryButton);

        TextButton helpButton = new TextButton("How to play", AssetLoader.getButtonStyle());
        helpButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON - 2 * (ADVANCED_BUTTON_HEIGHT + ADVANCED_BUTTON_WHITE_SPACE), MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuGroup.addAction(Actions.alpha(0, .2f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        main.setScreen(new TutorialScreen(main));
                    }
                }, 0.25f);
            }
        });
        helpButton.getLabel().setFontScale(ADVANCED_FONT_SCALE);
        advancedButtons.addActor(helpButton);

        TextButton clearButton = new TextButton("Clear score", AssetLoader.getButtonStyle());
        clearButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON - 3 * (ADVANCED_BUTTON_HEIGHT + ADVANCED_BUTTON_WHITE_SPACE), MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        clearButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                com.pocket.rocket.broken.Preference.reset();
                menuGroup.addAction(Actions.alpha(0, .18f));
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
        backToMenuButton.setBounds(X_MENU_BUTTON_POSITION, Y_ADVANCED_BUTTON - 4 * (ADVANCED_BUTTON_HEIGHT + ADVANCED_BUTTON_WHITE_SPACE), MENU_BUTTON_WIDTH, ADVANCED_BUTTON_HEIGHT);
        backToMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuGroup.addAction(Actions.moveTo(0, 0, MENU_SWITCH_TIME));
            }
        });
        backToMenuButton.getLabel().setFontScale(ADVANCED_FONT_SCALE);
        advancedButtons.addActor(backToMenuButton);

        return advancedButtons;
    }
}
