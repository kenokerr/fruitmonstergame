package com.fruitmonster.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by rushane on 5/16/17.
 */

public class Constants {

    public static final int ITEM_COUNT = 20;
    public static final String  Atari_FONT="fonts/atari full.ttf";

    public static final float WORLD_WIDTH = 972;
    public static final float WORLD_HEIGHT = 1526;
    public static final float GAME_PLAY_WORLD_HEIGHT = 1620;
    public static final float SPAWNS_PER_SECOND = 2.0F;

    public static final float APPLE_WEIGHT = 2.0f;
    public static final float BANANA_WEIGHT = 1.8f;
    public static final float GRAPE_WEIGHT = 1.4f;
    public static final float ORANGE_WEIGHT = 2.4f;
    public static final float WATERMELON_WEIGHT = 5.0f;
    public static final float HALF_WATERMELON_WEIGHT = 2.5f;
    public static final float BOMB_WEIGHT = 5.0f;
    public static final float PINEAPPLE_WEIGHT = 4;


    public static final int PINEAPPLE_SCORE = 5;

    public static final int APPLE_SCORE = 4;
    public static final int BANANA_SCORE = 3;
    public static final int GRAPE_SCORE = 2;
    public static final int ORANGE_SCORE = 5;
    public static final int WATERMELON_SCORE = 6;
    public static final int HALF_WATERMELON_SCORE = 3;

    public static final float MENU_CLICK_VOLUME = 0.6f;
    public static final float GAME_PLAY_MUSIC_VOLUME = 0.6f;
    public static final float HOME_SCREEN_MUSIC_VOLUME = 0.6f;
    public static final float GAME_OVER_VOLUME = 0.6F;

    public static final Animation<TextureRegion> setAnimation(int cols, int rows, TextureRegion region, float duration){
        Animation<TextureRegion> animation;
        TextureRegion[][] tmp = region.split(region.getRegionWidth()/cols, region.getRegionHeight()/rows);
        TextureRegion[] frames = new TextureRegion[cols*rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {for (int j = 0; j < cols; j++) {frames[index++] = tmp[i][j];}}
        animation = new Animation<TextureRegion>(duration, frames);
        return animation;
    }

    public static final TextureRegion setTexture(String fileName, FruitMonster game){
        Texture texture = game.assetManager.get(fileName);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegion region = new TextureRegion(texture);
        return region;
    }


}