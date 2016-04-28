package com.hoa.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;


/**
 * Created by lorenzo on 28/04/16.
 */

public class Hud {
    public Stage stage;
    public Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label healthLabel;
    Label levelLabel;
    Label zoneLabel;
    Label playerLabel;

    public Hud(SpriteBatch spriteBatch){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

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
        table.add(zoneLabel).expandX().padTop(10);
        table.row();
        table.add(healthLabel).expandX();
        table.add(levelLabel).expandX();

        stage.addActor(table);
    }


}
