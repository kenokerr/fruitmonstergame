package com.vinta.game.fruits;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.vinta.game.Constants;
import com.vinta.game.FruitMonster;

/**
 * Created by rushane on 5/10/17.
 */

public class Banana extends Fruit {

    public Banana(Vector2 position, FruitMonster game){
        super(position, game);

        fruitCode = 0;
        weight = Constants.BANANA_WEIGHT;
        score = Constants.BANANA_SCORE;
        name ="Banana";
        duration = 1f;
        scalar = 0.49f;
        setSprite(fruitCode);



    }


    protected void setPosition(Vector2 position){
        this.position = position;
    }



}
