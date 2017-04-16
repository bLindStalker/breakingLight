package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageActor extends Image {

    public ImageActor(int xPosition, int yPosition, int width, int height, Texture texture) {
        super(texture);
        setBounds(xPosition, yPosition, width, height);
    }

    public ImageActor(int xPosition, int yPosition, int width, int height, TextureRegion textureRegion) {
        super(textureRegion);
        setBounds(xPosition, yPosition, width, height);
    }

    public void setImage(Texture texture) {
        setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
    }
}