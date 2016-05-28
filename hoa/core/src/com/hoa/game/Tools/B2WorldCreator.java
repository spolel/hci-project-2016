package com.hoa.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.hoa.game.HoA;
import com.hoa.game.Sprites.*;

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

    // Boss definition down here

    public B2WorldCreator(World world, TiledMap map, HoA game){

        Texture boss1 = new Texture("Sprites/Bosses/Flaming_ent.png");
        Texture boss2 = new Texture("Sprites/Bosses/Boss2.png");

        Boss boss1_test = new Boss(100*game.level,"Flaming Ent",boss1, 12, 9200, 8888, 100*game.level);
        Boss boss2_test = new Boss(300*game.level,"Flaming Ent Crying",boss2, 14, 9500, 8888, 300*game.level);





        //collision
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new CollisionBox(world, map, rect, game);
        }

        //d1 entrance
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new CaveDoor(world, map, rect, game);
        }

        //d1 exit
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //d2 entrance
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new VolcanoDoor(world, map, rect, game);
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
            new TavernDoor(world, map, rect, game);
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

        //Boss2
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
