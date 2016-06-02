package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Scenes.Mainmenu;
import com.hoa.game.Scenes.Resumemenu;

/**
 * Created by BMW on 17/05/2016.
 */
public class ResumeMenuScreen implements Screen {

    //game class
    private HoA game;
    private Viewport gamePort;

    private Stage stage;
    private InputMultiplexer input;

    // Current game camera & screen display, currently a FitViewPort


    //hud of the program
    private Resumemenu resumemenu;

    private SuperClass screen;
    //Label newGame;
    //Label

    public ResumeMenuScreen(HoA game, SuperClass screen){

        //actual game variable
        this.game = game;
        this.screen = screen;

        stage = new Stage(new FitViewport(HoA.screenWidth, HoA.screenHeight));
        input = new InputMultiplexer();

        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight);

        //hud
        resumemenu = new Resumemenu(game.batch);




    }







    // This part moves the camera on the wasd key input.
    // created by shughi
    //using raw velocity, stops when stop pressing;
    //only one key usable now
    //if the else if is removed weird stuff happens
    public void handleInput(float dt){

        //insert click listener

        if (Gdx.input.isKeyJustPressed(Input.Keys.R) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || resumemenu.resume.isPressed()){
            game.setScreen(screen);
            this.dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.M) || resumemenu.mainmenu.isPressed()){
            game.setScreen(new MainMenuScreen(game));
            this.dispose();
        }
        // exit game
        else if (Gdx.input.isKeyPressed(Input.Keys.E) || resumemenu.exit.isPressed()){
            Gdx.app.exit();
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

       // stage.act(delta);
        //stage.setDebugAll();

        //stage.getViewport().apply();
        resumemenu.stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        stage.getViewport().update(width, height);
        resumemenu = new Resumemenu(game.batch);


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
       // mainmenu.dispose();

    }
}
