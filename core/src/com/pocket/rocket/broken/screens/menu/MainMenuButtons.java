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
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.screens.GameScreen;
import com.pocket.rocket.broken.screens.tutorial.TutorialScreen;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.utils.Timer.Task;
import static com.badlogic.gdx.utils.Timer.schedule;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Preference.getTotalScore;
import static com.pocket.rocket.broken.screens.MenuScreen.LOGO_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_HEIGHT;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_SWITCH_TIME;
import static com.pocket.rocket.broken.screens.MenuScreen.X_MENU_BUTTON_POSITION;
import static com.pocket.rocket.broken.screens.MenuScreen.Y_MENU_BUTTON;

public class MainMenuButtons extends Group {
    private final Main main;
    private final Group menuGroup;
    private final Group gallery;

    public MainMenuButtons(final Main main, final Group menuGroup) {
        addActor(new ImageActor((WIDTH - LOGO_WIDTH) / 2, HEIGHT - LOGO_WIDTH - 100, LOGO_WIDTH, LOGO_WIDTH, AssetLoader.getLogoLabel()));
        this.main = main;
        this.menuGroup = menuGroup;
        this.gallery = new Gallery(menuGroup);


        addActor(buildButtons());
    }

    private Group buildButtons() {
        final Group menuButtonsGroup = new Group();

        TextButton startButton = new TextButton("START", AssetLoader.getButtonStyle());
        startButton.setBounds(X_MENU_BUTTON_POSITION, Y_MENU_BUTTON, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuGroup.addAction(Actions.moveTo(0, -HEIGHT, 0.25f));
                schedule(new Task() {
                    @Override
                    public void run() {
                        if (getTotalScore() > 1) {
                            main.setScreen(new GameScreen(main));
                        } else {
                            main.setScreen(new TutorialScreen(main));
                        }
                    }
                }, 0.25f);
            }
        });
        menuButtonsGroup.addActor(startButton);

        Label galleryLabel = new Label("GALLERY", new Label.LabelStyle(getFont(WHITE)));
        galleryLabel.setBounds(X_MENU_BUTTON_POSITION, Y_MENU_BUTTON - 75, MENU_BUTTON_WIDTH, 75);
        galleryLabel.setAlignment(Align.center);
        galleryLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gallery gallery = new Gallery(menuGroup);
                addActor(gallery);
                menuGroup.addAction(Actions.moveTo(-WIDTH, 0, MENU_SWITCH_TIME));
            }
        });
        menuButtonsGroup.addActor(galleryLabel);

        Label settings = new Label("SETTINGS", new Label.LabelStyle(getFont(WHITE)));
        settings.setBounds(X_MENU_BUTTON_POSITION, Y_MENU_BUTTON - 190, MENU_BUTTON_WIDTH, 100);
        settings.setAlignment(Align.center);
        menuButtonsGroup.addActor(settings);
        return menuButtonsGroup;
    }
}
