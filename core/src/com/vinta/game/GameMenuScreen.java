package com.vinta.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by rushane on 8/25/17.
 */

public class GameMenuScreen extends InputAdapter implements Screen {
    private final int bubbleDisplayLimit = 5;

    private int firstBubble;

    float bubbleSwipe;


    private FruitMonster game;
    private Viewport viewport;
    private Sprite bg, arena, blast, cloaudOne, cloudTwo, emptyCard;
    private Sprite greyB, leaves, playerBoard, redB;

    private Animation<TextureRegion> romanBuilding;
    private float dur = 5000f, stateTime;
    private Vector2 pboard;
    private Sprite life, powerFrag;
    private Vector2 lifePosition, powerFragPosition;

    private Array<Sprite> bubbles;
    private boolean blastTouch;


    public GameMenuScreen(FruitMonster game){
        this.game = game;
    }

    @Override
    public void show() {
        game.assetManager.load("gameMenuScreen/menu-screen-background.png", Texture.class);
        game.assetManager.load("gameMenuScreen/romanBuilding.png", Texture.class);
        game.assetManager.load("gameMenuScreen/playerBoard.png", Texture.class);
        game.assetManager.load("gameMenuScreen/leaves.png", Texture.class);
        game.assetManager.load("gameMenuScreen/emptyCharacterCard.png", Texture.class);
        game.assetManager.load("gameMenuScreen/cloudTwo.png", Texture.class);
        game.assetManager.load("gameMenuScreen/cloudOne.png", Texture.class);
        game.assetManager.load("gameMenuScreen/blast.png", Texture.class);
        game.assetManager.load("gameMenuScreen/arena.png", Texture.class);
        game.assetManager.load("gameMenuScreen/redBalloon.png", Texture.class);
        game.assetManager.load("gameMenuScreen/greyBalloon.png", Texture.class);
//       game.assetManager.update();
        game.assetManager.finishLoading();

        viewport = new ScalingViewport(Scaling.fillY, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        bg = new Sprite(Constants.setTexture("gameMenuScreen/menu-screen-background.png", game));
        playerBoard = new Sprite(Constants.setTexture("gameMenuScreen/playerBoard.png", game));
        leaves = new Sprite(Constants.setTexture("gameMenuScreen/leaves.png", game));
        emptyCard = new Sprite(Constants.setTexture("gameMenuScreen/emptyCharacterCard.png", game));
        cloudTwo = new Sprite(Constants.setTexture("gameMenuScreen/cloudTwo.png", game));
        cloaudOne = new Sprite(Constants.setTexture("gameMenuScreen/cloudOne.png", game));
        blast = new Sprite(Constants.setTexture("gameMenuScreen/blast.png", game));
        arena = new Sprite(Constants.setTexture("gameMenuScreen/arena.png", game));
        greyB =new Sprite(Constants.setTexture ("gameMenuScreen/greyBalloon.png",game));
        redB =new Sprite(Constants.setTexture ("gameMenuScreen/redBalloon.png",game));
        
        romanBuilding = Constants.setAnimation(3,1,Constants.setTexture("gameMenuScreen/romanBuilding.png", game), dur);
        romanBuilding.setPlayMode(Animation.PlayMode.LOOP);

        life = new Sprite(Constants.setTexture("shop/lifeAndScore.png", game));
        powerFrag = new Sprite(Constants.setTexture("shop/powerHolder.png", game));
        //new Sprite(Constants.setTexture("", game));

        bubbles = new Array<Sprite>(true, Constants.ITEM_COUNT);

        for (int i = 0; i < Constants.ITEM_COUNT; i++) {
            Sprite bubble = new Sprite(Constants.setTexture("shop/powerBubble.png", game));

            bubbles.add(bubble);

        }

        Gdx.input.setInputProcessor(this);

    }

    public Sprite Animation(){
        TextureRegion currentFrame = romanBuilding.getKeyFrame(stateTime);
        Sprite sprite = new Sprite(currentFrame);
        sprite.setCenterX(viewport.getWorldWidth()/2);
        sprite.setY(viewport.getWorldHeight()-viewport.getWorldHeight()*1.05f);
        pboard = new Vector2(sprite.getX()+sprite.getWidth()/2, sprite.getY()+sprite.getHeight()/2);

        return sprite;

    }

    @Override
    public void render(float delta) {

        stateTime+=delta;
        viewport.apply();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Sprite sprite = Animation();
        playerBoard.setCenter(pboard.x, pboard.y);

        game.spriteBatch.begin();
        bg.draw(game.spriteBatch);
        arena.draw(game.spriteBatch);
        blast.draw(game.spriteBatch);
      //  cloaudOne.draw(game.spriteBatch);
      //  cloudTwo.draw(game.spriteBatch);
      //  emptyCard.draw(game.spriteBatch);
        greyB.draw(game.spriteBatch);
        leaves.draw(game.spriteBatch);

        redB.draw(game.spriteBatch);
        sprite.draw(game.spriteBatch);
        playerBoard.draw(game.spriteBatch);

        life.draw(game.spriteBatch);
        powerFrag.draw(game.spriteBatch);
        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        bg.setCenter(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2);
        arena.setCenter(viewport.getWorldWidth()/8, viewport.getWorldHeight()*0.85f);
        blast.setCenter(viewport.getWorldWidth()/2, viewport.getWorldHeight()*0.75f);
//        cloaudOne.setCenter();
//        cloudTwo.setCenter();
//        emptyCard.setCenter();
//        greyB.setCenter();
        leaves.setCenterX(viewport.getWorldWidth()); leaves.setY(viewport.getWorldHeight()-leaves.getHeight());

      //  redB.setCenter();

        lifePosition = new Vector2(viewport.getWorldWidth()/2, viewport.getWorldHeight()-life.getHeight()+3);
        powerFragPosition = new Vector2(viewport.getWorldWidth()/2,-3);

        life.setY(lifePosition.y);
        life.setCenterX(lifePosition.x);
        powerFrag.setY(powerFragPosition.y);
        powerFrag.setCenterX(powerFragPosition.x);
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
        game.assetManager.unload("gameMenuScreen/menu-screen-background.png");
        game.assetManager.unload("gameMenuScreen/romanBuilding.png");
        game.assetManager.unload("gameMenuScreen/playerBoard.png");
        game.assetManager.unload("gameMenuScreen/leaves.png");
        game.assetManager.unload("gameMenuScreen/emptyCharacterCard.png");
        game.assetManager.unload("gameMenuScreen/cloudTwo.png");
        game.assetManager.unload("gameMenuScreen/cloudOne.png");
        game.assetManager.unload("gameMenuScreen/blast.png");
        game.assetManager.unload("gameMenuScreen/arena.png");
        game.assetManager.unload("gameMenuScreen/redBalloon.png");
        game.assetManager.unload("gameMenuScreen/greyBalloon.png");
    }

    public void SpriteMove (int SpriteNumber){
        Array<Boolean> bool = new Array<Boolean>();
        Array<Sprite> sprites = new Array<Sprite>();


    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 touch = viewport.unproject(new Vector2(screenX, screenY));
        if(blast.getBoundingRectangle().contains(touch)){
            blastTouch = true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 touch = viewport.unproject(new Vector2(screenX, screenY));
        if(blast.getBoundingRectangle().contains(touch)){
            if(blastTouch){
                game.getHomeScreenMusic().stop();
                game.setGameScreen();
            }
        }

        blastTouch = false;
        return false;
    }
}
