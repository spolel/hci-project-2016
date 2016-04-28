package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Scenes.Hud;

import static com.badlogic.gdx.Input.Buttons.RIGHT;

/**
 * Created by BMW on 26/04/2016.
 */
public class PlayScreen implements Screen {

    private HoA game;

    // Current game camera & screen display
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private int speed = 4;

    //map loader and renderer
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(HoA game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight, gamecam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Maps/test_map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);




    }



    // This part moves the camera on the wasd key input.
    // created by shughi
    // problemino : diagonal movement vector is twice as fast as the horizontal and vertical one.
    // solverino : old school games feelings. We keep it that way.
    public void handleInput(float dt){

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            gamecam.position.x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            gamecam.position.y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            gamecam.position.y += -1*speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            gamecam.position.x += -1*speed;
        }

    }

    public void update(float dt){
        handleInput(dt);

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

    }
}
