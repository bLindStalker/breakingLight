package com.breaking.game.object;

import com.breaking.game.enums.LightBulbPosition;
import com.breaking.game.enums.LightBulbStatus;

import static com.breaking.game.AssetLoader.getLampImage;
import static com.breaking.game.Constants.LIGHT_HEIGHT;
import static com.breaking.game.Constants.LIGHT_WIDTH;
import static com.breaking.game.enums.LightBulbStatus.BROKEN;
import static com.breaking.game.enums.LightBulbStatus.TURN_OFF;
import static com.breaking.game.enums.LightBulbStatus.TURN_ON;

public class LightBulb extends GameObject {
    private static final LightBulbStatus DEFAULT_STATUS = TURN_OFF;

    private LightBulbStatus status = DEFAULT_STATUS;

    public LightBulb(LightBulbPosition position, int yPosition) {
        super(position.getPosition(), yPosition, LIGHT_WIDTH, LIGHT_HEIGHT, getLampImage(DEFAULT_STATUS));
    }

    public boolean justClicked() {
        if (status == TURN_ON) {
            status = BROKEN;
            setImage(getLampImage(status));
            return true;
        }
        return false;
    }

    public boolean isNonActive() {
        return status == TURN_OFF;
    }

    public void activate() {
        status = TURN_ON;
        setImage(getLampImage(status));
    }
}