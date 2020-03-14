package com.vinta.game.fruits;

import com.badlogic.gdx.math.Vector2;
import com.vinta.game.Constants;
import com.vinta.game.FruitMonster;

/**
 * Created by rushane on 7/7/17.
 */

public class Pineapple extends Fruit{
    public Pineapple(Vector2 position, FruitMonster game) {
        super(position, game);
        fruitCode = 6;
        weight = Constants.PINEAPPLE_WEIGHT;
        score = Constants.PINEAPPLE_SCORE;
        name ="Pineapple";
        duration = 0.9f;
        scalar = 0.8f;
        setSprite(fruitCode);

    }


    protected void setPosition(Vector2 position){
        this.position = position;
    }
}
