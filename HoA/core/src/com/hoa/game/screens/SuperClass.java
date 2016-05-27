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
public class SuperClass implements Screen {

    //game class
    public HoA game;
    private TextureAtlas atlas;

    // Current game camera & screen display, currently a FitViewPort
    protected OrthographicCamera gamecam;
    protected Viewport gamePort;

    //hud of the program
    protected Hud hud;

    //speed of camera movement
    protected int speed = 1000;
    //speed of char
    protected float speedchar = 10f;

    //map loader and renderer
    private TmxMapLoader mapLoader;
    private TiledMap map;
    public OrthogonalTiledMapRenderer renderer;

    //box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;
    private boolean inventory;


    public SuperClass(HoA game){
        //sprite
        atlas = new TextureAtlas("Sprites/packs/player.pack");


        //actual game variable
        this.game = game;

        //camera variable
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight, gamecam);

        //hud
        hud = new Hud(game.batch, game);

        hud.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y)
            {
            }
        });


        Gdx.input.setInputProcessor(hud.stage);


    }

    public TextureAtlas getAtlas() {
        return atlas;
    }




    // This part moves the camera on the wasd key input.
    // created by shughi
    //using raw velocity, stops when stop pressing;
    //only one key usable now
    //if the else if is removed weird stuff happens
    public void handleInput(float dt){


        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)|| hud.options.isPressed()){
            game.setPos((int)player.b2body.getPosition().x, (int)player.b2body.getPosition().y);

            game.setScreen(new ResumeMenuScreen(game));
        }

        // opens inventory
        if (Gdx.input.isKeyJustPressed(Input.Keys.I) || hud.inventory.isPressed()) {

            game.setScreen(new InventoryScreen(game, this));

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

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        hud.stage.draw();


        // render the Box2d lines
        //b2dr.render(world,gamecam.combined);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        hud = new Hud(game.batch, game);


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
