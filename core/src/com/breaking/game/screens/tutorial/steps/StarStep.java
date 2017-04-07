package com.breaking.game.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.actors.DialogBuilder;
import com.breaking.game.actors.ProgressBar;
import com.breaking.game.actors.StarBuilder;
import com.breaking.game.actors.userData.ScoreActor;

import static com.breaking.game.Constants.HEIGHT;

public class StarStep implements TutorialStep {
    private static final int STEPS = 6;
    private final Stage stage;
    private final DialogBuilder info;
    private final ScoreActor scoreActor;
    private final StarBuilder starBuilder;
    private final Group lampGroup;
    private final ProgressBar progressBar;
    private Queue<Actor> progress = new Queue<Actor>();
    private boolean firstRun = true;
    private int starCollected = 0;

    public StarStep(Stage stage, ScoreActor scoreActor, Group lampGroup, StarBuilder starBuilder) {
        this.stage = stage;
        this.starBuilder = starBuilder;
        this.scoreActor = scoreActor;
        this.lampGroup = lampGroup;

        info = new DialogBuilder(50, HEIGHT / 2, 630, 150, "TRY TO CATCH STAR!").build();
        info.addAction(Actions.alpha(0f, 0f));

        progressBar = new ProgressBar(200, STEPS);
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
