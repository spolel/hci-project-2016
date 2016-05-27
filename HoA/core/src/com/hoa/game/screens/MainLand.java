package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.hoa.game.HoA;
import com.hoa.game.Sprites.InteractiveTile;
import com.hoa.game.Sprites.Player;
import com.badlogic.gdx.math.Vector2;
import com.hoa.game.Tools.B2WorldCreator;
import com.hoa.game.Tools.WorldContactListener;

/**
 * Created by BMW on 26/04/2016.
 */
public class MainLand extends SuperClass {

    //game class
    public HoA game;


    //map loader and renderer
    private TmxMapLoader mapLoader;
    private TiledMap map;
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



        new B2WorldCreator(world,map, game);

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
                                                 float x = player.getBody().getPosition().x +30;
                                                 float y = player.getBody().getPosition().y-30;
                                                 game.setPos((int) x, (int) y);
                                                 ((InteractiveTile) object.getUserData()).onCollision();

                                             }
                                         }


                                     }

                                 });}




    // This part moves the camera on the wasd key input.
    // created by shughi
    //using raw velocity, stops when stop pressing;
    //only one key usable now
    //if the else if is removed weird stuff happens
    public void handleInput(float dt){
        super.handleInput(dt);

        if (Gdx.input.isKeyPressed(Input.Keys.W) && player.b2body.getLinearVelocity().y <= super.speed){
            player.b2body.applyLinearImpulse(new Vector2(0, super.speedchar), player.b2body.getWorldCenter(),true);

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S) && player.b2body.getLinearVelocity().y >= -speed){
            player.b2body.applyLinearImpulse(new Vector2(0, -speedchar), player.b2body.getWorldCenter(),true);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A) && player.b2body.getLinearVelocity().x >= -speed){
            player.b2body.applyLinearImpulse(new Vector2(-speedchar,0), player.b2body.getWorldCenter(),true);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D) && player.b2body.getLinearVelocity().x <= speed){
            player.b2body.applyLinearImpulse(new Vector2(speedchar, 0), player.b2body.getWorldCenter(),true);
        }
        else {
            player.b2body.setLinearVelocity(0,0);
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


        /** 60 times a second calculate the physics*/
        world.step(1/32f,0 ,0);

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
        b2dr.render(world,gamecam.combined);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        //hud = new Hud(game.batch, game);


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
