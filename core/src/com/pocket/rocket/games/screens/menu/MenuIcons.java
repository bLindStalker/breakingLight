package com.pocket.rocket.games.screens.menu;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pocket.rocket.games.AssetLoader;
import com.pocket.rocket.games.Constants;
import com.pocket.rocket.games.Main;
import com.pocket.rocket.games.actors.ImageActor;

public class MenuIcons extends Group {
    private static final int ICON_SIZE = 105;
    private static final int xPosition = ICON_SIZE + 28;

    public MenuIcons(Main main) {
        setBounds(40, 35, Constants.WIDTH - 80, ICON_SIZE);


        ImageActor authors = new ImageActor(0, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getAuthors());
        addActor(authors);

        ImageActor ads = new ImageActor(xPosition, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getAds());
        addActor(ads);

        ImageActor noAds = new ImageActor(2 * xPosition, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getNoAds());
        addActor(noAds);

        ImageActor rateUs = new ImageActor(3 * xPosition, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getRateUs());
        addActor(rateUs);

        ImageActor sound = new ImageActor(4 * xPosition, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getSoundOn());
        addActor(sound);
    }
}
