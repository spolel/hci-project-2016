package com.hoa.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
public class Instructions extends Table implements Disposable{

    public HoA game;
    public Stage stage;
    public Viewport viewport;


    //
    //private Actor newGame;
//    private Actor Instructions;
//     private Actor Credits;
//    private Actor Exit;

    private Drawable background;


    private Button back;


    private Drawable backbt;

    private float buttwidth;
    private float buttheight;




    public Instructions(SpriteBatch spriteBatch){

        buttheight = 50;
        buttwidth = 200;


        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        background = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));



        backbt = new SpriteDrawable( new Sprite(new Texture("Menu/newgamebutton.png")));

        back = new Button(backbt);


        //newgame.setSize(10f, 10f);
        //instructions.setSize(10f, 10f);
//        credits.setSize(10f, 10f);
//        exit.setSize(10f, 10f);


        /** the table is used to place the elements of the screen */
        Table table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);

//        /** I don't understant what was done here still have to see the effects*/
//        Title =new Label("Heart of Ages", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
//        //newGame =new Label("(N)ew Game", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
//        Instructions = new Label("(I)nstructions", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
//        Credits = new Label("(C)redits", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
//        Exit = new Label("(E)xit", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
//
//        Title.setFontScale(2, 2);
//        //newGame.setFontScale(1.2f, 1.2f);
//        Instructions.setFontScale(1.2f, 1.2f);
//        Credits.setFontScale(1.2f, 1.2f);
//        Exit.setFontScale(1.2f, 1.2f);


//        table.add(Title).expandX().padTop(100);
//        table.row();
//        table.add(newGame).expandX().padTop(50);
//        table.row();
//        table.add(Instructions).expandX().padTop(50);
//        table.row();
//        table.add(Credits).expandX().padTop(50);
//        table.row();
//        table.add(Exit).expandX().padTop(50);

//        newgame.addListener(new ClickListener(){
//            public void clicked(InputEvent e, float x, float y) {
//                game.setPos(6150, 7100);
//                game.setScreen(new MainLand(game));
//             }
//        });

        back.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y)
            {
                back.setSize(100, 100);
                //game.setScreen(new MainMenuScreen(game));
            }
        });


        table.setBackground(background);

        table.add();
        table.add(back).width(buttwidth).height(buttheight).pad(50,0,0,0);
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
