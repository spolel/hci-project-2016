package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private InputListener input;

    private Image background;


    // Current game camera & screen display, currently a FitViewPort


    //hud of the program
    private Mainmenu mainmenu;

    //Label newGame;
    //Label

    public MainMenuScreen (final HoA game){

        //actual game variable
        this.game = game;

        //newgame = new Button(newbutton, );
                //new Button()

        background = new Image(new Texture("Menu/HoA_menu.jpg"));

        stage = new Stage(new FitViewport(HoA.screenWidth, HoA.screenHeight));
        //input = new InputMultiplexer();

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


        if (Gdx.input.isKeyPressed(Input.Keys.N) || mainmenu.newgame.isPressed()){
            game.setPos(6150, 7100);
            game.setZone("Main Land");
            game.setHealth(4);
            game.setHealthThreshold(4);
            game.setXP(0);
            game.setXPThreshold(20);
            game.setLevel(1);
            game.setScreen(new MainLand(game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.I) || mainmenu.instructions.isPressed()){
            game.setScreen(new InstructionScreen(game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.C) || mainmenu.credits.isPressed()){
            game.setScreen(new CreditScreen(game));
        }
        // exit game
        else if (Gdx.input.isKeyPressed(Input.Keys.E) || mainmenu.exit.isPressed()){
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
        //stage.getViewport().update(width, height);
        mainmenu = new Mainmenu(game.batch);

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
