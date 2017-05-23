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
import static com.pocket.rocket.broken.AssetLoader.getLogoLabel;
import static com.pocket.rocket.broken.AssetLoader.getLogoText;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Preference.getScore;
import static com.pocket.rocket.broken.Preference.getTotalScore;
import static com.pocket.rocket.broken.Utils.buildLogo;
import static com.pocket.rocket.broken.enums.Text.MAX;
import static com.pocket.rocket.broken.enums.Text.TOTAL;
import static java.lang.String.valueOf;

public class MenuScreen extends BaseScreen {

    public static final float MENU_SWITCH_TIME = 0.35f;
    public static final int MENU_BUTTON_WIDTH = 480;
    public static final int MENU_BUTTON_HEIGHT = 210;
    public static final int Y_MENU_BUTTON = 445;
    public static final int X_MENU_BUTTON_POSITION = X_CENTER - MENU_BUTTON_WIDTH / 2;
    public static final int LOGO_SIZE = 400;
    public final MainMenuButtons menuButtons;

    public MenuScreen(final Main main, boolean fade) {
        super(main);

        Group menu = new Group();

        if (Preference.getScore() > 0) {
            menu.addActor(new MenuIcons(main));
        }

        menu.addActor(buildLogo(getLogoText(), getLogoLabel()));
        menu.addActor(buildScoreLabel());
        menuButtons = new MainMenuButtons(main, menu);
        menu.addActor(menuButtons);

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
        int totalScore = getTotalScore();

        labelGroup.addActor(buildScoreLabel(getFont(), MAX.get() + valueOf(score), 700));
        labelGroup.addActor(buildScoreLabel(getFont(), TOTAL.get() + valueOf(totalScore), 660));

        labelGroup.setVisible(score > 0 && totalScore > 0);
        return labelGroup;
    }

    private Label buildScoreLabel(Label.LabelStyle font, String text, int y) {
        Label label = new Label(text, font);

        label.setAlignment(Align.left);
        label.setPosition(X_CENTER - label.getWidth() / 2, y);
        label.setFontScale(0.8f, 0.8f);

        return label;
    }
}
