package com.fruitmonster.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by rushane on 5/17/17.
 */

public class LoadScreen implements Screen {

    private FruitMonster game;

    private TextureRegion background;
    private Sprite load, loadCont;
    private float logoY = 850;

    float width ;
    float height;

    private Viewport viewport;
    private Vector2 loadpos;
    int count;



    public LoadScreen(FruitMonster game){
        this.game = game;

    }

//    public void loadAnimation(TextureRegion currentFrame1, TextureRegion currentFrame2, TextureRegion currentFrame3,SpriteBatch batch){
//
//        currentFrame1 =   loadSprite.get(0).getKeyFrame(stateTime);
//
//
//        Sprite sprite1 = new Sprite(currentFrame1);
//        sprite1.setCenterX(viewport.getWorldWidth()/2 - loadSep);
//        sprite1.setY(loadY);
//
//
//
//        if(loadSprite.get(0).isAnimationFinished(stateTime)){
//            loadSprite.get(0).setPlayMode(Animation.PlayMode.LOOP);
//            second = true;
//        }
//
//        if(second) {
//            currentFrame2 = loadSprite.get(1).getKeyFrame(stateTime2);
//            Sprite sprite2 = new Sprite(currentFrame2);
//            sprite2.setCenterX(viewport.getWorldWidth() / 2);
//            sprite2.setY(loadY);
//            sprite2.draw(batch);
//
//            if(loadSprite.get(1).isAnimationFinished(stateTime2)){
//                loadSprite.get(1).setPlayMode(Animation.PlayMode.LOOP);
//                third = true;
//            }
//        }
//
//        if(third){
//        currentFrame3 =   loadSprite.get(2).getKeyFrame(stateTime3);
//        Sprite sprite3 = new Sprite(currentFrame3);
//        sprite3.setCenterX(viewport.getWorldWidth()/2 + loadSep);
//        sprite3.setY(loadY);
//        sprite3.draw(batch);
//            if(loadSprite.get(2).isAnimationFinished(stateTime3)){loadSprite.get(2).setPlayMode(Animation.PlayMode.LOOP);
//            }
//        }
//        sprite1.draw(batch);
//    }

    @Override
    public void show() {
        //viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        viewport = new ScalingViewport(Scaling.fillY, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);

        Texture texture = new Texture(Gdx.files.internal("start/fruitMonsterLoadingScreen.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);



        load = new Sprite (new Texture (Gdx.files.internal(("start/fruit-monster-loader.png"))));
        loadCont = new Sprite(new Texture (Gdx.files.internal(("start/fruit-monster-loader-container.png"))));

        background = new TextureRegion(texture);


        width = load.getWidth();
         height = load.getHeight();



        logoY = 850;



    }

    public void spriteCalculator(Vector2 xy, float spriteWidth, float spriteHeight, int sprites, int cols, int rows){
        Vector2 output[] = new Vector2[sprites];
        int colCount = 1;
        int rowCount = 1;
        int index = 0;
        float y;
        float x;

        for (int i = 0; i < rows; i++) {
            y = xy.y+(spriteHeight/2*rowCount);
            for (int j = 0; j < cols; j++) {
                x = xy.x+(spriteWidth/2*colCount);
                output[index++] = new Vector2(x,y);
                colCount++;
            }
            colCount = 1;
            rowCount++;
        }

        for (int i = 0; i < output.length; i++) {
            System.out.println(output[i].toString());
            System.out.println("");
        }



    }

    @Override
    public void render(float delta) {


        game.assetManager.load("start/Second Page Sprite Sheet (HOME).png", Texture.class);
        game.assetManager.load("start/fruitMonsterLoadingScreen.png", Texture.class);
        game.assetManager.load("start/fruitMonsterHomeScreen.png", Texture.class);
        game.assetManager.load("start/fruitMonsterLogo.png", Texture.class);
        game.assetManager.load("start/hi.png", Texture.class);
        game.assetManager.load("start/swipeBelow.png", Texture.class);
        game.assetManager.load("start/fruit-monster-blur-background-settings.png", Texture.class);
        game.assetManager.load("powerUps/heart-life-bundle.png", Texture.class);
        game.assetManager.load("powerUps/ice-hammer.png", Texture.class);
        game.assetManager.load("powerUps/portion-bottle.png", Texture.class);
        game.assetManager.load("powerUps/portion-bottle-sheild.png", Texture.class);
        game.assetManager.load("shop/powerBubble.png", Texture.class);
        game.assetManager.load("shop/lifeAndScore.png", Texture.class);
        game.assetManager.load("shop/powerHolder.png", Texture.class);
        game.assetManager.load("shop/shopHolder.png", Texture.class);
        game.assetManager.load("shop/shopTile.png", Texture.class);
        game.assetManager.load("shop/currency/gems.png", Texture.class);
        game.assetManager.load("shop/currency/star.png", Texture.class);
        game.assetManager.load("settings/credits.png", Texture.class);
        game.assetManager.load("settings/mainMenu.png", Texture.class);
        game.assetManager.load("settings/fanShop.png", Texture.class);
        game.assetManager.load("settings/settingsHolder.png", Texture.class);
        game.assetManager.load("settings/policy.png", Texture.class);
        game.assetManager.load("buttons/FRUIT-MONSTER-buttons-cart.png", Texture.class);
        game.assetManager.load("buttons/FRUIT-MONSTER-buttons-exit.png", Texture.class);
        game.assetManager.load("buttons/FRUIT-MONSTER-buttons-menu.png", Texture.class);
        game.assetManager.load("buttons/FRUIT-MONSTER-buttons-music.png", Texture.class);
        game.assetManager.load("buttons/FRUIT-MONSTER-buttons-pause.png", Texture.class);
        game.assetManager.load("buttons/FRUIT-MONSTER-buttons-play.png", Texture.class);
        game.assetManager.load("buttons/FRUIT-MONSTER-buttons-replay.png", Texture.class);
        game.assetManager.load("buttons/FRUIT-MONSTER-buttons-settings.png", Texture.class);
        game.assetManager.load("buttons/sfxButton.png", Texture.class);
        game.assetManager.load("audio/fruit monster music Hawaiian.wav", Music.class);
        game.assetManager.load("audio/fruit monster Click.wav", Sound.class);

        final float scale = 1.0f/34;

        load.setSize(width*(game.assetManager.getLoadedAssets()*scale), height);
        load.setPosition(loadpos.x, loadpos.y);

        viewport.apply(true);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);





        game.spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(background,0,0);


        loadCont.draw(game.spriteBatch);

        load.draw(game.spriteBatch);
        if (load.getWidth()==width){
             count++;
        }

        if(count==30){
            game.setHomeScreenMusic("audio/fruit monster music Hawaiian.wav");

            game.setClick("audio/fruit monster Click.wav");
            game.setHomeScreen();
        }

        game.assetManager.update();


        game.spriteBatch.end();



    }

    public void load(){

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        load.setX(viewport.getWorldWidth()/2-this.width/2);
        load.setCenterY(viewport.getWorldHeight()/2);
        loadCont.setCenter(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2);
        loadpos = new Vector2(load.getX(), load.getY());
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
