package com.hoa.game.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Sprites.Boss;

/**
 * Created by shughi on 19/05/2016.
 */


public class CombatHud extends Table implements Disposable{

    public HoA game;
    public Stage stage;
    public Viewport viewport;

    long startTime;
    long endTime;
    long fightlast;


    private Label Title;
    private Label Counter;
    private Label Out;
    private Image enemy;

    private Boss enemyBoss;

    private String bossName;
    private int bossLife;
    private Texture bossTexture;

    private int tot = 0;
    private String countertot;

    private Table table;




    public CombatHud(SpriteBatch batch, Boss boss){




        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        enemyBoss = boss;

        bossLife = enemyBoss.getLife();
        bossName = enemyBoss.getName();
        bossTexture = enemyBoss.getTexture();

        countertot = "Boss life : " + bossLife;

        Sprite sprenemy = new Sprite(bossTexture);
        sprenemy.setSize(500f, 1000f);
        SpriteDrawable drawenemy = new SpriteDrawable(sprenemy);
        enemy = new Image(drawenemy);


        /** the table is used to place the elements of the screen */
        table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);

        String title1 = "You are fighting now: " + bossName ;
        Title =new Label(title1, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Counter =new Label(countertot, new Label.LabelStyle(new BitmapFont(), Color.RED));
        Out = new Label("press ESC to return to the game, click to add counter", new Label.LabelStyle(new BitmapFont(), Color.BLACK));


        Title.setFontScale(1.2f, 1.2f);
        Counter.setFontScale(1.2f, 1.2f);
        Out.setFontScale(1.2f, 1.2f);



        table.add(Title).expandX().padTop(100);
        table.row();
        table.add(Counter).expandX().padTop(50);
        table.row();
        table.add(enemy);
        table.row();
        table.row();
        table.add(Out).expandX().padTop(50);


        stage.addActor(table);

    }

    // method to call in the CombatScreen class
    public void addCounter() {

        if(tot == 0){

        startTime = System.currentTimeMillis();
            tot = tot + 1;
            int giorgio = bossLife - tot;
            Counter.setText("Boss life : " + giorgio);

        }
        else if (tot == bossLife-1) {
            tot = tot+1;
            fightlast = ((System.currentTimeMillis() - startTime) / 1000);
            String ciao = "Time passed since the fight started: " + fightlast;
            Counter.setText(ciao);

        }
        else if(tot >= bossLife){

            if(fightlast <= 10){

            Counter.setText("Boss is dead! You gained 100 exp!");
                // add experience

        }
            else{
                Counter.setText("Too slow! You lost 1 heart!");
                //remove 1 life
            }


            }

        else {
            tot = tot + 1;
            int giorgio = bossLife - tot;
            Counter.setText("Boss life : " + giorgio);
        }
    }


    

    @Override
    public void dispose() {
        stage.dispose();
    }

}
