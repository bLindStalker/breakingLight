package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.screens.menu.MainMenuButtons;
import com.pocket.rocket.broken.screens.menu.MenuIcons;

import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Preference.getScore;
import static com.pocket.rocket.broken.Preference.getTotalScore;
import static java.lang.String.valueOf;

public class MenuScreen extends BaseScreen {

    public static final float MENU_SWITCH_TIME = 0.35f;
    public static final int MENU_BUTTON_WIDTH = 480;
    public static final int MENU_BUTTON_HEIGHT = 210;
    public static final int Y_MENU_BUTTON = 445;
    public static final int X_MENU_BUTTON_POSITION = X_CENTER - MENU_BUTTON_WIDTH / 2;
    public static final int LOGO_WIDTH = 400;

    public MenuScreen(final Main main, boolean fade) {
        super(main);

        Group menu = new Group();
        if (Preference.getScore() > 0) {
            menu.addActor(new MenuIcons(main));
        }
        menu.addActor(buildScoreLabel());
        menu.addActor(new MainMenuButtons(main, menu));

        //Gallery gallery = new Gallery();
        //menu.addActor(new AdvancedMenu(main, menu, gallery));
        //menu.addActor(gallery);

        addActor(menu);

        menu.addAction(Actions.alpha(0, 0));
        menu.addAction(Actions.alpha(1, .25f));

        if (fade) {
            addAction(Actions.alpha(0, 0));
            addAction(Actions.alpha(1, .5f));
        }
    }

    private Group buildScoreLabel() {
        Group labelGroup = new Group();

        int score = getScore();
        labelGroup.addActor(buildScoreLabel(getFont(), "max: ", X_CENTER - 300, 690));
        labelGroup.addActor(buildScoreLabel(getFont(), valueOf(score), X_CENTER - 210, 690));

        int totalScore = getTotalScore();
        labelGroup.addActor(buildScoreLabel(getFont(), "total: ", X_CENTER + 135, 690));
        labelGroup.addActor(buildScoreLabel(getFont(), valueOf(totalScore), X_CENTER + 135 + 90, 690));

        labelGroup.setVisible(score > 0 && totalScore > 0);
        return labelGroup;
    }

    private Label buildScoreLabel(Label.LabelStyle font, String text, int x, int y) {
        Label label = new Label(text, font);

        label.setAlignment(Align.left);
        label.setBounds(x, y, 100, 50);
        label.setFontScale(0.9f, 0.9f);

        return label;
    }
}
