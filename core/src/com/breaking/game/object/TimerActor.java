package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.AssetLoader;
import com.breaking.game.enums.LightTime;

import static com.breaking.game.Constants.HARD_CORE_TIME;
import static com.breaking.game.Constants.MAX_ACTIVE_LAMP_TIME;
import static com.breaking.game.Constants.MAX_BROKEN_TIME;
import static com.breaking.game.Constants.MIN_ACTIVE_LAMP_TIME;
import static com.breaking.game.Constants.MIN_BROKEN_TIME;
import static com.breaking.game.Constants.TIME;
import static com.breaking.game.Constants.UPDATE_DIFFICULTY;


public class TimerActor extends Label {

    private int time = TIME;
    public LightTime lightTime = new LightTime();

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
        if (time <= UPDATE_DIFFICULTY && time > HARD_CORE_TIME) {
            lightTime.minActiveTime -= (MAX_ACTIVE_LAMP_TIME - MIN_ACTIVE_LAMP_TIME) / (UPDATE_DIFFICULTY - HARD_CORE_TIME);
            lightTime.activationTime -= (MAX_BROKEN_TIME - MIN_BROKEN_TIME) / (UPDATE_DIFFICULTY - HARD_CORE_TIME);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public int getTime() {
        return time;
    }
}
