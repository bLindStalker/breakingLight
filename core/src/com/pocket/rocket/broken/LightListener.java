package com.pocket.rocket.broken;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Queue;
import com.pocket.rocket.broken.actors.BonusBuilder;
import com.pocket.rocket.broken.actors.HeartActor;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import java.util.concurrent.Callable;

public class LightListener extends ClickListener {

    private Callable<Boolean> action;
    private Queue<HeartActor> heartActors;
    private ScoreActor scoreActor;
    private BonusBuilder bonusBuilder;

    public LightListener(Callable<Boolean> action, Queue<HeartActor> heartActors, ScoreActor scoreActor, BonusBuilder bonusBuilder) {
        this.action = action;
        this.heartActors = heartActors;
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
        if (heartActors.size != 0) {
            final HeartActor heart = heartActors.first();
            heart.remove();
            heartActors.removeValue(heart, false);
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
