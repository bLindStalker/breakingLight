package com.breaking.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breaking.game.screens.MainGameScreen;

public class Main extends Game {
    private SpriteBatch batch;
    private AssetLoader assetLoader;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetLoader = new AssetLoader();

        setScreen(new MainGameScreen(this));
    }

    public AssetLoader getAssetLoader() {
        return assetLoader;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void dispose() {
        super.dispose();
        assetLoader.dispose();
        batch.dispose();
    }
}
