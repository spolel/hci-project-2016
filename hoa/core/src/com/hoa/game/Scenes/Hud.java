package com.hoa.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;


/**
 * Created by lorenzo on 28/04/16.
 */

public class Hud implements Disposable{
    public Stage stage;
    public Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;
    private Image textureplayer;

    Label healthLabel;
    Label levelLabel;
    Label zoneLabel;
    Label playerLabel;
    Label nullLabel;

    public Hud(SpriteBatch spriteBatch){
        worldTimer = 300;
        timeCount = 0;
        score = 0;


        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);


        Texture texplayer = new Texture("Sprites/pg/HoA_sprite.png");
        Sprite splayer = new Sprite(texplayer);
        splayer.setSize(35f, 35f);
        splayer.setPosition(splayer.getX()+15f, splayer.getY());
        SpriteDrawable drawplayer = new SpriteDrawable(splayer);
        textureplayer = new Image(drawplayer);

        /** the table is used to place the elements of the screen */
        Table table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);

        /** I don't understant what was done here still have to see the effects*/
        healthLabel =new Label(String.format("%05d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("Early Stage", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        zoneLabel = new Label("ZONE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerLabel = new Label("GOLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(playerLabel).expandX().padTop(10);
        table.add(nullLabel);
        table.add(zoneLabel).expandX().padTop(10);
        table.row();
        table.add(healthLabel).expandX();
        table.add(nullLabel);
        table.add(levelLabel).expandX();
        table.row();
        table.add(nullLabel);
        table.add(textureplayer).pad(0, 30f,50f,0).padTop(viewport.getScreenHeight()/2);
        table.add(nullLabel);


        stage.addActor(table);
        
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
