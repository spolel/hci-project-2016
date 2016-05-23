//package com.hoa.game.Scenes;
//
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
//import com.badlogic.gdx.utils.Disposable;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//import com.hoa.game.HoA;
//

/**
 * Created by lorenzo on 28/04/16.
 */

//public class Hud extends Table implements Disposable{
//    public Stage stage;
//    public Viewport viewport;
//
//    private Integer score;
//    //private Image textureplayer;
//
//    Label healthLabel;
//    Label levelLabel;
//    Label zoneLabel;
//    Label playerLabel;

//
//    public Hud(SpriteBatch spriteBatch){
//        score = 0;
//
//
//        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
//        stage = new Stage(viewport, spriteBatch);
//
//
//        //Texture texplayer = new Texture("Sprites/pg/HoA_sprite.png");
//        //Sprite splayer = new Sprite(texplayer);
//        //splayer.setSize(35f, 35f);
//        //splayer.setPosition(splayer.getX()+15f, splayer.getY());
//        //SpriteDrawable drawplayer = new SpriteDrawable(splayer);
//        //textureplayer = new Image(drawplayer);
//
//        /** the table is used to place the elements of the screen */
//        Table table = new Table();
//        table.top();
//        /** This will se the table to the size of the screen */
//        table.setFillParent(true);
//
//        /** I don't understant what was done here still have to see the effects*/

//        playerLabel = new Label("GOLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
//        table.add(playerLabel).expandX().padTop(10);
//        table.add(nullLabel);
//        table.add(zoneLabel).expandX().padTop(10);
//        table.row();
//        table.add(healthLabel).expandX();
//        table.add(nullLabel);
//        table.add(levelLabel).expandX();
//        table.row();
//        table.add(nullLabel);
//        //table.add(textureplayer).pad(0, 30f,50f,0).padTop(viewport.getScreenHeight()/2);
//        table.add(nullLabel);
//
//
//        stage.addActor(table);
//
//    }
//
//
//    @Override
//    public void dispose() {
//        stage.dispose();
//    }
//}



package com.hoa.game.Scenes;

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
public class inventoryHud extends Table implements Disposable{

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




    public inventoryHud(SpriteBatch spriteBatch, HoA game){

        buttheight = 50;
        buttwidth = 200;
        //test = game.getZone();


        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        //background = new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));


        //CHANGE IMAGE OF HEARTS
        switch (game.health){
            case 1: healthbutton =  new SpriteDrawable(new Sprite(new Texture("Items/ironsword.png")));
                break;
            case 2: healthbutton =  new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));
                break;
            case 3: healthbutton =  new SpriteDrawable(new Sprite(new Texture("Menu/background.jpg")));
                break;
            case 4: healthbutton =  new SpriteDrawable(new Sprite(new Texture("Items/ironsword.png")));
                break;
        }

        health = new Button (healthbutton);

        invbutton = new SpriteDrawable(new Sprite(new Texture("Items/ironsword.png")));
        inventory = new Button(invbutton);

        //healthLabel =new Label("Health: "+ Integer.toString(game.health) + " / " + Integer.toString(game.healththresh), new Label.LabelStyle(new BitmapFont(), Color.RED));
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
//        Gdx.input.setInputProcessor(stage);


        inventory.addListener(new ClickListener() {});


//        table.setBackground(background);


        table.add(health).padTop(10);
        table.add(nullLabel).expandX();
        table.add(zoneLabel).expandX().padTop(10);


        table.row();
        table.add(levelLabel);
        table.add(nullLabel);
        table.row();
        table.add(xpLabel);
        table.add(nullLabel);
        table.add(nullLabel);
        table.row();
        table.add(nullLabel);
        table.add(nullLabel);
        table.add(inventory).pad(450,0,0,0);

        //table.add(newgame).width(buttwidth).height(buttheight).padTop(70);
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
