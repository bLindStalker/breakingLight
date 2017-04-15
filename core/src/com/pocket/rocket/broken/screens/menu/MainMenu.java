package com.pocket.rocket.broken.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.screens.GameScreen;
import com.pocket.rocket.broken.screens.tutorial.TutorialScreen;

import static com.badlogic.gdx.graphics.Color.FIREBRICK;
import static com.badlogic.gdx.utils.Timer.Task;
import static com.badlogic.gdx.utils.Timer.schedule;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Preference.getScore;
import static com.pocket.rocket.broken.Preference.getTotalScore;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_SWITCH_TIME;
import static com.pocket.rocket.broken.screens.MenuScreen.X_MENU_BUTTON_POSITION;
import static com.pocket.rocket.broken.screens.MenuScreen.Y_MENU_BUTTON;
import static java.lang.String.valueOf;

public class MainMenu extends Group {
    private static final int MENU_BUTTON_HEIGHT = 140;
    private static final int MENU_BUTTON_WHITE_SPACE = 40;
    private static final int LOGO_WIDTH = 700;

    private final com.pocket.rocket.broken.Main main;
    private final Group menuGroup;

    public MainMenu(final com.pocket.rocket.broken.Main main, final Group menuGroup) {
        addActor(new ImageActor((WIDTH - LOGO_WIDTH) / 2, HEIGHT - 385 - 130, LOGO_WIDTH, 385, AssetLoader.getLabel()));
        this.main = main;
        this.menuGroup = menuGroup;

        addActor(buildScoreLabel());
        addActor(buildButtons());
    }

    private Group buildScoreLabel() {
        Group labelGroup = new Group();

        int score = getScore();
        labelGroup.addActor(buildScoreLabel(AssetLoader.getFont(AssetLoader.BUTTON_TEXT_COLOR), "Max: ", 780, X_CENTER - 300));
        labelGroup.addActor(buildScoreLabel(AssetLoader.getFont(FIREBRICK), valueOf(score), 780, X_CENTER - 190));

        int totalScore = getTotalScore();
        labelGroup.addActor(buildScoreLabel(AssetLoader.getFont(AssetLoader.BUTTON_TEXT_COLOR), "Total: ", 720, X_CENTER - 300));
        labelGroup.addActor(buildScoreLabel(AssetLoader.getFont(FIREBRICK), valueOf(totalScore), 720, X_CENTER - 175));

        labelGroup.setVisible(score > 0 && totalScore > 0);
        return labelGroup;
    }

    private Group buildButtons() {
        Group menuButtonsGroup = new Group();

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

        TextButton advancedButton = new TextButton("ADVANCED", AssetLoader.getButtonStyle());
        advancedButton.setBounds(X_MENU_BUTTON_POSITION, Y_MENU_BUTTON - MENU_BUTTON_HEIGHT - MENU_BUTTON_WHITE_SPACE, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        advancedButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuGroup.addAction(Actions.moveTo(-WIDTH, 0, MENU_SWITCH_TIME));
            }
        });
        menuButtonsGroup.addActor(advancedButton);

        TextButton exitButton = new TextButton("EXIT", AssetLoader.getButtonStyle());
        exitButton.setBounds(X_MENU_BUTTON_POSITION, Y_MENU_BUTTON - (2 * MENU_BUTTON_HEIGHT) - (2 * MENU_BUTTON_WHITE_SPACE), MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        menuButtonsGroup.addActor(exitButton);
        return menuButtonsGroup;
    }

    private Label buildScoreLabel(Label.LabelStyle font, String text, int y, int x) {
        Label label = new Label(text, font);

        label.setAlignment(Align.left);
        label.setBounds(x, y, 100, 50);
        label.setFontScale(0.7f, 0.6f);

        return label;
    }
}
