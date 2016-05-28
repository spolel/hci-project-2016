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

    public HoA game;

    private TiledMap map;

    public Stage stage;
    public Viewport viewport;


    private Label Title;
    public Label Counter;
    private Label Out;
    public Button enemy;
//    private Button enemyrender;


    private Boss enemyBoss;

    private String bossName;
    private int bossLife;
    private Texture bossTexture;

    private int counter = 0;
    private String countertot;
    public boolean killed = false;

    private Table table;


    public CombatHud(SpriteBatch batch, Boss boss , HoA game, TiledMap map){

        this.map = map;
        this.game = game;



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
      //  enemy = new Image(drawenemy);

        enemy = new Button(drawenemy);



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

        enemy.addListener(new ClickListener(Input.Keys.LEFT){
//            @Override
//            public boolean keyUp(InputEvent event, int keycode) {
//                damageHandler();
//                return super.keyUp(event, keycode);
//            }
        });



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

    // method to update labels in combat (health, time,...)
    public void manageLabel(Label Counter){
        if(killed = false) {
            String hp = bossName + " life : " + (bossLife - (counter * game.dmg));
            Counter.setText(hp);
        }
     /**   else {
            String hp = "You defeated " + bossName;
            Counter.setText(hp);
        } */


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

//    // method to call in the CombatScreen class with the input just touched method (the clicker)
//
//    // this is the "new" prototype for combat, it should be easier to understand and further modify since it only uses
//    // the key handler to calculate damage
//    // ****TESTED NOT FINISHED****     TODO: combat text, timer, labels, ...
//    public void damageHandler() {
//        if(killed == false) {
//            bossLife = bossLife - game.dmg;
//            counter = counter +1;
//            manageLabel(Counter);
//            if (bossLife <= 0){
//                killed = true;
//            }
//        }
//        else if (killed == true){
//            game.setScreen(new MainLand(game));
//        }

     // Old, working, combatHud uncomment to make CombatHud great again.
     /**   if(killed){   // this is to make this if enter only once.

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

        } */
     // }





//    private void victory(){
//
//        int currentxp = game.xp;
//        int boss_xp = enemyBoss.getXp();
//        int boss_layer = enemyBoss.getLayer();
//
//
//        //handle level xp end so on
//        if( (currentxp + boss_xp) >= game.xpthresh){  // when you increase level
//            game.setLevel(game.level+1);
//            game.setXP((currentxp + boss_xp) - game.xpthresh);
//            game.setXPThreshold(game.xpthresh * 2);
//        }
//        else{
//            game.setXP(game.xp + boss_xp);
//        }


        // deactivate the layers  --> GOD DONUT NEED TO IMPLEMENT LAYER NUMBER IN BOSS CLASS
//
//        map.getLayers().remove(boss_layer);
//        map.getLayers().remove(boss_layer+1);
//
//
//    }


//
//    public Button getEnemy(){
//        return enemy;
//    }


    @Override
    public void dispose() {
        stage.dispose();
    }

}
