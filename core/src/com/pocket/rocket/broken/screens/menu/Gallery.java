package com.pocket.rocket.broken.screens.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pocket.rocket.broken.AssetLoader;
import com.pocket.rocket.broken.Preference;
import com.pocket.rocket.broken.actors.userData.gallery.CanBeOpenGalleryElement;
import com.pocket.rocket.broken.actors.userData.gallery.GalleryElement;
import com.pocket.rocket.broken.actors.userData.gallery.LockedGalleryElement;
import com.pocket.rocket.broken.actors.userData.gallery.OpenGalleryElement;
import com.pocket.rocket.broken.enums.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pocket.rocket.broken.AssetLoader.LAMPS_PREFIX_1;
import static com.pocket.rocket.broken.AssetLoader.LAMPS_PREFIX_2;
import static com.pocket.rocket.broken.AssetLoader.LAMPS_PREFIX_3;
import static com.pocket.rocket.broken.AssetLoader.getPrefix;
import static com.pocket.rocket.broken.Constants.HEART_BONUS_TOTAL;
import static com.pocket.rocket.broken.Constants.HEIGHT;
import static com.pocket.rocket.broken.Constants.LAMP_OPEN_MAX;
import static com.pocket.rocket.broken.Constants.LAMP_OPEN_TOTAL;
import static com.pocket.rocket.broken.Constants.SUPPER_BONUS_MAX;
import static com.pocket.rocket.broken.Constants.WIDTH;
import static com.pocket.rocket.broken.Preference.bonusActivatedHeart;
import static com.pocket.rocket.broken.Preference.doubleBonusActivated;
import static com.pocket.rocket.broken.Preference.getScore;
import static com.pocket.rocket.broken.Preference.getTotalScore;
import static com.pocket.rocket.broken.Preference.lamp2Open;
import static com.pocket.rocket.broken.Preference.lamp3Open;
import static com.pocket.rocket.broken.enums.Text.ANGRY_LAMP;
import static com.pocket.rocket.broken.enums.Text.GALLERY;
import static com.pocket.rocket.broken.enums.Text.GALLERY_MAX_SCORE;
import static com.pocket.rocket.broken.enums.Text.GALLERY_TOTAL;
import static com.pocket.rocket.broken.enums.Text.HEART_BONUS;
import static com.pocket.rocket.broken.enums.Text.LAMP;
import static com.pocket.rocket.broken.enums.Text.MEGA_LAMP;
import static com.pocket.rocket.broken.enums.Text.SUPER_BONUS;

public class Gallery extends BackScreen {
    public static final int GALLERY_HEIGHT = 490;
    public static final int GALLERY_WIDTH = 575;

    public static final int X_ROUND_POSITION = 130;
    public static final int Y_ROUND_POSITION = 380;

    private final Map<Integer, Actor> selectedLamp = new HashMap<Integer, Actor>();

    public Gallery(Group menuGroup) {
        super(GALLERY.get(), menuGroup);
        ScrollPane scroller = new ScrollPane(buildGallery());
        scroller.setBounds(0, 0, WIDTH, HEIGHT + (GALLERY_HEIGHT / 2));
        scroller.layout();
        scroller.setScrollY(((getPrefix() - 1) * GALLERY_HEIGHT) * 2);
        scroller.updateVisualScroll();

        for (Map.Entry<Integer, Actor> entry : selectedLamp.entrySet()) {
            if (entry.getKey() == getPrefix()) {
                entry.getValue().setVisible(true);
                break;
            }
        }

        addActor(scroller);
        scroller.toBack();
    }

    private Group buildGallery() {
        List<GalleryElement> galleryItems = new ArrayList<GalleryElement>();
        galleryItems.add(new GalleryElement());

        OpenGalleryElement lamp1 = new OpenGalleryElement(LAMP.get(), AssetLoader.getGalleryLampImage(LAMPS_PREFIX_1), lampClickListener(LAMPS_PREFIX_1));
        selectedLamp.put(LAMPS_PREFIX_1, lamp1.getSelect());
        galleryItems.add(lamp1);

        galleryItems.add(buildGalleryElement(SUPER_BONUS.get(), GALLERY_MAX_SCORE.get() + SUPPER_BONUS_MAX, AssetLoader.getGalleryBonus(),
                getScore() >= SUPPER_BONUS_MAX, doubleBonusActivated(), new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Preference.activateDoubleBonus();
                    }
                }));

        GalleryElement lamp2 = buildGalleryElement(MEGA_LAMP.get(), GALLERY_TOTAL.get() + LAMP_OPEN_TOTAL, AssetLoader.getGalleryLampImage(LAMPS_PREFIX_2),
                getTotalScore() >= LAMP_OPEN_TOTAL, lamp2Open(), lampClickListener(LAMPS_PREFIX_2));
        selectedLamp.put(LAMPS_PREFIX_2, lamp2.getSelect());
        galleryItems.add(lamp2);

        galleryItems.add(buildGalleryElement(HEART_BONUS.get(), GALLERY_TOTAL.get() + HEART_BONUS_TOTAL, AssetLoader.getGalleryHeart(),
                getTotalScore() >= HEART_BONUS_TOTAL, bonusActivatedHeart(), new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Preference.setHeartBonus();
                    }
                }));

        GalleryElement lamp3 = buildGalleryElement(ANGRY_LAMP.get(), GALLERY_MAX_SCORE.get() + LAMP_OPEN_MAX, AssetLoader.getGalleryLampImage(LAMPS_PREFIX_3),
                getScore() >= LAMP_OPEN_MAX, lamp3Open(), lampClickListener(LAMPS_PREFIX_3));
        selectedLamp.put(LAMPS_PREFIX_3, lamp3.getSelect());
        galleryItems.add(lamp3);

        galleryItems.add(new LockedGalleryElement(Text.COMING_SOON.get()));
        galleryItems.add(new LockedGalleryElement(Text.COMING_SOON.get()));
        galleryItems.add(new LockedGalleryElement(Text.COMING_SOON.get()));

        final Table scrollTable = new Table();

        for (Group item : galleryItems) {
            scrollTable.add(item);
            scrollTable.row();
        }

        return scrollTable;
    }

    private ClickListener lampClickListener(final int index) {
        return new ClickListener() {
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
        };
    }

    private GalleryElement buildGalleryElement(String desc, String desctToOpen,
                                               Texture texture, boolean canBeOpen, boolean isOpen,
                                               ClickListener clickListener) {
        if (canBeOpen && isOpen) {
            return new OpenGalleryElement(desc, texture, clickListener);
        }

        if (canBeOpen) {
            return new CanBeOpenGalleryElement(desc, texture, clickListener);
        }

        return new LockedGalleryElement(desctToOpen);
    }
}
