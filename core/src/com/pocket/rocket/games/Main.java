package com.pocket.rocket.games;

import com.badlogic.gdx.Game;
import com.pocket.rocket.games.api.PlayServices;
import com.pocket.rocket.games.screens.MenuScreen;
import com.pocket.rocket.games.screens.SplashScreen;

public class Main extends Game {
    private final PlayServices playServices;

    public Main(PlayServices playServices) {
        this.playServices = playServices;
    }

    @Override
    public void create() {
       // setScreen(new SplashScreen(this));
        AssetLoader.initialize();
        setScreen(new MenuScreen(this, false));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public PlayServices getPlayServices() {
        return playServices;
    }
}
