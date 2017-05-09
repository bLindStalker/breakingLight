package com.pocket.rocket.broken.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Constants;
import com.pocket.rocket.broken.actors.BonusBuilder;
import com.pocket.rocket.broken.actors.LightBulb;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import java.util.ArrayList;
import java.util.LinkedList;

public class StepManager {
    public static final float DIALOG_SHOW_TIME = 2.5f;
    private LinkedList<TutorialStep> steps = new LinkedList<TutorialStep>();

    public StepManager(Group stage, ArrayList<LightBulb> lamps,
                       ScoreActor scoreActor, BonusBuilder bonusBuilder, Group lampGroup) {

        Label tutorialLabel = new Label("", new Label.LabelStyle(AssetLoader.getFont()));
        tutorialLabel.setBounds(0, Constants.HEIGHT - 275, Constants.WIDTH, 100);
        tutorialLabel.setAlignment(Align.center);
        stage.addActor(tutorialLabel);

        steps.add(new BreakLampsStep(stage, lampGroup, lamps, tutorialLabel));
        steps.add(new BonusStep(stage, scoreActor, lampGroup, bonusBuilder, tutorialLabel));
        steps.add(new FinalStep(stage, lampGroup));
    }

    public boolean runStep() {
        for (TutorialStep step : steps) {
            if (step.run()) {
                steps.remove(step);
            }
            return true;
        }

        return false;
    }
}
