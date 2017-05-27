package com.pocket.rocket.broken.screens.tutorial.steps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Constants;
import com.pocket.rocket.broken.actors.LightBulb;
import com.pocket.rocket.broken.actors.ProgressBar;
import com.pocket.rocket.broken.enums.LightBulbStatus;

import java.util.ArrayList;

import static com.pocket.rocket.broken.enums.Text.TAP_RED;

public class BreakLampsStep extends Group implements TutorialStep {
    private static final int NEED_TO_COLLECT = 10;

    private final Group stage;
    private final Group lampGroup;
    private final ProgressBar progressBar;
    private boolean showUI = true;
    private boolean complete = false;

    public BreakLampsStep(Group stage, Group lampGroup, ArrayList<LightBulb> lamps) {
        this.stage = stage;
        this.lampGroup = lampGroup;

        Label tutorialLabel = new Label(TAP_RED.get(), new Label.LabelStyle(AssetLoader.getFont(Color.GOLD)));
        tutorialLabel.setBounds(0, Constants.HEIGHT - 275, Constants.WIDTH, 100);
        tutorialLabel.setAlignment(Align.center);
        addActor(tutorialLabel);

        progressBar = new ProgressBar(290, NEED_TO_COLLECT);
        addActor(progressBar);

        for (final LightBulb lamp : lamps) {
            lamp.addClickAction(new Runnable() {
                @Override
                public void run() {
                    if (lamp.getStatus() == LightBulbStatus.ANGRY) {
                        progressBar.step();
                    }
                }
            });
        }
        addAction(Actions.alpha(0f));
    }

    @Override
    public boolean run() {
        if (showUI) {
            stage.addActor(this);
            this.addAction(Actions.alpha(1f, 0.7f));
            lampGroup.setVisible(true);
            lampGroup.addAction(Actions.alpha(1f, 0.7f));
            showUI = false;
        }

        if (progressBar.complete()) {
            this.addAction(Actions.fadeOut(.3f));
            lampGroup.addAction(Actions.fadeOut(.3f));
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    lampGroup.setVisible(false);
                    complete = true;
                }
            }, .3f);
        }

        return complete;
    }

    @Override
    public void disappear() {
        remove();
    }
}
