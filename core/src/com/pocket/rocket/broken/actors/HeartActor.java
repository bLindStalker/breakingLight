package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;

import static com.badlogic.gdx.utils.Timer.schedule;

public class HeartActor extends Image {

    public HeartActor(int xPosition, int yPosition, int width, int height, Texture texture) {
        super(texture);
        setBounds(xPosition, yPosition, width, height);
    }

    public void removeHeart() {
        Gdx.input.vibrate(100);
        addAction(Actions.fadeOut(0.7f, Interpolation.bounceOut));
        schedule(new Timer.Task() {
                     @Override
                     public void run() {
                         setVisible(false);
                     }
        }, 1f);
    }
}
