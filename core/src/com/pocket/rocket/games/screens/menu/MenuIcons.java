package com.pocket.rocket.games.screens.menu;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.SnapshotArray;
import com.pocket.rocket.games.AssetLoader;
import com.pocket.rocket.games.Main;
import com.pocket.rocket.games.actors.ImageActor;

import static com.pocket.rocket.games.Constants.WIDTH;

public class MenuIcons extends Group {
    private static final int ICON_SIZE = 100;
    public static final int ICON_GOURP_WIDTH = WIDTH - 40;

    public MenuIcons(Main main) {
        setBounds(20, 35, WIDTH - 40, ICON_SIZE);

        addActor(new ImageActor(ICON_GOURP_WIDTH / 2 - ICON_SIZE / 2, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getAuthors()));
        addActor(new ImageActor(ICON_GOURP_WIDTH / 2 - ICON_SIZE / 2, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getAuthors()));
        addActor(new ImageActor(ICON_GOURP_WIDTH / 2 - ICON_SIZE / 2, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getRateUs()));
        addActor(new ImageActor(ICON_GOURP_WIDTH / 2 - ICON_SIZE / 2, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getAds()));
        addActor(new ImageActor(ICON_GOURP_WIDTH / 2 - ICON_SIZE / 2, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getNoAds()));
        addActor(new ImageActor(ICON_GOURP_WIDTH / 2 - ICON_SIZE / 2, 0, ICON_SIZE, ICON_SIZE, AssetLoader.getSoundOn()));


        SnapshotArray<Actor> children = getChildren();
        for (int i = 0; i < children.size; i++) {
            children.get(i).addAction(Actions.moveTo(((ICON_GOURP_WIDTH / (children.size + 1)) * (i + 1)) - ICON_SIZE / 2, 0, 1.5f, Interpolation.swingOut));
        }
    }
}
