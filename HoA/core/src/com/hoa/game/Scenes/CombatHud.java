package com.hoa.game.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;

/**
 * Created by shughi on 19/05/2016.
 */
public class CombatHud extends Table implements Disposable{

    public HoA game;
    public Stage stage;
    public Viewport viewport;


    //
    //private Actor newGame;
//    private Actor Instructions;
//     private Actor Credits;
//    private Actor Exit;

    private Label Title;
    private Label Counter;
    private Label Out;



    private int tot = 0;
    private String countertot = "counter is : " + tot;



    public CombatHud(SpriteBatch spriteBatch){


        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        /** the table is used to place the elements of the screen */
        Table table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);


        Title =new Label("This is a combat screen test", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Counter =new Label(countertot, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Out = new Label("press H to return to the game, V to add counter", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        Title.setFontScale(2, 2);
        Counter.setFontScale(1.2f, 1.2f);
        Out.setFontScale(1.2f, 1.2f);



        table.add(Title).expandX().padTop(100);
        table.row();
        table.add(Counter).expandX().padTop(50);
        table.row();
        table.row();
        table.add(Out).expandX().padTop(50);


        stage.addActor(table);

    }

    // method to call in the CombatScreen class
    public void addCounter(){
        tot = tot+1;
        Counter.setText("counter is : " + tot);
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
