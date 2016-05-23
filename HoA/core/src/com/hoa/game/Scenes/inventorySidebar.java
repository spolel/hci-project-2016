package com.hoa.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
public class inventorySidebar extends Table implements Disposable{

    public HoA game;
    public Stage stage;
    public Viewport viewport;


    private Drawable background;


    public Button inventory;
    public Button health;



    private Label healthLabel;
    private Label levelLabel;
    private Label zoneLabel;
    private Label xpLabel;
    private Label nullLabel;


    //    private ImageTextButton xpButton;
//    private Drawable xpbuttonimg;
    private Drawable healthbutton;
    private Drawable invbutton;


    private float buttwidth;
    private float buttheight;

    private String test;




    public inventorySidebar(SpriteBatch spriteBatch, HoA game){

//        buttheight = 50;
//        buttwidth = 200;
        //test = game.getZone();


       viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);


        background = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));


        //CHANGE IMAGE OF HEARTS
        switch (game.health){
            case 1: healthbutton =  new SpriteDrawable(new Sprite(new Texture("Icons/1hp.png")));
                break;
            case 2: healthbutton =  new SpriteDrawable(new Sprite(new Texture("Icons/2hp.png")));
                break;
            case 3: healthbutton =  new SpriteDrawable(new Sprite(new Texture("Icons/3hp.png")));
                break;
            case 4: healthbutton =  new SpriteDrawable(new Sprite(new Texture("Icons/4hp.png")));
                break;
        }

        health = new Button (healthbutton);

        invbutton = new SpriteDrawable(new Sprite(new Texture("Items/ironsword.png")));
        inventory = new Button(invbutton);

        levelLabel = new Label("Level: " + Integer.toString(game.level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        xpLabel = new Label("XP: " + Integer.toString(game.xp) + " / " + Integer.toString(game.xpthresh), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        zoneLabel = new Label("Zone: " + game.getZone(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));



        //newbutton = new SpriteDrawable( new Sprite(new Texture("Menu/newgamebutton.png")));



        // newgame = new Button(newbutton);


        /** the table is used to place the elements of the screen */
        Table table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);


        inventory.addListener(new ClickListener() {});


        table.add(health).expandX().padTop(20);
        table.row();
        table.add(levelLabel).expandX().padTop(20);
        table.row();
        table.add(xpLabel).expandX().padTop(20);
        table.row();
        table.add(zoneLabel).expandX().padTop(10);
        table.row();
        table.add(inventory);
//        table.setBackground(background);

//        table.setSize(200,600);
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
