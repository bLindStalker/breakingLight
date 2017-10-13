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

import static com.badlogic.gdx.graphics.Color.CYAN;
import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.math.MathUtils.randomBoolean;
import static com.pocket.rocket.broken.AssetLoader.getFreeze;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Utils.MAX_Y_POSITION;
import static com.pocket.rocket.broken.Utils.MIN_Y_POSITION;
import static com.pocket.rocket.broken.Utils.buildMoveActions;
import static com.pocket.rocket.broken.Utils.buildShine;
import static com.pocket.rocket.broken.Utils.showWord;
import static com.pocket.rocket.broken.enums.Text.FREEZE;


public class FreezeBonusActor extends Group {
    private static final int FREEZE_HEIGHT = 90;
    private static final int FREEZE_WIDTH = 90;
    private static final float HEIGHT_SCALE = FREEZE_HEIGHT / 1.95f;
    private static final float WIDTH_SCALE = FREEZE_WIDTH / 1.95f;
    private static final float VELOCITY = 3.7f;

    private final TextureRegion texture;
    private boolean remove = false;

    public FreezeBonusActor(final Runnable setFreeze) {
        float heightSize = FREEZE_HEIGHT + HEIGHT_SCALE;
        float widthBonusSize = FREEZE_WIDTH + WIDTH_SCALE;

        int xPosition = randomBoolean() ? -50 : WIDTH + 50;
        int yPosition = random(MIN_Y_POSITION, MAX_Y_POSITION);

        setBounds(xPosition, yPosition, widthBonusSize, heightSize);
        setOrigin(Align.center);

        final Actor shine = buildShine(CYAN, getWidth(), getHeight());
        addActor(shine);

        final SequenceAction moveAction = Actions.sequence(buildMoveActions(xPosition, VELOCITY));
        addAction(moveAction);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!remove) {
                    showWord(getX() + FREEZE_WIDTH / 2 - 30, getY() + FREEZE_HEIGHT / 2, getStage(), FREEZE.get());
                    setFreeze.run();
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
            }
        });

        texture = new TextureRegion(getFreeze());
        addAction(Actions.forever(Actions.rotateBy(-360, 5f)));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!remove) {
            batch.draw(texture, getX() + WIDTH_SCALE / 2, getY() + HEIGHT_SCALE / 2, FREEZE_WIDTH / 2, FREEZE_HEIGHT / 2, FREEZE_WIDTH,
                    FREEZE_HEIGHT, 1, 1, getRotation());
        }
    }
}