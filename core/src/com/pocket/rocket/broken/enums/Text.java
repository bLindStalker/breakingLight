package com.pocket.rocket.broken.enums;


import static com.pocket.rocket.broken.Preference.getLanguage;
import static com.pocket.rocket.broken.screens.menu.Settings.RU_INDEX;
import static com.pocket.rocket.broken.screens.menu.Settings.UA_INDEX;

public enum Text {
    //menu
    MAX("max: ", "бали: ", "балы: "),
    TOTAL("total: ", "сума: ", "сумма: "),
    START("START", "ПОЧАТИ", "НАЧАТЬ"),

    // gallery
    GALLERY("GALLERY", "ГАЛЕРЕЯ", "ГАЛЕРЕЯ"),
    COMING_SOON("wait for update", "чекати оновлення", "ждать обновления"),
    GALLERY_MAX_SCORE("max score = ", "найкращий рахунок = ", "наилучший счёт = "),
    GALLERY_TOTAL("total score = ", "сумарний рахунок = ", "сумарный счёт = "),
    TAP_TO_OPEN("TAP TO OPEN!", "НАТИСНIТЬ ЩОБ ВIДКРИТИ!", "НАЖМИТЕ ЧТОБЫ ОТКРЫТЬ!"),
    UNLOCKED("UNLOCKED!", "ВIДКРИТО!", "ОТКРЫТО!"),
    LAMP("Incandescent lamp", "Лампа розжарювання", "Лампа накаливания"),
    MEGA_LAMP("MEGA lamp", "Мега лампа", "Мега лампа"),
    ANGRY_LAMP("Cool lamp", "Крута лампа", "Крута лампа"),
    SUPER_BONUS("Super BONUS", "Супер БОНУС", "Супер БОНУС"),
    SUPER_BONUS_DESC("collect %s bonuses", "спiймати %s бонусiв", "поймайть %s бонусов"),
    HEART_BONUS("Heart BONUS", "+ ЖИТТЯ", "+ ЖИЗНЬ"),
    HEART_BONUS_DESC("play game and have fun", "грати в гру та насолоджуватись", "играть в игру и наслаждаться"),
    TEXT_TO_UNLOCK("to unlock you need", "щоб вiдкрити треба", "чтобы открыть нужно"),

    // setting
    SETTINGS("SETTINGS", "НАЛАШТУНКИ", "НАСТРОЙКИ"),
    MUSIC("Music", "Звук", "Звук"),
    GOOGLE_PLAY("Google Play", "Google Play", "Google Play"),
    LANGUAGE("Language", "Мова", "Язык"),
    TUTORIAL("Tutorial", "Як грати?", "Обучение"),

    TIME("Time", "Час", "Время"),
    SCORE("Score", "Рахунок", "Счёт"),

    // game over
    BEST_SCORE("BEST SCORE", "НАЙКРАЩИЙ РАXУНОК", "ЛУЧШИЙ СЧЁТ"),
    MENU("MENU", "МЕНЮ", "МЕНЮ"),
    RETRY("RETRY", "ЩЕ РАЗ", "ПОВТОРИТЬ"),
    NEW_ITEM_IN_GALLERY("NEW ITEM IN GALLERY", "НОВИЙ БОНУС В ГАЛЕРЕЇ", "НОВЫЙ БОНУС В ГАЛЕРЕЕ"),

    // tutorial
    GOOD_JOB("GOOD JOB!", "Гарна робота!", "Отлично!"),
    TRY_TO_CATCH_BONUS("TRY TO CATCH BONUS!", "СПIЙМАЙ БОНУС!", "ПОЙМАЙ БОНУС!"),
    TAP_ON_ANGRY("TAP ON ANGRY!", "НАТИСКАЙ НА ЗЛИX!", "НАЖИМАЙ НА ЗЛЫX!"),
    PLAY_REAL_GAME("PLAY REAL GAME", "ПОЧАТИ ГРУ", "НАЧАТЬ ИГРУ"),

    WORDS("WOW!, Super!, Amazing!, Well done!, Very well!", "WOW!, Супер, Круто, Так тримати", "WOW!, Супер, Круто, Так держать"),
    MISSING_WORDS("Miss, Bad, Shit, Blunder, Boner, Mistake", "Промах, Погано, Дуже погано, Помилка", "Промах, Плохо, Очень плохо, Ошибка");

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