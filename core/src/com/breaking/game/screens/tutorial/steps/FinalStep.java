package com.breaking.game.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.Constants;
import com.breaking.game.actors.DialogBuilder;

public class FinalStep implements TutorialStep {
    private final Stage stage;
    private final DialogBuilder info;
    private final Group lampGroup;

    private boolean showInfoDialog = true;
    private boolean complete = false;

    public FinalStep(Stage stage, Group lampGroup) {
        this.stage = stage;
        this.lampGroup = lampGroup;

        info = new DialogBuilder(50, Constants.HEIGHT / 2, 630, 150, "GOOD JOB!").build();
    }

    @Override
    public boolean run() {
        if (showInfoDialog) {
            lampGroup.addAction(Actions.alpha(0.3f, 0f));
            stage.addActor(info);
            info.addAction(Actions.alpha(0f, 0f));
            info.addAction(Actions.alpha(1f, 1f));

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    info.removeDialog();
                }
            }, 2f);
            showInfoDialog = false;

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    complete = true;
                }
            }, 2.2f);

        } else {
            if (complete) {
                return true;
            }
        }

        return false;
    }
}
