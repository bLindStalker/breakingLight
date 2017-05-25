package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.actions.ScoreAction;
import com.pocket.rocket.broken.actors.userData.ScoreActor;
import com.pocket.rocket.broken.api.PlayServices;

import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getGameOverLabel;
import static com.pocket.rocket.broken.AssetLoader.getGameOverText;
import static com.pocket.rocket.broken.Constants.BASIC_BONUS_SCORE;
import static com.pocket.rocket.broken.Constants.COLLECT_BONUSES;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_OPEN_MAX;
import static com.pocket.rocket.broken.Constants.LAMP_OPEN_TOTAL;
import static com.pocket.rocket.broken.Constants.PLAY_TIMES;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Constants.X_CENTER;
import static com.pocket.rocket.broken.Preference.bonusActivatedHeart;
import static com.pocket.rocket.broken.Preference.doubleBonusActivated;
import static com.pocket.rocket.broken.Preference.getBonusCount;
import static com.pocket.rocket.broken.Preference.getPlayTimes;
import static com.pocket.rocket.broken.Preference.getScore;
import static com.pocket.rocket.broken.Preference.getTotalScore;
import static com.pocket.rocket.broken.Preference.lamp2Open;
import static com.pocket.rocket.broken.Preference.lamp3Open;
import static com.pocket.rocket.broken.Preference.saveBonusCount;
import static com.pocket.rocket.broken.Preference.saveScore;
import static com.pocket.rocket.broken.Preference.updatePlayTimes;
import static com.pocket.rocket.broken.Utils.buildLogo;
import static com.pocket.rocket.broken.Utils.pulseAnimation;
import static com.pocket.rocket.broken.actions.ScoreAction.scoreAction;
import static com.pocket.rocket.broken.enums.Achievement.Get1000OrMoreAtOnce;
import static com.pocket.rocket.broken.enums.Achievement.Get2000OrMoreAtOnce;
import static com.pocket.rocket.broken.enums.Achievement.Get3000OrMoreAtOnce;
import static com.pocket.rocket.broken.enums.Achievement.Survive30Seconds;
import static com.pocket.rocket.broken.enums.Achievement.Survive60Seconds;
import static com.pocket.rocket.broken.enums.Achievement.TotalCount10000;
import static com.pocket.rocket.broken.enums.Achievement.TotalCount20000;
import static com.pocket.rocket.broken.enums.Achievement.TotalCount40000;
import static com.pocket.rocket.broken.enums.Text.BEST_SCORE;
import static com.pocket.rocket.broken.enums.Text.GALLERY;
import static com.pocket.rocket.broken.enums.Text.MENU;
import static com.pocket.rocket.broken.enums.Text.NEW_ITEM_IN_GALLERY;
import static com.pocket.rocket.broken.enums.Text.RETRY;
import static com.pocket.rocket.broken.enums.Text.SCORE;
import static com.pocket.rocket.broken.enums.Text.TIME;
import static com.pocket.rocket.broken.enums.Text.UNLOCKED;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_HEIGHT;
import static com.pocket.rocket.broken.screens.MenuScreen.MENU_BUTTON_WIDTH;
import static com.pocket.rocket.broken.screens.MenuScreen.X_MENU_BUTTON_POSITION;

public class GameOverScreen extends BaseScreen {

    private boolean canBeClose = false;

    public GameOverScreen(Main main, ScoreActor scoreActor, int time) {
        super(main);
        int score = scoreActor.getScore();
        int bonusCollected = scoreActor.getbonusCollected();
        int bonus2Collected = scoreActor.getbonus2Collected();

        score += time * 3;
        score += bonusCollected * BASIC_BONUS_SCORE;
        score += bonus2Collected * BASIC_BONUS_SCORE * 2;

        saveBonusCount(bonusCollected + bonus2Collected);
        saveScore(score);
        updatePlayTimes();

        playServiceOperation(main, time);
        addActor(buildResult(score, time));

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                canBeClose = true;
            }
        }, 0.5f);
    }

    private void playServiceOperation(Main main, int time) {
        int totalScore = Preference.getTotalScore();
        int score = Preference.getScore();

        PlayServices playServices = main.getPlayServices();
        playServices.submitScore(score);
        playServices.submitTotalScore(totalScore);

        if (score > Get1000OrMoreAtOnce.getData()) {
            playServices.unlockAchievement(Get1000OrMoreAtOnce);
        }

        if (score > Get2000OrMoreAtOnce.getData()) {
            playServices.unlockAchievement(Get2000OrMoreAtOnce);
        }

        if (score > Get3000OrMoreAtOnce.getData()) {
            playServices.unlockAchievement(Get3000OrMoreAtOnce);
        }

        if (totalScore > TotalCount10000.getData()) {
            playServices.unlockAchievement(TotalCount10000);
        }

        if (totalScore > TotalCount20000.getData()) {
            playServices.unlockAchievement(TotalCount20000);
        }

        if (totalScore > TotalCount40000.getData()) {
            playServices.unlockAchievement(TotalCount40000);
        }

        if (time > Survive30Seconds.getData()) {
            playServices.unlockAchievement(Survive30Seconds);
        }

        if (time > Survive60Seconds.getData()) {
            playServices.unlockAchievement(Survive60Seconds);
        }
    }

    private Group buildResult(int score, int time) {
        final Group resultGroup = new Group();

        resultGroup.addActor(buildLogo(getGameOverText(), getGameOverLabel()));

        buildResultData(resultGroup, TIME.get(), time, 700);
        buildResultData(resultGroup, SCORE.get(), score, 580);

        if (anyGalleryItemOpen()) {
            resultGroup.addActor(newGalleryItemLabel());
            Label menuButton = new Label(RETRY.get(), new Label.LabelStyle(getFont()));
            menuButton.setBounds(X_MENU_BUTTON_POSITION, 40, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
            menuButton.setAlignment(Align.center);
            menuButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (canBeClose) {
                        resultGroup.addAction(Actions.alpha(0, 0.25f));
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                main.setScreen(new MainGameScreen(main));
                            }
                        }, 0.25f);
                    }
                }
            });
            resultGroup.addActor(menuButton);

            TextButton retryButton = new TextButton(GALLERY.get(), AssetLoader.getButtonStyle());
            retryButton.setBounds(X_MENU_BUTTON_POSITION, 170, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
            retryButton.addListener(new ClickListener() {
                @Override
                public void clicked(final InputEvent event, final float x, final float y) {
                    if (canBeClose) {
                        resultGroup.addAction(Actions.moveTo(0, -HEIGHT, 0.25f));
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                MenuScreen screen = new MenuScreen(main, false);
                                screen.menuButtons.gallery.setVisible(true);
                                screen.menuButtons.menuGroup.addAction(Actions.moveTo(-WIDTH, 0));
                                main.setScreen(screen);
                            }
                        }, 0.25f);
                    }
                }
            });
            resultGroup.addActor(retryButton);
        } else {
            resultGroup.addActor(bestResultLabel());

            Label menuButton = new Label(MENU.get(), new Label.LabelStyle(getFont()));
            menuButton.setBounds(X_MENU_BUTTON_POSITION, 40, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
            menuButton.setAlignment(Align.center);
            menuButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (canBeClose) {
                        resultGroup.addAction(Actions.moveTo(0, -HEIGHT, 0.25f));
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                main.setScreen(new MenuScreen(main, false));
                            }
                        }, 0.25f);
                    }
                }
            });
            resultGroup.addActor(menuButton);

            TextButton retryButton = new TextButton(RETRY.get(), AssetLoader.getButtonStyle());
            retryButton.setBounds(X_MENU_BUTTON_POSITION, 170, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
            retryButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (canBeClose) {
                        resultGroup.addAction(Actions.alpha(0, 0.25f));
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                main.setScreen(new MainGameScreen(main));
                            }
                        }, 0.25f);
                    }
                }
            });
            resultGroup.addActor(retryButton);
        }

        resultGroup.addAction(Actions.alpha(0, 0));
        resultGroup.addAction(Actions.alpha(1, 0.25f));

        return resultGroup;
    }

    private boolean anyGalleryItemOpen() {
        return (getBonusCount() >= COLLECT_BONUSES && !doubleBonusActivated())
                || (getTotalScore() >= LAMP_OPEN_TOTAL && !lamp2Open())
                || (getPlayTimes() >= PLAY_TIMES && !bonusActivatedHeart())
                || (getScore() >= LAMP_OPEN_MAX && !lamp3Open());
    }

    private Label bestResultLabel() {
        Label label = new Label(BEST_SCORE.get() + "\n" + Preference.getScore(), getFont());
        label.setAlignment(Align.center);
        label.setPosition(X_CENTER - label.getWidth() / 2, 400);
        return label;
    }

    private Group newGalleryItemLabel() {
        Group group = new Group();
        group.setBounds(X_CENTER - WIDTH / 2, 410, WIDTH, 100);

        Label label = new Label(NEW_ITEM_IN_GALLERY.get(), getFont(Color.GOLD));
        label.setAlignment(Align.center);
        label.setPosition(group.getWidth() / 2 - label.getWidth() / 2, 50);
        label.setFontScale(0.7f);
        group.addActor(label);

        Label unlock = new Label(UNLOCKED.get(), getFont(Color.GOLD));
        unlock.setAlignment(Align.center);
        unlock.setPosition(group.getWidth() / 2 - unlock.getWidth() / 2, 0);
        unlock.setFontScale(1.3f);
        group.addActor(unlock);
        group.addAction(pulseAnimation(group.getWidth(), group.getHeight(), 1.10f, 0.7f));

        return group;
    }

    private void buildResultData(Group result, String header, int data, int y) {
        Label headerLabel = new Label(header, getFont());
        headerLabel.setAlignment(Align.center);
        headerLabel.setPosition(X_CENTER - headerLabel.getWidth() / 2, y);
        headerLabel.setFontScale(0.8f);
        result.addActor(headerLabel);

        Label dataLabel = new Label(String.valueOf(data), getFont());
        dataLabel.setAlignment(Align.center);
        dataLabel.setPosition(X_CENTER - dataLabel.getWidth() / 2, y - 40);
        dataLabel.setFontScale(1.3f);
        dataLabel.addAction(Actions.action(ScoreAction.class));
        dataLabel.addAction(scoreAction(data, dataLabel));
        result.addActor(dataLabel);
    }
}
