package com.pocket.rocket.broken.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pocket.rocket.broken.Main;

import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;


public class DesktopLauncher {

    private static final String appName = DesktopLauncher.class.getPackage().getName();
    // change this
    private static SCREEN_CFG screenConfig = SCREEN_CFG.MY_RESOLUTION;

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = screenConfig.getWidth() / 2;
        config.height = screenConfig.getHeight() / 2;
        config.title = "" + appName + " [ " + config.width + " x " + config.height + " ]";

        new LwjglApplication(new Main( /*new AdMobImpl()*/ new GPGSImpl()), config);
    }

    private enum SCREEN_CFG {
        GALAXY_TAB2(1024, 554),// not 600
        SQUARE(1024, 768),
        FULL_HD(1920, 1080),
        SMALL(320, 240),
        SCREEN_SHOT(1024, 500),
        DEFAULT(800, 480),
        MY_RESOLUTION(HEIGHT, WIDTH);

        final boolean LANDSCAPE = true;
        final boolean PORTRAIT = false;
        final boolean screenOrientation = PORTRAIT;

        private final int width;
        private final int height;

        private SCREEN_CFG(final int width, final int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return screenOrientation ? width : height;
        }

        public int getHeight() {
            return screenOrientation ? height : width;
        }
    }
}
