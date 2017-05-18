package com.pocket.rocket.broken.enums;


import static com.pocket.rocket.broken.Preference.getLanguage;
import static com.pocket.rocket.broken.screens.menu.Settings.RU_INDEX;
import static com.pocket.rocket.broken.screens.menu.Settings.UA_INDEX;

public enum Text {
    //menu
    MAX("max: ", "очки:", "очки:"),
    TOTAL("  total:  ", "  сума: ", "сумма: "),
    START("START", "ПОЧТАТИ", "НАЧАТЬ"),

    // gallery
    GALLERY("GALLERY", "ГАЛЕРЕЯ", "ГАЛЕРЕЯ"),
    COMING_SOON("coming soon", "скоро", "скоро"),
    GALLERY_MAX_SCORE("max score: ", "найкращий рахунок: ", "наилучший счёт: "),
    GALLERY_MAX_TOTAL("max total: ", "суммарный рахунок: ", "сумарный счёт: "),
    BONUS_ACTIVATED("Bonus activated", "Бонус активований ", "Бонус активирован"),
    TAP_TO_ACTIVATE("Tap for activate", "Натисніть щоб активувати", "Нажмите для активации"),

    // setting
    SETTINGS("SETTINGS", "НАЛАШТУНКИ", "НАСТРОЙКИ"),
    MUSIC("Music", "Звук", "Звук"),
    GOOGLE_PLAY("Google Play", "Google Play", "Google Play"),
    LANGUAGE("Language", "Мова", "Язык"),
    TUTORIAL("Tutorial", "Як грати?", "Обучение"),

    TIME("Time", "Час", "Время"),
    SCORE("Score", "Рахунок", "Счёт"),

    // game over
    BEST_SCORE("BEST SCORE: ", "НАЙКРАЩИЙ: ", "НАИЛУЧШИЙ: "),
    MENU("MENU", "МЕНЮ", "МЕНЮ"),
    RETRY("RETRY", "ЩЕ РАЗ", "ПОВТОРИТЬ"),

    // tutorial
    GOOD_JOB("GOOD JOB!", "Гарна робота", "Отлично!"),
    TRY_TO_CATCH_BONUS("TRY TO CATCH BONUS!", "Спробуй зловити бонус", "Попробуй поймать бонус!"),
    TAP_ON_ANGRY("TAP ON ANGRY!", "НАТИСКАЙТЕ НА ЗЛИХ", "НАЖИМАЙТЕ НА ЗЛЫХ"),
    PLAY_REAL_GAME("PLAY REAL GAME", "ПОЧАТИ ГРУ", "НАЧАТЬ ИГРУ");

    private String en;
    private String ua;
    private String ru;

    Text(String en, String ua, String ru) {
        this.en = en;
        this.ua = ua;
        this.ru = ru;
    }

    public String get() {
        switch (getLanguage()) {
            case RU_INDEX:
                return ru;
            case UA_INDEX:
                return ua;
            default:
                return en;
        }
    }
}
