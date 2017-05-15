package com.pocket.rocket.broken.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.BonusBuilder;
import com.pocket.rocket.broken.Constants;
import com.pocket.rocket.broken.actors.DialogBuilder;
import com.pocket.rocket.broken.actors.ProgressBar;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import static com.pocket.rocket.broken.screens.tutorial.steps.StepManager.DIALOG_SHOW_TIME;

public class BonusStep implements TutorialStep {
    private static final int NEED_TO_COLLECT = 6;
    private final Group stage;
    private final DialogBuilder info;
    private final ScoreActor scoreActor;
    private final BonusBuilder bonusBuilder;
    private final Group lampGroup;
    private final ProgressBar progressBar;
    private final Label tutorialLabel;
    private boolean firstRun = true;
    private int bonusCollected = 0;

    public BonusStep(Group stage, ScoreActor scoreActor, Group lampGroup, BonusBuilder bonusBuilder, Label tutorialLabel) {
        this.stage = stage;
        this.bonusBuilder = bonusBuilder;
        this.scoreActor = scoreActor;
        this.lampGroup = lampGroup;
        this.tutorialLabel = tutorialLabel;

        info = new DialogBuilder(50, Constants.HEIGHT / 2, 630, 150, "TRY TO CATCH BONUS!").build();
        info.addAction(Actions.alpha(0f, 0f));

        progressBar = new ProgressBar(290, NEED_TO_COLLECT);
        stage.addActor(progressBar);
        progressBar.addAction(Actions.alpha(0.2f, 0f));

    }

    @Override
    public boolean run() {
        if (progressBar.complete()) {
            progressBar.removeBar();
            tutorialLabel.remove();
            return true;
        }

        if (firstRun) {
            prepareUi();
            firstRun = false;
        }

        manageDifficulty();

        return false;
    }

    private void manageDifficulty() {
        if (scoreActor.getbonusCollected() != bonusCollected) {
            bonusCollected = scoreActor.getbonusCollected();
            progressBar.step();
        }

        if (scoreActor.getbonusCollected() == 0 && scoreActor.getbonusCollected() < 2) {
            bonusBuilder.setBonusVelocity(5.5f);
            bonusBuilder.setClickToCreate(0, 0);
        }

        if (scoreActor.getbonusCollected() == 2) {
            bonusBuilder.setBonusVelocity(4.5f);
            bonusBuilder.setClickToCreate(1, 2);
        }

        if (scoreActor.getbonusCollected() > 3) {
            bonusBuilder.setBonusVelocity(4f);
            bonusBuilder.setClickToCreate(1, 4);
        }

        if (scoreActor.getbonusCollected() > 5) {
            bonusBuilder.setClickToCreate(2, 5);
        }
    }

    private void prepareUi() {
        lampGroup.addAction(Actions.alpha(0.3f, 0f));
        stage.addActor(info);
        info.addAction(Actions.alpha(1f, 1f));
        tutorialLabel.setText("Try to catch bonus!");
        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               lampGroup.addAction(Actions.alpha(1f, 1f));
                               progressBar.addAction(Actions.alpha(1f, 1f));
                               info.removeDialog();
                           }
                       }, DIALOG_SHOW_TIME
        );
    }
}
