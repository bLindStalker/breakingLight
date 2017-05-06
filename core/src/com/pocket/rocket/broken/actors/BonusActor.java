package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.actors.userData.ScoreActor;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.AssetLoader.getBonus;
import static com.pocket.rocket.broken.Constants.BONUS_SIZE;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_WIDTH;


public class BonusActor extends Actor {
    private static final float bonus_ACTOR_SCALE = BONUS_SIZE / 1.7f;
    private final TextureRegion texture = new TextureRegion(getBonus());

    public BonusActor(int xPosition, int yPosition, final ScoreActor scoreActor, float velocity) {
        float bonusSize = BONUS_SIZE + bonus_ACTOR_SCALE;
        setBounds(xPosition + (LAMP_WIDTH / 2) - bonusSize / 2, yPosition + (LAMP_HEIGHT / 2) - bonusSize / 2, bonusSize, bonusSize);

        setOrigin(Align.center);
        addAction(Actions.moveBy(random(-250, 250), HEIGHT, velocity, Interpolation.sine));
        addAction(Actions.rotateBy(360 * velocity, velocity));
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                scoreActor.increasebonusScore();
                remove();
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX() + bonus_ACTOR_SCALE / 2, getY() + bonus_ACTOR_SCALE / 2, BONUS_SIZE / 2, BONUS_SIZE / 2, BONUS_SIZE,
                BONUS_SIZE, 1, 1, getRotation());
    }
}