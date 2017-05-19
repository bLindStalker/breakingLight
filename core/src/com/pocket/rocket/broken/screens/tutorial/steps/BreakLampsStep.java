package com.pocket.rocket.broken.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.actors.DialogBuilder;
import com.pocket.rocket.broken.actors.LightBulb;
import com.pocket.rocket.broken.actors.ProgressBar;
import com.pocket.rocket.broken.enums.LightBulbStatus;

import java.util.ArrayList;

import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.enums.Text.TAP_ON_ANGRY;
import static com.pocket.rocket.broken.screens.tutorial.steps.StepManager.DIALOG_SHOW_TIME;

public class BreakLampsStep implements TutorialStep {
    private static final int NEED_TO_COLLECT = 10;

    private final DialogBuilder info;
    private final Group stage;
    private final Group lampGroup;
    private final ProgressBar progressBar;
    private boolean showInfoDialog = true;

    public BreakLampsStep(Group stage, Group lampGroup, ArrayList<LightBulb> lamps, Label tutorialLabel) {
        this.stage = stage;
        this.lampGroup = lampGroup;
        tutorialLabel.setText(TAP_ON_ANGRY.get().toLowerCase());
        info = new DialogBuilder(50, HEIGHT / 2, 630, 150, TAP_ON_ANGRY.get()).build();

        progressBar = new ProgressBar(290, NEED_TO_COLLECT);
        progressBar.addAction(Actions.alpha(0.2f, 0f));
        lampGroup.addAction(Actions.alpha(0.2f, 0f));

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
    }

    @Override
    public boolean run() {
        if (showInfoDialog) {
            stage.addActor(info);
            info.addAction(Actions.alpha(0f, 0f));
            info.addAction(Actions.alpha(1f, 1f));
            stage.addActor(progressBar);

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    lampGroup.addAction(Actions.alpha(1f, 1f));
                    progressBar.addAction(Actions.alpha(1f, 1f));
                    info.removeDialog();
                }
            }, DIALOG_SHOW_TIME);

            showInfoDialog = false;
        } else {
            if (progressBar.complete()) {
                progressBar.removeBar();
                return true;
            }
        }

        return false;
    }
}
