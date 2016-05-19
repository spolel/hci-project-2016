package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Scenes.Mainmenu;

/**
 * Created by BMW on 17/05/2016.
 */
public class MainMenuScreen implements Screen {

    //game class
    private HoA game;
    private Viewport gamePort;

    private Stage stage;
    private InputMultiplexer input;

    private Image background;

    // Current game camera & screen display, currently a FitViewPort


    //hud of the program
    private Mainmenu mainmenu;

    //Label newGame;
    //Label

    public MainMenuScreen (HoA game){

        //actual game variable
        this.game = game;

        background = new Image(new Texture("Menu/HoA_menu.jpg"));

        stage = new Stage(new FitViewport(HoA.screenWidth, HoA.screenHeight));
        input = new InputMultiplexer();

        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight);

        //hud
        mainmenu = new Mainmenu(game.batch);




    }







    // This part moves the camera on the wasd key input.
    // created by shughi
    //using raw velocity, stops when stop pressing;
    //only one key usable now
    //if the else if is removed weird stuff happens
    public void handleInput(float dt){

        //insert click listener

        if (Gdx.input.isKeyPressed(Input.Keys.N)){
            game.setPos(800, 1000);
            game.setScreen(new MainLand(game));
        }
        // exit game
        else if (Gdx.input.isKeyPressed(Input.Keys.E)){
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
        mainmenu.stage.draw();

       // background.draw(game.batch, 10f);



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        stage.getViewport().update(width, height);

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
