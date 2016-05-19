package com.hoa.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.hoa.game.HoA;
import com.hoa.game.Sprites.CollisionBox;
import com.hoa.game.Sprites.Door;
import com.hoa.game.Sprites.EnterCave;
import com.hoa.game.Sprites.EnterTavern;

/**
 * Created by lorenzo on 28/04/16.
 */
public class B2WorldCreator {
    /** for now here but later on its own class*/
    public HoA game;
    BodyDef bdef = new BodyDef();
    PolygonShape shape = new PolygonShape();
    FixtureDef fdef = new FixtureDef();
    Body body;

    public B2WorldCreator(World world, TiledMap map, HoA game){


        //collision
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new CollisionBox(world, map, rect, game);
        }

        //tavern
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new EnterCave(world, map, rect, game);
        }

        //Cave
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new EnterTavern(world, map, rect, game);
        }

        //Caveexit
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //tavernexit
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }





    }
}
