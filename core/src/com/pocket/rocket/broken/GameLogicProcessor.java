package com.pocket.rocket.broken;

import com.pocket.rocket.broken.actors.LightBulb;
import com.pocket.rocket.broken.enums.LampLogic;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;
import static java.util.Collections.shuffle;


public class GameLogicProcessor {

    private final List<LightBulb> activeLamps = new ArrayList<LightBulb>();
    private final List<LightBulb> allLamps;
    private final LampLogic lampLogic;
    private final List<LightBulb> lockedLamps = new ArrayList<LightBulb>();

    public GameLogicProcessor(List<LightBulb> allLamps, LampLogic lampLogic) {
        this.allLamps = allLamps;
        this.lampLogic = lampLogic;
    }

    public void activateLamp() {
        List<LightBulb> canBeActiveLamps = getCanBeActiveLamps();
        activeLamps.removeAll(canBeActiveLamps);
        if (activeLamps.size() < lampLogic.maxAngryLamps) {
            if (!canBeActiveLamps.isEmpty()) {
                shuffle(canBeActiveLamps);
                LightBulb lamp = canBeActiveLamps.get(random(0, canBeActiveLamps.size() - 1));
                activeLamps.add(lamp);

                lamp.activate(lampLogic);
            }
        }
    }

    private List<LightBulb> getCanBeActiveLamps() {
        List<LightBulb> canBeActive = new ArrayList<LightBulb>();

        for (LightBulb lamp : allLamps) {
            if (lamp.canBeActive()) {
                canBeActive.add(lamp);
            }
        }

        return canBeActive;
    }
}
