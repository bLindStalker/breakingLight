package com.breaking.game.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.breaking.game.AssetLoader;
import com.breaking.game.Main;
import com.breaking.game.Preference;
import com.breaking.game.object.ImageActor;

import static com.breaking.game.Constants.HEIGHT;
import static com.breaking.game.Constants.X_CENTER_LAMP_POSITION;

public class ResultScreen extends BaseScreen {

    public ResultScreen(final Main main, int score) {
        super(main);
        Preference.saveScore(score);
        addActor(buildResult(score));
    }

    private Group buildResult(int score) {
        final Group result = new Group();

        result.addActor(new ImageActor(35, 450, 650, 500, AssetLoader.getResult()));

        Label.LabelStyle font = AssetLoader.getFont();
        Label label = new Label("Game Over", font);
        label.setAlignment(Align.center);
        label.setBounds(X_CENTER_LAMP_POSITION - 300, 840, 600, 150);
        label.setFontScale(0.8f, 0.8f);
        result.addActor(label);

        Label total = new Label("Score: " + score, font);
        total.setAlignment(Align.left);
        total.setBounds(X_CENTER_LAMP_POSITION - 300, 800, 100, 50);
        total.setFontScale(0.6f, 0.6f);
        result.addActor(total);

        Label max = new Label("Total: " + Preference.getTotalScore(), font);
        max.setAlignment(Align.right);
        max.setBounds(X_CENTER_LAMP_POSITION + 200, 800, 100, 50);
        max.setFontScale(0.6f, 0.6f);
        result.addActor(max);

        result.addActor(new ImageActor(X_CENTER_LAMP_POSITION - 300, 700, 50, 50, AssetLoader.getStar()));

        Label starsLabel = new Label(": 5", font);
        starsLabel.setAlignment(Align.left);
        starsLabel.setBounds(X_CENTER_LAMP_POSITION - 200, 700, 100, 50);
        starsLabel.setFontScale(0.6f, 0.6f);
        result.addActor(starsLabel);

        TextButton menuButton = new TextButton("Menu", AssetLoader.getButton());
        menuButton.setBounds(X_CENTER_LAMP_POSITION - 300, 500, 200, 75);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                result.addAction(Actions.moveTo(0, -HEIGHT, 0.25f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        main.setScreen(new MenuScreen(main));
                    }
                }, 0.25f);
            }
        });
        result.addActor(menuButton);

        TextButton retryButton = new TextButton("Retry", AssetLoader.getButton());
        retryButton.setBounds(X_CENTER_LAMP_POSITION + 100, 500, 200, 75);
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                result.addAction(Actions.alpha(0, 0.25f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        main.setScreen(new MainGameScreen(main));
                    }
                }, 0.25f);
            }
        });
        result.addActor(retryButton);

        result.addAction(Actions.alpha(0, 0));
        result.addAction(Actions.alpha(1, 0.25f));

        return result;
    }
}
