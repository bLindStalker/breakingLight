package com.breaking.game;

import com.badlogic.gdx.Game;
import com.breaking.game.screens.MenuScreen;
import com.breaking.game.screens.SplashScreen;

public class Main extends Game {
    @Override
    public void create() {
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
