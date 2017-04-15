package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedActor extends ImageActor {
    private Animation<TextureRegion> animation = null;
    private float stateTime = 0;
    private boolean animate = false;

    public AnimatedActor(int xPosition, int yPosition, int width, int height, Animation<TextureRegion> animation) {
        super(xPosition, yPosition, width, height, animation.getKeyFrame(0));
        this.animation = animation;
    }

    @Override
    public void act(float delta) {
        if (animate) {
            ((TextureRegionDrawable) getDrawable()).setRegion(animation.getKeyFrame(stateTime += delta));
        }
        super.act(delta);
    }

    public void animate() {
        animate = true;
    }
}
