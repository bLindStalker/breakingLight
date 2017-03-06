package com.breaking.game;

import com.badlogic.gdx.Game;
import com.breaking.game.screens.MainGameScreen;

public class Main extends Game {
    @Override
    public void create() {
        AssetLoader.initialize();
        setScreen(new MainGameScreen());
    }


    @Override
    public void dispose() {
        super.dispose();
    }
}
