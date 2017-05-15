package com.pocket.rocket.broken.actors.userData;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Queue;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.actors.HeartActor;

import static com.pocket.rocket.broken.Constants.WIDTH;

public class HeartData extends Group {

    private Queue<HeartActor> hearts = new Queue<HeartActor>();

    public HeartData() {
        Texture heard = AssetLoader.getHeart();

        int width = heard.getWidth();
        int height = heard.getHeight();
        int groupWidth = width * 3 + 80;
        setBounds((WIDTH - 180) / 2 - groupWidth / 2, 0, groupWidth, height);

        HeartActor heart1 = new HeartActor(0, 0, width, height, heard);
        hearts.addLast(heart1);
        addActor(heart1);

        HeartActor heart2 = new HeartActor(groupWidth / 2 - width / 2, 0, width, height, heard);
        hearts.addLast(heart2);
        addActor(heart2);

        HeartActor heart3 = new HeartActor(groupWidth - width, 0, width, height, heard);
        hearts.addLast(heart3);
        addActor(heart3);
    }

    public int getActiveHearts() {
        int count = 0;
        for (HeartActor heart : hearts) {
            if (heart.isVisible()) {
                count++;
            }
        }
        return count;
    }

    public void removeHeart() {
        for (HeartActor heart : hearts) {
            if (heart.isVisible()) {
                heart.removeHeart();
                return;
            }
        }
    }

    public void activate() {
        for (HeartActor heart : hearts) {
            if (!heart.isVisible()) {
                heart.setVisible(true);
                heart.addAction(Actions.fadeIn(0.4f));
                break;
            }
        }
    }
}
