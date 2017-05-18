package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.actors.userData.HeartData;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.math.MathUtils.randomBoolean;
import static com.pocket.rocket.broken.AssetLoader.getBonusHeart;
import static com.pocket.rocket.broken.Constants.WIDTH;


public class HeartBonusActor extends Actor {
    private static final int HEART_HEIGHT = 70;
    private static final int HEART_WIDTH = 90;
    private static final float HEIGHT_SCALE = HEART_HEIGHT / 1.8f;
    private static final float WIDTH_SCALE = HEART_WIDTH / 1.8f;
    private static final int MAX_Y_POSITION = 800;
    private static final int MIN_Y_POSITION = 150;
    private final TextureRegion texture;
    private float VELOCITY = 0.6f;

    public HeartBonusActor(final HeartData heartData) {
        float heightSize = HEART_HEIGHT + HEIGHT_SCALE;
        float widthBonusSize = HEART_WIDTH + WIDTH_SCALE;

        int y = random(MIN_Y_POSITION, MAX_Y_POSITION);
        int x = randomBoolean() ? -50 : WIDTH + 50;
        setBounds(x, y, widthBonusSize, heightSize);
        setOrigin(Align.center);

        int amountX = (x > 0 ? -WIDTH - 200 : WIDTH + 200) / 5;
        int amountY = generateVector();
        addAction(Actions.sequence(
                Actions.moveBy(amountX, amountY, VELOCITY),
                Actions.moveBy(amountX, -amountY, VELOCITY),
                Actions.moveBy(amountX, amountY, VELOCITY),
                Actions.moveBy(amountX, -amountY, VELOCITY),
                Actions.moveBy(amountX, amountY, VELOCITY)
        ));
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (heartData.getActiveHearts() < 3) {
                    heartData.activate();
                }
                remove();
            }
        });

        texture = new TextureRegion(getBonusHeart());
    }

    private int generateVector() {
        int vector = random(-300, 300);
        return MIN_Y_POSITION + vector < MAX_Y_POSITION || MAX_Y_POSITION + vector > MAX_Y_POSITION
                ? -vector
                : vector;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX() + WIDTH_SCALE / 2, getY() + HEIGHT_SCALE / 2, HEART_WIDTH / 2, HEART_HEIGHT / 2, HEART_WIDTH,
                HEART_HEIGHT, 1, 1, getRotation());
    }
}