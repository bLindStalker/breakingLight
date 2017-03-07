package com.breaking.game.enums;

import static com.breaking.game.Constants.LAMPS_SPACE;
import static com.breaking.game.Constants.LIGHT_WIDTH;
import static com.breaking.game.Constants.X_CENTER_LAMP_POSITION;

public enum LightBulbPosition {
    LEFT(X_CENTER_LAMP_POSITION - (LIGHT_WIDTH / 2) - LAMPS_SPACE - LIGHT_WIDTH),
    CENTER(X_CENTER_LAMP_POSITION - (LIGHT_WIDTH / 2)),
    RIGHT(X_CENTER_LAMP_POSITION + (LIGHT_WIDTH / 2) + LAMPS_SPACE);

    private int position;

    LightBulbPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
