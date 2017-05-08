package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LabelActor extends Actor {

    private final TextureRegion textTextureRegion;

    public LabelActor(int xPosition, int yPosition, int width, int height, Texture textTexture) {
        this.textTextureRegion = new TextureRegion(textTexture);
        setBounds(xPosition, yPosition, width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textTextureRegion, getX() / 2, getY() / 2, getWidth() / 2, getHeight() / 2, getWidth(),
                getHeight(), 1, 1, getRotation());
    }
}