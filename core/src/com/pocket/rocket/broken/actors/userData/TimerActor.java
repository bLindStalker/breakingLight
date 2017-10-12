package com.pocket.rocket.broken.actors.userData;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.LampLogic;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.pocket.rocket.broken.Constants.ACTIVE_INTERVAL;
import static com.pocket.rocket.broken.Constants.ANGRY_INTERVAL;
import static com.pocket.rocket.broken.Constants.FREEZE_POWER;
import static com.pocket.rocket.broken.Constants.FREEZE_TIME;
import static com.pocket.rocket.broken.Constants.HARD_CORE_TIME;
import static com.pocket.rocket.broken.Constants.MAX_ACTIVE_LAMPS;
import static com.pocket.rocket.broken.Constants.NEUTRAL_INTERVAL;
import static com.pocket.rocket.broken.enums.Text.TIME;


public class TimerActor extends LabelData {

    public LampLogic lampLogicData = new LampLogic();
    public boolean freeze = false;
    private int currentTime = 1;

    public TimerActor() {
        super(TIME.get(), 0);

        startTimer();
    }

    private void startTimer() {
        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               data.setText(String.valueOf(currentTime));
                               updateDifficulty(currentTime);
                               currentTime++;
                               startTimer();
                           }
                       }, freeze ? (float) (1 * FREEZE_POWER) : 1
        );
    }

    private void updateDifficulty(int currentTime) {
        if (lampLogicData.maxAngryLamps < MAX_ACTIVE_LAMPS) {
            lampLogicData.maxAngryLamps += random(1, 2);
        }

        if (freeze) {
            lampLogicData.setFreeze();
        } else {
            lampLogicData.setActiveLampInterval(ANGRY_INTERVAL / HARD_CORE_TIME);
        }

        lampLogicData.setNeutralTimeInterval(ACTIVE_INTERVAL / HARD_CORE_TIME);
        lampLogicData.setTurnOffTimeInterval(NEUTRAL_INTERVAL / HARD_CORE_TIME);

        lampLogicData.difficultUpdate(currentTime);
    }

    public int getTime() {
        return currentTime;
    }

    public void setFreeze() {
        this.freeze = true;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                freeze = false;
            }
        }, FREEZE_TIME);
    }
}
