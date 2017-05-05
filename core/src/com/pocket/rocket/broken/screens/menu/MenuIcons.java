package com.pocket.rocket.broken.screens.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.SnapshotArray;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.api.PlayServices;

import static com.pocket.rocket.broken.AssetLoader.getAchievements;
import static com.pocket.rocket.broken.AssetLoader.getAds;
import static com.pocket.rocket.broken.AssetLoader.getNoAds;
import static com.pocket.rocket.broken.Constants.WIDTH;

public class MenuIcons extends Group {
    private static final int ICON_SIZE = 100;
    private static final int ICON_GROUP_WIDTH = WIDTH + ICON_SIZE / 2;
    private final PlayServices playServices;
    private Main main;
    private ImageActor serviceIcon;

    public MenuIcons(final Main main) {
        this.main = main;
        playServices = main.getPlayServices();
        setBounds(-ICON_SIZE / 4, 90, ICON_GROUP_WIDTH, ICON_SIZE);

        addActor(buildIcon(AssetLoader.getTop(), new

                ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        playServices.showLeaderboard();
                    }
                }));

        addActor(buildIcon(getAchievements(), new

                ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        playServices.showAchievements();
                    }
                }));

        addActor(buildIcon(getAds(), new

                ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        //super.clicked(event, x, y);
                    }
                }));

        addActor(buildIcon(getNoAds(), new

                ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        //super.clicked(event, x, y);
                    }
                }));

        addActor(buildIcon(AssetLoader.getRateUs(), new

                ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        playServices.rateGame();
                    }
                }));

        setPosition();
    }

    private void setPosition() {
        SnapshotArray<Actor> children = getChildren();
        for (int i = 0; i < children.size; i++) {
            children.get(i).addAction(Actions.moveTo(((ICON_GROUP_WIDTH / (children.size + 1)) * (i + 1)) - ICON_SIZE / 2, 0, 1.5f, Interpolation.exp10Out));
        }
    }

   /* private ImageActor getServiceIcon() {
        serviceIcon = buildIcon(playServices.isConnected() ? getServiceOn() : getServiceOff(), null);

        ClickListener listener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (playServices.isConnected()) {
                    playServices.signOut();
                    playServices.disconnect();
                } else {
                    playServices.connect();
                }
            }
        };

        serviceIcon.addListener(listener);
        return serviceIcon;
    }*/

    private ImageActor buildIcon(Texture texture, ClickListener listener) {
        ImageActor actor = new ImageActor(ICON_GROUP_WIDTH / 2 - ICON_SIZE / 2, 0, ICON_SIZE, ICON_SIZE, texture);
        if (listener != null) {
            actor.addListener(listener);
        }
        return actor;
    }

    /*@Override
    public void act(float delta) {
        super.act(delta);
        if (main.gpgsStateChange) {
            main.gpgsStateChange = false;
            serviceIcon.addAction(alpha(0, .3f));
            schedule(new Timer.Task() {
                         @Override
                         public void run() {
                             serviceIcon.setImage(playServices.isConnected() ? getServiceOn() : getServiceOff());
                             serviceIcon.addAction(alpha(1, .3f));
                         }
                     }, .3f
            );
        }
    }*/
}
