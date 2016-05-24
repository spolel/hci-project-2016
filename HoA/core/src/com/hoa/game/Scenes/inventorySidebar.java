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


    public Button exit;
    public Button health;



    private Label healthLabel;
    private Label levelLabel;
    private Label zoneLabel;
    private Label xpLabel;
    private Label InventoryLabel;
    private Label nullLabel;


    //    private ImageTextButton xpButton;
//    private Drawable xpbuttonimg;
    private Drawable healthbutton;
    private Drawable exitbutton;


    private float buttwidth;
    private float invpadding;
    private float padleft;





    public inventorySidebar(SpriteBatch spriteBatch, HoA game){

//        buttheight = 50;
//        buttwidth = 200;
        //test = game.getZone();
        invpadding = game.screenHeight/20;
        padleft = game.screenWidth/16;


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

        exitbutton = new SpriteDrawable(new Sprite(new Texture("Icons/exit.png")));
        exit = new Button(exitbutton);

        healthLabel = new Label("Lives: ", new Label.LabelStyle(new BitmapFont(), Color.RED));
        levelLabel = new Label("Level:                               " + Integer.toString(game.level), new Label.LabelStyle(new BitmapFont(), Color.RED));
        xpLabel = new Label("XP:                                " + Integer.toString(game.xp) + " / " + Integer.toString(game.xpthresh), new Label.LabelStyle(new BitmapFont(), Color.RED));
        zoneLabel = new Label("Zone:                          " + game.getZone(), new Label.LabelStyle(new BitmapFont(), Color.RED));

        InventoryLabel = new Label("Inventory", new Label.LabelStyle(new BitmapFont(), Color.RED));
        InventoryLabel.setFontScale(2f);



        //newbutton = new SpriteDrawable( new Sprite(new Texture("Menu/newgamebutton.png")));



        // newgame = new Button(newbutton);


        /** the table is used to place the elements of the screen */
        Table table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);

        //background.setLeftWidth(400);
        //background.set(200);


        exit.addListener(new ClickListener() {});


        table.add(InventoryLabel).pad(invpadding, padleft, 0, 0).left();
        table.row();
        table.add(healthLabel).pad(invpadding, padleft, 0, 0).width(20).left();
        table.add(health).pad(invpadding, 0, 0, 800).left();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.row();
        table.add(levelLabel).expandX().pad(invpadding, padleft, 0, 0).left();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.row();
        table.add(xpLabel).expandX().pad(invpadding, padleft, 0, 0).left();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();

        table.row();
        table.add(zoneLabel).expandX().pad(invpadding, padleft, 0, 0).left();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();

        table.row();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();
        table.add(nullLabel).expandX();

        table.add(exit).pad(game.screenHeight*4/10,0, 0, 3*invpadding);
        table.setSize(200,600);
        table.setBackground(background);

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
