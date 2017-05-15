package com.pocket.rocket.broken;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.actors.userData.HeartData;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import java.util.concurrent.Callable;

import static com.pocket.rocket.broken.Constants.MISSING_WORDS;
import static com.pocket.rocket.broken.Constants.WORDS;

public class GameLampListener extends ClickListener {

    private Callable<Boolean> action;
    private HeartData heartData;
    private ScoreActor scoreActor;
    private BonusBuilder bonusBuilder;

    public GameLampListener(Callable<Boolean> action, HeartData heartData, ScoreActor scoreActor, BonusBuilder bonusBuilder) {
        this.action = action;
        this.heartData = heartData;
        this.scoreActor = scoreActor;
        this.bonusBuilder = bonusBuilder;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (doAction()) {
            bonusBuilder.buildBonus((int) event.getListenerActor().getX(), (int) event.getListenerActor().getY(), scoreActor);
            scoreActor.increaseScore();
            scoreActor.increaseLamps();
            if (scoreActor.collectedLamps() == 10) {
                scoreActor.resetLamps();
                showWord(getWord(WORDS), (int) event.getListenerActor().getX(), (int) event.getListenerActor().getY());
            }
        } else {
            heartOperation();
            showWord(getWord(MISSING_WORDS), (int) event.getListenerActor().getX(), (int) event.getListenerActor().getY());
        }
        super.clicked(event, x, y);
    }

    private String getWord(String[] worlds) {
        return worlds[MathUtils.random(0, worlds.length - 1)];
    }

    private void showWord(String text, float x, float y) {
        final Label label = new Label(text, new Label.LabelStyle(AssetLoader.getFont()));
        label.setPosition(x + Constants.LAMP_WIDTH / 2 - 70, y + Constants.LAMP_HEIGHT + 50);
        label.addAction(Actions.alpha(0));
        label.addAction(Actions.alpha(0.7f, 0.4f));
        label.addAction(Actions.moveBy(0, 170, 1.7f));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                label.addAction(Actions.fadeOut(0.4f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        label.remove();
                    }
                }, 0.5f);
            }
        }, 1.2f);
        bonusBuilder.getStage().addActor(label);
    }

    private void heartOperation() {
        if (heartData.getActiveHearts() != 0) {
            heartData.removeHeart();
        }
    }

    private boolean doAction() {
        try {
            return action.call();
        } catch (Exception e) {
            return false;
        }
    }
}
