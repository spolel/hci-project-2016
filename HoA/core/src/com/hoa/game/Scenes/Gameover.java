package com.hoa.game.Scenes;

import com.badlogic.gdx.Gdx;
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

/**
 * Created by BMW on 17/05/2016.
 */
public class Gameover extends Table implements Disposable{


    public HoA game;
    public Stage stage;
    public Viewport viewport;


    //
    //private Actor newGame;
//    private Actor Instruction;
//     private Actor Credits;
//    private Actor Exit;

    private Drawable background;


    public Button mainmenu;



    private Drawable mainmenubutt;

    public Button exit;



    private Drawable exitbutt;


    private float buttwidth;
    private float buttheight;
    private float buttonpad;
    private final float titlepad;




    public Gameover(SpriteBatch spriteBatch){

        buttheight = game.screenHeight/12;
        buttwidth = game.screenWidth/4;
        buttonpad = game.screenHeight/20;
        titlepad = game.screenHeight/8.5f;


        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        background = new SpriteDrawable(new Sprite(new Texture("Menu/endscreen.jpg")));



        mainmenubutt = new SpriteDrawable( new Sprite(new Texture("Menu/mainmenubutton.png")));

        exitbutt = new SpriteDrawable( new Sprite(new Texture("Menu/exitbutton.png")));

        mainmenu = new Button(mainmenubutt);
        exit = new Button(exitbutt);



        /** the table is used to place the elements of the screen */
        Table table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);


        mainmenu.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y)
            {

            }
        });

        exit.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y)
            {

            }
        });

        table.setBackground(background);

        table.add(mainmenu).width(buttwidth).height(buttheight).padTop(6*titlepad);
        table.row();
        table.add(exit).width(buttwidth).height(buttheight).padTop(buttonpad);
        table.row();
        stage.addActor(table);

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
