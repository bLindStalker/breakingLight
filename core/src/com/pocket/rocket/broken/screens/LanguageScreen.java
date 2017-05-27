package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Constants.Y_CENTER;
import static com.pocket.rocket.broken.screens.menu.Settings.EN_INDEX;
import static com.pocket.rocket.broken.screens.menu.Settings.RU_INDEX;
import static com.pocket.rocket.broken.screens.menu.Settings.UA_INDEX;


public class LanguageScreen extends BaseScreen {

    private final Main main;

    public LanguageScreen(Main main) {
        super(main);
        this.main = main;

        Group group = new Group();
        group.setBounds(X_CENTER - 190, Y_CENTER, 380, 100);

        group.addActor(getLanguage(RU_INDEX));
        group.addActor(getLanguage(UA_INDEX));
        group.addActor(getLanguage(EN_INDEX));

        addActor(group);

        addAction(Actions.alpha(0, 0));
        addAction(Actions.alpha(1, .5f));
    }

    private ImageActor getLanguage(int index) {
        ImageActor language = new ImageActor(index * 140, 0, 100, 100, AssetLoader.getLanguage(index));
        language.addListener(buildLanguageListener(index));
        return language;
    }

    private ClickListener buildLanguageListener(final int index) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preference.saveLanguage(index);
                main.setScreen(new MenuScreen(main, true));
            }
        };
    }

}

