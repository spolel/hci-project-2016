package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Scenes.Instruction;
import com.hoa.game.Scenes.Map;

/**
 * Created by BMW on 26/04/2016.
 */
public class MapScreen implements Screen {

    //game class
    public HoA game;
    public Image textureplayer;

    // Current game camera & screen display, currently a FitViewPort
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private SuperClass current;

    //hud of the program
    private Map map;


    public MapScreen(HoA game, SuperClass current){
        //actual game variable
        this.game = game;
        this.current=current;

        //camera variable
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight, gamecam);

        //hud
        map = new Map(game.batch);

    }



    // This part moves the camera on the wasd key input.
    // created by shughi
    //using raw velocity, stops when stop pressing;
    //only one key usable now
    //if the else if is removed weird stuff happens
    public void handleInput(float dt){



// warps to cave
        if (Gdx.input.isKeyJustPressed(Input.Keys.M) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(current);
            this.dispose();
        }

    }


    public void update(float dt){
        handleInput(dt);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        map.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        map = new Map(game.batch);


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

    }
}
