package com.hoa.game.Sprites;


import com.badlogic.gdx.graphics.Texture;

/**
 * Created by shughi on 23/05/2016.
 */

public class Boss {

    private int life;
    private String name;
    private Texture texture;
    private int layer;
    private int x_pos;
    private int y_pos;
    private int xp;

    public Boss(int life, String name, Texture texture,int layer, int x_pos, int y_pos, int xp){

        this.life = life;
        this.name = name;
        this.texture = texture;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.layer = layer;
        this.xp = xp;

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

    public int getX(){

        return x_pos;
    }

    public int getY(){

        return y_pos;
    }

    public int getLayer(){

        return layer;
    }

    public int getXp(){

        return xp;
    }


}
