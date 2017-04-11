package com.pocket.rocket.games.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.games.actors.DialogBuilder;
import com.pocket.rocket.games.actors.ProgressBar;
import com.pocket.rocket.games.actors.StarBuilder;
import com.pocket.rocket.games.actors.userData.ScoreActor;

import static com.pocket.rocket.games.Constants.HEIGHT;

public class StarStep implements TutorialStep {
    private static final int NEED_TO_COLLECT = 8;
    private final Stage stage;
    private final DialogBuilder info;
    private final ScoreActor scoreActor;
    private final StarBuilder starBuilder;
    private final Group lampGroup;
    private final ProgressBar progressBar;
    private boolean firstRun = true;
    private int starCollected = 0;

    public StarStep(Stage stage, ScoreActor scoreActor, Group lampGroup, StarBuilder starBuilder) {
        this.stage = stage;
        this.starBuilder = starBuilder;
        this.scoreActor = scoreActor;
        this.lampGroup = lampGroup;

        info = new DialogBuilder(50, HEIGHT / 2, 630, 150, "TRY TO CATCH STAR!").build();
        info.addAction(Actions.alpha(0f, 0f));

        progressBar = new ProgressBar(200, NEED_TO_COLLECT);
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
        if (scoreActor.getStarCollected() != starCollected) {
            starCollected = scoreActor.getStarCollected();
            progressBar.step();
        }

        if (scoreActor.getStarCollected() == 0 && scoreActor.getStarCollected() < 2) {
            starBuilder.setStarVelocity(5f);
            starBuilder.setClickToCreate(0, 0);
        }

        if (scoreActor.getStarCollected() == 2) {
            starBuilder.setStarVelocity(4f);
            starBuilder.setClickToCreate(1, 2);
        }

        if (scoreActor.getStarCollected() > 3) {
            starBuilder.setClickToCreate(1, 4);
        }

        if (scoreActor.getStarCollected() > 5) {
            starBuilder.setClickToCreate(2, 5);
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
