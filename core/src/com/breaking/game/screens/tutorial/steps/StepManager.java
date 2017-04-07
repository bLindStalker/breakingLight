package com.breaking.game.screens.tutorial.steps;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.actors.StarBuilder;
import com.breaking.game.actors.userData.ScoreActor;
import com.breaking.game.enums.LightBulbStatus;
import com.breaking.game.screens.tutorial.TutorialLamp;

import java.util.ArrayList;
import java.util.LinkedList;

public class StepManager {
    private final ArrayList<TutorialLamp> lamps;
    private LinkedList<TutorialStep> steps = new LinkedList<TutorialStep>();

    public StepManager(Stage stage, ArrayList<TutorialLamp> lamps,
                       ScoreActor scoreActor, StarBuilder starBuilder, Group lampGroup) {
        this.lamps = lamps;
        steps.add(new BreakLampsStep(stage, lampGroup, lamps));
        steps.add(new StarStep(stage, scoreActor, lampGroup, starBuilder));
        steps.add(new FinalStep(stage, lampGroup));
    }

    public boolean runStep() {
        activateLamp();
        for (TutorialStep step : steps) {
            if (step.run()) {
                steps.remove(step);
            }
            return false;
        }

        return true;
    }

    private void activateLamp() {
        for (TutorialLamp lamp : lamps) {
            if (lamp.getCurrentStatus() == LightBulbStatus.TURN_ON) {
                return;
            }
        }

        manageLamps();
    }

    private void manageLamps() {
        for (final TutorialLamp lamp : lamps) {
            if (lamp.getCurrentStatus() == LightBulbStatus.BROKEN) {
                Timer.schedule(new Timer.Task() {
                                   @Override
                                   public void run() {
                                       lamp.addAction(Actions.alpha(0, 0.4f));
                                       Timer.schedule(new Timer.Task() {
                                                          @Override
                                                          public void run() {
                                                              lamp.setStatus(lamp.getPreviousStatus());
                                                              lamp.addAction(Actions.alpha(1, 0.4f));
                                                          }
                                                      }, 0.5f
                                       );
                                   }
                               }, 0.5f
                );
            } else {
                lamp.setStatus(lamp.getPreviousStatus());
            }
        }
    }
}
