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

        Texture fEnt = new Texture("Sprites/Bosses/Flaming_ent.png");
        Texture slime = new Texture("Sprites/Bosses/slime.png");
        Texture BK = new Texture("Sprites/Bosses/blood knight.png");
        Texture wizard = new Texture("Sprites/Bosses/frostmage.png");
        Texture ghost = new Texture("Sprites/Bosses/ghost.png");


        SpriteDrawable forest = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));
        SpriteDrawable island = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));
        SpriteDrawable cave = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));
        SpriteDrawable volcano = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));
        SpriteDrawable iceland = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));

        Boss flamingEnt = new Boss(35*game.level*game.level,"Flaming Ent",fEnt, 12, 100*game.level, forest);
        Boss spookiGhost = new Boss(40*game.level*game.level,"Spooki Ghost",ghost, 14, 150*game.level, island);
        Boss Slime = new Boss(45*game.level*game.level,"Slimee",slime, 12, 200*game.level, cave);
        Boss bloodKnight = new Boss(50*game.level*game.level,"Blood Knight",BK, 12, 250*game.level, volcano);
        Boss frostMage = new Boss(400000+(game.level)*100000,"Frost Mage",wizard, 12, 500*game.level, iceland);






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
            new Boss1(world, map, rect, game, flamingEnt, current);
        }

        //bonus
        for(MapObject object : map.getLayers().get(13).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //Boss2
        for(MapObject object : map.getLayers().get(14).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Boss1(world, map, rect, game, spookiGhost,current);
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
            new Boss1(world, map, rect, game, bloodKnight,current);
        }

        //bonus
        for(MapObject object : map.getLayers().get(17).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //Boss4
        for(MapObject object : map.getLayers().get(18).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Boss1(world, map, rect, game, Slime, current);
        }

        //bonus
        for(MapObject object : map.getLayers().get(19).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Door(world, map, rect, game);
        }

        //Boss5
        for(MapObject object : map.getLayers().get(20).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Boss1(world, map, rect, game, frostMage,current);
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
