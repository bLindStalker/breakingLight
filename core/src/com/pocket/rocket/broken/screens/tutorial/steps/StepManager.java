package com.pocket.rocket.broken.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pocket.rocket.broken.BonusBuilder;
import com.pocket.rocket.broken.actors.LightBulb;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import java.util.ArrayList;
import java.util.LinkedList;

public class StepManager {
    private LinkedList<TutorialStep> steps = new LinkedList<TutorialStep>();

    public StepManager(Group stage, ArrayList<LightBulb> lamps,
                       ScoreActor scoreActor, BonusBuilder bonusBuilder, Group lampGroup) {

        lampGroup.setVisible(false);
        lampGroup.addAction(Actions.alpha(0f));

        steps.add(new FirstStep(stage));
        steps.add(new BreakLampsStep(stage, lampGroup, lamps));
        steps.add(new SecondStep(stage));
        steps.add(new BonusStep(stage, scoreActor, lampGroup, bonusBuilder));
        steps.add(new FinalStep(stage));
    }

    public boolean runStep() {
        for (TutorialStep step : steps) {
            if (step.run()) {
                step.disappear();
                steps.remove(step);
            }
            return true;
        }

        return false;
    }
}
