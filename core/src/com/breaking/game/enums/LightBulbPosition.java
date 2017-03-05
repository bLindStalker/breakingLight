package com.breaking.game.enums;

import static com.breaking.game.Constants.CENTER_X_LAMP_POSITION;
import static com.breaking.game.Constants.LAMPS_WHITE_SPACE;
import static com.breaking.game.Constants.LIGHT_WIDTH;

public enum LightBulbPosition {
    LEFT(CENTER_X_LAMP_POSITION - (LIGHT_WIDTH / 2) - LAMPS_WHITE_SPACE - LIGHT_WIDTH),
    CENTER(CENTER_X_LAMP_POSITION - (LIGHT_WIDTH / 2)),
    RIGHT(CENTER_X_LAMP_POSITION + (LIGHT_WIDTH / 2) + LAMPS_WHITE_SPACE);

    private int position;

    LightBulbPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
