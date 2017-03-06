package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.AssetLoader;

import static com.breaking.game.Constants.TIME;


public class TimerActor extends Label {

    private int time = TIME;

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
                               time--;
                               startTimer();
                           }
                       }, 1
        );
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public int getTime() {
        return time;
    }
}
