package com.vinta.game.fruits;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vinta.game.Constants;
import com.vinta.game.FruitMonster;

/**
 * Created by rushane on 6/3/17.
 */

public class WaterMelon extends Fruit {
    public WaterMelon(Vector2 position, FruitMonster game) {
        super(position, game);
        fruitCode = 4;
        weight = Constants.WATERMELON_WEIGHT;
        score = Constants.WATERMELON_SCORE;
        name ="Watermelon";
        duration = 1.8f;

        scalar = 0.8f;
        setSprite(fruitCode);

    }


    protected void setPosition(Vector2 position){
        this.position = position;
    }

}
