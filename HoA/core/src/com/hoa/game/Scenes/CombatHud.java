package com.hoa.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Sprites.Boss;
import com.hoa.game.screens.MainLand;


/**
 * Created by shughi on 19/05/2016.
 */


public class CombatHud extends Table implements Disposable{

    private SpriteDrawable background;
    public HoA game;

    private TiledMap map;

    public Stage stage;
    public Viewport viewport;


    private Label Title;
    public Label Counter;
    private Label Out;
    public Button enemy;
    private Label Timer;


    private Boss enemyBoss;

    private String bossName;
    private double bossLife;
    private Texture bossTexture;

    private String countertot;
    
    private Table table;


    public CombatHud(SpriteBatch batch, Boss boss , HoA game, TiledMap map){

        this.map = map;
        this.game = game;
        this.background=boss.getBackground();



        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);


        enemyBoss = boss;

        bossLife = enemyBoss.getLife();
        bossName = enemyBoss.getName();
        bossTexture = enemyBoss.getTexture();

        countertot = bossName+" life : " + bossLife;

        Sprite sprenemy = new Sprite(bossTexture);
        sprenemy.setSize(300f, 300f);
        SpriteDrawable drawenemy = new SpriteDrawable(sprenemy);

        enemy = new Button(drawenemy);



        /** the table is used to place the elements of the screen */
        table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);

        String title1 = "You are fighting now: " + bossName ;

        Title =new Label(title1, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Counter =new Label(countertot, new Label.LabelStyle(new BitmapFont(), Color.RED));
        Out = new Label("press E to return to the game, click to start the fight!", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Timer = new Label("Timer: xx",new Label.LabelStyle(new BitmapFont(), Color.BLACK));


        Title.setFontScale(1.2f, 1.2f);
        Counter.setFontScale(1.2f, 1.2f);
        Out.setFontScale(1.2f, 1.2f);

        enemy.addListener(new ClickListener(Input.Keys.LEFT){
//            @Override
//            public boolean keyUp(InputEvent event, int keycode) {
//                damageHandler();
//                return super.keyUp(event, keycode);
//            }
        });



        table.add(Title).expandX().padTop(100);
        table.row();
        table.add(Counter).expandX().padTop(30);
        table.row();
        table.add(Timer).expandX().padTop(30);
        table.row();
        table.add(enemy);
        table.row();
        table.row();
        table.add(Out).expandX().padTop(50);

        table.setBackground(background);


        stage.addActor(table);

    }



    public void setTitle(String text){
        Title.setText(text);
    }
    public void setCounter(String text){
        Counter.setText(text);
    }
    public void setOut(String text){
        Out.setText(text);
    }
    public void setTimer(String text){
        Timer.setText(text);
    }



    @Override
    public void dispose() {
        stage.dispose();
    }

}
