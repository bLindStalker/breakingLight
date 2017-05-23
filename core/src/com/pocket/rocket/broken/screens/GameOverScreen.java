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
import com.pocket.rocket.broken.actions.ScoreAction;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getGameOverLabel;
import static com.pocket.rocket.broken.AssetLoader.getGameOverText;
import static com.pocket.rocket.broken.Constants.BASIC_BONUS_SCORE;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Utils.buildLogo;
import static com.pocket.rocket.broken.actions.ScoreAction.scoreAction;
import static com.pocket.rocket.broken.enums.Text.BEST_SCORE;
import static com.pocket.rocket.broken.enums.Text.MENU;
import static com.pocket.rocket.broken.enums.Text.RETRY;
import static com.pocket.rocket.broken.enums.Text.SCORE;
import static com.pocket.rocket.broken.enums.Text.TIME;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_HEIGHT;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.X_MENU_BUTTON_POSITION;

public class GameOverScreen extends BaseScreen {

    private boolean canBeClose = false;

    public GameOverScreen(Main main, ScoreActor scoreActor, int time) {
        super(main);
        int score = scoreActor.getScore();
        int bonusCollected = scoreActor.getbonusCollected();
        int bonus2Collected = scoreActor.getbonus2Collected();

        score += time * 3;
        score += bonusCollected * BASIC_BONUS_SCORE;
        score += bonus2Collected * BASIC_BONUS_SCORE * 2;

        Preference.saveScore(score);
        main.getPlayServices().submitScore(score);
        main.getPlayServices().submitTotalScore(Preference.getTotalScore());
        addActor(buildResult(score, time));

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                canBeClose = true;
            }
        }, 0.5f);
    }

    private Group buildResult(int score, int time) {
        final Group resultGroup = new Group();

        resultGroup.addActor(buildLogo(getGameOverText(), getGameOverLabel()));

        buildResultData(resultGroup, TIME.get(), time, 700);
        buildResultData(resultGroup, SCORE.get(), score, 580);
        resultGroup.addActor(bestResultLabel());

        Label menuButton = new Label(MENU.get(), new Label.LabelStyle(getFont()));
        menuButton.setBounds(X_MENU_BUTTON_POSITION, 40, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
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

        TextButton retryButton = new TextButton(RETRY.get(), AssetLoader.getButtonStyle());
        retryButton.setBounds(X_MENU_BUTTON_POSITION, 170, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
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
        Label label = new Label(BEST_SCORE.get() + "\n" + Preference.getScore(), getFont());
        label.setAlignment(Align.center);
        label.setPosition(X_CENTER - label.getWidth() / 2, 420);
        return label;
    }

    private void buildResultData(Group result, String header, int data, int y) {
        Label headerLabel = new Label(header, getFont());
        headerLabel.setAlignment(Align.center);
        headerLabel.setPosition(X_CENTER - headerLabel.getWidth() / 2, y);
        headerLabel.setFontScale(0.8f);
        result.addActor(headerLabel);

        Label dataLabel = new Label(String.valueOf(data), getFont());
        dataLabel.setAlignment(Align.center);
        dataLabel.setPosition(X_CENTER - dataLabel.getWidth() / 2, y - 40);
        dataLabel.setFontScale(1.3f);
        dataLabel.addAction(Actions.action(ScoreAction.class));
        dataLabel.addAction(scoreAction(data, dataLabel));
        result.addActor(dataLabel);
    }
}
