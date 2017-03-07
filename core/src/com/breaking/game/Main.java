package com.breaking.game;

import com.badlogic.gdx.Game;
import com.breaking.game.screens.MainGameScreen;
import com.breaking.game.screens.MenuScreen;
import com.breaking.game.screens.ResultScreen;

public class Main extends Game {
    @Override
    public void create() {
        AssetLoader.initialize();
        setScreen(new MenuScreen(this));
    }


    @Override
    public void dispose() {
        super.dispose();
    }
}
