package com.breaking.game.object;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameObject extends Actor {

    public GameObject(int xPosition, int yPosition, int width, int height) {
        setBounds(xPosition, yPosition, width, height);
    }
}