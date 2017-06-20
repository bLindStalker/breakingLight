package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer.Task;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.actors.ImageActor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.utils.Align.center;
import static com.badlogic.gdx.utils.Timer.schedule;
import static com.pocket.rocket.broken.AssetLoader.getBlock;
import static com.pocket.rocket.broken.AssetLoader.getButtonStyle;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getFullStar;
import static com.pocket.rocket.broken.AssetLoader.getHollowStar;
import static com.pocket.rocket.broken.AssetLoader.getLampImage;
import static com.pocket.rocket.broken.AssetLoader.getLight;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Constants.Y_CENTER;
import static com.pocket.rocket.broken.Preference.resetRateCount;
import static com.pocket.rocket.broken.Preference.setRated;
import static com.pocket.rocket.broken.Utils.pulseAnimation;
import static com.pocket.rocket.broken.enums.LightBulbStatus.ACTIVE;
import static com.pocket.rocket.broken.enums.Text.GIVE_THANKS;
import static com.pocket.rocket.broken.enums.Text.LATER;
import static com.pocket.rocket.broken.enums.Text.RATRE;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_HEIGHT;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_SWITCH_TIME;

public class RateUsScreen extends BaseScreen {

    private static final int GROUP_WIDTH = 645;
    private static final int GROUP_HEIGHT = 660;
    private static final int SIZE = 60;
    private static final int DISTANCE = 20;
    private final Runnable nextScreen;
    private final Group group;

    public RateUsScreen(final Main main, final Runnable nextScreen) {
        super(main);
        this.nextScreen = nextScreen;

        group = buildRateUsElements(main);
        addActor(group);
    }

    private Group buildRateUsElements(final Main main) {
        resetRateCount();
        Group group = new Group();
        group.setBounds(X_CENTER - GROUP_WIDTH / 2, Y_CENTER - GROUP_HEIGHT / 2 - 60, GROUP_WIDTH, GROUP_HEIGHT);

        Image light = new Image(getLight());
        light.setOrigin(center);
        light.setPosition(GROUP_WIDTH / 2 - light.getWidth() / 2, GROUP_HEIGHT / 2 - light.getHeight() / 2);
        light.addAction(forever(rotateBy(360, 25f)));
        group.addActor(light);

        ImageActor lamp = new ImageActor(GROUP_WIDTH / 2 - LAMP_WIDTH / 2, GROUP_HEIGHT - 340, LAMP_WIDTH, LAMP_HEIGHT, getLampImage(ACTIVE));
        lamp.addAction(alpha(0));
        lamp.addAction(parallel(alpha(1, 1f), moveBy(0, 230, 1.7f, Interpolation.exp5In)));
        group.addActor(lamp);

        group.addActor(new ImageActor(0, 0, GROUP_WIDTH, GROUP_HEIGHT, getBlock()));

        TextButton rateButton = new TextButton(RATRE.get(), getButtonStyle());
        rateButton.setBounds(GROUP_WIDTH / 2 - MENU_BUTTON_WIDTH / 2, 190, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        rateButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.getPlayServices().rateGame();
                setRated();
                nextScreen();
            }
        });
        group.addActor(rateButton);

        Label laterButton = new Label(LATER.get(), new Label.LabelStyle(getFont(Color.GRAY)));
        laterButton.setFontScale(0.8f);
        laterButton.setBounds(GROUP_WIDTH / 2 - MENU_BUTTON_WIDTH / 2, 135, MENU_BUTTON_WIDTH, 75);
        laterButton.setAlignment(center);
        laterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nextScreen();
            }
        });
        group.addActor(laterButton);

        group.addActor(buildStars());

        Label text = new Label(GIVE_THANKS.get(), new Label.LabelStyle(getFont(Color.BLACK)));
        text.setFontScale(1.2f);
        text.setPosition(GROUP_WIDTH / 2 - text.getWidth() / 2, 525);
        text.setAlignment(center);
        group.addActor(text);

        group.addAction(alpha(0, 0));
        group.addAction(alpha(1, 0.5f));

        return group;
    }

    private void nextScreen() {
        group.addAction(moveBy(0, -HEIGHT, MENU_SWITCH_TIME));
        schedule(new Task() {
            @Override
            public void run() {
                nextScreen.run();
            }
        }, MENU_SWITCH_TIME);
    }

    private Group buildStars() {
        Group group = new Group();

        int groupWidth = SIZE * 5 + DISTANCE * 4;
        group.setBounds(GROUP_WIDTH / 2 - groupWidth / 2, 410, groupWidth, SIZE);

        group.addActor(new ImageActor(0, 0, SIZE, SIZE, getFullStar()));
        group.addActor(new ImageActor(DISTANCE + SIZE, 0, SIZE, SIZE, getFullStar()));
        group.addActor(new ImageActor(2 * (DISTANCE + SIZE), 0, SIZE, SIZE, getFullStar()));
        group.addActor(new ImageActor(3 * (DISTANCE + SIZE), 0, SIZE, SIZE, getHollowStar()));
        group.addActor(new ImageActor(4 * (DISTANCE + SIZE), 0, SIZE, SIZE, getHollowStar()));

        group.addActor(buildAnimatedStar(3));
        group.addActor(buildAnimatedStar(4));

        return group;
    }

    private ImageActor buildAnimatedStar(int position) {
        final ImageActor star = new ImageActor(position * (DISTANCE + SIZE), 0, SIZE, SIZE, getFullStar());
        star.setOrigin(center);
        star.addAction(alpha(0));

        schedule(new Task() {
            @Override
            public void run() {
                star.addAction(parallel(fadeIn(0.8f), pulseAnimation(0.3f, 0.8f, 1)/*, Actions.rotateTo(360, 1.6f)*/));
            }
        }, 1.7f);

        return star;
    }
}
