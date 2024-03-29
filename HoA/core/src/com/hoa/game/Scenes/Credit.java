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
import com.hoa.game.screens.MainMenuScreen;

/**
 * Created by BMW on 17/05/2016.
 */
public class Credit extends Table implements Disposable{

    public HoA game;
    public Stage stage;
    public Viewport viewport;


    private Drawable background;


    public Button back;


    private Drawable backbt;

    private float buttwidth;
    private float buttheight;




    public Credit(SpriteBatch spriteBatch){

        buttheight = 50;
        buttwidth = 200;


        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        background = new SpriteDrawable(new Sprite(new Texture("Menu/credits.jpg")));



        backbt = new SpriteDrawable( new Sprite(new Texture("Menu/backbutton.png")));

        back = new Button(backbt);



        /** the table is used to place the elements of the screen */
        Table table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);


//        newgame.addListener(new ClickListener(){
//            public void clicked(InputEvent e, float x, float y) {
//                game.setPos(6150, 7100);
//                game.setScreen(new MainLand(game));
//             }
//        });

        back.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y)
            {
                //back.setSize(100, 100);
                //HoA a = new HoA();
                //game.setScreen(new MainMenuScreen(game));
            }
        });






        table.setBackground(background);

        table.add();
        table.add(back).width(buttwidth).height(buttheight).pad(520,400,0,0);
        table.add();

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
