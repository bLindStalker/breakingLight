package com.pocket.rocket.games;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Timer.Task;
import com.pocket.rocket.games.actors.AnimatedActor;
import com.pocket.rocket.games.actors.StarBuilder;
import com.pocket.rocket.games.actors.userData.ScoreActor;

import java.util.concurrent.Callable;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.utils.Timer.schedule;

public class LightListener extends ClickListener {

    private Callable<Boolean> action;
    private Queue<AnimatedActor> heartActors;
    private ScoreActor scoreActor;
    private StarBuilder starBuilder;

    public LightListener(Callable<Boolean> action, Queue<AnimatedActor> heartActors, ScoreActor scoreActor, StarBuilder starBuilder) {
        this.action = action;
        this.heartActors = heartActors;
        this.scoreActor = scoreActor;
        this.starBuilder = starBuilder;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (doAction()) {
            starBuilder.buildStar((int) event.getListenerActor().getX(), (int) event.getListenerActor().getY(), scoreActor);
            scoreActor.increaseScore();
        } else {
            heartOperation();
        }
        super.clicked(event, x, y);
    }

    private void heartOperation() {
        if (heartActors.size != 0) {
            final AnimatedActor heart = heartActors.first();
            heart.animate();

            schedule(new Task() {
                               @Override
                               public void run() {
                                   heart.addAction(alpha(0f, .5f));
                                   schedule(new Task() {
                                                      @Override
                                                      public void run() {
                                                          heart.remove();
                                                      }
                                                  }, 0.5f
                                   );
                               }
                           }, 0.5f
            );
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
