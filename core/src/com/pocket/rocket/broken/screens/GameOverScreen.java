package com.pocket.rocket.broken.screens;

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
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.pocket.rocket.broken.AssetLoader.getBonus;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getGameOverLabel;
import static com.pocket.rocket.broken.AssetLoader.getGameOverText;
import static com.pocket.rocket.broken.Constants.BASIC_bonus_SCORE;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Utils.buildLogo;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_HEIGHT;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.X_MENU_BUTTON_POSITION;
import static java.lang.String.valueOf;

public class GameOverScreen extends BaseScreen {

    private boolean canBeClose = false;

    public GameOverScreen(final Main main, int score, int bonusCollected, int time) {
        super(main);
        score += time * 3;
        score += bonusCollected * BASIC_bonus_SCORE;
        Preference.saveScore(score);
        main.getPlayServices().submitScore(score);
        addActor(buildResult(score, bonusCollected, time));

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                canBeClose = true;
            }
        }, 0.5f);
    }

    private Group buildResult(int score, int bonusCollected, int time) {
        final Group resultGroup = new Group();

        resultGroup.addActor(buildLogo(getGameOverText(), getGameOverLabel()));
        resultGroup.addActor(bestResultLabel());

        buildResultData(resultGroup, X_CENTER - 250, "Time", valueOf(time));
        buildResultData(resultGroup, X_CENTER + 150, "Score", valueOf(score));

        resultGroup.addActor(new ImageActor(X_CENTER - 15, 575, 30, 60, getBonus()));
        Label dataLabel = new Label(valueOf(bonusCollected), getFont());
        dataLabel.setAlignment(Align.center);
        dataLabel.setBounds(X_CENTER - 50, 525, 100, 50);
        dataLabel.setFontScale(1.1f);
        resultGroup.addActor(dataLabel);

        Label menuButton = new Label("MENU", new Label.LabelStyle(getFont()));
        menuButton.setBounds(X_MENU_BUTTON_POSITION, 150, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        menuButton.setAlignment(Align.center);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (canBeClose) {
                    resultGroup.addAction(Actions.moveTo(0, -HEIGHT, 0.25f));
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            main.setScreen(new MenuScreen(main, false));
                        }
                    }, 0.25f);
                }
            }
        });
        resultGroup.addActor(menuButton);

        TextButton retryButton = new TextButton("RETRY", AssetLoader.getButtonStyle());
        retryButton.setBounds(X_MENU_BUTTON_POSITION, 300, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (canBeClose) {
                    resultGroup.addAction(Actions.alpha(0, 0.25f));
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            main.setScreen(new MainGameScreen(main));
                        }
                    }, 0.25f);
                }
            }
        });
        resultGroup.addActor(retryButton);

        resultGroup.addAction(Actions.alpha(0, 0));
        resultGroup.addAction(Actions.alpha(1, 0.25f));

        return resultGroup;
    }

    private Label bestResultLabel() {
        Label label = new Label("BEST RESULT: " + Preference.getScore(), getFont());
        label.setAlignment(Align.center);
        label.setBounds(X_CENTER - 200, 680, 400, 50);
        label.setFontScale(1.1f);
        return label;
    }

    private void buildResultData(Group result, int xPosition, String header, String data) {
        Label headerLabel = new Label(header, getFont());
        headerLabel.setAlignment(Align.center);
        headerLabel.setBounds(xPosition, 575, 100, 50);
        headerLabel.setFontScale(0.8f);
        result.addActor(headerLabel);

        Label dataLabel = new Label(data, getFont());
        dataLabel.setAlignment(Align.center);
        dataLabel.setBounds(xPosition, 575 - 50, 100, 50);
        dataLabel.setFontScale(1.1f);
        result.addActor(dataLabel);
    }
}
