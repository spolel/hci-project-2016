package com.hoa.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.hoa.game.HoA;

/**
 * Created by BMW on 03/05/2016.
 */
public class Boss extends InteractiveTile{

    public Boss(World world, TiledMap map, Rectangle bounds, HoA game) {
        super(world, map, bounds, game);
        fixture.setUserData(this);
    }

    @Override
    public void onCollision() {

    }
}
