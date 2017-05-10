package com.pocket.rocket.broken.screens.menu;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.actors.ImageActor;
import com.pocket.rocket.broken.api.PlayServices;
import com.pocket.rocket.broken.screens.MenuScreen;
import com.pocket.rocket.broken.screens.tutorial.TutorialScreen;

import static com.pocket.rocket.broken.AssetLoader.getActiveLanguage;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getTogleRoundOff;
import static com.pocket.rocket.broken.AssetLoader.getTogleRoundOn;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Preference.soundStatus;

public class Settings extends BackScreen {
    public static final int X_LANGUAGE_POSITION = 90;
    public static final int EN_INDEX = 2;
    public static final int UA_INDEX = 1;
    public static final int RU_INDEX = 0;
    private static final int SETTING_ELEMENT_HEIGHT = 130;
    private final Main main;
    private final PlayServices playServices;
    private final Group googleCheckBox;
    private ImageActor selectedLanguage;

    public Settings(Group parent, Main main) {
        super("SETTINGS", parent);
        this.main = main;
        this.playServices = main.getPlayServices();

        Group setting = new Group();

        setting.addActor(buildSettingElement(1, "Music", getCheckBox(soundStatus(), musicAction(), true)));

        googleCheckBox = getCheckBox(playServices.isConnected(), gpgsAction(), false);
        setting.addActor(buildSettingElement(2, "Google Play", googleCheckBox));

        setting.addActor(buildSettingElement(3, "Language", getLanguage()));
        setting.addActor(buildSettingElement(4, "Tutorial", getTutorial()));
        setting.addActor(buildSettingElement(5, "Clear score", getClearScore()));

        addActor(setting);
        setting.toBack();
    }

    private Runnable musicAction() {
        return new Runnable() {
            @Override
            public void run() {
                Preference.setSoundStatus();
            }
        };
    }

    private Runnable gpgsAction() {
        return new Runnable() {
            @Override
            public void run() {
                if (playServices.isConnected()) {
                    playServices.signOut();
                    playServices.disconnect();
                } else {
                    playServices.connect();
                }
            }
        };
    }

    private Group getTutorial() {
        Group group = new Group();
        group.setBounds(0, 0, WIDTH, SETTING_ELEMENT_HEIGHT);
        group.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addAction(Actions.alpha(0, .2f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        main.setScreen(new TutorialScreen(main));
                    }
                }, 0.25f);
            }
        });
        return group;
    }

    private Group getClearScore() {
        Group group = new Group();
        group.setBounds(0, 0, WIDTH, SETTING_ELEMENT_HEIGHT);
        group.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preference.reset();
                addAction(Actions.alpha(0, .2f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        main.setScreen(new MenuScreen(main, false));
                    }
                }, 0.25f);
            }
        });
        return group;
    }

    private Group getLanguage() {
        Group group = new Group();
        group.setBounds(X_CENTER + 50, SETTING_ELEMENT_HEIGHT / 2 - 80 / 2, 260, 80);

        group.addActor(getLanguage(RU_INDEX));
        group.addActor(getLanguage(UA_INDEX));
        group.addActor(getLanguage(EN_INDEX));

        selectedLanguage = new ImageActor(getLanguagePosition(), 0, 82, 80, getActiveLanguage());
        group.addActor(selectedLanguage);

        return group;
    }

    private ImageActor getLanguage(int index) {
        ImageActor language = new ImageActor(index * X_LANGUAGE_POSITION, 0, 82, 80, AssetLoader.getLanguage(index));
        language.addListener(buildLanguageListener(index));
        return language;
    }

    private ClickListener buildLanguageListener(final int index) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preference.saveLanguage(index);
                selectedLanguage.setX(index * X_LANGUAGE_POSITION);
            }
        };
    }

    private int getLanguagePosition() {
        return Preference.getLanguage() * X_LANGUAGE_POSITION;
    }

    private Group buildSettingElement(int index, String labelName, Group action) {
        Group group = new Group();
        group.setBounds(0, HEIGHT - HEADER_HEIGHT - (SETTING_ELEMENT_HEIGHT * index) + 15, WIDTH, SETTING_ELEMENT_HEIGHT);
        Label nameLabel = new Label(labelName, new Label.LabelStyle(getFont()));
        nameLabel.setPosition(50, SETTING_ELEMENT_HEIGHT / 2 - 25);

        group.addActor(nameLabel);
        group.addActor(action);
        group.addActor(new ImageActor(0, 0, WIDTH, 1, AssetLoader.getLine()));

        return group;
    }

    private Group getCheckBox(boolean on, final Runnable action, final boolean click) {
        Group checkBox = new Group();
        checkBox.setBounds(WIDTH - 140, SETTING_ELEMENT_HEIGHT / 2 - 60 / 2, 86, 60);
        checkBox.addActor(new ImageActor(0, 13, 86, 36, AssetLoader.getTogle()));
        final ImageActor round = new ImageActor(on ? 30 : -5, 0, 60, 60, on ? getTogleRoundOn() : getTogleRoundOff());

        if (on) {
            round.setName("on");
        } else {
            round.setName("off");
        }

        checkBox.addActor(round);

        checkBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (click) {
                    if ("on".equals(round.getName())) {
                        round.setImage(getTogleRoundOff());
                        round.setName("off");
                        round.addAction(Actions.moveTo(-5, 0, 0.2f));
                    } else {
                        round.setImage(getTogleRoundOn());
                        round.setName("on");
                        round.addAction(Actions.moveTo(30, 0, 0.2f));
                    }
                }

                action.run();
            }
        });
        return checkBox;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (main.gpgsStateChange) {
            main.gpgsStateChange = false;

            ImageActor round = (ImageActor) googleCheckBox.getChildren().get(1);
            if (!playServices.isConnected()) {
                round.setImage(getTogleRoundOff());
                round.setName("off");
                round.addAction(Actions.moveTo(-5, 0, 0.2f));
            } else {
                round.setImage(getTogleRoundOn());
                round.setName("on");
                round.addAction(Actions.moveTo(30, 0, 0.2f));
            }
        }
    }
}
