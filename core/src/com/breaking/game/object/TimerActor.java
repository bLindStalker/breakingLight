package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.AssetLoader;
import com.breaking.game.enums.LampData;

import static com.breaking.game.Constants.ACTIVE_LAMP_INTERVAL;
import static com.breaking.game.Constants.BROKEN_TIME_INTERVAL;
import static com.breaking.game.Constants.HARD_CORE_TIME;
import static com.breaking.game.Constants.TIME;
import static com.breaking.game.Constants.TURN_OFF_TIME_INTERVAL;


public class TimerActor extends Label {

    private static final float UPDATE_DIFFICULT_TIME = TIME - HARD_CORE_TIME;
    private int time = TIME;
    public LampData lampData = new LampData();

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
        lampData.setActiveLampInterval(ACTIVE_LAMP_INTERVAL / UPDATE_DIFFICULT_TIME);
        lampData.setBrokenTimeInterval(BROKEN_TIME_INTERVAL / UPDATE_DIFFICULT_TIME);
        lampData.setTurnOffTimeInterval(TURN_OFF_TIME_INTERVAL / UPDATE_DIFFICULT_TIME);

        if (time == TIME / 2) {
            lampData.activeLamps += 1;
        }
    }

    public int getTime() {
        return time;
    }
}
