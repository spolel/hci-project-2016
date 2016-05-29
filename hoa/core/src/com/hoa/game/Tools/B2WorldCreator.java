package com.hoa.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.hoa.game.HoA;
import com.hoa.game.Sprites.*;
import com.hoa.game.screens.SuperClass;

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
    protected SuperClass current;

    // Boss definition down here

    public B2WorldCreator(World world, TiledMap map, HoA game, SuperClass current){
        this.current=current;

        Texture boss1 = new Texture("Sprites/Bosses/Flaming_ent.png");
        Texture boss2 = new Texture("Sprites/Bosses/slime.png");

        SpriteDrawable forest = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));
        SpriteDrawable island = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));

        Boss boss1_test = new Boss(100*game.level,"Flaming Ent",boss1, 12, 9200, 8888, 100*game.level, forest);
        Boss boss2_test = new Boss(300*game.level,"Flaming Ent Crying",boss2, 14, 9500, 8888, 300*game.level, island);





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
            new TavernExit(world, map, rect, game);
        }

        //Boss1
        for(MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Boss1(world, map, rect, game, boss1_test, current);
        }

        //bonus
        for(MapObject object : map.getLayers().get(13).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //Boss2
        for(MapObject object : map.getLayers().get(14).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Boss1(world, map, rect, game, boss2_test,current);
        }

        //bonus
        for(MapObject object : map.getLayers().get(15).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //FROM BOSS3 TILL BOSS 5 STILL TO IMPLEMENT
        //Boss3
        for(MapObject object : map.getLayers().get(16).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Boss1(world, map, rect, game, boss2_test,current);
        }

        //bonus
        for(MapObject object : map.getLayers().get(17).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //Boss4
        for(MapObject object : map.getLayers().get(18).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Boss1(world, map, rect, game, boss2_test,current);
        }

        //bonus
        for(MapObject object : map.getLayers().get(19).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //Boss5
        for(MapObject object : map.getLayers().get(20).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Boss1(world, map, rect, game, boss2_test,current);
        }

        //bonus
        for(MapObject object : map.getLayers().get(21).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //teleport 1
        for(MapObject object : map.getLayers().get(22).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new TeleportMountain(world, map, rect, game);
        }

        //teleport 2
        for(MapObject object : map.getLayers().get(23).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new TeleportVolcano(world, map, rect, game);
        }

        //teleport 3
        for(MapObject object : map.getLayers().get(24).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new TeleportIsland(world, map, rect, game);
        }

        //teleport 4
        for(MapObject object : map.getLayers().get(25).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new TeleportForest(world, map, rect, game);
        }

        //teleport 5
        for(MapObject object : map.getLayers().get(26).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new TeleportIceland(world, map, rect, game);
        }
    }
}
