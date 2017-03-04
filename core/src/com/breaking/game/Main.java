package com.breaking.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breaking.game.screens.MainGameScreen;

public class Main extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MainGameScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
