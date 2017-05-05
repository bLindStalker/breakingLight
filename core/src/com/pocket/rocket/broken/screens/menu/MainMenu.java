package com.pocket.rocket.broken.screens.menu;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.screens.GameScreen;
import com.pocket.rocket.broken.screens.tutorial.TutorialScreen;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.utils.Timer.Task;
import static com.badlogic.gdx.utils.Timer.schedule;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Preference.getScore;
import static com.pocket.rocket.broken.Preference.getTotalScore;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.X_MENU_BUTTON_POSITION;
import static com.pocket.rocket.broken.screens.MenuScreen.Y_MENU_BUTTON;
import static java.lang.String.valueOf;

public class MainMenu extends Group {
    private static final int MENU_BUTTON_HEIGHT = 120;
    private static final int MENU_BUTTON_WHITE_SPACE = 10;
    private static final int LOGO_WIDTH = 400;

    private final Main main;
    private final Group menuGroup;

    public MainMenu(final Main main, final Group menuGroup) {
        addActor(new ImageActor((WIDTH - LOGO_WIDTH) / 2, HEIGHT - LOGO_WIDTH - 100, LOGO_WIDTH, LOGO_WIDTH, AssetLoader.getLogoLabel()));
        this.main = main;
        this.menuGroup = menuGroup;

        addActor(buildScoreLabel());
        addActor(buildButtons());
    }

    private Group buildScoreLabel() {
        Group labelGroup = new Group();

        int score = getScore();
        labelGroup.addActor(buildScoreLabel(AssetLoader.getFont(WHITE), "max: ", X_CENTER - 300, 690));
        labelGroup.addActor(buildScoreLabel(AssetLoader.getFont(WHITE), valueOf(score), X_CENTER - 210, 690));

        int totalScore = getTotalScore();
        labelGroup.addActor(buildScoreLabel(AssetLoader.getFont(WHITE), "total: ", X_CENTER + 135, 690));
        labelGroup.addActor(buildScoreLabel(AssetLoader.getFont(WHITE), valueOf(totalScore), X_CENTER + 135 + 90, 690));

        labelGroup.setVisible(score > 0 && totalScore > 0);
        return labelGroup;
    }

    private Group buildButtons() {
        Group menuButtonsGroup = new Group();

        TextButton startButton = new TextButton("START", AssetLoader.getButtonStyle());
        startButton.setBounds(X_MENU_BUTTON_POSITION, Y_MENU_BUTTON, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuGroup.addAction(Actions.moveTo(0, -HEIGHT, 0.25f));
                schedule(new Task() {
                    @Override
                    public void run() {
                        if (getTotalScore() > 1) {
                            main.setScreen(new GameScreen(main));
                        } else {
                            main.setScreen(new TutorialScreen(main));
                        }
                    }
                }, 0.25f);
            }
        });
        menuButtonsGroup.addActor(startButton);

        Label gallery = new Label("GALLERY", new Label.LabelStyle(AssetLoader.getFont(WHITE)));
        gallery.setBounds(X_MENU_BUTTON_POSITION, Y_MENU_BUTTON - MENU_BUTTON_HEIGHT - MENU_BUTTON_WHITE_SPACE - 5, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        gallery.setAlignment(Align.center);
        menuButtonsGroup.addActor(gallery);

        Label settings = new Label("SETTINGS", new Label.LabelStyle(AssetLoader.getFont(WHITE)));
        settings.setBounds(X_MENU_BUTTON_POSITION, 15 + Y_MENU_BUTTON - (2 * MENU_BUTTON_HEIGHT) - (2 * MENU_BUTTON_WHITE_SPACE), MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        settings.setAlignment(Align.center);
        menuButtonsGroup.addActor(settings);
        return menuButtonsGroup;
    }

    private Label buildScoreLabel(Label.LabelStyle font, String text, int x, int y) {
        Label label = new Label(text, font);

        label.setAlignment(Align.left);
        label.setBounds(x, y, 100, 50);
        label.setFontScale(0.9f, 0.9f);

        return label;
    }
}
