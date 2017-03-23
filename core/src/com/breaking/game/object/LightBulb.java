package com.breaking.game.object;

import com.breaking.game.enums.LampLogicData;
import com.breaking.game.enums.LightBulbPosition;
import com.breaking.game.enums.LightBulbStatus;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.breaking.game.AssetLoader.getLampImage;
import static com.breaking.game.Constants.LIGHT_HEIGHT;
import static com.breaking.game.Constants.LIGHT_WIDTH;
import static com.breaking.game.enums.LightBulbStatus.BROKEN;
import static com.breaking.game.enums.LightBulbStatus.TURN_OFF;
import static com.breaking.game.enums.LightBulbStatus.TURN_ON;

public class LightBulb extends ImageActor {
    private static final LightBulbStatus DEFAULT_STATUS = TURN_OFF;

    private LightBulbStatus status = DEFAULT_STATUS;
    private float activationTime = 0;
    private float second = 0;
    private float turnOffTime = 0;

    public LightBulb(LightBulbPosition position, int yPosition) {
        super(position.getPosition(), yPosition, LIGHT_WIDTH, LIGHT_HEIGHT, getLampImage(DEFAULT_STATUS));
    }

    public boolean justClicked(LampLogicData time) {
        if (status == TURN_ON) {
            setStatus(BROKEN);
            activationTime = random(time.maxBrokenTime, time.minBrokenTime);
            turnOffTime = getTurnOffTime(time);

            return true;
        }

        return false;
    }

    @Override
    public void act(float delta) {
        if (activationTime > 0 && (status == TURN_ON || status == BROKEN)) {
            //disappear();

            if (activationTime <= second) {
                setStatus(TURN_OFF);

          /*      if (getColor().a < 1) {
                    addAction(Actions.color(new Color(1f, 1f, 1f, 1f), 0.1f));
                }*/

                second = 0;
                activationTime = 0;
            } else {
                second += delta;
            }
        } else {
            activationTime = 0;
        }

        if (turnOffTime > 0 && status == TURN_OFF) {
            turnOffTime -= delta;
        }

        super.act(delta);
    }

  /*  private void disappear() {
        if (status == BROKEN && activationTime - second <= 0.3f && getColor().a >= 1) {
            addAction(Actions.color(new Color(1f, 1f, 1f, 0f), 0.15f));
        }
    }*/

    private void setStatus(LightBulbStatus status) {
        this.status = status;
        setImage(getLampImage(status));
    }

    public boolean canBeActive() {
        return status == TURN_OFF && turnOffTime <= 0;
    }

    public void activate(LampLogicData time) {
        setStatus(TURN_ON);
        activationTime = random(time.minActiveTime, time.maxActiveTime);
        turnOffTime = getTurnOffTime(time);
    }

    private float getTurnOffTime(LampLogicData time) {
        return random(time.minTurnOffTime, time.maxTurnOffTime);
    }
}