package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.Constants;


public class TimerActor extends Actor {

    private int time = Constants.TIME;

    public TimerActor(int xPosition, int yPosition, int width, int height) {
        setBounds(xPosition, yPosition, width, height);
        startTimer();
    }

    private void startTimer() {
        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               System.out.println(time);
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
