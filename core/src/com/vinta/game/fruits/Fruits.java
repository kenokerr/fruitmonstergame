package com.vinta.game.fruits;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vinta.game.Constants;
import com.vinta.game.FruitMonster;
import com.vinta.game.Monster;


/**
 * Created by rushane on 5/10/17.
 */

public class Fruits {

    private DelayedRemovalArray<Fruit> fruitsArray;


    private Viewport viewport;
    private Vector2 Acceleration;
    private FruitMonster game;
    private int score;
    private int scoreIncrease;
    private boolean scoreIncreased;
    private boolean bombRemoved;
    private int totscore;
    private int exDamage;
    private boolean applyExDamage;

    public Fruits(Viewport viewport, FruitMonster game){
        fruitsArray = new DelayedRemovalArray<Fruit>(true, 100);


        this.viewport = viewport;
        this.game = game;
        exDamage = 5;

        Acceleration = new Vector2(0,-100.0f);
    }

    public void render(SpriteBatch renderer, float delta){
        for (Fruit fruit: fruitsArray) {
            fruit.render(renderer, delta);

        }

    }

    public void update(float delta){

        if (MathUtils.random() < delta * Constants.SPAWNS_PER_SECOND) {
            Vector2 fruitPosition = new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight()
            );
            Fruit fruit;
            switch (MathUtils.random(0,5)){
                case 0:
                    fruit = new Apple(fruitPosition, game);
                    fruitsArray.add(fruit);
                    break;

                case 1:
                    fruit = new WaterMelon(fruitPosition, game);
                    fruitsArray.add(fruit);
                    break;

                case 2:
                    fruit = new Banana(fruitPosition, game);
                    fruitsArray.add(fruit);
                    break;
                case 3:
                    fruit = new Bomb(fruitPosition, game);
                    fruitsArray.add(fruit);
                    break;
                case 4:
                    fruit = new HalfWaterMelon(fruitPosition, game);
                    fruitsArray.add(fruit);
                    break;
                case 5:
                    fruit = new Pineapple(fruitPosition, game);
                    fruitsArray.add(fruit);
                    break;
            }
        }

        for (Fruit fruit : fruitsArray) {
            fruit.update(delta, Acceleration);
        }

        fruitsArray.begin();


        for (int i = 0; i < fruitsArray.size; i++) {
            if (fruitsArray.get(i).position.y < Monster.footStand) {
                fruitsArray.get(i).setImpact(true);
                if(!fruitsArray.get(i).isBomb()) {
                    fruitsArray.removeIndex(i);
                }
                Acceleration.y-=0.1;
            }

            if(fruitsArray.get(i).isImpact()&&fruitsArray.get(i).isImpactAnimationFinished()){
                fruitsArray.removeIndex(i);
            }
        }

        fruitsArray.end();

    }

    public void fruitSwallowed(Monster monster){

        totscore = 0;
        for (int i = 0; i < fruitsArray.size; i++) {
            totscore = totscore + fruitsArray.get(i).getScore();

            if (monster.getBoundingRectangle().contains(fruitsArray.get(i).getBoundingRectangle())) {

                score += fruitsArray.get(i).getScore();
                scoreIncrease = fruitsArray.get(i).getScore();
                setScoreIncreased(true);
                if (fruitsArray.get(i).isBomb()) {
                    setBombRemoved(true);
                }
                fruitsArray.removeIndex(i);


            }
        }

    }

    public void fruitBounced(Monster monster){

        for (int i = 0; i < fruitsArray.size; i++) {
            if (!monster.getBoundingRectangle().contains(fruitsArray.get(i).getBoundingRectangle()) && monster.getBoundingRectangle().overlaps(fruitsArray.get(i).getBoundingRectangle())) {
                fruitsArray.get(i).setBounced(true);
                if(fruitsArray.get(i).isBomb()){
                    fruitsArray.get(i).setImpact(true);
                    exDamage+=5;
                    applyExDamage = true;
                }else {
                    if (fruitsArray.get(i).getCenterX() > monster.getCenterX()||fruitsArray.get(i).getCenterX() == monster.getCenterX()&&monster.getState() == Monster.walkRight) {
                        fruitsArray.get(i).setBouncedValue(new Vector2(600, 50));
                    } else if (fruitsArray.get(i).getCenterX() < monster.getCenterX()&&monster.getState() == Monster.walkLeft) {
                        fruitsArray.get(i).setBouncedValue(new Vector2(-600, 50));
                    }
                }


            }
        }
    }



    public Array<Fruit> getFruits(){
        return fruitsArray;
    }

    public int getScore(){
        return score;
    }

    public boolean isBombRemoved() {
        return bombRemoved;
    }

    public void setBombRemoved(boolean bombRemoved) {
        this.bombRemoved = bombRemoved;
    }

    public int getScoreIncrease(){
        return scoreIncrease;
    }

    public void setScoreIncrease(int scoreIncrease) {
        this.scoreIncrease = scoreIncrease;
    }

    public boolean isScoreIncreased() {
        return scoreIncreased;
    }

    public void setScoreIncreased(boolean scoreIncreased) {
        this.scoreIncreased = scoreIncreased;
    }

    public boolean isApplyExDamage() {
        return applyExDamage;
    }

    public void setApplyExDamage(boolean applyExDamage) {
        this.applyExDamage = applyExDamage;
    }

    public int getExDamage() {
        return exDamage;
    }

    public void setExDamage(int exDamage) {
        this.exDamage = exDamage;
    }
}


