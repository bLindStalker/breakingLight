package com.pocket.rocket.broken.screens.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.actors.ImageActor;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.pocket.rocket.broken.AssetLoader.LAMPS_PREFIX_0;
import static com.pocket.rocket.broken.AssetLoader.LAMPS_PREFIX_1;
import static com.pocket.rocket.broken.AssetLoader.LAMPS_PREFIX_2;
import static com.pocket.rocket.broken.AssetLoader.LAMPS_PREFIX_3;
import static com.pocket.rocket.broken.AssetLoader.getChecked;
import static com.pocket.rocket.broken.AssetLoader.getFont;
import static com.pocket.rocket.broken.AssetLoader.getGallery;
import static com.pocket.rocket.broken.AssetLoader.getGalleryLampImage;
import static com.pocket.rocket.broken.AssetLoader.getPrefix;
import static com.pocket.rocket.broken.Constants.DOUBLE_BONUS_MAX;
import static com.pocket.rocket.broken.Constants.HEART_BONUS_TOTAL;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_OPEN_MAX;
import static com.pocket.rocket.broken.Constants.LAMP_OPEN_TOTAL;
import static com.pocket.rocket.broken.Constants.LAMP_WIDTH;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Preference.bonusHeart;
import static com.pocket.rocket.broken.Preference.doubleBonus;
import static com.pocket.rocket.broken.Preference.getScore;
import static com.pocket.rocket.broken.Preference.getTotalScore;
import static com.pocket.rocket.broken.Preference.lamp2Open;
import static com.pocket.rocket.broken.Preference.lamp3Open;

public class Gallery extends BackScreen {
    private static final int GALLERY_HEIGHT = 490;
    private static final int GALLERY_WIDTH = 575;
    private static final int GALLERY_LAMP_WIDTH = (int) (LAMP_WIDTH * 1);
    private static final int GALLERY_LAMP_HEIGHT = (int) (LAMP_HEIGHT * 1);

    private static final int X_ROUND_POSITION = 130;
    private static final int Y_ROUND_POSITION = 380;

    private final Map<Integer, Actor> selectedLamp = new HashMap<Integer, Actor>();

    public Gallery(Group menuGroup) {
        super("GALLERY", menuGroup);
        ScrollPane scroller = new ScrollPane(buildGallery());
        scroller.setBounds(0, 0, WIDTH, HEIGHT + (GALLERY_HEIGHT / 2));
        scroller.layout();
        scroller.setScrollY((getPrefix() - 1) * GALLERY_HEIGHT);
        scroller.updateVisualScroll();

        addActor(scroller);
        scroller.toBack();
    }

    private Group buildGallery() {
        final Table scrollTable = new Table();
        scrollTable.add(buildGalleryElement(null, "", false, false));
        scrollTable.row();

        scrollTable.add(buildGalleryElement(buildGalleryLampItem(LAMPS_PREFIX_1),
                "", true, true));
        scrollTable.row();

        scrollTable.add(buildGalleryElement(buildGalleryBonusItem(),
                "max score = " + DOUBLE_BONUS_MAX,
                getScore() >= DOUBLE_BONUS_MAX, doubleBonus()));
        scrollTable.row();

        scrollTable.add(buildGalleryElement(buildGalleryLampItem(LAMPS_PREFIX_2),
                "max score = " + LAMP_OPEN_MAX,
                getTotalScore() >= LAMP_OPEN_MAX, lamp2Open()));
        scrollTable.row();

        scrollTable.add(buildGalleryElement(buildGalleryHeartItem(),
                "max total = " + HEART_BONUS_TOTAL,
                getTotalScore() >= HEART_BONUS_TOTAL, bonusHeart()));
        scrollTable.row();

        scrollTable.add(buildGalleryElement(buildGalleryLampItem(LAMPS_PREFIX_3),
                "total score = " + LAMP_OPEN_TOTAL,
                getTotalScore() >= LAMP_OPEN_TOTAL, lamp3Open()));
        scrollTable.row();

        scrollTable.add(buildGalleryElement(buildGalleryItem(getGalleryLampImage(LAMPS_PREFIX_0)),
                "coming soon", false, false));
        scrollTable.row();

        scrollTable.add(buildGalleryElement(buildGalleryItem(getGalleryLampImage(LAMPS_PREFIX_0)),
                "coming soon", false, false));
        scrollTable.row();

        scrollTable.add(buildGalleryElement(buildGalleryItem(getGalleryLampImage(LAMPS_PREFIX_0)),
                "coming soon", false, false));
        scrollTable.row();

        return scrollTable;
    }

    private Group buildGalleryItem(Texture texture) {
        Group group = new Group();
        group.addActor(new ImageActor(GALLERY_WIDTH / 2 - texture.getWidth() / 2,
                GALLERY_HEIGHT / 2 - texture.getHeight() / 2 + 20,
                texture.getWidth(), texture.getHeight(),
                texture));
        return group;
    }

    private Group buildGalleryElement(final Group item, String description, boolean canBeOpen, boolean isOpen) {
        Group elementGroup = new Group();
        elementGroup.setBounds(0, 0, GALLERY_WIDTH, GALLERY_HEIGHT);
        elementGroup.addActor(new ImageActor(0, 0, GALLERY_WIDTH, GALLERY_HEIGHT, getGallery()));

        if (item == null) {
            return elementGroup;
        }

        elementGroup.addActor(item);
        if (!(canBeOpen && isOpen)) {

            item.addAction(alpha(0));
            item.setVisible(false);

            final Label display = new Label(canBeOpen ? "Tap to open" : description, getFont(Color.BLACK));
            display.setAlignment(Align.center);
            display.setHeight(35);
            display.setWidth(GALLERY_WIDTH);
            display.setPosition(0, 60);
            display.setFontScale(0.7f, 0.7f);
            elementGroup.addActor(display);

            final Group disabledItem = buildGalleryItem(AssetLoader.getGalleryLampImage(LAMPS_PREFIX_0));
            elementGroup.addActor(disabledItem);
            if (canBeOpen) {
                disabledItem.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        item.setVisible(true);
                        disabledItem.addAction(fadeOut(0.5f));
                        display.addAction(fadeOut(0.5f));
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                item.addAction(fadeIn(0.7f));
                                display.remove();
                                disabledItem.remove();
                            }
                        }, 0.5f);
                    }
                });
            }
        }
        return elementGroup;
    }

    private Group buildGalleryLampItem(final int index) {
        Group group = buildGalleryItem(AssetLoader.getGalleryLampImage(index));
        group.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actor previousSelect = selectedLamp.get(getPrefix());
                if (previousSelect != null) {
                    previousSelect.setVisible(false);
                }

                selectedLamp.get(index).setVisible(true);
                AssetLoader.setPrefix(index);
                Preference.saveLampPrefix(index);
                if (index == LAMPS_PREFIX_2) {
                    Preference.setLamp2Open();
                }

                if (index == LAMPS_PREFIX_3) {
                    Preference.setLamp3Open();
                }
            }
        });

        ImageActor okActor = new ImageActor(X_ROUND_POSITION, Y_ROUND_POSITION, 45, 45, getChecked());
        okActor.setVisible(index == getPrefix());

        selectedLamp.put(index, okActor);
        group.addActor(okActor);

        return group;
    }

    private Group buildGalleryBonusItem() {
        Group group = buildGalleryItem(AssetLoader.getBonus(true));
        final ImageActor okActor = new ImageActor(X_ROUND_POSITION, Y_ROUND_POSITION, 45, 45, getChecked());
        okActor.setVisible(false);
        group.addActor(okActor);

        group.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preference.setDoubleBonus();
                okActor.setVisible(true);
            }
        });

        return group;
    }

    private Group buildGalleryHeartItem() {
        Group group = buildGalleryItem(AssetLoader.getBonusHeart());
        final ImageActor okActor = new ImageActor(X_ROUND_POSITION, Y_ROUND_POSITION, 45, 45, getChecked());
        okActor.setVisible(false);
        group.addActor(okActor);

        group.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preference.setHeartBonus();
                okActor.setVisible(true);
            }
        });

        return group;
    }
}
