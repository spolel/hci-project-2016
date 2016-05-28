package com.hoa.game.Sprites;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by shughi on 28/05/16.
 */
public class Mob {

    private int life;
    private String name;
    private Texture texture;
    private int xp;

    public Mob(int life, String name, Texture texture, int xp){

        this.life = life;
        this.name = name;
        this.texture = texture;
        this.xp = xp;

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
}
