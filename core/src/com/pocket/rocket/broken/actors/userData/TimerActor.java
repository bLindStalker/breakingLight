package com.pocket.rocket.broken.actors.userData;

import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.enums.LampLogic;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.Constants.ACTIVE_LAMP_INTERVAL;
import static com.pocket.rocket.broken.Constants.neutral_TIME_INTERVAL;
import static com.pocket.rocket.broken.Constants.HARD_CORE_TIME;
import static com.pocket.rocket.broken.Constants.MAX_ACTIVE_LAMPS;
import static com.pocket.rocket.broken.Constants.TURN_OFF_TIME_INTERVAL;


public class TimerActor extends LabelData {

    public LampLogic lampData = new LampLogic();
    private int currentTime = 1;

    public TimerActor(int xPosition, int yPosition, int width, int height) {
        super(String.valueOf(0), xPosition, yPosition, width, height, AssetLoader.getFont());
        startTimer();
    }

    private void startTimer() {
        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               setText(String.valueOf(currentTime));
                               updateDifficulty();
                               currentTime++;
                               startTimer();
                           }
                       }, 1
        );
    }

    private void updateDifficulty() {
        if (lampData.activeLamps < MAX_ACTIVE_LAMPS) {
            lampData.activeLamps += random(1, 2);
        }

        lampData.setActiveLampInterval(ACTIVE_LAMP_INTERVAL / HARD_CORE_TIME);
        lampData.setNeutralTimeInterval(neutral_TIME_INTERVAL / HARD_CORE_TIME);
        lampData.setTurnOffTimeInterval(TURN_OFF_TIME_INTERVAL / HARD_CORE_TIME);
    }

    public int getTime() {
        return currentTime;
    }
}
