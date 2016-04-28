package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Scenes.Hud;
import com.hoa.game.Sprites.Player;
import com.badlogic.gdx.math.Vector2;
import static com.badlogic.gdx.Input.Buttons.RIGHT;
import com.badlogic.gdx.math.Rectangle;
import com.hoa.game.Tools.B2WorldCreator;

/**
 * Created by BMW on 26/04/2016.
 */
public class PlayScreen implements Screen {

    //game class
    private HoA game;

    // Current game camera & screen display, currently a FitViewPort
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    //hud of the program
    private Hud hud;

    //speed of camera movement
    private int speed = 4;
    //speed of char
    private float speedchar = 20f;

    //map loader and renderer
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;


    public PlayScreen(HoA game){
        //actual game variable
        this.game = game;

        //camera variable
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight, gamecam);

        //hud
        hud = new Hud(game.batch);

        //world, 0,0 = no gravity, if body at rest calculate no physics
        world = new World(new Vector2(0,0), true);
        //shows outlines debug
        b2dr = new Box2DDebugRenderer();


        player = new Player(world);

        //map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Maps/test_map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);



        new B2WorldCreator(world,map);
    }



    // This part moves the camera on the wasd key input.
    // created by shughi
    // problemino : diagonal movement vector is twice as fast as the horizontal and vertical one.
    // solverino : old school games feelings. We keep it that way.
    public void handleInput(float dt){

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            player.b2body.applyLinearImpulse(new Vector2(speedchar,0), player.b2body.getWorldCenter(), false);

            //gamecam.position.x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            player.b2body.applyLinearImpulse(new Vector2(0, speedchar), player.b2body.getWorldCenter(), false);
            //gamecam.position.y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            player.b2body.applyLinearImpulse(new Vector2(0, -speedchar), player.b2body.getWorldCenter(), false);
            //gamecam.position.y += -1*speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            player.b2body.applyLinearImpulse(new Vector2(-speedchar,0), player.b2body.getWorldCenter(), false);
            //gamecam.position.x += -1*speed;
        }

    }

    public void update(float dt){
        handleInput(dt);


        /** 60 times a second calculate the physics*/
        world.step(1/60f, 0 ,0);

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

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        // render the Box2d lines
        b2dr.render(world,gamecam.combined);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

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
        hud.dispose();

    }
}
