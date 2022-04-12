package com.fruitmonster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FruitMonster extends Game {

    public SpriteBatch spriteBatch;//Used to draw images
    public AssetManager assetManager;

    public Music homeScreenMusic;
    public Music gamePlayMusic;

    public Sound click, gameOver;


	@Override
	public void create() {


        assetManager = new AssetManager();

        setLoadScreen();
        spriteBatch = new SpriteBatch();


	}

	public void setLoadScreen(){
        setScreen(new LoadScreen(this));
    }

    public void setHomeScreen(){
        setScreen(new HomeScreen(this));
    }

    public void setGameMenuScreen(){setScreen(new GameMenuScreen(this));}

    public void setGameScreen(){
        setScreen(new MainGame(this));
    }

    public Music getHomeScreenMusic() {
        return homeScreenMusic;
    }

    public void setHomeScreenMusic(String filename) {
        homeScreenMusic = assetManager.get(filename);
    }

    public Music getGamePlayMusic() {
        return gamePlayMusic;
    }

    public void setGamePlayMusic(String filename) {
        gamePlayMusic = assetManager.get(filename);
    }

    public Sound getClick() {
        return click;
    }

    public void setClick(String filename) {
        click = assetManager.get(filename);
    }

    public Sound getGameOver() {
        return gameOver;
    }

    public void setGameOver(String filename) {
        gameOver = assetManager.get(filename);
    }

    public void setShopScreen(){
        setScreen(new ShopScreen(this));
    }

    public void setSettingsScreen(){setScreen(new SettingsScreen(this));}

    public void unload() {
        assetManager.unload("buttons/FRUIT-MONSTER-buttons-cart.png");
        assetManager.unload("buttons/FRUIT-MONSTER-buttons-exit.png");
        assetManager.unload("buttons/FRUIT-MONSTER-buttons-menu.png");
        assetManager.unload("buttons/FRUIT-MONSTER-buttons-music.png");
        assetManager.unload("buttons/FRUIT-MONSTER-buttons-pause.png");
        assetManager.unload("buttons/FRUIT-MONSTER-buttons-play.png");
        assetManager.unload("buttons/FRUIT-MONSTER-buttons-replay.png");
        assetManager.unload("buttons/FRUIT-MONSTER-buttons-settings.png");

        assetManager.unload("gamePlay/fruits/apple.png");
        assetManager.unload("gamePlay/fruits/banana.png");
        assetManager.unload("gamePlay/fruits/halfWatermelon.png");
        assetManager.unload("gamePlay/fruits/pineapple.png");
        assetManager.unload("gamePlay/fruits/watermelon.png");

        assetManager.unload("gamePlay/fruitMonsterOfficialSpriteSheet.png");
        assetManager.unload("gamePlay/fruit sprite.png");
        assetManager.unload("gamePlay/bombSpriteSheet.png");
        assetManager.unload("gamePlay/FRUIT-MONSTER-life-gage-yellow.png");
        assetManager.unload("gamePlay/FRUIT-MONSTER-life-gage-red.png");
        assetManager.unload("gamePlay/lifeAndScore.png");

        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER--BOARD-FLOOR.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-BACK-MOON-.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER--BACK-MOUNTAIN-B.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER--BANANA-LEAVES-D.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-BROWN-TIKI-IN-FRONT-1.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-BROWN-TIKI-IN-FRONT-2.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER--first-background-A.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-FRONT-MOON-SHINE.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER--FRONT-MOUNTAIN-C.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-PURPLE-TIKI-1.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-PURPLE-TIKI-2.png");
        assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-TOP-LEAVES.png");


        assetManager.unload("start/FRUIT-MONSTER--Loading-screen-vinta-logo.png");
        assetManager.unload("start/Second Page Sprite Sheet (HOME).png");
        assetManager.unload("start/fruitMonsterVintaLogoLoadingScreen.png");
        assetManager.unload("start/fruitMonsterHomeScreen.png");
        assetManager.unload("start/fruitMonsterLogo.png");
        assetManager.unload("start/hi.png");
        assetManager.unload("start/swipeBelow.png");

        assetManager.unload("powerUps/heart-life-bundle.png");
        assetManager.unload("powerUps/ice-hammer.png");
        assetManager.unload("powerUps/portion-bottle.png");
        assetManager.unload("powerUps/portion-bottle-sheild.png");

        assetManager.unload("shop/powerBubble.png");
        assetManager.unload("shop/lifeAndScore.png");
        assetManager.unload("shop/powerHolder.png");
        assetManager.unload("shop/shopHolder.png");
        assetManager.unload("shop/shopTile.png");
        assetManager.unload("shop/currency/gems.png");
        assetManager.unload("shop/currency/star.png");

        assetManager.unload("settings/credits.png");
        assetManager.unload("settings/mainMenu.png");
        assetManager.unload("settings/fanShop.png");
        assetManager.unload("settings/settingsHolder.png");


    }
    @Override
    public void dispose(){
      //  unload();
        spriteBatch.dispose();
        assetManager.dispose();
        getScreen().dispose();
    }
}
