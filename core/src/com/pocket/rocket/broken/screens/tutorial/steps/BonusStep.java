package com.pocket.rocket.broken.screens.tutorial.steps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.BonusBuilder;
import com.pocket.rocket.broken.Constants;
import com.pocket.rocket.broken.actors.ProgressBar;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import static com.pocket.rocket.broken.enums.Text.CATCH_BONUS;

public class BonusStep extends Group implements TutorialStep {
    private static final int NEED_TO_COLLECT = 6;
    private final Group stage;
    private final ScoreActor scoreActor;
    private final BonusBuilder bonusBuilder;
    private final Group lampGroup;
    private final ProgressBar progressBar;
    private boolean showUI = true;
    private int bonusCollected = 0;
    private boolean complete = false;

    public BonusStep(Group stage, ScoreActor scoreActor, Group lampGroup, BonusBuilder bonusBuilder) {
        this.stage = stage;
        this.bonusBuilder = bonusBuilder;
        this.scoreActor = scoreActor;
        this.lampGroup = lampGroup;

        Label tutorialLabel = new Label(CATCH_BONUS.get(), new Label.LabelStyle(AssetLoader.getFont(Color.GOLD)));
        tutorialLabel.setBounds(0, Constants.HEIGHT - 275, Constants.WIDTH, 100);
        tutorialLabel.setAlignment(Align.center);
        addActor(tutorialLabel);

        progressBar = new ProgressBar(290, NEED_TO_COLLECT);
        addActor(progressBar);

        addAction(Actions.alpha(0));
    }

    @Override
    public boolean run() {
        if (progressBar.complete()) {
            this.addAction(Actions.fadeOut(.5f));
            lampGroup.addAction(Actions.fadeOut(.5f));
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    lampGroup.setVisible(false);
                    complete = true;
                }
            }, .5f);
        }

        if (showUI) {
            stage.addActor(this);
            this.addAction(Actions.alpha(1f, 0.7f));
            lampGroup.setVisible(true);
            lampGroup.addAction(Actions.alpha(1f, 0.7f));
            showUI = false;
        }

        manageDifficulty();

        return complete;
    }

    @Override
    public void disappear() {
        remove();
    }

    private void manageDifficulty() {
        if (scoreActor.getBonusCollected() != bonusCollected) {
            bonusCollected = scoreActor.getBonusCollected();
            progressBar.step();
        }

        if (scoreActor.getBonusCollected() == 0 && scoreActor.getBonusCollected() < 2) {
            bonusBuilder.setBonusVelocity(5.5f);
            bonusBuilder.setClickToCreate(0, 0);
        }

        if (scoreActor.getBonusCollected() == 2) {
            bonusBuilder.setBonusVelocity(4.5f);
            bonusBuilder.setClickToCreate(0, 2);
        }

        if (scoreActor.getBonusCollected() == 3) {
            bonusBuilder.setClickToCreate(0, 3);
        }

        if (scoreActor.getBonusCollected() == 4) {
            bonusBuilder.setClickToCreate(0, 3);
        }

        if (scoreActor.getBonusCollected() > 5) {
            bonusBuilder.setClickToCreate(1, 2);
        }
    }
}
