package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.screens.menu.AdvancedMenu;
import com.pocket.rocket.broken.screens.menu.MenuIcons;

import static com.pocket.rocket.broken.Constants.X_CENTER;

public class MenuScreen extends BaseScreen {

    public static final float MENU_SWITCH_TIME = 0.35f;
    public static final int X_MENU_BUTTON_POSITION = X_CENTER - 200;
    public static final int Y_MENU_BUTTON = 550;
    public static final int MENU_BUTTON_WIDTH = 400;


    public MenuScreen(final com.pocket.rocket.broken.Main main, boolean fade) {
        super(main);

        if (Preference.getScore() > 0) {
            addActor(new MenuIcons(main));
        }

        Group menu = new Group();
        Group gallery = new com.pocket.rocket.broken.screens.menu.Gallery();

        menu.addActor(new com.pocket.rocket.broken.screens.menu.MainMenu(main, menu));
        menu.addActor(new AdvancedMenu(main, menu, gallery));
        menu.addActor(gallery);

        addActor(menu);

        menu.addAction(Actions.alpha(0, 0));
        menu.addAction(Actions.alpha(1, .25f));

        if (fade) {
            addAction(Actions.alpha(0, 0));
            addAction(Actions.alpha(1, .5f));
        }
    }
}
