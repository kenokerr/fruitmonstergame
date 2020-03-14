package com.vinta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by rushane on 7/10/17.
 */

public class SettingsScreen extends InputAdapter implements Screen {
    private FruitMonster game;
    private Viewport viewport;
    private Sprite settingsFrag, cedits, mainMenu, fanShop, music, sfx, policy;
    private Vector2 settingsFragPosition, ceditsPosition, mainMenuPosition, fanShopPosition, musicPosition, sfxPosition, policyPosition, touch;
    private boolean settingsFragPressed, ceditsPressed, mainMenuPressed, fanShopPressed, musicPressed, sfxPressed, policyPressed;

    private TextureRegion background;

    public SettingsScreen(FruitMonster game){
        this.game = game;
    }
    @Override
    public void show() {
        viewport = new ScalingViewport(Scaling.fillY, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);

        Texture bgTexture = game.assetManager.get("start/fruitMonsterHomeScreen.png");
        bgTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        background = new TextureRegion(bgTexture);

        settingsFrag = new Sprite(Constants.setTexture("settings/settingsHolder.png", game));
        cedits = new Sprite(Constants.setTexture("settings/credits.png", game));
        mainMenu = new Sprite(Constants.setTexture("settings/mainMenu.png", game));
        fanShop = new Sprite(Constants.setTexture("settings/fanShop.png", game));
        music = new Sprite(Constants.setTexture("buttons/FRUIT-MONSTER-buttons-music.png", game));
        sfx = new Sprite(Constants.setTexture("buttons/sfxButton.png", game));
        policy = new Sprite(Constants.setTexture("settings/policy.png", game));

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        viewport.apply(true);

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(background,0,0);

        settingsFrag.draw(game.spriteBatch);
        cedits.draw(game.spriteBatch);
        mainMenu.draw(game.spriteBatch);
        fanShop.draw(game.spriteBatch);
        music.draw(game.spriteBatch);
        sfx.draw(game.spriteBatch);
        policy.draw(game.spriteBatch);


        game.spriteBatch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            dispose();
            game.setHomeScreen();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);

        settingsFragPosition = new Vector2(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2);
        ceditsPosition = new Vector2(480.39233f,896.5484f);
        mainMenuPosition = new Vector2(486.00003f,360.48715f);
        fanShopPosition = new Vector2(484.1308f,536.0612f);
        musicPosition = new Vector2(304.6846f,1174.8519f);
        sfxPosition = new Vector2(669.18463f,1174.8519f);
        policyPosition = new Vector2(484.1308f,713.50305f);

        settingsFrag.setCenter(settingsFragPosition.x, settingsFragPosition.y);
        cedits.setCenter(ceditsPosition.x, ceditsPosition.y);
        mainMenu.setCenter(mainMenuPosition.x, mainMenuPosition.y);
        fanShop.setCenter(fanShopPosition.x, fanShopPosition.y);
        music.setCenter(musicPosition.x, musicPosition.y);
        sfx.setCenter(sfxPosition.x, sfxPosition.y);
        policy.setCenter(policyPosition.x, policyPosition.y);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {




        return false;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {

        touch = viewport.unproject(new Vector2(screenX, screenY));
        if(cedits.getBoundingRectangle().contains(touch)){
            ceditsPressed = true;
            mainMenuPressed=false; fanShopPressed=false; musicPressed=false; sfxPressed=false; policyPressed=false;
        }else if(sfx.getBoundingRectangle().contains(touch)){
            sfxPressed = true;
            ceditsPressed =false; mainMenuPressed=false; fanShopPressed=false; musicPressed=false; policyPressed=false;
        }else if(music.getBoundingRectangle().contains(touch)){
            musicPressed = true;
            ceditsPressed =false; mainMenuPressed=false; fanShopPressed=false; sfxPressed=false; policyPressed=false;
        }else if(fanShop.getBoundingRectangle().contains(touch)){
            fanShopPressed = true;
            ceditsPressed =false; mainMenuPressed=false; musicPressed=false; sfxPressed=false; policyPressed=false;
        }else if(policy.getBoundingRectangle().contains(touch)){
            policyPressed = true;
            ceditsPressed =false; mainMenuPressed=false; fanShopPressed=false; musicPressed=false; sfxPressed=false;
        }else if(mainMenu.getBoundingRectangle().contains(touch)){
            mainMenuPressed = true;
            ceditsPressed =false; fanShopPressed=false; musicPressed=false; sfxPressed=false; policyPressed=false;
        }
        return false;

    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        Vector2 touch = viewport.unproject(new Vector2(screenX, screenY));
        if(ceditsPressed){
            cedits.setCenter(touch.x, touch.y);
            System.out.println(touch+" credits");
        }else if(sfxPressed){
            sfx.setCenter(touch.x, touch.y);
            System.out.println(touch+" sfx");
        }else if(musicPressed){
            music.setCenter(touch.x, touch.y);
            System.out.println(touch+" music");
        }else if(fanShopPressed){
            fanShop.setCenter(touch.x, touch.y);
            System.out.println(touch+" fanshop");
        }else if(policyPressed){
            policy.setCenter(touch.x, touch.y);
            System.out.println(touch+" policy");
        }else if(mainMenuPressed){
            mainMenu.setCenter(touch.x, touch.y);
            System.out.println(touch+" mainMenu");
        }
        return false;
    }

    @Override
    public void dispose() {

    }


}
