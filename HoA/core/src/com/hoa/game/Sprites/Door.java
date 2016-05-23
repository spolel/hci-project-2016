package com.hoa.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.hoa.game.HoA;
import com.hoa.game.screens.MainLand;


/**
 * Created by BMW on 03/05/2016.
 */
public class Door extends InteractiveTile {

    public Door(World world, TiledMap map, Rectangle bounds, HoA game) {
        super(world, map, bounds, game);
        fixture.setUserData(this);


        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(bounds.getX() + bounds.getWidth() / 2, bounds.getY() + bounds.getHeight() / 2);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2, bounds.getHeight() / 2);
        fdef.shape = shape;
        body.createFixture(fdef);
    }

    @Override
    public void onCollision() {
        super.game.setZone("Main Land");
        super.game.setScreen(new MainLand(game));
                //map = mapLoader.load("Maps/Test_cave.tmx");
    }


}