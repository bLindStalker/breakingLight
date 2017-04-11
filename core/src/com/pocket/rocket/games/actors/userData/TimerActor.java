package com.pocket.rocket.games.actors.userData;

import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.games.enums.LampLogicData;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.games.Constants.ACTIVE_LAMP_INTERVAL;
import static com.pocket.rocket.games.Constants.BROKEN_TIME_INTERVAL;
import static com.pocket.rocket.games.Constants.HARD_CORE_TIME;
import static com.pocket.rocket.games.Constants.MAX_ACTIVE_LAMPS;
import static com.pocket.rocket.games.Constants.TIME;
import static com.pocket.rocket.games.Constants.TURN_OFF_TIME_INTERVAL;


public class TimerActor extends LabelData {

    private static final float UPDATE_DIFFICULT_TIME = TIME - HARD_CORE_TIME;

    public LampLogicData lampData = new LampLogicData();
    private int currentTime = TIME;

    public TimerActor(int xPosition, int yPosition, int width, int height, LabelStyle style) {
        super(String.valueOf(TIME), xPosition, yPosition, width, height, style);
        startTimer();
    }

    private void startTimer() {
        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               setText(String.valueOf(currentTime));
                               updateDifficulty();
                               currentTime--;
                               startTimer();
                           }
                       }, 1
        );
    }

    private void updateDifficulty() {
        if (lampData.activeLamps < MAX_ACTIVE_LAMPS) {
            lampData.activeLamps += random(1, 2);
        }

        lampData.setActiveLampInterval(ACTIVE_LAMP_INTERVAL / UPDATE_DIFFICULT_TIME);
        lampData.setBrokenTimeInterval(BROKEN_TIME_INTERVAL / UPDATE_DIFFICULT_TIME);
        lampData.setTurnOffTimeInterval(TURN_OFF_TIME_INTERVAL / UPDATE_DIFFICULT_TIME);
    }

    public int getTime() {
        return currentTime;
    }
}
