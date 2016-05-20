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

/**
 * Created by shughi on 19/05/2016.
 */
public class CombatHud extends Table implements Disposable{

    public HoA game;
    public Stage stage;
    public Viewport viewport;


    private Label Title;
    private Label Counter;
    private Label Out;
    private Image enemy;



    private int tot = 0;
    private String countertot = "counter is : " + tot;



    public CombatHud(SpriteBatch batch){


        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Texture texenemy = new Texture("Sprites/Bosses/Flaming_ent.png");
        Sprite sprenemy = new Sprite(texenemy);
        sprenemy.setSize(500f, 1000f);
        SpriteDrawable drawenemy = new SpriteDrawable(sprenemy);
        enemy = new Image(drawenemy);


        /** the table is used to place the elements of the screen */
        Table table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);


        Title =new Label("This is a combat screen test", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Counter =new Label(countertot, new Label.LabelStyle(new BitmapFont(), Color.RED));
        Out = new Label("press ESC to return to the game, V to add counter", new Label.LabelStyle(new BitmapFont(), Color.BLACK));


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
    public void addCounter(){
        tot = tot+420;
        Counter.setText("counter is : " + tot);
    }


    

    @Override
    public void dispose() {
        stage.dispose();
    }

}
