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
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import static com.pocket.rocket.broken.AssetLoader.getBonus;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getGameOverLabel;
import static com.pocket.rocket.broken.AssetLoader.getGameOverText;
import static com.pocket.rocket.broken.Constants.BASIC_BONUS_SCORE;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Utils.buildLogo;
import static com.pocket.rocket.broken.actions.ScoreAction.scoreAction;
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
        addActor(buildResult(score, bonusCollected, bonus2Collected, time));

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                canBeClose = true;
            }
        }, 0.5f);
    }

    private Group buildResult(int score, int bonusCollected, int bonus2Collected, int time) {
        final Group resultGroup = new Group();

        resultGroup.addActor(buildLogo(getGameOverText(), getGameOverLabel()));
        resultGroup.addActor(bestResultLabel());

        buildResultData(resultGroup, X_CENTER - 250, "Time", time);
        buildResultData(resultGroup, X_CENTER + 150, "Score", score);

        Group bonus = buildBonuses(bonusCollected, false);
        if (Preference.doubleBonus()) {
            bonus.setPosition(bonus.getX() - 40, bonus.getY());
            Group doubleBonus = buildBonuses(bonus2Collected, true);
            doubleBonus.setPosition(doubleBonus.getX() + 40, doubleBonus.getY());
            resultGroup.addActor(doubleBonus);
        }

        resultGroup.addActor(bonus);

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

    private Group buildBonuses(int bonusCollected, boolean doubleBonus) {
        Group bonusGroup = new Group();
        bonusGroup.setBounds(X_CENTER - 30, 525, 60, 110);
        bonusGroup.addActor(new ImageActor(15, 50, 30, 60, getBonus(doubleBonus)));

        final Label bonusLabel = new Label("", getFont());
        bonusLabel.setAlignment(Align.center);
        bonusLabel.setBounds(0, 0, 60, 50);
        bonusLabel.setFontScale(1.1f);
        bonusLabel.addAction(scoreAction(bonusCollected, bonusLabel));
        bonusGroup.addActor(bonusLabel);

        return bonusGroup;
    }

    private Label bestResultLabel() {
        Label label = new Label("BEST SCORE: " + Preference.getScore(), getFont());
        label.setAlignment(Align.center);
        label.setBounds(X_CENTER - 200, 680, 400, 50);
        label.setFontScale(1.1f);
        return label;
    }

    private void buildResultData(Group result, int xPosition, String header, int data) {
        Label headerLabel = new Label(header, getFont());
        headerLabel.setAlignment(Align.center);
        headerLabel.setBounds(xPosition, 575, 100, 50);
        headerLabel.setFontScale(0.8f);
        result.addActor(headerLabel);

        Label dataLabel = new Label("", getFont());
        dataLabel.setAlignment(Align.center);
        dataLabel.setBounds(xPosition, 575 - 50, 100, 50);
        dataLabel.setFontScale(1.1f);
        dataLabel.addAction(Actions.action(ScoreAction.class));
        dataLabel.addAction(scoreAction(data, dataLabel));
        result.addActor(dataLabel);
    }
}
