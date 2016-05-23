package com.hoa.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.screens.*;
import com.hoa.game.screens.InstructionScreen;

/**
 * Created by BMW on 17/05/2016.
 */
public class Mainmenu extends Table implements Disposable{

    public HoA game;
    public Stage stage;
    public Viewport viewport;


    //
    //private Actor newGame;
//    private Actor Instruction;
//     private Actor Credits;
//    private Actor Exit;

    private Drawable background;


    public Button newgame;
    public Button credits;
    public Button instructions;
    public Button exit;
    private Button title;


    private Drawable newbutton;
    private Drawable credbutton;
    private Drawable instbutton;
    private Drawable exbutton;
    private Drawable titlebutt;

    private float buttwidth;
    private float buttheight;




    public Mainmenu(SpriteBatch spriteBatch){

        buttheight = 50;
        buttwidth = 200;


        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        background = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));



        newbutton = new SpriteDrawable( new Sprite(new Texture("Menu/newgamebutton.png")));
        credbutton = new SpriteDrawable( new Sprite(new Texture("Menu/creditsbutton.png")));
        instbutton = new SpriteDrawable( new Sprite(new Texture("Menu/Instructionsbutton.png")));
        exbutton = new SpriteDrawable( new Sprite(new Texture("Menu/exitbutton.png")));
        titlebutt = new SpriteDrawable( new Sprite(new Texture("Menu/title.png")));


        newgame = new Button(newbutton);
        credits = new Button(credbutton);
        instructions = new Button(instbutton);
        exit = new Button(exbutton);
        title = new Button(titlebutt);


        /** the table is used to place the elements of the screen */
        Table table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);


        newgame.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y)
            {
                //newgame.setSize(100, 100);
                //MainMenuScreen a = new MainMenuScreen(game);
                //game.setPos(6150, 7100);
                //game.setScreen(new MainLand(game));
            }
        });

        credits.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y)
            {
                //credits.setSize(100, 100);
                //game.setPos(6150, 7100);
                //game.setScreen(new MainLand(game));
            }
        });

        instructions.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y)
            {
                //instructions.setSize(100, 100);
                //game.setScreen(new InstructionScreen(game));
            }
        });

        exit.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y)
            {
                Gdx.app.exit();
            }
        });

        table.setBackground(background);

        table.add(title).width(4*buttwidth).height(3*buttheight).padTop(20);
        table.row();
        table.add(newgame).width(buttwidth).height(buttheight).padTop(70);
        table.row();
        table.add(instructions).width(buttwidth).height(buttheight).padTop(50);
        table.row();
        table.add(credits).width(buttwidth).height(buttheight).padTop(50);
        table.row();
        table.add(exit).width(buttwidth).height(buttheight).padTop(50);
        stage.addActor(table);

    }

    public Button getNewgame(){
        return newgame;
    }





//
//    newGame = new NewGame();
//    addActor(newGame);
//    setSize
//    setPosition
//Label.setText();
//
//    extends Button
//    set texture
//            super (new SpriteDrawable(new Sprite(texture)))
//    addListener(new InputListener)
//
//
//        addListener(new InputListener(){
//            public void touchDown() {
//                //...
//            }
//
//            private Image image = new Image();
//        })
//
//
//
//            Label name = new Label(text, Label.LabelStyle(font, size, color))

    @Override
    public void dispose() {
        stage.dispose();
    }

}
