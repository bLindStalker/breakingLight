package com.pocket.rocket.broken;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pocket.rocket.broken.actors.userData.HeartData;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import java.util.concurrent.Callable;

public class LightListener extends ClickListener {

    private Callable<Boolean> action;
    private HeartData heartData;
    private ScoreActor scoreActor;
    private BonusBuilder bonusBuilder;

    public LightListener(Callable<Boolean> action, HeartData heartData, ScoreActor scoreActor, BonusBuilder bonusBuilder) {
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
        } else {
            heartOperation();
        }
        super.clicked(event, x, y);
    }

    private void heartOperation() {
        if (heartData.getActiveHearts() != 0) {
            heartData.removeHeart();
            // heartActors.removeValue(heart, false);
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
