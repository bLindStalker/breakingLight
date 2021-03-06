package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.badlogic.gdx.graphics.Color.FIREBRICK;
import static com.pocket.rocket.broken.AssetLoader.BUTTON_TEXT_COLOR;
import static com.pocket.rocket.broken.AssetLoader.getButtonStyle;
import static com.pocket.rocket.broken.AssetLoader.getCongratulationsLabel;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getGameOverLabel;
import static com.pocket.rocket.broken.AssetLoader.getResultBg;
import static com.pocket.rocket.broken.AssetLoader.getStar;
import static com.pocket.rocket.broken.Constants.BASIC_STAR_SCORE;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static java.lang.String.valueOf;

public class ResultScreen extends BaseScreen {

    private static final int RESULT_BG_WIDTH = 680;
    private static final int RESULT_BG_HEIGHT = 1070;
    private static final int GAME_OVER_WIDTH = 620;
    private static final int GAME_OVER_HEIGHT = 360;
    private boolean canBeClose = false;

    public ResultScreen(final Main main, int score, int starCollected, int time) {
        super(main);
        score += time * 3;
        score += starCollected * BASIC_STAR_SCORE;
        Preference.saveScore(score);
        addActor(buildResult(score, starCollected, time));

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                canBeClose = true;
            }
        }, 0.5f);
    }

    private Group buildResult(int score, int starCollected, int time) {
        final Group resultGroup = new Group();

        resultGroup.addActor(new ImageActor((WIDTH - RESULT_BG_WIDTH) / 2, (HEIGHT - RESULT_BG_HEIGHT) / 2, RESULT_BG_WIDTH, RESULT_BG_HEIGHT, getResultBg()));
        resultGroup.addActor(buildLabel(time));

        buildResultData(resultGroup, X_CENTER - 250, "Score", valueOf(score));
        buildResultData(resultGroup, X_CENTER + 100, "Time", valueOf(time));

        resultGroup.addActor(new ImageActor(X_CENTER - 250, 540, 85, 85, getStar()));

        Label scoreLabel = new Label(": " + starCollected, getFont(BUTTON_TEXT_COLOR));
        scoreLabel.setAlignment(Align.left);
        scoreLabel.setBounds(X_CENTER - 150, 560, 100, 50);
        scoreLabel.setScale(0.6f, 0.6f);
        resultGroup.addActor(scoreLabel);

        TextButton menuButton = new TextButton("MENU", getButtonStyle());
        menuButton.setBounds(30, 280, 300, 100);
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

        TextButton retryButton = new TextButton("RETRY", getButtonStyle());
        retryButton.setBounds(WIDTH - 330, 280, 300, 100);
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (canBeClose) {
                    resultGroup.addAction(Actions.alpha(0, 0.25f));
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            main.setScreen(new GameScreen(main));
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

    private Actor buildLabel(int time) {
        return time == 0
                ? new ImageActor(10, (HEIGHT) / 2 + 220, WIDTH - 20, 300, getCongratulationsLabel())
                : new ImageActor((WIDTH - GAME_OVER_WIDTH) / 2, (HEIGHT) / 2 + 140, GAME_OVER_WIDTH, GAME_OVER_HEIGHT, getGameOverLabel());
    }

    private void buildResultData(Group result, int xPosition, String header, String data) {
        Label scoreLabel = new Label(header, getFont(BUTTON_TEXT_COLOR));
        scoreLabel.setAlignment(Align.left);
        scoreLabel.setBounds(xPosition, 700, 100, 50);
        result.addActor(scoreLabel);

        Label scoreActor = new Label(data, getFont(FIREBRICK));
        scoreActor.setAlignment(Align.center);
        scoreActor.setBounds(xPosition, 650, 150, 50);
        result.addActor(scoreActor);
    }
}
