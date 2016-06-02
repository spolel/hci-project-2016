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
import com.hoa.game.Scenes.Credit;

/**
 * Created by BMW on 26/04/2016.
 */
public class CreditScreen implements Screen {

    //game class
    public HoA game;
    public Image textureplayer;

    // Current game camera & screen display, currently a FitViewPort
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    //hud of the program
    private Credit credit;


    public CreditScreen(HoA game){
        //actual game variable
        this.game = game;

        //camera variable
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight, gamecam);

        //hud
        credit = new Credit(game.batch);

    }



    // This part moves the camera on the wasd key input.
    // created by shughi
    //using raw velocity, stops when stop pressing;
    //only one key usable now
    //if the else if is removed weird stuff happens
    public void handleInput(float dt){



// warps to cave
        if (Gdx.input.isKeyJustPressed(Input.Keys.B) || credit.back.isPressed()) {
            game.setScreen(new MainMenuScreen(game));
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

        credit.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        credit = new Credit(game.batch);


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
        credit.dispose();

    }
}
