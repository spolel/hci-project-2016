package com.hoa.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by shughi on 28/05/16.
 */
public class Mob {

    private int life;
    private String name;
    private Texture texture;
    private int xp;
    private SpriteDrawable background;

    public Mob(int life, String name, Texture texture, int xp, SpriteDrawable background){

        this.life = life;
        this.name = name;
        this.texture = texture;
        this.xp = xp;
        this.background = background;

    }

    public int getLife(){

        return life;
    }

    public String getName(){
        return name;
    }

    public Texture getTexture(){
        return texture;
    }

    public int getXp(){

        return xp;
    }

    public SpriteDrawable getBackground(){
        return background;
    }
}
