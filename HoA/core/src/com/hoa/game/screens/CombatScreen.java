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
import com.hoa.game.Tools.WorldContactListener;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by shughi on 17/05/2016.
 */
public class CombatScreen implements Screen {

    //game class
    private HoA game;
    private Viewport gamePort;

    private Stage stage;
    private InputMultiplexer input;

    private TiledMap map;

    // Current game camera & screen display, currently a FitViewPort


    //hud of the program
    private CombatHud combatscene;
    private Boss boss;

    //Label newGame;
    //Label

    public CombatScreen (HoA game, Boss boss, TiledMap map){

        //actual game variable
        this.game = game;
        this.boss = boss;
        this.map = map;

        stage = new Stage(new FitViewport(HoA.screenWidth, HoA.screenHeight));
        input = new InputMultiplexer();

        gamePort = new FitViewport(HoA.screenWidth, HoA.screenHeight);

        //hud
        combatscene = new CombatHud(game.batch, boss , game, map);




    }

    // Method to see if the target is killed, used for adding xp after the combat
    public boolean isKill(){
        return combatscene.killed;
    }




    // This part moves the camera on the wasd key input.
    // created by shughi
    //using raw velocity, stops when stop pressing;
    //only one key usable now
    //if the else if is removed weird stuff happens
    public void handleInput(float dt){

        //insert click listener

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainLand(game));
        }

        else if (Gdx.input.justTouched()) {

            combatscene.damageHandler();
        }

    }

    public void update(float dt){
        handleInput(dt);
        // This is used for the new combat, comment when using old combat.
        if(isKill() == true) {
            game.xp = game.xp + boss.getXp();
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

        // stage.act(delta);
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
        // combatscene.dispose();

    }
}