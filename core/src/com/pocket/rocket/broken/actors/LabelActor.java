package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;

public class LabelActor extends Actor {

    private final TextureRegion texture;

    public LabelActor(int xPosition, int yPosition, int width, int height, Texture textTexture) {
        this.texture = new TextureRegion(textTexture);
        setBounds(xPosition, yPosition, width, height);
        setOrigin(Align.center);
        addAction(rotateTo(-360 * 100, 35 * 100));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX() / 2, getY() / 2, 400 / 2, 400 / 2, 400,
                400, 1, 1, getRotation());
    }
}