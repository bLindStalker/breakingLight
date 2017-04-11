package com.pocket.rocket.games;

import com.badlogic.gdx.Game;
import com.pocket.rocket.games.screens.SplashScreen;

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
