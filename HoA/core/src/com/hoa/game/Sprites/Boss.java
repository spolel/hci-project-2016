package com.hoa.game.Sprites;


import com.badlogic.gdx.graphics.Texture;

/**
 * Created by shughi on 23/05/2016.
 */

public class Boss {

    private int life;
    private String name;
    private Texture texture;

    public Boss(int life, String name, Texture texture){

        this.life = life;
        this.name = name;
        this.texture = texture;


    }

    // Getter methods below


    public String getName(){

        return name;

    }

    public int getLife(){

        return life;

    }

    public Texture getTexture(){

        return texture;

    }


}
