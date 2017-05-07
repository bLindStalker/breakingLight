package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.utils.Timer.schedule;

public class HeartActor extends AnimatedActor {

    public HeartActor(int xPosition, int yPosition, int width, int height, Animation<TextureRegion> animation) {
        super(xPosition, yPosition, width, height, animation);
    }

    @Override
    public void animate() {
        super.animate();
        Gdx.input.vibrate(100);

        schedule(new Timer.Task() {
                     @Override
                     public void run() {
                         addAction(alpha(0f, .5f));
                         schedule(new Timer.Task() {
                                      @Override
                                      public void run() {
                                          remove();
                                      }
                                  }, 0.5f
                         );
                     }
                 }, 0.5f
        );

    }
}
