package com.hoa.game.Sprites;

import com.badlogic.gdx.Gdx;
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

    private Boss boss;
    private TiledMap map;


    public Boss1(World world, TiledMap map, Rectangle bounds, HoA game, Boss boss) {
        super(world, map, bounds, game);
        fixture.setUserData(this);

        this.boss = boss;
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(bounds.getX() + bounds.getWidth() / 2, bounds.getY() + bounds.getHeight() / 2);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2, bounds.getHeight() / 2);
        fdef.shape = shape;
        body.createFixture(fdef);

        this.map = map;

        }

        @Override
        public void onCollision() {

            game.setScreen(new CombatScreen(game, boss, map));
        }

}

