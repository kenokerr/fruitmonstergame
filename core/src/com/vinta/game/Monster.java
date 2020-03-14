package com.vinta.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.vinta.game.fruits.Fruit;

/**
 * Created by rushane on 5/10/17.
 */

public class Monster extends Sprite{

    public static final int walkLeft = 0;
    public  static final int walkRight = 1;
    public  static final int mouthAction = 2;
    public static final int idle = 3;
    public static final int explode = 5;
    public static final int explode2 = 6;
    private boolean dead;

    private Vector2 position;

    public static final float footStand = 60;

    private TextureRegion walkLeftSprite;
    private TextureRegion walkRightSprite;
    private TextureRegion mouthSprite;
    private TextureRegion explodeSprite;
    private TextureRegion explodeSprite2;
    private TextureRegion idleSprite;

    private Animation<TextureRegion> walkLeftAnimation;
    private Animation<TextureRegion> walkRightAnimation;
    private Animation<TextureRegion> mouthAnimation;
     Animation<TextureRegion> mouthCloseAnimation;
    private Animation<TextureRegion> explodeAnimation;
    private Animation<TextureRegion> explodeAnimation2;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> animation;

    private float stateTime,  explodeStateTime;

    private float walkDur = 0.2001f;
    private float mouthDur = 0.0105f;
    private float explodeDur = 0.2f;
    private float explodeDur2 = 0.100f;
    private float idleDur = 1.000f;



    private boolean move, left;
    private boolean exploding;
    private float destination;
    private float walkSpeed = 12;
    private int state;



     public Monster(Viewport viewport, FruitMonster game){
         position = new Vector2(viewport.getWorldWidth()/2, 0);

         Texture spritesheet = game.assetManager.get("gamePlay/fruitMonsterOfficialSpriteSheet.png", Texture.class);
         spritesheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


         mouthSprite        = new TextureRegion(spritesheet,    0,              0,          1164,               270);
         walkRightSprite    = new TextureRegion(spritesheet,    1161,           0,          1160,               270);
         walkLeftSprite     = new TextureRegion(spritesheet,    0,              270,        1160,               270);
         idleSprite         = new TextureRegion(spritesheet,    1163,           270,        1160,               270);
         explodeSprite      = new TextureRegion(spritesheet,    0,              540,        290,            270);
         explodeSprite2     = new TextureRegion(spritesheet,    290,        540,        2030,           271);


         initAnimation();

         setCenterX(viewport.getWorldWidth()/2);
         setY(footStand);

         stateTime = 0f;
         explodeStateTime = 0f;



         state = idle;

       //  rect = new Rectangle(getWidth()*0.8f, getHeight(), getX(), getY());
     }

    public void render (SpriteBatch batch, float delta){

        stateTime+=delta;


    }



    public void mouth(SpriteBatch batch){

        TextureRegion currentFrame = mouthAnimation.getKeyFrame(stateTime, false);

        set(new Sprite(currentFrame));
        setCenterX(position.x);
        setY(footStand);

        draw(batch);

    }

    

    public void setPosition(float position){
        if(position > this.position.x){
            state = walkRight;
        }else if(position < this.position.x){
            state = walkLeft;
        }

        destination = position;

    }


    public void walkLeft( SpriteBatch batch){
        if (this.position.x > destination){
            this.position.x-=walkSpeed;
        }else {
            state = idle;
        }


        TextureRegion currentFrame  = walkLeftAnimation.getKeyFrame(stateTime, true);
        set(new Sprite(currentFrame));
        setCenterX(position.x);
        setY(footStand);

        draw(batch);
    }

    public void walkRight(SpriteBatch batch){
        if (this.position.x < destination){
            this.position.x+=walkSpeed;
        }else {
            state = idle;
        }


        TextureRegion currentFrame =  walkRightAnimation.getKeyFrame(stateTime, true);
        set(new Sprite(currentFrame));
        setCenterX(position.x);
        setY(footStand);

        draw(batch);
    }

    public void idle(SpriteBatch batch){
        move = false;
        TextureRegion currentFrame =  idleAnimation.getKeyFrame(stateTime, true);
        set(new Sprite(currentFrame));
        setCenterX(position.x);
        setY(footStand);

        draw(batch);

    }


    public void explode(SpriteBatch batch, float delta){
        explodeStateTime +=delta;
        TextureRegion currentFrame =  explodeAnimation.getKeyFrame(explodeStateTime);
        explodeAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        set(new Sprite(currentFrame));
        setCenterX(position.x);
        setY(footStand);

        draw(batch);

        if(explodeAnimation.isAnimationFinished(explodeStateTime)){
            state = explode2;
        }
    }

    public boolean isDead(){
        return  dead;
    }

    public void setDead(boolean dead){
        this.dead = dead;
    }

    public void explode2(SpriteBatch batch, float delta){
        explodeStateTime +=delta;
        TextureRegion currentFrame = explodeAnimation2.getKeyFrame(explodeStateTime);
        explodeAnimation2.setPlayMode(Animation.PlayMode.NORMAL);
        set(new Sprite(currentFrame));
        setCenterX(position.x);
        setY(footStand);

        draw(batch);

        if(explodeAnimation2.isAnimationFinished(explodeStateTime)){
           dead = true;
        }
    }


    public void initAnimation(){
        mouthAnimation = setAnimation(4,1,mouthSprite,mouthDur);
        mouthCloseAnimation = setAnimation(4,1,mouthSprite,mouthDur);
        mouthAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        mouthCloseAnimation.setPlayMode(Animation.PlayMode.REVERSED);
        walkRightAnimation = setAnimation(4,1,walkRightSprite,walkDur);
        walkLeftAnimation = setAnimation(4,1,walkLeftSprite,walkDur);
        idleAnimation = setAnimation(4,1,idleSprite,idleDur);
        explodeAnimation = setAnimation(1,1,explodeSprite,explodeDur);
        explodeAnimation2 = setAnimation(7,1,explodeSprite2,explodeDur2);

    }



    public boolean isExploding() {
        return exploding;
    }

    public void setExploding(boolean exploding) {
        state = explode;
        this.exploding = exploding;
    }



    public boolean isMove() {
        return move;
    }

    public boolean isLeft() {
        return left;
    }

    public Vector2 getCenter(){
        return new Vector2(getCenterX(), getCenterY());
    }

    public float getCenterX(){
        return getX()+(getWidth()/2);
    }

    public float getCenterY(){
        return  getY()+(getHeight()/2);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    public void setmove(boolean move){
        this.move = move;
    }

    public void setleft(boolean left) {
        this.left = left;
    }

    public  Animation<TextureRegion> setAnimation(int cols, int rows, TextureRegion region, float duration){
        Animation<TextureRegion> animation;
        TextureRegion[][] tmp = region.split(region.getRegionWidth()/cols, region.getRegionHeight()/rows);
        TextureRegion[] frames = new TextureRegion[cols*rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {for (int j = 0; j < cols; j++) {frames[index++] = tmp[i][j];}}
        animation = new Animation<TextureRegion>(duration, frames);
        return animation;
    }


    public Animation<TextureRegion> getAnimation(){
        return animation;
    }

    public void setAnimaion(Animation<TextureRegion> animation){
        this.animation = animation;
    }

    public float getStateTime(){
        return stateTime;
    }


}
