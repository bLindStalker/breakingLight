package com.breaking.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
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
    static private final Color tempColor = new Color();
    public LampLogicData lampData = new LampLogicData();
    private int time = TIME;

    public TimerActor(int xPosition, int yPosition, int width, int height, LabelStyle style) {
        super(String.valueOf(TIME), style);
        setBounds(xPosition, yPosition, width, height);
        setAlignment(Align.center);
        setFontScale(1.3f, 1.2f);
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();
        Color color = tempColor.set(getColor());
        if (getStyle().background != null) {
            batch.setColor(color.r, color.g, color.b, 0.7f);
            getStyle().background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        if (getStyle().fontColor != null) color.mul(getStyle().fontColor);
        getBitmapFontCache().tint(color);
        getBitmapFontCache().setPosition(getX(), getY());
        getBitmapFontCache().draw(batch);
    }
}
