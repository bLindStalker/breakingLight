package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.broken.Main;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.actors.userData.ScoreActor;
import com.pocket.rocket.broken.enums.Achievement;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.math.MathUtils.randomBoolean;
import static com.pocket.rocket.broken.AssetLoader.getBonus;
import static com.pocket.rocket.broken.Constants.BONUS_HEIGHT;
import static com.pocket.rocket.broken.Constants.BONUS_WIDTH;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_WIDTH;


public class BonusActor extends Actor {
    private static final float X_BONUS_ACTOR_SCALE = BONUS_WIDTH / 2.2f;
    private static final float Y_BONUS_ACTOR_SCALE = BONUS_HEIGHT / 1.9f;
    private final TextureRegion texture;

    public BonusActor(final Main main, int xPosition, int yPosition, final ScoreActor scoreActor, float velocity) {

        final float xBonusSize = BONUS_WIDTH + X_BONUS_ACTOR_SCALE;
        final float yBonusSize = BONUS_HEIGHT + Y_BONUS_ACTOR_SCALE;
        final boolean isDoubleBonus = Preference.doubleBonusActivated() && randomBoolean();

        setBounds(xPosition + 30 + LAMP_WIDTH / 2 - xBonusSize / 2, yPosition + 100, xBonusSize, yBonusSize);
        setOrigin(Align.center);
        addAction(Actions.moveBy(random(-250, 250), HEIGHT, velocity, Interpolation.sine));
        //addAction(Actions.rotateBy(360 * velocity, velocity));
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                scoreActor.increaseBonusScore(isDoubleBonus);
                main.getPlayServices().unlockIncrementAchievement(Achievement.Catch100Bonuses, 1);
                main.getPlayServices().unlockIncrementAchievement(Achievement.Catch500Bonuses, 1);
                main.getPlayServices().unlockIncrementAchievement(Achievement.Catch1000Bonuses, 1);
                remove();
            }
        });

        texture = new TextureRegion(getBonus(isDoubleBonus));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX() + X_BONUS_ACTOR_SCALE / 2, getY() + Y_BONUS_ACTOR_SCALE / 2, BONUS_WIDTH / 2, BONUS_HEIGHT / 2, BONUS_WIDTH,
                BONUS_HEIGHT, 1, 1, getRotation());
    }
}