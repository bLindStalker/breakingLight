package com.breaking.game.object;

import com.breaking.game.Main;

import static com.breaking.game.Constants.CENTER_X_LAMP_POSITION;
import static com.breaking.game.Constants.LAMPS_WHITE_SPACE;
import static com.breaking.game.Constants.LIGHT_HEIGHT;
import static com.breaking.game.Constants.LIGHT_WIDTH;

public class LightBulb extends GameObject {

    public LightBulb(POSITION position, int yPosition, Main main) {
        super(position.position, yPosition, LIGHT_WIDTH, LIGHT_HEIGHT, main.getBatch(), main.getAssetLoader().getLightBulb());
    }

    public enum POSITION {
        LEFT(CENTER_X_LAMP_POSITION - (LIGHT_WIDTH / 2) - LAMPS_WHITE_SPACE - LIGHT_WIDTH),
        CENTER(CENTER_X_LAMP_POSITION - (LIGHT_WIDTH / 2)),
        RIGHT(CENTER_X_LAMP_POSITION + (LIGHT_WIDTH / 2) + LAMPS_WHITE_SPACE);

        private int position;

        POSITION(int position) {
            this.position = position;
        }
    }
}