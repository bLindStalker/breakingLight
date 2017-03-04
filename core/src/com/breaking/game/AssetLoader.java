package com.breaking.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader extends AssetManager {

    public AssetLoader() {
        load("background.png", Texture.class);
        load("heard.png", Texture.class);
        load("badlogic.jpg", Texture.class);
        finishLoading();
    }

    public Texture getBackGround() {
        return get("background.png");
    }

    public Texture getHeard() {
        return get("heard.png");
    }

    public Texture getLightBulb() {
        return get("badlogic.jpg");
    }
}
