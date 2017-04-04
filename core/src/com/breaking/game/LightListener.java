package com.breaking.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.breaking.game.actors.StarBuilder;
import com.breaking.game.actors.userData.ScoreActor;

import java.util.concurrent.Callable;

public class LightListener extends ClickListener {

    private Callable<Boolean> action;
    private Array<Actor> lifeActors;
    private ScoreActor scoreActor;
    private StarBuilder starBuilder;

    public LightListener(Callable<Boolean> action, Array<Actor> lifeActors, ScoreActor scoreActor, StarBuilder starBuilder) {
        this.action = action;
        this.lifeActors = lifeActors;
        this.scoreActor = scoreActor;
        this.starBuilder = starBuilder;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (doAction()) {
            starBuilder.buildStar((int) event.getListenerActor().getX(), (int) event.getListenerActor().getY(), scoreActor);
            scoreActor.increaseScore();
        } else {
            Actor actor = lifeActors.first();
            actor.remove();
            lifeActors.removeValue(actor, false);
        }
        super.clicked(event, x, y);
    }

    private boolean doAction() {
        try {
            return action.call();
        } catch (Exception e) {
            return false;
        }
    }
}
