package com.pocket.rocket.broken.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Constants;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.SpriteAccessor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class SplashScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private Sprite logo;
    private TweenManager tweenManager;
    private Main main;
    private Tween end;

    public SplashScreen(Main main) {
        this.main = main;
        main.getPlayServices().connect();
        AssetLoader.initialize();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        batch.begin();
        logo.draw(batch);
        batch.end();

        if (end.isFinished()) {
            if (Preference.getLanguage() == -1) {
                main.setScreen(new LanguageScreen(main));
            } else {
                main.setScreen(new MenuScreen(main, true));
            }
        }
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        logo = new Sprite(new Texture("studio_logo.png"));
        logo.setScale((float) width / Constants.WIDTH);
        logo.setPosition(width / 2 - logo.getWidth() / 2, height / 2 - logo.getHeight() / 2);

        Tween.set(logo, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(logo, SpriteAccessor.ALPHA, 1f).target(1).start(tweenManager);
        end = Tween.to(logo, SpriteAccessor.ALPHA, 0.8f).target(0).delay(2).start(tweenManager);
    }

    @Override
    public void hide() {
        batch.dispose();
        logo.getTexture().dispose();
    }
}
