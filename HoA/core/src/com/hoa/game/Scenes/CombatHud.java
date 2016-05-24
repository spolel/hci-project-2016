package com.hoa.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    boolean killed = false;

    private Table table;




    public CombatHud(SpriteBatch batch, Boss boss , HoA game){


        this.game = game;

        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        enemyBoss = boss;

        bossLife = enemyBoss.getLife();
        bossName = enemyBoss.getName();
        bossTexture = enemyBoss.getTexture();

        countertot = bossName+" life : " + bossLife;

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

        if(killed){   // this is to make this if enter only once.

         if (tot >= bossLife-1 || ((System.currentTimeMillis() - startTime)/1000)<0) {  // boss is dead. This is the click that kills him, or 10 sec have passed.


            if (fightlast <= 10) {
                String ciao = "You beated the " + bossName + " in : " + fightlast + " seconds!";
                Counter.setText(ciao);
            } else {
                String ciao = "" + "Too slow! The " + bossName + " beated you in " + fightlast + " seconds!";  //added the ""+ at the beginning just to avoid a stupid feature on intelliJ about duplicates
                Counter.setText(ciao);
            }
        }


        }

        else  {

            if (tot == 0) {  // fight is about to start

                startTime = System.currentTimeMillis();
                tot = tot + 1;
                int giorgio = bossLife - tot;
                Counter.setText(bossName + " life : " + giorgio);

            }
            else if(tot==bossLife-1){

                endTime = System.currentTimeMillis();
                fightlast = ((endTime - startTime) / 1000);


                killed = true;

                if (fightlast <= 10) {
                    String ciao = "You beated the " + bossName + " in : " + fightlast + " seconds!";
                    Counter.setText(ciao);
                    victory();

                } else {
                    String ciao = "" + "Too slow! The " + bossName + " beated you in " + fightlast + " seconds!";  //added the ""+ at the beginning just to avoid a stupid feature on intelliJ about duplicates
                    Counter.setText(ciao);
                    defeat();
                }


            }
            else {  // fight is going on
                tot = tot + 1;
                int giorgio = bossLife - tot;
                Counter.setText(bossName + " life : " + giorgio);
            }

        }
    }





    private void victory(){

        int currentxp = game.xp;
        int boss_xp = enemyBoss.getXp();

        if( (currentxp + boss_xp) >= game.xpthresh){
            game.setLevel(game.level+1);
            game.setXP((currentxp + boss_xp) - game.xpthresh);
            game.setXPThreshold(game.xpthresh * 2);
        }
        else{
            game.setXP(game.xp + boss_xp);
        }
        // deactivate the layers  --> GOD DONUT NEED TO IMPLEMENT LAYER NUMBER IN BOSS CLASS

    }

    private void defeat() {

        game.decreaseHealth();

    }
    

    @Override
    public void dispose() {
        stage.dispose();
    }

}
