package com.hoa.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.hoa.game.HoA;
import com.hoa.game.screens.Cave;
import com.hoa.game.screens.Volcano;


/**
 * Created by BMW on 03/05/2016.
 */
public class VolcanoDoor extends InteractiveTile {

    public VolcanoDoor(World world, TiledMap map, Rectangle bounds, HoA game) {
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
        super.game.setPos(game.getPosx(), game.getPosy());
        super.game.setZone("Volcano");
        super.game.setScreen(new Volcano(game));
    }


}