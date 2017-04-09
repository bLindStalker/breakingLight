package com.breaking.game.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.breaking.game.Main;
import com.breaking.game.screens.menu.AdvancedMenu;
import com.breaking.game.screens.menu.Gallery;
import com.breaking.game.screens.menu.MainMenu;

import static com.breaking.game.Constants.X_CENTER;

public class MenuScreen extends BaseScreen {

    public static final float MENU_SWITCH_TIME = 0.35f;
    public static final int X_MENU_BUTTON_POSITION = X_CENTER - 200;
    public static final int Y_MENU_BUTTON = 500;
    public static final int MENU_BUTTON_WIDTH = 400;


    public MenuScreen(final Main main, boolean fade) {
        super(main);
        Group menu = new Group();
        Group gallery = new Gallery();

        menu.addActor(new MainMenu(main, menu));
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
