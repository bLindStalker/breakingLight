package com.pocket.rocket.broken.actors;

import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.pocket.rocket.broken.enums.LampLogic;
import com.pocket.rocket.broken.enums.LightBulbPosition;
import com.pocket.rocket.broken.enums.LightBulbStatus;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.pocket.rocket.broken.AssetLoader.getLampImage;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_WIDTH;
import static com.pocket.rocket.broken.Utils.pulseAnimation;
import static com.pocket.rocket.broken.enums.LightBulbStatus.ACTIVE;
import static com.pocket.rocket.broken.enums.LightBulbStatus.ANGRY;
import static com.pocket.rocket.broken.enums.LightBulbStatus.NEUTRAL;

public class LightBulb extends ImageActor {
    private static final LightBulbStatus DEFAULT_STATUS = NEUTRAL;

    private LightBulbStatus status = DEFAULT_STATUS;
    private float activationTime = 0;
    private float second = 0;
    private float turnOffTime = 0;
    private Runnable clickAction;
    private RepeatAction action;

    public LightBulb(LightBulbPosition position, int yPosition) {
        super(position.getPosition(), yPosition, LAMP_WIDTH, LAMP_HEIGHT, getLampImage(DEFAULT_STATUS));
    }

    public boolean justClicked(LampLogic time) {

        if (clickAction != null) {
            clickAction.run();
        }

        if (status == ANGRY) {
            setStatus(ACTIVE);
            action = pulseAnimation(getWidth(), getHeight(), 1.025f, 0.3f);
            action.setCount(1);
            addAction(action);
            activationTime = random(time.maxActiveTime, time.minActiveTime);
            turnOffTime = getTurnOffTime(time);

            return true;
        }

        return false;
    }

    @Override
    public void act(float delta) {
        if (activationTime > 0 && (status == ANGRY || status == ACTIVE)) {

            if (activationTime <= second) {
                setStatus(NEUTRAL);
                second = 0;
                activationTime = 0;
            } else {
                second += delta;
            }
        } else {
            activationTime = 0;
        }

        if (turnOffTime > 0 && status == NEUTRAL) {
            turnOffTime -= delta;
        }

        super.act(delta);
    }

    public boolean canBeActive() {
        return status == NEUTRAL && turnOffTime <= 0;
    }

    public void activate(LampLogic time) {
        setStatus(ANGRY);
        activationTime = random(time.minAngryTime, time.maxAngryTime);
        turnOffTime = getTurnOffTime(time);
    }

    private float getTurnOffTime(LampLogic time) {
        return random(time.minNeutralTime, time.maxNeutralTime);
    }

    public void addClickAction(Runnable clickAction) {
        this.clickAction = clickAction;
    }

    public LightBulbStatus getStatus() {
        return status;
    }

    private void setStatus(LightBulbStatus status) {
        this.status = status;
        if (action != null) {
            removeAction(action);
            action = null;
        }
        setImage(getLampImage(status));
    }
}