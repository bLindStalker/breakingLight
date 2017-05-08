package com.pocket.rocket.broken.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.Constants;
import com.pocket.rocket.broken.actors.BonusBuilder;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

public class BonusStep implements TutorialStep {
    private static final int NEED_TO_COLLECT = 8;
    private final Stage stage;
    private final com.pocket.rocket.broken.actors.DialogBuilder info;
    private final ScoreActor scoreActor;
    private final BonusBuilder bonusBuilder;
    private final Group lampGroup;
    private final com.pocket.rocket.broken.actors.ProgressBar progressBar;
    private boolean firstRun = true;
    private int bonusCollected = 0;

    public BonusStep(Stage stage, ScoreActor scoreActor, Group lampGroup, BonusBuilder bonusBuilder) {
        this.stage = stage;
        this.bonusBuilder = bonusBuilder;
        this.scoreActor = scoreActor;
        this.lampGroup = lampGroup;

        info = new com.pocket.rocket.broken.actors.DialogBuilder(50, Constants.HEIGHT / 2, 630, 150, "TRY TO CATCH bonus!").build();
        info.addAction(Actions.alpha(0f, 0f));

        progressBar = new com.pocket.rocket.broken.actors.ProgressBar(200, NEED_TO_COLLECT);
        progressBar.setVisible(false);
        progressBar.addAction(Actions.alpha(0f, 0f));
        stage.addActor(progressBar);

    }

    @Override
    public boolean run() {
        if (progressBar.complete()) {
            progressBar.removeBar();
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
            bonusBuilder.setBonusVelocity(5f);
            bonusBuilder.setClickToCreate(0, 0);
        }

        if (scoreActor.getbonusCollected() == 2) {
            bonusBuilder.setBonusVelocity(4f);
            bonusBuilder.setClickToCreate(1, 2);
        }

        if (scoreActor.getbonusCollected() > 3) {
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

        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               lampGroup.addAction(Actions.alpha(1f, 1f));
                               progressBar.setVisible(true);
                               progressBar.addAction(Actions.alpha(1f, 1f));
                               info.removeDialog();
                           }
                       }, 2f
        );
    }
}
