package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Scenes.Hud;
import com.hoa.game.Sprites.Player;
import com.hoa.game.Tools.B2WorldCreator;
import com.hoa.game.Tools.WorldContactListener;

/**
 * Created by BMW on 26/04/2016.
 */
public class Tavern extends SuperClass {

    //game class
    public HoA game;



    //map loader and renderer
    private TmxMapLoader mapLoader;
    private TiledMap map;
    public OrthogonalTiledMapRenderer renderer;

    //box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;


    public Tavern(HoA game){
        super(game);

        world = new World(new Vector2(0,0), true);
        //shows outlines debug
        b2dr = new Box2DDebugRenderer();


        //update tavern position
        player = new Player(world, this, 1216, 544);




        //map
        mapLoader = new TmxMapLoader();
        //update tavern map
        map = mapLoader.load("Maps/tavern.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);



        new B2WorldCreator(world,map, game, this);

        world.setContactListener(new WorldContactListener(){});

    }





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



        super.hud.stage.draw();


        // render the Box2d lines
        //b2dr.render(world,gamecam.combined);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        super.hud = new Hud(super.game.batch, super.game);


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
