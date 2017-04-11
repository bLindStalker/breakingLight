package com.pocket.rocket.games.screens.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pocket.rocket.games.AssetLoader;
import com.pocket.rocket.games.Preference;
import com.pocket.rocket.games.actors.ImageActor;

import java.util.HashMap;
import java.util.Map;

import static com.pocket.rocket.games.AssetLoader.LAMPS_PREFIX_0;
import static com.pocket.rocket.games.AssetLoader.LAMPS_PREFIX_1;
import static com.pocket.rocket.games.AssetLoader.LAMPS_PREFIX_2;
import static com.pocket.rocket.games.AssetLoader.LAMPS_PREFIX_3;
import static com.pocket.rocket.games.AssetLoader.getCheckround;
import static com.pocket.rocket.games.AssetLoader.getFont;
import static com.pocket.rocket.games.AssetLoader.getLampImage;
import static com.pocket.rocket.games.AssetLoader.getPrefix;
import static com.pocket.rocket.games.Constants.HEIGHT;
import static com.pocket.rocket.games.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.games.Constants.LAMP_OPEN_MAX;
import static com.pocket.rocket.games.Constants.LAMP_OPEN_TOTAL;
import static com.pocket.rocket.games.Constants.LAMP_WIDTH;
import static com.pocket.rocket.games.Constants.WIDTH;
import static com.pocket.rocket.games.Preference.getScore;
import static com.pocket.rocket.games.Preference.getTotalScore;
import static com.pocket.rocket.games.screens.MenuScreen.MENU_SWITCH_TIME;

public class Gallery extends Group {
    private static final int GALLERY_HEIGHT = HEIGHT / 2;
    private static final int GALLERY_WIDTH = WIDTH - 100;
    private static final int GALLERY_LAMP_WIDTH = (int) (LAMP_WIDTH * 1.7);
    private static final int GALLERY_LAMP_HEIGHT = (int) (LAMP_HEIGHT * 1.7);

    private static final int X_ROUND_POSITION = GALLERY_WIDTH - 80 - 100;
    private static final int Y_ROUND_POSITION = GALLERY_HEIGHT - 80 - 50;
    private static final int EMPTY = 100;

    private final Map<Integer, Actor> selectedLamp = new HashMap<Integer, Actor>();

    public Gallery() {
        addActor(buildGallery());
    }

    private Group buildGallery() {
        final Table scrollTable = new Table();
        scrollTable.add(buildGalleryElement(EMPTY, "", false));
        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_1, "", true));
        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_2,
                "total score = " + LAMP_OPEN_TOTAL,
                getTotalScore() >= LAMP_OPEN_TOTAL));

        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_3,
                "max score = " + LAMP_OPEN_MAX,
                getScore() >= LAMP_OPEN_MAX));
        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_0, "coming soon", false));
        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_0, "coming soon", false));
        scrollTable.row();
        scrollTable.add(buildGalleryElement(LAMPS_PREFIX_0, "coming soon", false));
        scrollTable.row();

        final ScrollPane scroller = new ScrollPane(scrollTable);

        scroller.setBounds(WIDTH, HEIGHT, WIDTH, HEIGHT + (GALLERY_HEIGHT / 2));
        scroller.layout();
        scroller.setScrollY((getPrefix() - 1) * GALLERY_HEIGHT);
        scroller.updateVisualScroll();

        return scroller;
    }

    private Group buildGalleryElement(final int index, String displayValue, boolean visibility) {
        Group element = new Group();
        element.setBounds(0, 0, GALLERY_WIDTH, GALLERY_HEIGHT);
        element.addActor(new ImageActor(0, 0, GALLERY_WIDTH, GALLERY_HEIGHT, AssetLoader.getGallery()));

        if (index != EMPTY) {
            ImageActor lamp = buildGalleryLamp(index, visibility);

            if (index == LAMPS_PREFIX_0 || !visibility) {
                Label display = new Label(displayValue, getFont(Color.BLACK));
                display.setAlignment(Align.center);
                display.setHeight(35);
                display.setWidth(GALLERY_WIDTH);
                display.setPosition(0, 60);
                display.setFontScale(0.7f, 0.7f);
                element.addActor(display);
            } else {
                lamp.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Actor previousSelect = selectedLamp.get(getPrefix());
                        if (previousSelect != null) {
                            previousSelect.setVisible(false);
                        }

                        selectedLamp.get(index).setVisible(true);
                        AssetLoader.setPrefix(index);
                        Preference.saveLampPrefix(index);

                        addAction(Actions.moveBy(0, HEIGHT, MENU_SWITCH_TIME));
                    }
                });

                element.addActor(new ImageActor(X_ROUND_POSITION, Y_ROUND_POSITION, 80, 80, getCheckround()));
                ImageActor okActor = new ImageActor(X_ROUND_POSITION + 5, Y_ROUND_POSITION + 15, 100, 100, AssetLoader.getCheckok());
                okActor.setVisible(index == getPrefix());

                selectedLamp.put(index, okActor);
                element.addActor(okActor);
            }

            element.addActor(lamp);

        }
        return element;
    }

    private ImageActor buildGalleryLamp(int index, boolean showLamp) {
        return new ImageActor(GALLERY_WIDTH / 2 - GALLERY_LAMP_WIDTH / 2,
                GALLERY_HEIGHT / 2 - GALLERY_LAMP_HEIGHT / 2 + 20,
                GALLERY_LAMP_WIDTH, GALLERY_LAMP_HEIGHT,
                getLampImage(showLamp ? index : LAMPS_PREFIX_0));
    }
}
