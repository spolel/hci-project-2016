package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Scenes.Inventory;

/**
 * Created by BMW on 26/04/2016.
 */
public class InventoryScreen implements Screen {

    //game class
    public HoA game;

    // Current game camera & screen display, currently a FitViewPort
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    //hud of the program
    private Inventory inventory;


    public InventoryScreen(HoA game){
        //actual game variable
        this.game = game;

        //camera variable
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight, gamecam);

        //hud
        inventory = new Inventory(game.batch, game);

    }



    // This part moves the camera on the wasd key input.
    // created by shughi
    //using raw velocity, stops when stop pressing;
    //only one key usable now
    //if the else if is removed weird stuff happens
    public void handleInput(float dt){



// warps to cave
        if (Gdx.input.isKeyJustPressed(Input.Keys.I) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || inventory.exit.isPressed()) {
            game.setScreen(new MainLand(game));
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

        inventory.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        inventory = new Inventory(game.batch, game);

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
        inventory.dispose();

    }
}
