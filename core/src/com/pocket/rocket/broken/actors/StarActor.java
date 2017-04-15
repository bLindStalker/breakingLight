package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_WIDTH;
import static com.pocket.rocket.broken.Constants.STAR_SIZE;


public class StarActor extends Actor {
    private static final float STAR_ACTOR_SCALE = STAR_SIZE / 1.7f;
    private static final float VELOCITY = random(2.7f, 4.7f);
    private final TextureRegion texture = new TextureRegion(com.pocket.rocket.broken.AssetLoader.getStar());

    public StarActor(int xPosition, int yPosition, final com.pocket.rocket.broken.actors.userData.ScoreActor scoreActor) {
        new StarActor(xPosition, yPosition, scoreActor, VELOCITY);
    }

    public StarActor(int xPosition, int yPosition, final com.pocket.rocket.broken.actors.userData.ScoreActor scoreActor, float velocity) {
        float starSize = STAR_SIZE + STAR_ACTOR_SCALE;
        setBounds(xPosition + (LAMP_WIDTH / 2) - starSize / 2, yPosition + (LAMP_HEIGHT / 2) - starSize / 2, starSize, starSize);

        setOrigin(Align.center);
        addAction(Actions.moveBy(random(-250, 250), HEIGHT, velocity, Interpolation.sine));
        addAction(Actions.rotateBy(360 * velocity, velocity));
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                scoreActor.increaseStarScore();
                remove();
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX() + STAR_ACTOR_SCALE / 2, getY() + STAR_ACTOR_SCALE / 2, STAR_SIZE / 2, STAR_SIZE / 2, STAR_SIZE,
                STAR_SIZE, 1, 1, getRotation());
    }
}