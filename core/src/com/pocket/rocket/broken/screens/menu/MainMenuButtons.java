package com.pocket.rocket.broken.screens.menu;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.screens.MainGameScreen;
import com.pocket.rocket.broken.screens.tutorial.TutorialScreen;

import static com.badlogic.gdx.utils.Timer.Task;
import static com.badlogic.gdx.utils.Timer.schedule;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Preference.getTotalScore;
import static com.pocket.rocket.broken.enums.Text.GALLERY;
import static com.pocket.rocket.broken.enums.Text.SETTINGS;
import static com.pocket.rocket.broken.enums.Text.START;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_HEIGHT;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_SWITCH_TIME;
import static com.pocket.rocket.broken.screens.MenuScreen.X_MENU_BUTTON_POSITION;
import static com.pocket.rocket.broken.screens.MenuScreen.Y_MENU_BUTTON;

public class MainMenuButtons extends Group {
    public final Group menuGroup;
    public final Gallery gallery;
    private final Main main;
    private final Group settings;

    public MainMenuButtons(final Main main, final Group menuGroup) {
        this.main = main;
        this.menuGroup = menuGroup;

        this.gallery = new Gallery(main, menuGroup);
        gallery.setVisible(false);

        this.settings = new Settings(menuGroup, main);
        settings.setVisible(false);

        addActor(gallery);
        addActor(settings);
        addActor(buildButtons());
    }

    private Group buildButtons() {
        final Group menuButtonsGroup = new Group();

        TextButton startButton = new TextButton(START.get(), AssetLoader.getButtonStyle());
        startButton.setBounds(X_MENU_BUTTON_POSITION, Y_MENU_BUTTON, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuGroup.addAction(Actions.moveTo(0, -HEIGHT, 0.25f));
                schedule(new Task() {
                    @Override
                    public void run() {
                        if (getTotalScore() > 1) {
                            main.setScreen(new MainGameScreen(main));
                        } else {
                            main.setScreen(new TutorialScreen(main));
                        }
                    }
                }, 0.25f);
            }
        });
        menuButtonsGroup.addActor(startButton);

        Label galleryLabel = new Label(GALLERY.get(), new Label.LabelStyle(getFont()));
        galleryLabel.setBounds(X_MENU_BUTTON_POSITION, Y_MENU_BUTTON - 75, MENU_BUTTON_WIDTH, 75);
        galleryLabel.setAlignment(Align.center);
        galleryLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gallery.setVisible(true);
                menuGroup.addAction(Actions.moveTo(-WIDTH, 0, MENU_SWITCH_TIME));
            }
        });
        menuButtonsGroup.addActor(galleryLabel);

        final Label settingsLabel = new Label(SETTINGS.get(), new Label.LabelStyle(getFont()));
        settingsLabel.setBounds(X_MENU_BUTTON_POSITION, Y_MENU_BUTTON - 190, MENU_BUTTON_WIDTH, 100);
        settingsLabel.setAlignment(Align.center);
        settingsLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settings.setVisible(true);
                menuGroup.addAction(Actions.moveTo(-WIDTH, 0, MENU_SWITCH_TIME));
            }
        });
        menuButtonsGroup.addActor(settingsLabel);
        return menuButtonsGroup;
    }
}
