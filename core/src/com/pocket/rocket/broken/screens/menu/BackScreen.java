package com.pocket.rocket.broken.screens.menu;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.utils.Timer.schedule;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_SWITCH_TIME;

public class BackScreen extends Group {

    public static final int HEADER_HEIGHT = 160;
    private Group parent;

    public BackScreen(String header, Group parent) {
        this.parent = parent;
        setPosition(WIDTH, 0);
        Group headerGroup = new Group();
        headerGroup.addActor(new ImageActor(0, HEIGHT - HEADER_HEIGHT, WIDTH, HEADER_HEIGHT, AssetLoader.getHeader()));
        ImageActor back = new ImageActor(25, HEIGHT - 150 / 2 - 50, 100, 100, AssetLoader.getBack());
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                back();
            }
        });
        headerGroup.addActor(back);

        Label headerLabel = new Label(header, new Label.LabelStyle(getFont()));
        headerLabel.setBounds(X_CENTER - 150, HEIGHT - 125, 300, 100);
        headerLabel.setAlignment(Align.center);
        headerGroup.addActor(headerLabel);
        addActor(headerGroup);
    }

    protected void back() {
        parent.addAction(Actions.moveTo(0, 0, MENU_SWITCH_TIME));
        schedule(new Timer.Task() {
            @Override
            public void run() {
                setVisible(false);
            }
        }, MENU_SWITCH_TIME);
    }
}
