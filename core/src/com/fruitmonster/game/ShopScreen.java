package com.fruitmonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by rushane on 7/8/17.
 */

public class ShopScreen extends InputAdapter implements Screen {
    private FruitMonster game;
    private Viewport viewport;

    private TextureRegion background;
    private Sprite life, shopFrag, tile, bubble, powerFrag;
    private Vector2 lifePosition, shopFragPosition,  powerFragPosition;
    private Array<Vector2> tilePosition, bubblePosition;

    public ShopScreen(FruitMonster game){
        this.game = game;
    }

    @Override
    public void show() {
        viewport = new ScalingViewport(Scaling.fillY, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);

        Texture bgTexture = game.assetManager.get("start/fruitMonsterHomeScreen.png");
        bgTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        background = new TextureRegion(bgTexture);

        life = new Sprite(Constants.setTexture("shop/lifeAndScore.png", game));
        shopFrag = new Sprite(Constants.setTexture("shop/shopHolder.png", game));
        tile = new Sprite(Constants.setTexture("shop/shopTile.png", game));
        bubble = new Sprite(Constants.setTexture("shop/powerBubble.png", game));
        powerFrag = new Sprite(Constants.setTexture("shop/powerHolder.png", game));

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

        life.draw(game.spriteBatch);
        shopFrag.draw(game.spriteBatch);
        powerFrag.draw(game.spriteBatch);

        game.spriteBatch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            dispose();
            game.setHomeScreen();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);

        lifePosition = new Vector2(0, viewport.getWorldHeight()-life.getHeight());
        shopFragPosition = new Vector2(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2);
        powerFragPosition = new Vector2(0,0);

        shopFrag.setSize(shopFrag.getWidth()*0.925f, shopFrag.getHeight()*0.925f);

        life.setPosition(lifePosition.x, lifePosition.y);
        shopFrag.setCenter(shopFragPosition.x, shopFragPosition.y);
        powerFrag.setPosition(powerFragPosition.x, powerFragPosition.y);


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
    public void dispose() {

    }
}
