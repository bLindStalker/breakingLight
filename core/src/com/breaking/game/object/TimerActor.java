package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.AssetLoader;
import com.breaking.game.enums.LampLogicData;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.breaking.game.Constants.ACTIVE_LAMP_INTERVAL;
import static com.breaking.game.Constants.BROKEN_TIME_INTERVAL;
import static com.breaking.game.Constants.HARD_CORE_TIME;
import static com.breaking.game.Constants.MAX_ACTIVE_LAMPS;
import static com.breaking.game.Constants.TIME;
import static com.breaking.game.Constants.TURN_OFF_TIME_INTERVAL;


public class TimerActor extends Label {

    private static final float UPDATE_DIFFICULT_TIME = TIME - HARD_CORE_TIME;
    private int time = TIME;
    public LampLogicData lampData = new LampLogicData();

    public TimerActor(int xPosition, int yPosition, int width, int height) {
        super(String.valueOf(TIME), AssetLoader.getFont());
        setBounds(xPosition, yPosition, width, height);
        setAlignment(Align.center);
        startTimer();
    }

    private void startTimer() {
        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               setText(String.valueOf(time));
                               updateDifficulty();
                               time--;
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
        return time;
    }
}
