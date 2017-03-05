package com.breaking.game.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameObject extends Image {

    public GameObject(int xPosition, int yPosition, int width, int height, Texture texture) {
        super(texture);
        setBounds(xPosition, yPosition, width, height);
    }

    protected void setImage(Texture texture) {
        setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
    }
}