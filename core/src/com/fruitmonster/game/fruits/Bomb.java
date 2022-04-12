package com.fruitmonster.game.fruits;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.fruitmonster.game.Constants;
import com.fruitmonster.game.FruitMonster;

/**
 * Created by rushane on 5/18/17.
 */

public class Bomb extends Fruit {

    private Animation<TextureRegion> animExplosion;
    private TextureRegion explosionSprite;

    private float bombloop = 0.125f;
    private float expDur = 0.11f;


    public Bomb(Vector2 position, FruitMonster game) {
        super(position, game);
        fruitCode = -1;
        weight = Constants.BOMB_WEIGHT;
        scalar = 1;
        score = 0;
        name ="Bomb";
        bomb = true;

        Texture spritesheet = game.assetManager.get("gamePlay/bombSpriteSheet.png", Texture.class);
        spritesheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        explosionSprite = new TextureRegion(spritesheet,0,100,500,100);
        region = new TextureRegion(spritesheet,0,0,500,100);

        animation = Constants.setAnimation(5,1,region,bombloop);
        impactAnimation = Constants.setAnimation(5,1,explosionSprite,expDur);

        impactAnimation.setPlayMode(Animation.PlayMode.NORMAL);


    }


}
