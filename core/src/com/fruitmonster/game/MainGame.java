package com.fruitmonster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fruitmonster.game.fruits.Fruit;

/**
 * Created by rushane on 5/10/17.
 */

public class MainGame extends InputAdapter implements Screen {
    private Viewport viewport;
    private FruitMonster game;
    private com.fruitmonster.game.fruits.Fruits fruits;
    private Monster monster;
    private TextureRegion backGround, leaves, tiki1, tiki2, frontTiki, frontTiki2, backMountain, frontMountain, bananaLeaves, platform, lifeAndScore;
    private Sprite lifeGage, backMoon, frontMoon;
    private Sprite lifeGage2;
    private TextureRegion [] life;
    private float tiki1Speed, tiki2Speed, frontTikiSpeed, frontTiki2Speed, backMoonSpeed, frontMoonSpeed, backMountainSpeed, frontMountainSpeed, bananaLeavesSpeed, platformSpeed;
    private float monsterPosition;
    private Vector2 leavesPosition, tiki1Position, tiki2Position, frontTikiPosition, frontTiki2Position,
                    backMoonPosition, frontMoonPosition, backMountainPosition, frontMountainPosition,
                    bananaLeavesPosition, platformPosition, lifeGagePosition, lifeandScorePosition,
                    labelPosition, scorePosition;
    private Vector2[]lifePosition;

    private FreeTypeFontGenerator score;
    private FreeTypeFontGenerator.FreeTypeFontParameter scoreLabelParameter, scoreParameter;
    private BitmapFont label, Score;
    private int hunger;
    private int full;
    int count = 0;
    int lifeLeft = 5;


    private boolean bounced, swallow;



    public MainGame(FruitMonster game){
        this.game = game;
    }


    @Override
    public void show() {

        game.assetManager.load("gamePlay/fruits/apple.png", Texture.class);
        game.assetManager.load("gamePlay/fruits/banana.png", Texture.class);
        game.assetManager.load("gamePlay/fruits/halfWatermelon.png", Texture.class);
        game.assetManager.load("gamePlay/fruits/pineapple.png", Texture.class);
        game.assetManager.load("gamePlay/fruits/watermelon.png", Texture.class);
        game.assetManager.load("gamePlay/fruitMonsterOfficialSpriteSheet.png", Texture.class);
        game.assetManager.load("gamePlay/fruit sprite.png", Texture.class);
        game.assetManager.load("gamePlay/bombSpriteSheet.png", Texture.class);
        game.assetManager.load("gamePlay/life.png", Texture.class);
        game.assetManager.load("gamePlay/FRUIT-MONSTER-life-gage-yellow.png", Texture.class);
        game.assetManager.load("gamePlay/FRUIT-MONSTER-life-gage-red.png", Texture.class);
        game.assetManager.load("gamePlay/lifeAndScore.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER--BOARD-FLOOR.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER-BACK-MOON-.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER--BACK-MOUNTAIN-B.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER--BANANA-LEAVES-D.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER-BROWN-TIKI-IN-FRONT-1.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER-BROWN-TIKI-IN-FRONT-2.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER--first-background-A.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER-FRONT-MOON-SHINE.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER--FRONT-MOUNTAIN-C.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER-PURPLE-TIKI-1.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER-PURPLE-TIKI-2.png", Texture.class);
        game.assetManager.load("gamePlay/tikiBackGround/FRUIT-MONSTER-TOP-LEAVES.png", Texture.class);
        game.assetManager.load("audio/fruit monster Fail.wav", Sound.class);
        game.assetManager.load("audio/fruit monster Game Audio.wav", Music.class);
        game.assetManager.finishLoading();

        game.setGamePlayMusic("audio/fruit monster Game Audio.wav");
        game.setGameOver("audio/fruit monster Fail.wav");
        
        game.getGamePlayMusic().setVolume(Constants.GAME_PLAY_MUSIC_VOLUME);
        game.getGamePlayMusic().setLooping(true);
        game.getGamePlayMusic().play();

        viewport = new ScalingViewport(Scaling.fillY, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
       // viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        score = new FreeTypeFontGenerator(Gdx.files.internal(Constants.Atari_FONT));

        scoreLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        scoreLabelParameter.size = 30;
        scoreLabelParameter.magFilter = Texture.TextureFilter.Linear;
        scoreLabelParameter.minFilter = Texture.TextureFilter.Linear;
        label = new BitmapFont();
        label = score.generateFont(scoreLabelParameter);

        scoreParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        scoreParameter.size = 40;
        scoreParameter.magFilter = Texture.TextureFilter.Linear;
        scoreParameter.minFilter = Texture.TextureFilter.Linear;
        Score = new BitmapFont();
        Score = score.generateFont(scoreParameter);

        Score.setColor(Color.WHITE);
        label.setColor(Color.WHITE);

        platformSpeed = 4*2;
        frontTikiSpeed = 2*2;          frontTiki2Speed = 1.8f*2;
        tiki1Speed = 1.4f*2;           tiki2Speed = 1.2f*2;
        bananaLeavesSpeed = 0.8f*2;
        frontMountainSpeed = 0.4f*2;   backMountainSpeed = 0.2f*2;
        frontMoonSpeed = 0.08f*2;      backMoonSpeed = 0.08f*2;


        monster = new Monster(viewport, game);
        fruits = new com.fruitmonster.game.fruits.Fruits(viewport, game);

        platform = Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER--BOARD-FLOOR.png", game);

        backGround = Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER--first-background-A.png", game);

        backMoon = new Sprite(Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER-BACK-MOON-.png", game));

        backMountain = Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER--BACK-MOUNTAIN-B.png", game);

        bananaLeaves = Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER--BANANA-LEAVES-D.png", game);

        frontTiki = Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER-BROWN-TIKI-IN-FRONT-1.png", game);

        frontTiki2 = Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER-BROWN-TIKI-IN-FRONT-2.png", game);

        frontMoon = new Sprite(Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER-FRONT-MOON-SHINE.png", game));

        frontMountain = Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER--FRONT-MOUNTAIN-C.png", game);

        tiki1 = Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER-PURPLE-TIKI-1.png", game);

        tiki2 = Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER-PURPLE-TIKI-2.png", game);

        leaves = Constants.setTexture("gamePlay/tikiBackGround/FRUIT-MONSTER-TOP-LEAVES.png", game);

        life = new TextureRegion[5];
        lifePosition = new Vector2[5];

        lifeGage = new Sprite(Constants.setTexture("gamePlay/FRUIT-MONSTER-life-gage-yellow.png", game));
        lifeGage2 = new Sprite(Constants.setTexture("gamePlay/FRUIT-MONSTER-life-gage-red.png", game));



        hunger = (int)lifeGage.getWidth();
        full = (int)lifeGage.getWidth();


        for (int i = 0; i < 5; i++) {
            life[i] = Constants.setTexture("gamePlay/life.png", game);
        }


       lifeAndScore = Constants.setTexture("gamePlay/lifeAndScore.png", game);



        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

    }


    

    @Override
    public void render(float delta) {

        viewport.apply(true);
        bounced = false;
        int fruitCount = 0;

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fruits.update(delta);


        game.spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(backGround,0,0);

        game.spriteBatch.draw(backMountain, backMountainPosition.x, backMountainPosition.y);
        frontMoon.draw(game.spriteBatch);
        backMoon.draw(game.spriteBatch);



        game.spriteBatch.draw(frontMountain, frontMountainPosition.x, frontMountainPosition.y);
        game.spriteBatch.draw(bananaLeaves, bananaLeavesPosition.x, bananaLeavesPosition.y);
        game.spriteBatch.draw(tiki2, tiki2Position.x, tiki2Position.y);
        game.spriteBatch.draw(tiki1, tiki1Position.x, tiki1Position.y);
        game.spriteBatch.draw(frontTiki2, frontTiki2Position.x, frontTiki2Position.y);
        game.spriteBatch.draw(frontTiki, frontTikiPosition.x, frontTikiPosition.y);
        game.spriteBatch.draw(platform, platformPosition.x, platformPosition.y);



        monster.render(game.spriteBatch, delta);
        switch (monster.getState()) {
            case Monster.walkLeft:
                monster.walkLeft(game.spriteBatch);
                for (Fruit fruit: fruits.getFruits()) {
                    if (monster.getBoundingRectangle().overlaps(fruit.getBoundingRectangle())&&(monster.getState()==Monster.walkLeft||monster.getState()==Monster.walkRight)) {
                        bounced = true;
                        break;
                    }
                }
                monster.setleft(true);
                monster.setmove(true);
                break;

            case Monster.walkRight:
                monster.walkRight(game.spriteBatch);
                for (Fruit fruit: fruits.getFruits()) {
                    if (monster.getBoundingRectangle().overlaps(fruit.getBoundingRectangle())&&(monster.getState()==Monster.walkLeft||monster.getState()==Monster.walkRight)) {
                        bounced = true;
                        break;
                    }
                }
                monster.setleft(false);
                monster.setmove(true);
                break;

            case Monster.mouthAction:
                monster.setmove(false);
                monster.mouth(game.spriteBatch);
                int count = 0;
                for (Fruit fruit: fruits.getFruits()) {
                    count++;
                    if (monster.getBoundingRectangle().contains(fruit.getBoundingRectangle())) {
                        monster.mouthCloseAnimation.getKeyFrame(monster.getStateTime(), false);
                        if (monster.mouthCloseAnimation.isAnimationFinished(monster.getStateTime())) {
                            if (!fruit.isImpact()) {
                                swallow = true;

                                if (fruit.isBomb()&&!fruit.isImpact()) {
                                    monster.setExploding(true);
                                } else {
                                    monster.setState(Monster.idle);
                                }
                            }
                        }
                    }


//                    if (fruit.getX() < monster.getX() + monster.getWidth() * 0.05 || fruit.getX() < (monster.getX() + monster.getWidth() - (monster.getWidth() * 0.05))) {
                        if (fruit.getY() < monster.getY() || fruit.getY() > (monster.getY() + monster.getHeight() + (monster.getHeight() * 0.2))) {
                            fruitCount++;
                            if(fruitCount == fruits.getFruits().size) {
                                monster.setState(Monster.idle);
                            }
                        }

//                    }

                }
                break;

            case Monster.idle:
                monster.idle(game.spriteBatch);
                for (Fruit fruit: fruits.getFruits()) {
                    if (fruit.getX() > monster.getX() + monster.getWidth() * 0.05 && fruit.getX() < (monster.getX() + monster.getWidth() - (monster.getWidth() * 0.05))) {
                        if (fruit.getY() > (monster.getY() + monster.getHeight() / 2) && fruit.getY() < (monster.getY() + monster.getHeight() + (monster.getHeight() * 0.2))) {
                            if (monster.getBoundingRectangle().overlaps(fruit.getBoundingRectangle())&&(monster.getState()==Monster.walkLeft||monster.getState()==Monster.walkRight)) {
                                bounced = true;
                            }
                            if (!fruit.isImpact()) {
                                monster.setState(Monster.mouthAction);
                            }
                            break;
                        }
                    }
                }
                break;
            case Monster.explode:
                monster.setmove(false);
                monster.explode(game.spriteBatch, delta);
                break;
            case Monster.explode2:
                monster.setmove(false);
                monster.explode2(game.spriteBatch,delta);
                break;
        }
        fruits.render(game.spriteBatch, delta);


        if(swallow){
            fruits.fruitSwallowed(monster);
            swallow=false;
        }


        if(bounced){
            fruits.fruitBounced(monster);
        }

        if(fruits.isBombRemoved()){
            monster.setExploding(true);
        }




        game.spriteBatch.draw(leaves,leavesPosition.x, leavesPosition.y);
        game.spriteBatch.draw(lifeAndScore, lifeandScorePosition.x, lifeandScorePosition.y);
        for (int i = 0; i < lifeLeft ; i++) {
            game.spriteBatch.draw(life[i], lifePosition[i].x, lifePosition[i].y);
        }

        if(hunger > full/3){
            lifeGage.draw(game.spriteBatch);
        } else{
            lifeGage2.draw(game.spriteBatch);
        }

        label.draw(game.spriteBatch,"score", labelPosition.x, labelPosition.y);
        Score.draw(game.spriteBatch,Integer.toString(fruits.getScore()), scorePosition.x, scorePosition.y);






        if(monster.isMove()) {
            if (monster.isLeft()) {
                frontTikiPosition.add(frontTikiSpeed, 0);
                frontTiki2Position.add(frontTiki2Speed, 0);
                tiki1Position.add(tiki1Speed, 0);
                tiki2Position.add(tiki2Speed, 0);
                backMoonPosition.add(backMoonSpeed, 0);
                frontMoonPosition.add(frontMoonSpeed, 0);
                backMountainPosition.add(backMountainSpeed, 0);
                frontMountainPosition.add(frontMountainSpeed, 0);
                bananaLeavesPosition.add(bananaLeavesSpeed,0);
            } else {
                frontTikiPosition.sub(frontTikiSpeed, 0);
                frontTiki2Position.sub(frontTiki2Speed, 0);
                tiki1Position.sub(tiki1Speed, 0);
                tiki2Position.sub(tiki2Speed, 0);
                backMoonPosition.sub(backMoonSpeed, 0);
                frontMoonPosition.sub(frontMoonSpeed, 0);
                backMountainPosition.sub(backMountainSpeed, 0);
                frontMountainPosition.sub(frontMountainSpeed, 0);
                bananaLeavesPosition.sub(bananaLeavesSpeed,0);
            }
        }
        backMoon.setCenterX(backMoonPosition.x);
        backMoon.setCenterY(backMoonPosition.y);
        frontMoon.setCenterX(frontMoonPosition.x);
        frontMoon.setCenterY(frontMoonPosition.y);

        fruits.setBombRemoved(false);


        game.spriteBatch.end();

        if(fruits.isApplyExDamage()){
            hunger-=fruits.getExDamage();
            fruits.setApplyExDamage(true);
            fruits.setExDamage(0);
        }

        if (count == 13){
            hunger--;
            count = 0;
        }

        lifeGage.setSize(hunger, lifeGage.getHeight());
        lifeGage2.setSize(hunger, lifeGage.getHeight());

        if(hunger == 0||hunger < 0){
            lifeLeft--;
            hunger = full;
        }

        if(fruits.isScoreIncreased()){
            hunger+=fruits.getScoreIncrease();
            if(hunger > full){
                hunger = full;
            }
            fruits.setScoreIncreased(false);
        }

        if (lifeLeft == 0){
            monster.setDead(true);
        }

        if (monster.isDead()){
            game.getGamePlayMusic().stop();
          //  game.getGameOver().play(Constants.GAME_OVER_VOLUME);
            game.setHomeScreen();
        }

        count++;

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            dispose();
            game.getGamePlayMusic().stop();
            game.setGameMenuScreen();
        }




    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        leavesPosition = new Vector2(0-leaves.getRegionWidth()/2+viewport.getWorldWidth()/2, Constants.WORLD_HEIGHT-leaves.getRegionHeight());
        frontTikiPosition = new Vector2(-250, -30);
        frontTiki2Position = new Vector2(500, 80);

        tiki1Position = new Vector2(400, 100);
        tiki2Position = new Vector2(100,150);

        backMoonPosition = new Vector2(viewport.getWorldWidth()*0.7f,viewport.getWorldHeight()*0.9f);
        frontMoonPosition = new Vector2(viewport.getWorldWidth()*0.7f,viewport.getWorldHeight()*0.9f);
        backMountainPosition = new Vector2(-800,400);
        frontMountainPosition = new Vector2(-900,400);
        bananaLeavesPosition = new Vector2(-200,400);
        platformPosition = new Vector2(0,0);

        lifeandScorePosition = new Vector2(lifeAndScore.getRegionWidth()/8, viewport.getWorldHeight()-lifeAndScore.getRegionHeight()+15);
        lifeGagePosition = new Vector2(235+lifeAndScore.getRegionWidth()/8, lifeandScorePosition.y+85);
        float offsetY = 120;
        lifePosition[0] = new Vector2(295+lifeAndScore.getRegionWidth()/8,lifeandScorePosition.y+offsetY); lifePosition[1] = new Vector2(347+lifeAndScore.getRegionWidth()/8,lifeandScorePosition.y+offsetY); lifePosition[2] = new Vector2(404+lifeAndScore.getRegionWidth()/8,lifeandScorePosition.y+offsetY);
        lifePosition[3] = new Vector2(457+lifeAndScore.getRegionWidth()/8,lifeandScorePosition.y+offsetY); lifePosition[4] = new Vector2(510+lifeAndScore.getRegionWidth()/8,lifeandScorePosition.y+offsetY);

        scorePosition = new Vector2(viewport.getWorldWidth()/2+30, lifeandScorePosition.y+65);
        labelPosition = new Vector2(350, lifeandScorePosition.y+65);
        lifeGage.setPosition(lifeGagePosition.x, lifeGagePosition.y);
        lifeGage2.setPosition(lifeGagePosition.x, lifeGagePosition.y);

        backMoon.setCenterX(backMoonPosition.x);
        backMoon.setCenterY(backMoonPosition.y);
        frontMoon.setCenterX(frontMoonPosition.x);
        frontMoon.setCenterY(frontMoonPosition.y);

        backMoon.setSize(backMoon.getWidth()*0.8f, backMoon.getHeight()*0.8f);

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
        game.assetManager.unload("gamePlay/fruits/apple.png");
        game.assetManager.unload("gamePlay/fruits/banana.png");
        game.assetManager.unload("gamePlay/fruits/halfWatermelon.png");
        game.assetManager.unload("gamePlay/fruits/pineapple.png");
        game.assetManager.unload("gamePlay/fruits/watermelon.png");
        game.assetManager.unload("gamePlay/fruitMonsterOfficialSpriteSheet.png");
        game.assetManager.unload("gamePlay/fruit sprite.png");
        game.assetManager.unload("gamePlay/bombSpriteSheet.png");
        game.assetManager.unload("gamePlay/life.png");
        game.assetManager.unload("gamePlay/FRUIT-MONSTER-life-gage-yellow.png");
        game.assetManager.unload("gamePlay/FRUIT-MONSTER-life-gage-red.png");
        game.assetManager.unload("gamePlay/lifeAndScore.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER--BOARD-FLOOR.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-BACK-MOON-.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER--BACK-MOUNTAIN-B.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER--BANANA-LEAVES-D.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-BROWN-TIKI-IN-FRONT-1.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-BROWN-TIKI-IN-FRONT-2.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER--first-background-A.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-FRONT-MOON-SHINE.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER--FRONT-MOUNTAIN-C.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-PURPLE-TIKI-1.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-PURPLE-TIKI-2.png");
        game.assetManager.unload("gamePlay/tikiBackGround/FRUIT-MONSTER-TOP-LEAVES.png");
        game.assetManager.unload("audio/fruit monster Fail.wav");
        game.assetManager.unload("audio/fruit monster Game Audio.wav");
        score.dispose();

    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer)
    {

        return true;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {

        if(!monster.isExploding()){
        monsterPosition = viewport.unproject(new Vector2(screenX, screenY)).x;
        monster.setPosition(monsterPosition);
        }


        return false;

    }


}
