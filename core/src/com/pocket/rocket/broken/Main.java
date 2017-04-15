package com.pocket.rocket.broken;

import com.badlogic.gdx.Game;
import com.pocket.rocket.broken.api.PlayServices;

public class Main extends Game {
    private final PlayServices playServices;

    public Main(PlayServices playServices) {
        this.playServices = playServices;
    }

    @Override
    public void create() {
        setScreen(new com.pocket.rocket.broken.screens.SplashScreen(this));
        // AssetLoader.initialize();
        //setScreen(new MenuScreen(this, false));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public PlayServices getPlayServices() {
        return playServices;
    }
}
