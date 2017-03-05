package com.breaking.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import java.util.concurrent.Callable;

import static com.breaking.game.Constants.BASIC_SCORE;

public class LightListener extends ClickListener {

    private Callable<Boolean> action;
    private int score;
    private Array<Actor> lifeActors;

    public LightListener(Callable<Boolean> action, int score, Array<Actor> lifeActors) {
        this.action = action;
        this.score = score;
        this.lifeActors = lifeActors;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (doAction()) {
            score += BASIC_SCORE;
        } else {
            Actor actor = lifeActors.first();
            actor.remove();
            lifeActors.removeValue(actor, false);
        }
        System.out.println("SCORE: " + score);
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
