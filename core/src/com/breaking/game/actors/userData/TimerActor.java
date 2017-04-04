package com.breaking.game.actors.userData;

import com.badlogic.gdx.utils.Timer;
import com.breaking.game.enums.LampLogicData;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.breaking.game.Constants.ACTIVE_LAMP_INTERVAL;
import static com.breaking.game.Constants.BROKEN_TIME_INTERVAL;
import static com.breaking.game.Constants.HARD_CORE_TIME;
import static com.breaking.game.Constants.MAX_ACTIVE_LAMPS;
import static com.breaking.game.Constants.TIME;
import static com.breaking.game.Constants.TURN_OFF_TIME_INTERVAL;


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
