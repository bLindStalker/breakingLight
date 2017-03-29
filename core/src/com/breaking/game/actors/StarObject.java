package com.breaking.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.breaking.game.AssetLoader;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.breaking.game.Constants.HEIGHT;
import static com.breaking.game.Constants.LIGHT_HEIGHT;
import static com.breaking.game.Constants.LIGHT_WIDTH;
import static com.breaking.game.Constants.MAX_CLICK_FOR_STAR;
import static com.breaking.game.Constants.MIN_CLICK_FOR_STAR;
import static com.breaking.game.Constants.STAR_SIZE;

public class StarObject {

    private final Stage stage;
    private int clickToCreate = random(MIN_CLICK_FOR_STAR, MAX_CLICK_FOR_STAR);
    private int clickCount = 0;

    public StarObject(Stage stage) {
        this.stage = stage;
    }

    public void createStar(int xPosition, int yPosition, final ScoreActor scoreActor) {
        if (clickCount == clickToCreate) {
            clickCount = 0;
            clickToCreate = random(MIN_CLICK_FOR_STAR, MAX_CLICK_FOR_STAR);
            stage.addActor(new StarActor(xPosition, yPosition, scoreActor));
        } else {
            clickCount++;
        }
    }

    private class StarActor extends Actor {
        private static final float STAR_ACTOR_SCALE = STAR_SIZE / 1.7f;
        private final float velocity = random(2.8f, 5f);
        private final TextureRegion texture = new TextureRegion(AssetLoader.getStar());
        private final float actorSize = STAR_SIZE + STAR_ACTOR_SCALE;

        private StarActor(int xPosition, int yPosition, final ScoreActor scoreActor) {
            setBounds(xPosition + (LIGHT_WIDTH / 2) - actorSize / 2, yPosition + (LIGHT_HEIGHT / 2) - actorSize / 2, actorSize, actorSize);

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
}
