package com.fruitmonster.game.fruits;

import com.badlogic.gdx.math.Vector2;
import com.fruitmonster.game.Constants;
import com.fruitmonster.game.FruitMonster;

/**
 * Created by rushane on 5/10/17.
 */

public class Orange extends Fruit {

    public Orange(Vector2 position, FruitMonster game){
        super(position, game);
        fruitCode = 1;
        weight = Constants.ORANGE_WEIGHT;
        score = Constants.ORANGE_SCORE;
        name ="Orange";
        duration = 0.5f;
        setSprite(fruitCode);

    }



    protected void setPosition(Vector2 position){
        this.position = position;
    }


}
