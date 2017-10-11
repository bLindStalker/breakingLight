package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.actors.userData.HeartData;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.math.MathUtils.randomBoolean;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.pocket.rocket.broken.AssetLoader.getBonusHeart;
import static com.pocket.rocket.broken.AssetLoader.getGalleryLight;
import static com.pocket.rocket.broken.Constants.WIDTH;


public class HeartBonusActor extends Group {
    private static final int HEART_HEIGHT = 75;
    private static final int HEART_WIDTH = 95;
    private static final float HEIGHT_SCALE = HEART_HEIGHT / 1.95f;
    private static final float WIDTH_SCALE = HEART_WIDTH / 1.95f;
    private static final int MAX_Y_POSITION = 800;
    private static final int MIN_Y_POSITION = 150;
    private static final float VELOCITY = 0.78f;

    private final TextureRegion texture;
    private boolean remove = false;

    public HeartBonusActor(final HeartData heartData) {
        float heightSize = HEART_HEIGHT + HEIGHT_SCALE;
        float widthBonusSize = HEART_WIDTH + WIDTH_SCALE;

        int y = random(MIN_Y_POSITION, MAX_Y_POSITION);
        int x = randomBoolean() ? -50 : WIDTH + 50;
        setBounds(x, y, widthBonusSize, heightSize);
        setOrigin(Align.center);

        int amountX = (x > 0 ? -WIDTH - 200 : WIDTH + 200) / 5;
        int amountY = generateVector();

        final ImageActor shine = new ImageActor(getWidth() / 2 - 100, getHeight() / 2 - 100, 200, 200, getGalleryLight());
        shine.setOrigin(Align.center);
        shine.addAction(Actions.forever(Actions.rotateBy(360, 2f)));
        shine.setColor(Color.PINK);
        addActor(shine);

        final SequenceAction moveAction = Actions.sequence(
                Actions.moveBy(amountX, amountY, VELOCITY),
                Actions.moveBy(amountX, -amountY, VELOCITY),
                Actions.moveBy(amountX, amountY, VELOCITY),
                Actions.moveBy(amountX, -amountY, VELOCITY),
                Actions.moveBy(amountX, amountY, VELOCITY)
        );
        addAction(moveAction);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (heartData.getActiveHearts() < 3) {
                    heartData.activate();
                }
                remove = true;
                shine.addAction(Actions.fadeOut(0.5f));
                removeAction(moveAction);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        remove();
                    }
                }, 0.7f);
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
        super.draw(batch, parentAlpha);
        if (!remove) {
            batch.draw(texture, getX() + WIDTH_SCALE / 2, getY() + HEIGHT_SCALE / 2, HEART_WIDTH / 2, HEART_HEIGHT / 2, HEART_WIDTH,
                    HEART_HEIGHT, 1, 1, getRotation());
        }
    }
}