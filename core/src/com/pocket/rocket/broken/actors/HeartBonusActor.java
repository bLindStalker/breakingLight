package com.pocket.rocket.broken.actors;

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

import static com.badlogic.gdx.graphics.Color.VIOLET;
import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.math.MathUtils.randomBoolean;
import static com.pocket.rocket.broken.AssetLoader.getBonusHeart;
import static com.pocket.rocket.broken.Constants.FREEZE_POWER;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Utils.MAX_Y_POSITION;
import static com.pocket.rocket.broken.Utils.MIN_Y_POSITION;
import static com.pocket.rocket.broken.Utils.buildMoveActions;
import static com.pocket.rocket.broken.Utils.buildShine;


public class HeartBonusActor extends Group {
    private static final int HEART_HEIGHT = 75;
    private static final int HEART_WIDTH = 95;
    private static final float HEIGHT_SCALE = HEART_HEIGHT / 1.95f;
    private static final float WIDTH_SCALE = HEART_WIDTH / 1.95f;
    private static final float BASE_VELOCITY = 3.8f;

    private final TextureRegion texture;
    private boolean remove = false;

    public HeartBonusActor(final HeartData heartData, boolean nowFreeze) {
        float heightSize = HEART_HEIGHT + HEIGHT_SCALE;
        float widthBonusSize = HEART_WIDTH + WIDTH_SCALE;

        int yPosition = random(MIN_Y_POSITION, MAX_Y_POSITION);
        int xPosition = randomBoolean() ? -50 : WIDTH + 50;

        setBounds(xPosition, yPosition, widthBonusSize, heightSize);
        setOrigin(Align.center);

        final Actor shine = buildShine(VIOLET, getWidth(), getHeight());
        addActor(shine);

        float velocity = nowFreeze ? BASE_VELOCITY * FREEZE_POWER : BASE_VELOCITY;
        final SequenceAction moveAction = Actions.sequence(buildMoveActions(xPosition, velocity));
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!remove) {
            batch.draw(texture, getX() + WIDTH_SCALE / 2, getY() + HEIGHT_SCALE / 2, HEART_WIDTH / 2, HEART_HEIGHT / 2, HEART_WIDTH,
                    HEART_HEIGHT, 1, 1, getRotation());
        }
    }
}