package com.pocket.rocket.broken.actors.userData;

import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.Constants;

import static com.badlogic.gdx.math.MathUtils.random;


public class TimerActor extends LabelData {

    private static final float UPDATE_DIFFICULT_TIME = Constants.TIME - Constants.HARD_CORE_TIME;

    public com.pocket.rocket.broken.enums.LampLogicData lampData = new com.pocket.rocket.broken.enums.LampLogicData();
    private int currentTime = Constants.TIME;

    public TimerActor(int xPosition, int yPosition, int width, int height, LabelStyle style) {
        super(String.valueOf(Constants.TIME), xPosition, yPosition, width, height, style);
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
        if (lampData.activeLamps < Constants.MAX_ACTIVE_LAMPS) {
            lampData.activeLamps += random(1, 2);
        }

        lampData.setActiveLampInterval(Constants.ACTIVE_LAMP_INTERVAL / UPDATE_DIFFICULT_TIME);
        lampData.setBrokenTimeInterval(Constants.BROKEN_TIME_INTERVAL / UPDATE_DIFFICULT_TIME);
        lampData.setTurnOffTimeInterval(Constants.TURN_OFF_TIME_INTERVAL / UPDATE_DIFFICULT_TIME);
    }

    public int getTime() {
        return currentTime;
    }
}
