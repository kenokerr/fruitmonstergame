package com.fruitmonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by rushane on 5/23/17.
 */

public class HomeScreen extends InputAdapter implements Screen {

    private boolean Resize_Once;
    private FruitMonster game;
    private Viewport viewport;
    private final int bubbleDisplayLimit = 5;
    private final int tileDisplayLimit = 6;


    private int firstBubble, firstTile;

    float tileSwipe, bubbleSwipe;

    private Sprite logo, background, backgroundBlur;
    private Sprite settings, play, shop, close;
    private Sprite settingsFrag, cedits, mainMenu, fanShop, music, sfx, policy;
    private Sprite life, shopFrag, powerFrag;
    private Array<Sprite> tiles, bubbles;

    private Vector2 settingsFragPosition, ceditsPosition, mainMenuPosition, fanShopPosition, musicPosition, sfxPosition, policyPosition;
    private boolean creditsPressed, mainMenuPressed, fanShopPressed, musicPressed, sfxPressed, policyPressed;
    private boolean settingsPressed, playPressed, shopPressed, settingsPressed2, shopPressed2, close2;

    private Vector2 lifePosition, shopFragPosition,  powerFragPosition;

    private TextureRegion monsterWave;
    private TextureRegion monsterBlink;
    private TextureRegion message;

    private Animation<TextureRegion> monsterWaveAnimation;
    private Animation<TextureRegion> monsterBlinkAnimation;
    private Animation<TextureRegion> messageAnimation;

    private float monsterBlinkDur = 0.100f;
    private float monsterWaveDur = 0.14556789f;

    private float monsterY, logoY;
    private float stateTime;
    private float globalAlpha;

    private boolean blink;
    private Vector2  touch;

    private Vector2 settingsPosition, playPosition, shopPosition;
    private float h;
    private float w;

    private FreeTypeFontGenerator scroll;
    private FreeTypeFontGenerator.FreeTypeFontParameter s;
    private BitmapFont bScroll, tScroll;


    public HomeScreen (FruitMonster game){
        this.game = game;
    }

    public void monsterWaveAnimation(TextureRegion currentFrame, SpriteBatch batch){
        currentFrame = monsterWaveAnimation.getKeyFrame(stateTime);
        Sprite sprite = new Sprite(currentFrame);
        sprite.setCenterX(viewport.getWorldWidth()/2);
        sprite.setY(monsterY);
        if(globalAlpha<1||globalAlpha==1) {
            sprite.setAlpha(globalAlpha);
        }
        sprite.draw(batch);
        if(monsterWaveAnimation.isAnimationFinished(stateTime)){
            blink = true;
            stateTime = 0;
        }
    }

    public void monsterBlinkAnimation(TextureRegion currentFrame, SpriteBatch batch){
        currentFrame = monsterBlinkAnimation.getKeyFrame(stateTime);
        Sprite sprite = new Sprite(currentFrame);
        sprite.setCenterX(viewport.getWorldWidth()/2);
        sprite.setY(monsterY);

        sprite.draw(batch);
        if(monsterBlinkAnimation.isAnimationFinished(stateTime)){
            blink = false;
            stateTime = 0;
        }
    }

//    public void slideAnimation(TextureRegion currentFrame, SpriteBatch batch){
//        slideAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
//        currentFrame = slideAnimation.getKeyFrame(slideStatetime);
//        Sprite sprite = new Sprite(currentFrame);
//        sprite.setCenterX(slideX);
//        sprite.setY(slideY);
//
//        sprite.draw(batch);
//
//    }

    @Override
    public void show() {
        globalAlpha = 0;
        viewport = new ScalingViewport(Scaling.fillY, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);

        backgroundBlur = new Sprite(setTexture("start/fruit-monster-blur-background-settings.png", game));
        backgroundBlur.setAlpha(0.5f);

        Texture bgTexture = game.assetManager.get("start/fruitMonsterHomeScreen.png");
        bgTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        background = new Sprite(new TextureRegion(bgTexture));

        Texture logoTexture = game.assetManager.get("start/fruitMonsterLogo.png");
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logo = new Sprite(logoTexture);

        Texture monsterSpritesheet = game.assetManager.get("start/Second Page Sprite Sheet (HOME).png");
        monsterSpritesheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        monsterWave = new TextureRegion(monsterSpritesheet, 0,0, 3000, 600);
        monsterBlink = new TextureRegion(monsterSpritesheet, 0, 600, 3000, 1200);

        shop = new Sprite(setTexture("buttons/FRUIT-MONSTER-buttons-cart.png", game));
        play = new Sprite(setTexture("buttons/FRUIT-MONSTER-buttons-play.png", game));
        settings = new Sprite(setTexture("buttons/FRUIT-MONSTER-buttons-settings.png", game));

        settingsFrag = new Sprite(Constants.setTexture("settings/settingsHolder.png", game));
        cedits = new Sprite(Constants.setTexture("settings/credits.png", game));
        mainMenu = new Sprite(Constants.setTexture("settings/mainMenu.png", game));
        fanShop = new Sprite(Constants.setTexture("settings/fanShop.png", game));
        music = new Sprite(Constants.setTexture("buttons/FRUIT-MONSTER-buttons-music.png", game));
        sfx = new Sprite(Constants.setTexture("buttons/sfxButton.png", game));
        policy = new Sprite(Constants.setTexture("settings/policy.png", game));

        life = new Sprite(Constants.setTexture("shop/lifeAndScore.png", game));
        shopFrag = new Sprite(Constants.setTexture("shop/shopHolder.png", game));
        powerFrag = new Sprite(Constants.setTexture("shop/powerHolder.png", game));

        close = new Sprite(Constants.setTexture("buttons/FRUIT-MONSTER-buttons-exit.png", game));

        monsterWaveAnimation = Constants.setAnimation(5,1,monsterWave,monsterWaveDur);
        monsterBlinkAnimation = Constants.setAnimation(5,2,monsterBlink,monsterBlinkDur);

        tiles = new Array<Sprite>(true, Constants.ITEM_COUNT);
        bubbles = new Array<Sprite>(true, Constants.ITEM_COUNT);        

        for (int i = 0; i < Constants.ITEM_COUNT; i++) {
           Sprite tile = new Sprite(Constants.setTexture("shop/shopTile.png", game));
           Sprite bubble = new Sprite(Constants.setTexture("shop/powerBubble.png", game));
          
           tiles.add(tile);
           bubbles.add(bubble);

        }

//        slideAnimation = Constants.setAnimation(3,1, slide, slideDur);

        stateTime = 0f;

        monsterY = 430;
        logoY = 850;

        w = play.getWidth();
        h = play.getHeight();
        firstBubble = 0;
        firstTile = 0;

        scroll = new FreeTypeFontGenerator(Gdx.files.internal(Constants.Atari_FONT));

        s = new FreeTypeFontGenerator.FreeTypeFontParameter();
        s.size = 30;
        s.magFilter = Texture.TextureFilter.Linear;
        s.minFilter = Texture.TextureFilter.Linear;
        bScroll = new BitmapFont();
        bScroll = scroll.generateFont(s);

        tScroll = new BitmapFont();
        tScroll = scroll.generateFont(s);

        tScroll.setColor(Color.WHITE);
        bScroll.setColor(Color.WHITE);

        Gdx.input.setInputProcessor(this);

    }

    public  TextureRegion setTexture(String fileName, FruitMonster game){
        Texture texture = game.assetManager.get(fileName);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegion region = new TextureRegion(texture);
        return region;
    }

    @Override
    public void render(float delta) {

        if(!game.getHomeScreenMusic().isPlaying())
        {
            game.getHomeScreenMusic().setVolume(Constants.HOME_SCREEN_MUSIC_VOLUME);
            game.getHomeScreenMusic().setLooping(true);
            game.getHomeScreenMusic().play();
        }

        if(globalAlpha<1||globalAlpha==1) {
            settings.setAlpha(globalAlpha);
            play.setAlpha(globalAlpha);
            shop.setAlpha(globalAlpha);
            logo.setAlpha(globalAlpha);
            background.setAlpha(globalAlpha);
        }

        stateTime+=delta;
        logo.setY(logoY);
        logo.setCenterX(viewport.getWorldWidth()/2);

        viewport.apply(true);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        game.spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        game.spriteBatch.begin();
        background.draw(game.spriteBatch);
        logo.draw(game.spriteBatch);
        TextureRegion currentframe = null;

        monsterWaveAnimation(currentframe, game.spriteBatch);

        play.draw(game.spriteBatch);
        shop.draw(game.spriteBatch);
        settings.draw(game.spriteBatch);

        if (shopPressed2){

            backgroundBlur.draw(game.spriteBatch);
            life.draw(game.spriteBatch);
            shopFrag.draw(game.spriteBatch);
            powerFrag.draw(game.spriteBatch);

            close.setCenter(shopFrag.getX()+shopFrag.getWidth()/2, shopFrag.getY()+shopFrag.getHeight());
            close.draw(game.spriteBatch);
            final float x = viewport.getWorldWidth()/2;
            final float y = 1100;
            final float o = 135f;

            final float xx = 110;
            final float yy = 95;
            final float oo = 183f;

            int bcount = 0;
            for (int i = firstBubble; i < (firstBubble+bubbleDisplayLimit); i++){
                bubbles.get(i).setCenter(xx+(bcount*oo),yy);
                bubbles.get(i).draw(game.spriteBatch);
                bScroll.draw(game.spriteBatch,Integer.toString(i), xx+(bcount*oo),yy);
                bcount++;
            }




            int tcount = 0;
            for (int i = firstTile; i < (firstTile+tileDisplayLimit); i++){
                tiles.get(i).setCenter(x,y-(tcount*o));
                tiles.get(i).draw(game.spriteBatch);
                tScroll.draw(game.spriteBatch,Integer.toString(i), x,y-(tcount*o));
                tcount++;
            }
        }

        if(settingsPressed2){
            backgroundBlur.draw(game.spriteBatch);

            settingsFrag.draw(game.spriteBatch);
            cedits.draw(game.spriteBatch);
            mainMenu.draw(game.spriteBatch);
            fanShop.draw(game.spriteBatch);
            music.draw(game.spriteBatch);
            sfx.draw(game.spriteBatch);
            policy.draw(game.spriteBatch);

            close.setCenter(settingsFrag.getX()+settingsFrag.getWidth()/2, settingsFrag.getY()+settingsFrag.getHeight());
            close.draw(game.spriteBatch);
        }

        game.spriteBatch.end();
        if(globalAlpha<1) {
            globalAlpha += 0.1;
        }else{
            globalAlpha=1;
        }

    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width,height,true);
        float scalar = viewport.getWorldWidth()/(float)width;
        if(scalar>1){
            scalar=1;
        }
        settingsPosition = new Vector2(viewport.getWorldWidth()*0.8f, viewport.getWorldHeight()*0.1f);
        playPosition= new Vector2(viewport.getWorldWidth()*0.5f, viewport.getWorldHeight()*0.2f);
        shopPosition = new Vector2(viewport.getWorldWidth()*0.2f, viewport.getWorldHeight()*0.1f);

         h = play.getHeight();
         w = play.getWidth();
        final float scale = 0.4f;

        if(!Resize_Once) {
            play.setSize(w * (scale + 0.5f), h * (scale + 0.5f));
            shop.setSize(w * (scale + 0.5f), h * (scale + 0.5f));
            settings.setSize(w * (scale + 0.5f), h * (scale + 0.5f));

            life.setSize(life.getWidth() * scalar, life.getHeight());
            powerFrag.setSize(powerFrag.getWidth() * scalar, powerFrag.getHeight());
        }

        play.setCenterX(playPosition.x); play.setCenterY(playPosition.y);
        shop.setCenterX(shopPosition.x); shop.setCenterY(shopPosition.y);
        settings.setCenterX(settingsPosition.x); settings.setCenterY(settingsPosition.y);

        lifePosition = new Vector2(viewport.getWorldWidth()/2, viewport.getWorldHeight()-life.getHeight()+3);
        shopFragPosition = new Vector2(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2);
        powerFragPosition = new Vector2(viewport.getWorldWidth()/2,-3);

        if(!Resize_Once) {
            shopFrag.setSize(shopFrag.getWidth() * 0.9f, shopFrag.getHeight() * 0.9f);
        }
        life.setY(lifePosition.y);
        life.setCenterX(lifePosition.x);
        shopFrag.setCenter(shopFragPosition.x, shopFragPosition.y);
        powerFrag.setY(powerFragPosition.y);
        powerFrag.setCenterX(powerFragPosition.x);

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
        close.setPosition(-400, -400);

        Resize_Once = true;



            }


    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        Vector2 touch = viewport.unproject(new Vector2(screenX, screenY));
        if (globalAlpha==1) {
            if(settingsPressed){
                game.getClick().play(Constants.MENU_CLICK_VOLUME);
                settingsPressed2 = true;
            }
            if(shopPressed){
                game.getClick().play(Constants.MENU_CLICK_VOLUME);
                shopPressed2 = true;
            }
            if(playPressed){
                game.getClick().play(Constants.MENU_CLICK_VOLUME);
                if(play.getBoundingRectangle().contains(touch)){
                    game.setGameMenuScreen();
                }
            }

            if(close2){
                settingsPressed = false;
                shopPressed = false;
                settingsPressed2 = false;
                shopPressed2 = false;
                close2 = false;
                close.setPosition(-400, -400);
            }


            float scale = 0.4f;

            play.setSize(w*(scale+0.5f),h*(scale+0.5f));
            play.setCenterX(playPosition.x);
            play.setCenterY(playPosition.y);

            shop.setSize(w*(scale+0.5f),h*(scale+0.5f));
            shop.setCenterX(shopPosition.x);
            shop.setCenterY(shopPosition.y);

            settings.setSize(w*(scale+0.5f),h*(scale+0.5f));
            settings.setCenterX(settingsPosition.x);
            settings.setCenterY(settingsPosition.y);

            if(shopPressed2){
                if(powerFrag.getBoundingRectangle().contains(touch)){
                    bubbleSwipe = touch.x-bubbleSwipe;
                    if(bubbleSwipe >0){
                        if(bubbleSwipe >50){
                            if(firstBubble!=Constants.ITEM_COUNT-bubbleDisplayLimit-1) {
                                firstBubble = firstBubble + 1;
                            }
                        }

                    } else if(bubbleSwipe <0){
                        if(bubbleSwipe <-50){
                            if(firstBubble!=0) {
                                firstBubble = firstBubble - 1;
                            }
                        }
                    }
                }
            }

            if(shopPressed2){
                if(shopFrag.getBoundingRectangle().contains(touch)){
                    tileSwipe = tileSwipe-touch.y;
                    if(tileSwipe >0){
                        if(tileSwipe >50){
                            if(firstTile!=0) {
                                firstTile = firstTile - 1;
                            }
                        }

                    } else if(tileSwipe <0){
                        if(tileSwipe <-50){
                            if(firstTile!=Constants.ITEM_COUNT-tileDisplayLimit-1) {
                                firstTile = firstTile + 1;
                            }
                        }
                    }
                }

            }
        }


        return false;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        if(globalAlpha==1) {
            Vector2 touch = viewport.unproject(new Vector2(screenX, screenY));
            if (!settingsPressed && !shopPressed) {
                if (shop.getBoundingRectangle().contains(touch)) {
                    shopPressed = true;
                    playPressed = false;
                    settingsPressed = false;
                    settingsPressed2 = false;
                    shopPressed2 = false;

                    float scale = 0.3f;
                    shop.setSize(w * (scale + 0.5f), h * (scale + 0.5f));
                    shop.setCenterX(shopPosition.x);
                    shop.setCenterY(shopPosition.y);

                } else if (play.getBoundingRectangle().contains(touch)) {
                    playPressed = true;
                    shopPressed = false;
                    settingsPressed = false;
                    settingsPressed2 = false;
                    shopPressed2 = false;

                    float scale = 0.3f;
                    play.setSize(w * (scale + 0.5f), h * (scale + 0.5f));
                    play.setCenterX(playPosition.x);
                    play.setCenterY(playPosition.y);


                } else if (settings.getBoundingRectangle().contains(touch)) {
                    settingsPressed = true;
                    shopPressed = false;
                    playPressed = false;
                    settingsPressed2 = false;
                    shopPressed2 = false;

                    float scale = 0.3f;
                    settings.setSize(w * (scale + 0.5f), h * (scale + 0.5f));
                    settings.setCenterX(settingsPosition.x);
                    settings.setCenterY(settingsPosition.y);

                }
            }

            if (close.getBoundingRectangle().contains(touch)) {
                close2 = true;

            }
            
            if(shopPressed2){
                if(shopFrag.getBoundingRectangle().contains(touch)){
                    tileSwipe = touch.y;

                }
            }

            if(shopPressed2){
                if(powerFrag.getBoundingRectangle().contains(touch));{
                    bubbleSwipe = touch.x;
                }
            }

//            if(bubbles.get(0).getBoundingRectangle().contains(touch)){
//                b.set(0, true); b.set(1, false); b.set(2, false); b.set(3, false); b.set(4, false); b.set(5, false);
//            }else if(bubbles.get(1).getBoundingRectangle().contains(touch)){
//                b.set(0, false); b.set(1, true); b.set(2, false); b.set(3, false); b.set(4, false); b.set(5, false);
//            }else if(bubbles.get(2).getBoundingRectangle().contains(touch)){
//                b.set(0, false); b.set(1, false); b.set(2, true); b.set(3, false); b.set(4, false); b.set(5, false);
//            }else if(bubbles.get(3).getBoundingRectangle().contains(touch)){
//                b.set(0, false); b.set(1, false); b.set(2, false); b.set(3, true); b.set(4, false); b.set(5, false);
//            }else if(bubbles.get(4).getBoundingRectangle().contains(touch)){
//                b.set(0, false); b.set(1, false); b.set(2, false); b.set(3, false); b.set(4, true); b.set(5, false);
//            }else if(bubbles.get(5).getBoundingRectangle().contains(touch)){
//                b.set(0, false); b.set(1, false); b.set(2, false); b.set(3, false); b.set(4, false); b.set(5, true);
//            }

        }


        return false;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 touch = viewport.unproject(new Vector2(screenX, screenY));

//        if(shopPressed2){
//            if(powerFrag.getBoundingRectangle().contains(touch)){
//                bubbleSwipe = touch.x-bubbleSwipe;
//                if(bubbleSwipe >0){
//                    if(bubbleSwipe >50){
//                        if(firstBubble!=Constants.ITEM_COUNT-bubbleDisplayLimit-1) {
//                            firstBubble = firstBubble + 1;
//                        }
//                    }
//
//                } else if(bubbleSwipe <0){
//                    if(bubbleSwipe <-50){
//                        if(firstBubble!=0) {
//                            firstBubble = firstBubble - 1;
//                        }
//                    }
//                }
//            }
//        }
//
//        if(shopPressed2){
//                if(shopFrag.getBoundingRectangle().contains(touch)){
//                    tileSwipe = touch.y-tileSwipe;
//                    if(tileSwipe >0){
//                        if(tileSwipe >50){
//                            if(firstTile!=0) {
//                                tileSwipe = tileSwipe - 1;
//                            }
//                        }
//
//                    } else if(tileSwipe <0){
//                        if(firstTile <-50){
//                            if(tileSwipe!=Constants.ITEM_COUNT-tileDisplayLimit-1) {
//                                tileSwipe = tileSwipe + 1;
//                            }
//                        }
//                    }
//                }
//
//        }


//        float x = 490;
//        if(b.get(0)){
//            bubbles.get(0).setCenter(touch.x, touch.y);
//            System.out.println(touch+" tile 1");
//        }else if(b.get(1)){
//            bubbles.get(1).setCenter(touch.x, touch.y);
//            System.out.println(touch+" tile 2");
//        }else if(b.get(2)){
//            bubbles.get(2).setCenter(touch.x, touch.y);
//            System.out.println(touch+" tile 3");
//        }else if(b.get(3)){
//            bubbles.get(3).setCenter(touch.x, touch.y);
//            System.out.println(touch+" tile 4");
//        }else if(b.get(4)){
//            bubbles.get(4).setCenter(touch.x, touch.y);
//            System.out.println(touch+" tile 5");
//        }else if(b.get(5)){
//            bubbles.get(5).setCenter(touch.x, touch.y);
//            System.out.println(touch+" tile 6");
//        }
        return false;
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
