package com.hoa.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;
import com.hoa.game.Scenes.Hud;
import com.hoa.game.Scenes.CombatHud;
import com.hoa.game.Sprites.Boss;
import com.hoa.game.Sprites.Player;
import com.hoa.game.Tools.B2WorldCreator;
import com.hoa.game.Tools.CountDownTimer;
import com.hoa.game.Tools.WorldContactListener;
import com.badlogic.gdx.graphics.Texture;

import java.util.Timer;

import static java.lang.Math.log;

/**
 * Created by shughi on 17/05/2016.
 */
public class CombatScreen implements Screen {

    //game class


    //hud of the program
    private Boss boss;

    private static final int BATTLE_COUNTDOWN_SECONDS = 10;

    //Label newGame;
    //Label

    //game class
    private HoA game;
    //private CombatScreen current;
    private Viewport gamePort;
    private Stage stage;
    private InputMultiplexer input;
    private TiledMap map;
    // Current game camera & screen display, currently a FitViewPort
    //hud of the program
    private CombatHud combatscene;
    private Boss enemyBoss;
    private String bossName;
    private double bossLife;
    public boolean killed = false;
    private int tot = 0;
    long startTime;
    long endTime;
    long fightlast;
    private CountDownTimer timer;
    //private boolean running;
    private boolean defeated;
    private boolean startedfight = false;
    private SuperClass current;

    public CombatScreen (HoA game, Boss boss, TiledMap map, SuperClass current){


        //actual game variable
        this.game = game;
        this.enemyBoss = boss;
        this.map = map;
        defeated=false;
        this.current=current;

        this.bossLife = boss.getLife();
        this.bossName = boss.getName();

        stage = new Stage(new FitViewport(HoA.screenWidth, HoA.screenHeight));
        input = new InputMultiplexer();

        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight);

        //hud
        combatscene = new CombatHud(game.batch, enemyBoss , game, map);


        }



    // This part moves the camera on the wasd key input.
    // created by shughi
    //using raw velocity, stops when stop pressing;
    //only one key usable now
    //if the else if is removed weird stuff happens
    public void handleInput(float dt){

        //insert click listener

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.collisioncount=0;


            String zone = game.getZone();
            if (zone.equals("Main Land")) {



                MainLand m = (MainLand) current;
                World world = m.getWorld();
                SuperClass b = new MainLand(game);
                int x = (int)m.player.b2body.getPosition().x;
                int y = (int)m.player.b2body.getPosition().y;
                b.player= new Player(world, b, x, y);
                b.hud=new Hud(game.batch, game);
                game.setScreen(b);

            }


            if (zone.equals("Volcano")) {

                Volcano m = (Volcano) current;
                World world = m.getWorld();
                SuperClass b = new Volcano(game);
                int x = (int)m.player.b2body.getPosition().x;
                int y = (int)m.player.b2body.getPosition().y;
                b.player= new Player(world, b, x, y);
                b.hud=new Hud(game.batch, game);
                game.setScreen(b);
            }

             if (zone.equals("Cave")) {

                 Cave m = (Cave) current;
                 World world = m.getWorld();
                 SuperClass b = new Cave(game);
                 int x = (int)m.player.b2body.getPosition().x;
                 int y = (int)m.player.b2body.getPosition().y;
                 b.player= new Player(world, b, x, y);
                 b.hud=new Hud(game.batch, game);
                 game.setScreen(b);
             }


        }

        else if (Gdx.input.justTouched()) {

            damageHandler();
        }

    }


    public void damageHandler(){
        if(killed){   // this is to make this if enter only once.
            if (tot >= bossLife-game.dmg || ((System.currentTimeMillis() - startTime)/1000)<0) {  // boss is dead. This is the click that kills him, or 10 sec have passed.
                if (fightlast <= 10) {
                    String ciao = "You defeated the " + bossName + " in : " + fightlast + " seconds!";
                    combatscene.setCounter(ciao);
                } else {
                    String ciao = "" + "Too slow! The " + bossName + " defeated you in " + fightlast + " seconds!";  //added the ""+ at the beginning just to avoid a stupid feature on intelliJ about duplicates
                    combatscene.setCounter(ciao);
                }
            }
        }
        else  {
            if (tot == 0) {  // fight is about to start
                startTime = System.currentTimeMillis();
                tot = tot + game.dmg;
                double giorgio = bossLife - tot;
                String hue = bossName + " life : " + giorgio;
                combatscene.setCounter(hue);
                combatscene.setOut("Currently Fighting!");

                timer = new CountDownTimer(BATTLE_COUNTDOWN_SECONDS,( new Runnable() {
                    public void run() {
                        //@TODO this executes when the timer is 0
                        //timer.cancel();
                        defeated=true;
                        killed = true;
                        //current.defeat();
                    }
                }));

                new Timer().scheduleAtFixedRate(timer, 0, 1000);
                startedfight=true;

            }
            else if(tot>=bossLife-game.dmg){
                endTime = System.currentTimeMillis();
                fightlast = ((endTime - startTime) / 1000);
                killed = true;
                if (fightlast <= 10) {
                    String ciao = "You defeated the " + bossName + " in : " + fightlast + " seconds!";
                    combatscene.setCounter(ciao);
                    victory();
                } else {
                    String ciao = "" + "Too slow! The " + bossName + " defeated you in " + fightlast + " seconds!";  //added the ""+ at the beginning just to avoid a stupid feature on intelliJ about duplicates
                    combatscene.setCounter(ciao);
                    defeat();
                }
            }
            else {  // fight is going on
                tot = tot + game.dmg;
                double giorgio = bossLife - tot;
                String hue = bossName + " life : " + giorgio;
                combatscene.setCounter(hue);
            }
        }
    }

    public void update(float dt){
        handleInput(dt);
        defeat();
        if(startedfight){timerf();}
    }

    public void timerf(){

        String text = "time: "+ timer.getCount();
        combatscene.setTimer(text);

    }


    public void victory(){
        //System.out.println("hello");
        game.collisioncount=0;
        timer.cancel();
        int boss_xp = enemyBoss.getXp();
        int boss_layer = enemyBoss.getLayer();
        //handle level xp end so on
        if( (game.xp + boss_xp) >= game.xpthresh){  // when you increase level
            game.xp = game.xp + boss_xp;
            while (game.xp - game.xpthresh >= 0) {  // if you increase more than 1 level
                game.xp = game.xp - game.xpthresh;
                game.xpthresh = game.xpthresh + game.xpthresh*(game.level-1);
                game.level++;
                game.dmg=game.dmg*(int)log(10+game.dmg);
            }
            if (game.level % 5 == 0 & game.level < 10) {

                    game.healththresh++;
                    game.health++;
                } else if (game.health < game.healththresh) {
                    game.health++;
                }

            combatscene.setOut("You gained " + boss_xp + "XP, and you are now level "+game.level+ "!"+" press ESC to continue.");
        }
        else{
            game.setXP(game.xp + boss_xp);
            combatscene.setOut("You gained " + boss_xp + "XP, press ESC to continue.");
        }
        // deactivate the layers  --> GOD DONUT NEED TO IMPLEMENT LAYER NUMBER IN BOSS CLASS
        // NOT WORKING
        //   map.getLayers().remove(boss_layer);
        //   map.getLayers().remove(boss_layer+1);
    }
    public void defeat(){
        if(defeated){
            defeated=false;
            game.collisioncount=0;
            game.decreaseHealth();
            if (bossName == "Berserk Bandit") {
                combatscene.setOut("You got rekt son! Press ESC to continue.");

            }
            else {
                combatscene.setOut("You lost 1 heart and you are now at " + game.health + " HP! Press ESC to continue.");
            }
        //game.setScreen(new MainMenuScreen(game));
            }
    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //stage.act(delta);
        //stage.setDebugAll();

        //stage.getViewport().apply();
        combatscene.stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        stage.getViewport().update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        combatscene.dispose();

    }
}