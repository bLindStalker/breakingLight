package com.breaking.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.breaking.game.AssetLoader;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.breaking.game.Constants.HEIGHT;
import static com.breaking.game.Constants.LIGHT_HEIGHT;
import static com.breaking.game.Constants.LIGHT_WIDTH;
import static com.breaking.game.Constants.STAR_SIZE;


public class StarActor extends Actor {
    private static final float STAR_ACTOR_SCALE = STAR_SIZE / 1.7f;
    private final float VELOCITY = random(2.7f, 4.7f);
    private final TextureRegion texture = new TextureRegion(AssetLoader.getStar());
    private final float ACTOR_SIZE = STAR_SIZE + STAR_ACTOR_SCALE;

    public StarActor(int xPosition, int yPosition, final com.breaking.game.actors.userData.ScoreActor scoreActor) {
        setBounds(xPosition + (LIGHT_WIDTH / 2) - ACTOR_SIZE / 2, yPosition + (LIGHT_HEIGHT / 2) - ACTOR_SIZE / 2, ACTOR_SIZE, ACTOR_SIZE);

        setOrigin(Align.center);
        addAction(Actions.moveBy(random(-250, 250), HEIGHT, VELOCITY, Interpolation.sine));
        addAction(Actions.rotateBy(360 * VELOCITY, VELOCITY));
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