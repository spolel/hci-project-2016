package com.hoa.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.hoa.game.HoA;
import com.hoa.game.screens.CombatScreen;
import com.hoa.game.screens.MainLand;
import com.badlogic.gdx.graphics.Texture;

public class Boss1 extends InteractiveTile {

    private Texture texture;


    public Boss1(World world, TiledMap map, Rectangle bounds, HoA game, Texture boss) {
        super(world, map, bounds, game);
        fixture.setUserData(this);

        texture = boss;
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
            game.setScreen(new CombatScreen(game, texture));
        }

}

