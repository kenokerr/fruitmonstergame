package com.fruitmonster.game.fruits;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fruitmonster.game.FruitMonster;

/**
 * Created by rushane on 5/10/17.
 */

public class Fruit extends Sprite{
    protected float weight;
    protected int score;
    protected String name;
    protected Vector2 position;
    protected float width;
    protected float height;
    protected Vector2 velocity;
    protected boolean bomb;
    protected TextureRegion region;
    protected int fruitCode;
    protected Animation<TextureRegion> animation, impactAnimation;
    private float stateTime, explodeStateTime;
    protected float duration;
    private FruitMonster game;
    protected boolean bounced;
    protected Vector2 bouncedValue;
    protected Vector2 bouncedVelocity;
    protected float scalar;
    protected boolean impact, impactAnimationFinished;

    public Fruit(Vector2 position, FruitMonster game) {
        this.position = position;
        this.velocity = new Vector2();
        bouncedVelocity = new Vector2();
        this.game = game;
        stateTime=0f;
        setCenter(position.x, position.y);
        bouncedValue = new Vector2();

    }

    protected void setSprite(int fruitCode){
        switch (fruitCode){
            case 0:
                set(new Sprite(game.assetManager.get("gamePlay/fruits/banana.png", Texture.class)));
                break;
            case 1:
                //region = new TextureRegion(texture,0,0,600,100);
                break;
            case 2:
                //region = new TextureRegion(texture,0,0,600,100);
                break;
            case 3:
                set(new Sprite(game.assetManager.get("gamePlay/fruits/apple.png", Texture.class)));
                break;
            case 4:
                set(new Sprite(game.assetManager.get("gamePlay/fruits/watermelon.png", Texture.class)));
                break;
            case 5:
                set(new Sprite(game.assetManager.get("gamePlay/fruits/halfWatermelon.png", Texture.class)));
                break;
            case 6:
                set(new Sprite(game.assetManager.get("gamePlay/fruits/pineapple.png", Texture.class)));
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
        }

        //setPosition(position.x, position.y);
    }

    protected void render(SpriteBatch batch, float delta){

        if(!impact) {
            if (isBomb()) {
                stateTime += delta;

                TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
                set(new Sprite(currentFrame));
                setSize(getWidth() * scalar, getHeight() * scalar);
                getBoundingRectangle().setSize(getWidth() * scalar, getHeight() * scalar);
                setPosition(position.x, position.y);
                draw(batch);
            } else {
                setPosition(position.x, position.y);
                draw(batch);
            }
        }else {
            if(isBomb()){
                explodeStateTime += delta;

                TextureRegion currentFrame = impactAnimation.getKeyFrame(explodeStateTime, true);
                set(new Sprite(currentFrame));
                setSize(getWidth()*scalar, getHeight()*scalar);
                getBoundingRectangle().setSize(getWidth()*scalar, getHeight()*scalar);
                setPosition(position.x, position.y);
                draw(batch);

                if(impactAnimation.isAnimationFinished(explodeStateTime)){
                    impactAnimationFinished = true;
                }
            } else{
                setPosition(position.x, position.y);
                draw(batch);
            }
        }
    }




    public void update(float delta, Vector2 ACCELERATION) {

        if(!impact) {
            velocity.mulAdd(ACCELERATION, delta * (weight / 4));
            position.mulAdd(velocity, delta);
            getBoundingRectangle().setPosition(position);
            if (!isBomb()) {
                if (bounced) {
                    bouncedVelocity.mulAdd(bouncedValue, delta);
                    position.mulAdd(bouncedValue, delta);
                }
            }
        }

    }

    public boolean isBomb(){
        return bomb;
    }

    protected Vector2 getPosition() {
        return position;
    }

    protected float getHeight2() {
        return height;
    }

    public void setBouncedValue(Vector2 bouncedValue) {
        this.bouncedValue = bouncedValue;
    }

    public boolean isBounced() {
        return bounced;
    }

    public void setBounced(boolean bounced) {
        this.bounced = bounced;
    }

    protected void fall(Vector2 velocity, float delta){
        position.x += delta * velocity.x;
        position.y += delta * velocity.y*(weight/2);
    }

    protected boolean isSplat(){
        if (position.y < 0){
            return true;
        }
        return false;
    }

    protected boolean isSwallowed(Vector2 mouth){
        if(position.y == mouth.y && position.x == mouth.x){
            return true;
        }
        return false;
    }



    protected float getWidth2() {
        return width;
    }



    protected Rectangle getRect(){
        return getBoundingRectangle();
    }

    public Vector2 getCenter(){
        return new Vector2(getCenterX(), getCenterY());
    }

    public float getCenterX(){
        return getX()-(getWidth()/2);
    }

    public float getCenterY(){
        return  getY()-(getHeight()/2);
    }

    public int getScore(){
        return score;
    }

    public boolean isImpact() {
        return impact;
    }

    public void setImpact(boolean impact) {
        this.impact = impact;
    }

    public boolean isImpactAnimationFinished() {
        return impactAnimationFinished;
    }

    public void setImpactAnimationFinished(boolean impactAnimationFinished) {
        this.impactAnimationFinished = impactAnimationFinished;
    }
}
