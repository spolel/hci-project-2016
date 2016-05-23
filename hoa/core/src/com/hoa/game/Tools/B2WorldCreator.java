package com.hoa.game.Tools;

import com.badlogic.gdx.graphics.Texture;
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
import com.hoa.game.Sprites.Boss1;
import com.hoa.game.Sprites.Boss;

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


    Boss boss1_test = new Boss(25,"Flaming Ent",new Texture("Sprites/Bosses/Flaming_ent.png"));
    Boss boss2_test = new Boss(30,"Flaming Ent Crying",new Texture("Sprites/Bosses/Boss2.png"));

    Texture boss1 = new Texture("Sprites/Bosses/Flaming_ent.png");
    Texture boss2 = new Texture("Sprites/Bosses/Boss2.png");

    public B2WorldCreator(World world, TiledMap map, HoA game){


        //collision
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new CollisionBox(world, map, rect, game);
        }

        //d1 entrance
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new EnterCave(world, map, rect, game);
        }

        //d1 exit
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //d2 entrance
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new EnterCave(world, map, rect, game);
            //TEMPORARY
        }

        //d2 exit
        for(MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //tavernentrance
        for(MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new EnterTavern(world, map, rect, game);
        }

        //tavernexit
        for(MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //Boss1
        for(MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Boss1(world, map, rect, game, boss1_test);
        }

        //bonus
        for(MapObject object : map.getLayers().get(13).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //bonus
        for(MapObject object : map.getLayers().get(14).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Boss1(world, map, rect, game, boss2_test);
        }

        //bonus
        for(MapObject object : map.getLayers().get(15).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }






    }
}
