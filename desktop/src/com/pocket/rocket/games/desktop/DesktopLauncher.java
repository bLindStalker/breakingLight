package com.pocket.rocket.games.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pocket.rocket.games.Main;
import com.pocket.rocket.games.api.PlayServices;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 720 / 2;
        config.height = 1280 / 2;
        new LwjglApplication(new Main(new PlayServicesImpl()), config);
    }

    private static class PlayServicesImpl implements PlayServices {

        @Override
        public void signIn() {
            Gdx.app.log("DesktopLauncher", "sing in");
        }

        @Override
        public void signOut() {
            Gdx.app.log("DesktopLauncher", "sing out");
        }

        @Override
        public void rateGame() {
            Gdx.app.log("DesktopLauncher", "rateGame");
        }

        @Override
        public void unlockAchievement() {

        }

        @Override
        public void submitScore(int highScore) {

        }

        @Override
        public void showAchievement() {

        }

        @Override
        public void showScore() {

        }

        @Override
        public boolean isSignedIn() {
            return false;
        }
    }
}
