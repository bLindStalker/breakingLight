package com.breaking.game.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.actors.DialogBuilder;
import com.breaking.game.actors.ProgressBar;
import com.breaking.game.screens.tutorial.TutorialLamp;

import java.util.ArrayList;

import static com.breaking.game.Constants.HEIGHT;

public class BreakLampsStep implements TutorialStep {
    private static final int STEPS = 9;
    private final DialogBuilder info;
    private final Stage stage;
    private final Group lampGroup;
    private final ProgressBar progressBar;
    private boolean showInfoDialog = true;

    public BreakLampsStep(Stage stage, Group lampGroup, ArrayList<TutorialLamp> lamps) {
        this.stage = stage;
        this.lampGroup = lampGroup;
        info = new DialogBuilder(50, HEIGHT / 2, 630, 150, "BREAK ACTIVE LAMPS!").build();

        lampGroup.addAction(Actions.alpha(0.3f, 0f));
        progressBar = new ProgressBar(200, STEPS);
        progressBar.addAction(Actions.alpha(0f, 0f));

        for (TutorialLamp lamp : lamps) {
            lamp.addClickAction(new Runnable() {
                @Override
                public void run() {
                    progressBar.step();
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
            }, 2f);

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
