package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Constants;

public class ProgressBar extends Group {
    public static final int BAR_WIDTH = 600;
    private static final int BAR_HEIGHT = 55;
    private Queue<Actor> progress = new Queue<Actor>();

    public ProgressBar(int y, int steps) {
        int xPosition = Constants.WIDTH / 2 - BAR_WIDTH / 2;
        setBounds(xPosition, y, BAR_WIDTH, BAR_HEIGHT);
        addActor(new ImageActor(0, 0, BAR_WIDTH, BAR_HEIGHT, AssetLoader.getBar()));

        for (int i = 0; i < steps; i++) {
            int progressWidth = BAR_WIDTH / steps;
            ImageActor actor = new ImageActor(i * progressWidth, 0, progressWidth, BAR_HEIGHT, AssetLoader.getProgress());
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
