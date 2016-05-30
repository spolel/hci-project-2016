package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.hoa.game.HoA;
import com.hoa.game.Scenes.Hud;
import com.hoa.game.Sprites.Boss;
import com.hoa.game.Sprites.InteractiveTile;
import com.hoa.game.Sprites.Mob;
import com.hoa.game.Sprites.Player;
import com.badlogic.gdx.math.Vector2;
import com.hoa.game.Tools.B2WorldCreator;
import com.hoa.game.Tools.WorldContactListener;

import java.util.Random;

import static java.lang.Math.log;
import static java.lang.Math.sqrt;

/**
 * Created by BMW on 26/04/2016.
 */
public class MainLand extends SuperClass {

    public B2WorldCreator worldcreator;
    //game class
    public HoA game;


    //map loader and renderer
    private TmxMapLoader mapLoader;
    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;

    //box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    public Player player;


    public MainLand(final HoA game){
        super(game);

        world = new World(new Vector2(0,0), true);
        //shows outlines debug
        b2dr = new Box2DDebugRenderer();


        player = new Player(world, this, game.getPosx(), game.getPosy());

        //player = new Player(world, this, game.getPosx(), game.getPosy());



        //map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Maps/Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        super.gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);



        this.worldcreator= new B2WorldCreator(world,map, game, this);

        world.setContactListener(new WorldContactListener() {
                                     @Override

                                     public void beginContact(Contact contact) {
                                         Fixture a = contact.getFixtureA();
                                         Fixture b = contact.getFixtureB();

                                         if (a.getUserData() == "top" || a.getUserData() == "bot" || a.getUserData() == "left" || a.getUserData() == "right" ||
                                                 b.getUserData() == "top" || b.getUserData() == "bot" || b.getUserData() == "left" || b.getUserData() == "right") {

                                             Fixture player = (a.getUserData() == "top" || a.getUserData() == "bot" || a.getUserData() == "left" || a.getUserData() == "right") ? a : b;
                                             Fixture object = (b.getUserData() == "top" || b.getUserData() == "bot" || b.getUserData() == "left" || b.getUserData() == "right") ? a : b;

                                             //checks if objects is from class interactive tile
                                             if (object.getUserData() != null && InteractiveTile.class.isAssignableFrom(object.getUserData().getClass())) {
                                                 float x;
                                                 float y;
                                                 if (player.getBody().getLinearVelocity().x>0){
                                                     x = player.getBody().getPosition().x -30;
                                                 }
                                                 else if (player.getBody().getLinearVelocity().x<0){
                                                     x = player.getBody().getPosition().x +30;}
                                                 else{
                                                     x = player.getBody().getPosition().x;
                                                 }
                                                 if (player.getBody().getLinearVelocity().y>0){
                                                     y = player.getBody().getPosition().y -30;}
                                                 else if (player.getBody().getLinearVelocity().y<0){
                                                     y = player.getBody().getPosition().y +30;}
                                                 else {
                                                     y = player.getBody().getPosition().y;}
                                                    game.setPos((int) x, (int) y);
                                                    ((InteractiveTile) object.getUserData()).onCollision();

                                             }
                                         }


                                     }

                                 });}


//add diagonal movement?

    // This part moves the camera on the wasd key input.
    // created by shughi
    //using raw velocity, stops when stop pressing;
    //only one key usable now
    //if the else if is removed weird stuff happens
    public void handleInput(float dt){
        super.handleInput(dt);

        if ((Gdx.input.isKeyPressed(Input.Keys.W)|Gdx.input.isKeyPressed(Input.Keys.UP)) && player.b2body.getLinearVelocity().y <= super.speed){
            player.b2body.applyLinearImpulse(new Vector2(0, super.speedchar), player.b2body.getWorldCenter(),true);

        }
        else if ((Gdx.input.isKeyPressed(Input.Keys.S)|Gdx.input.isKeyPressed(Input.Keys.DOWN)) && player.b2body.getLinearVelocity().y >= -speed){
            player.b2body.applyLinearImpulse(new Vector2(0, -speedchar), player.b2body.getWorldCenter(),true);
        }
        else if ((Gdx.input.isKeyPressed(Input.Keys.A)|Gdx.input.isKeyPressed(Input.Keys.LEFT)) && player.b2body.getLinearVelocity().x >= -speed){
            player.b2body.applyLinearImpulse(new Vector2(-speedchar,0), player.b2body.getWorldCenter(),true);
        }
        else if ((Gdx.input.isKeyPressed(Input.Keys.D)|Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && player.b2body.getLinearVelocity().x <= speed){
            player.b2body.applyLinearImpulse(new Vector2(speedchar, 0), player.b2body.getWorldCenter(),true);
        }
        else {
            player.b2body.setLinearVelocity(0,0);
        }


        // map
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            super.game.setScreen(new MapScreen(super.game, this));
        }


        // warps to tavern
        if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            super.game.setPos(5792,7500);
            super.game.setScreen(new MainLand(super.game));
        }

        //warps to volcano
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {

            super.game.setPos(12160, 12480);
            super.game.setScreen(new MainLand(super.game));
        }

        // warps to cave
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            super.game.setPos(8832, 8800);
            super.game.setScreen(new MainLand(super.game));
        }

        // warps to ent
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            super.game.setPos(7456, 10720);
            super.game.setScreen(new MainLand(super.game));
        }

        // warps to ghost
        if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
            super.game.setPos(7040, 1088);
            super.game.setScreen(new MainLand(super.game));
        }

        // warps to mage
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            super.game.setPos(2528, 11488);
            super.game.setScreen(new MainLand(super.game));
        }

        //levelup
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            super.game.level++;
            super.game.dmg=super.game.dmg*(int)log(10+super.game.dmg);

        }

//        main map:
//        Entrata Dungeon 1 -> 8800,8832
//        Entrata dungeon 2 -> 12160,12384
//        Entrata taverna -> 5792,7520
//        individual maps:
//        uscita taverna -> 1216,544
//        uscita d1-> 512,1376
//        uscita d2-> 1376,544



    }

    public void update(float dt){
        handleInput(dt);

        //Boss boss1_test = new Boss(25,"Flaming Ent",new Texture("Sprites/Bosses/Flaming_ent.png"), 12, 9200, 8888, 20);

        //attempt at random encounters: should work, just put in the boss info and uncomment
        Random a = new Random();
        int value = a.nextInt(100000);
        if(10<value & value<50){
            SpriteDrawable forest = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));

            Mob bandit = new Mob(super.game.level*super.game.level*5, "Bandit", new Texture("Sprites/encounters/bandits/bandit.png"), super.game.level*15, forest);
            super.game.setPos((int)player.b2body.getPosition().x, (int)player.b2body.getPosition().y);
            super.game.setScreen(new CombatMob(super.game, bandit, this));
        }

        if(50<value & value<100){
            SpriteDrawable forest = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));

            Mob bandit3 = new Mob(super.game.level*super.game.level*10, "Bandit Chief", new Texture("Sprites/encounters/bandits/bandit3.png"), super.game.level*25, forest);
            super.game.setPos((int)player.b2body.getPosition().x, (int)player.b2body.getPosition().y);
            super.game.setScreen(new CombatMob(super.game, bandit3, this));
        }

        if(100<value & value<600){
            SpriteDrawable forest = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));

            Mob ninja = new Mob(super.game.dmg*(int)log(super.game.level+10)*15, "Ninja", new Texture("Sprites/encounters/ninja.png"), super.game.level*30, forest);
            super.game.setPos((int)player.b2body.getPosition().x, (int)player.b2body.getPosition().y);
            super.game.setScreen(new CombatMob(super.game, ninja, this));
        }

        if(value==1){
            SpriteDrawable forest = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));

            Boss berserkbandit = new Boss(super.game.level*super.game.level*69, "Berserk Bandit", new Texture("Sprites/encounters/bandits/bandit2.png"), 1, super.game.level*30, forest);
            super.game.setPos((int)player.b2body.getPosition().x, (int)player.b2body.getPosition().y);
            super.game.setScreen(new CombatScreen(super.game, berserkbandit, map, this));
        }
//        if(value==1){
//            Mob fireWisp = new Mob(super.game.level*20, "Fire Wisp", new Texture("Sprites/encounters/fire_thingy.png"), super.game.level*20);
//            super.game.setPos((int)player.b2body.getPosition().x, (int)player.b2body.getPosition().y);
//            super.game.setScreen(new CombatMob(super.game, fireWisp));
//        }
//        if(value==2){
//            Mob Skeleton = new Mob(super.game.level*15, "Skeleton", new Texture("Sprites/encounters/skelly.png"), super.game.level*15);
//            super.game.setPos((int)player.b2body.getPosition().x, (int)player.b2body.getPosition().y);
//            super.game.setScreen(new CombatMob(super.game, Skeleton));
//        }


        /** 60 times a second calculate the physics*/
        world.step(1/64f,0 ,0);

        player.update(dt);

        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.position.y = player.b2body.getPosition().y;

        gamecam.update();
        renderer.setView(gamecam);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        super.game.batch.setProjectionMatrix(super.gamecam.combined);
        super.game.batch.begin();
        player.draw(super.game.batch);
        super.game.batch.end();


        super.game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        super.hud.stage.draw();


        // render the Box2d lines
        //b2dr.render(world,gamecam.combined);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        //hud = new Hud(game.batch, game);


    }

    public World getWorld(){
        return this.world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        super.hud.dispose();

    }
}
