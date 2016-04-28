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

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLable;
    Label worldLable;
    Label playerLable;

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

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
        scoreLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
        timeLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
        levelLable = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
        playerLable = new Label("PLAYER", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));

//        table.add(playerLable).expand()
    }


}
