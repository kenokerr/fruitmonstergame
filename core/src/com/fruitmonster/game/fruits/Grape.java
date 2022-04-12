package com.fruitmonster.game.fruits;

import com.badlogic.gdx.math.Vector2;
import com.fruitmonster.game.Constants;
import com.fruitmonster.game.FruitMonster;

/**
 * Created by rushane on 5/10/17.
 */

public class Grape extends Fruit {

    public Grape(Vector2 position, FruitMonster game){
        super(position, game);
        fruitCode = 2;
        weight = Constants.GRAPE_WEIGHT;
        score = Constants.GRAPE_SCORE;
        name ="Banana";
        setSprite(fruitCode);

    }


    protected void setPosition(Vector2 position){
        this.position = position;
    }

}
