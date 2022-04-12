package com.fruitmonster.game.fruits;

import com.badlogic.gdx.math.Vector2;
import com.fruitmonster.game.Constants;
import com.fruitmonster.game.FruitMonster;

/**
 * Created by rushane on 6/3/17.
 */

public class Apple extends Fruit {
    public Apple(Vector2 position, FruitMonster game) {
        super(position, game);

        fruitCode = 3;
        weight = Constants.APPLE_WEIGHT;
        score = Constants.APPLE_SCORE;
        name ="Apple";
        duration = 0.9f;
        scalar = 0.6f;
        setSprite(fruitCode);



    }


    protected void setPosition(Vector2 position){
        this.position = position;
    }

}
