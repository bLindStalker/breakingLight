package com.breaking.game.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameObject extends Actor {
    protected Sprite sprite;
    protected Batch batch;

    public GameObject(int xPosition, int yPosition, int width, int height, Batch batch, Texture texture) {
        setBounds(xPosition, yPosition, width, height);
        this.batch = batch;
        sprite = new Sprite(texture);
        sprite.setBounds(xPosition, yPosition, width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
}