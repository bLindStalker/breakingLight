package com.breaking.game.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.AssetLoader;
import com.breaking.game.Constants;

public class ProgressBar extends Group {
    private Queue<Actor> progress = new Queue<Actor>();

    public ProgressBar(int y, int steps) {
        int xPosition = Constants.WIDTH / 2 - 300;
        setPosition(xPosition, y);
        setHeight(100);
        setWidth(600);
        addActor(new ImageActor(0, 0, 600, 100, AssetLoader.getBar()));

        for (int i = 0; i < steps; i++) {
            int progressWidth = (600 - 60) / steps;
            ImageActor actor = new ImageActor(30 + (i * progressWidth), 20, progressWidth, 60, AssetLoader.getProgress());
            actor.setVisible(false);
            actor.addAction(Actions.alpha(0f, 0f));
            progress.addLast(actor);
            addActor(actor);
        }
    }

    public void step() {
        if (progress.size == 0) {
            return;
        }

        Actor actor = progress.first();
        actor.setVisible(true);
        actor.addAction(Actions.alpha(1f, 0.3f));
        progress.removeFirst();
    }

    public boolean complete() {
        return progress.size == 0;
    }

    public void removeBar() {
        addAction(Actions.alpha(0f, 0.35f));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                remove();
            }
        }, 0.35f);
    }
}
