package com.pocket.rocket.broken.actors.userData;

import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.enums.LampLogic;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.Constants.ACTIVE_INTERVAL;
import static com.pocket.rocket.broken.Constants.ANGRY_INTERVAL;
import static com.pocket.rocket.broken.Constants.HARD_CORE_TIME;
import static com.pocket.rocket.broken.Constants.MAX_ACTIVE_LAMPS;
import static com.pocket.rocket.broken.Constants.NEUTRAL_INTERVAL;


public class TimerActor extends LabelData {

    public LampLogic lampLogicData = new LampLogic();
    private int currentTime = 1;

    public TimerActor() {
        super("Time", 0);

        startTimer();
    }

    private void startTimer() {
        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               data.setText(String.valueOf(currentTime));
                               updateDifficulty();
                               currentTime++;
                               startTimer();
                           }
                       }, 1
        );
    }

    private void updateDifficulty() {
        if (lampLogicData.maxAngryLamps < MAX_ACTIVE_LAMPS) {
            lampLogicData.maxAngryLamps += random(1, 2);
        }

        lampLogicData.setActiveLampInterval(ANGRY_INTERVAL / HARD_CORE_TIME);
        lampLogicData.setNeutralTimeInterval(ACTIVE_INTERVAL / HARD_CORE_TIME);
        lampLogicData.setTurnOffTimeInterval(NEUTRAL_INTERVAL / HARD_CORE_TIME);
    }

    public int getTime() {
        return currentTime;
    }
}
