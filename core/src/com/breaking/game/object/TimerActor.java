package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.AssetLoader;
import com.breaking.game.enums.LampData;

import static com.breaking.game.Constants.TIME;


public class TimerActor extends Label {

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
    /*    if (time <= UPDATE_DIFFICULTY && time > HARD_CORE_TIME) {
            lampData.minActiveTime -= (MAX_ACTIVE_LAMP_TIME - MIN_ACTIVE_LAMP_TIME) / (UPDATE_DIFFICULTY - HARD_CORE_TIME);
            lampData.brokenTime -= (MAX_BROKEN_TIME - MIN_BROKEN_TIME) / (UPDATE_DIFFICULTY - HARD_CORE_TIME);
        }*/
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public int getTime() {
        return time;
    }
}
