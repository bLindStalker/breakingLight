package com.pocket.rocket.broken;

import com.badlogic.gdx.Game;
import com.pocket.rocket.broken.api.PlayServices;
import com.pocket.rocket.broken.screens.SplashScreen;

public class Main extends Game {
    private final PlayServices playServices;
    public boolean gpgsStateChange = false;

    public Main(PlayServices playServices) {
        this.playServices = playServices;
    }

    @Override
    public void create() {
        setScreen(new SplashScreen(this));
        /*AssetLoader.initialize();
        setScreen(new TutorialScreen(this));*/
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public PlayServices getPlayServices() {
        return playServices;
    }
}
