package com.breaking.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.breaking.game.AssetLoader;
import com.breaking.game.Main;
import com.breaking.game.Preference;
import com.breaking.game.enums.LampData;
import com.google.gson.Gson;

public class ConfigScreen extends BaseScreen {

    public ConfigScreen(final Main main) {
        super(main);

        final String configs = Preference.getConfigs();

        Group setting = new Group();

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        BitmapFont bitmapFont = AssetLoader.assetManager.get("font.fnt");
        bitmapFont.getData().setScale(0.5f, 0.5f);
        style.font = bitmapFont;
        style.fontColor = Color.FIREBRICK;
        style.cursor = new SpriteDrawable(new Sprite(new Texture(5, 30, Pixmap.Format.RGB565)));
        style.background = new SpriteDrawable(new Sprite(new Texture(5, 30, Pixmap.Format.RGB565)));

        final TextField textField = new TextField(configs, style);
        textField.setBounds(10, 400, 700, 900);
        textField.setAlignment(Align.left);
        textField.setOnscreenKeyboard(new TextField.OnscreenKeyboard() {
            @Override
            public void show(boolean visible) {
                Gdx.input.setOnscreenKeyboardVisible(true);
                Gdx.input.getTextInput(new Input.TextInputListener() {
                    @Override
                    public void input(String text) {
                        textField.setText(text);
                    }

                    @Override
                    public void canceled() {
                    }
                }, "", configs, "");
            }
        });
        setting.addActor(textField);

        TextButton backToMenuButton = new TextButton("Menu", AssetLoader.getButton());
        backToMenuButton.setBounds(150, 100, 150, 100);
        backToMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MenuScreen(main));
            }
        });
        setting.addActor(backToMenuButton);

        TextButton save = new TextButton("Save", AssetLoader.getButton());
        save.setBounds(350, 100, 150, 100);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preference.saveConfigs(textField.getText());
                //main.setScreen(new MenuScreen(main));
            }
        });
        setting.addActor(save);

        TextButton reset = new TextButton("Reset", AssetLoader.getButton());
        reset.setBounds(550, 100, 150, 100);
        reset.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preference.saveConfigs(new Gson().toJson(new LampData()));
                main.setScreen(new MenuScreen(main));
            }
        });
        setting.addActor(reset);

        addActor(setting);
    }
}
