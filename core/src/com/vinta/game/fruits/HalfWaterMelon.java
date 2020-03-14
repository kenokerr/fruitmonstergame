package com.vinta.game.fruits;

import com.badlogic.gdx.math.Vector2;
import com.vinta.game.Constants;
import com.vinta.game.FruitMonster;

/**
 * Created by rushane on 6/26/17.
 */

public class HalfWaterMelon extends Fruit{
    public HalfWaterMelon(Vector2 position, FruitMonster game) {
        super(position, game);
        fruitCode = 5;
        weight = Constants.HALF_WATERMELON_WEIGHT;
        score = Constants.HALF_WATERMELON_SCORE;
        name ="Watermelon";
        duration = 0.9f;
        scalar = 0.8f;
        setSprite(fruitCode);

    }


    protected void setPosition(Vector2 position){
        this.position = position;
    }
}
