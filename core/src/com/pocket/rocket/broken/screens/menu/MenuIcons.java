package com.pocket.rocket.broken.screens.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.SnapshotArray;
import com.pocket.rocket.broken.Constants;
import com.pocket.rocket.broken.actors.ImageActor;

public class MenuIcons extends Group {
    private static final int ICON_SIZE = 100;
    public static final int ICON_GOURP_WIDTH = Constants.WIDTH - 40;

    public MenuIcons(com.pocket.rocket.broken.Main main) {
        setBounds(20, 35, Constants.WIDTH - 40, ICON_SIZE);

        addActor(buildIcon(com.pocket.rocket.broken.AssetLoader.getServiceOn(), new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //super.clicked(event, x, y);
            }
        }));

        addActor(buildIcon(com.pocket.rocket.broken.AssetLoader.getAuthors(), new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //super.clicked(event, x, y);
            }
        }));

        addActor(buildIcon(com.pocket.rocket.broken.AssetLoader.getRateUs(), new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //super.clicked(event, x, y);
            }
        }));

        addActor(buildIcon(com.pocket.rocket.broken.AssetLoader.getAds(), new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //super.clicked(event, x, y);
            }
        }));

        addActor(buildIcon(com.pocket.rocket.broken.AssetLoader.getNoAds(), new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //super.clicked(event, x, y);
            }
        }));

        addActor(buildIcon(com.pocket.rocket.broken.AssetLoader.getSoundOn(), new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //super.clicked(event, x, y);
            }
        }));

        SnapshotArray<Actor> children = getChildren();
        for (int i = 0; i < children.size; i++) {
            children.get(i).addAction(Actions.moveTo(((ICON_GOURP_WIDTH / (children.size + 1)) * (i + 1)) - ICON_SIZE / 2, 0, 1.5f, Interpolation.exp10Out));
        }
    }

    private ImageActor buildIcon(Texture texture, ClickListener listener) {
        ImageActor actor = new ImageActor(ICON_GOURP_WIDTH / 2 - ICON_SIZE / 2, 0, ICON_SIZE, ICON_SIZE, texture);
        actor.addListener(listener);
        return actor;
    }
}
