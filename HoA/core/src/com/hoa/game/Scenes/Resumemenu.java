package com.hoa.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hoa.game.HoA;

/**
 * Created by BMW on 17/05/2016.
 */
public class Resumemenu extends Table implements Disposable{

    public HoA game;
    public Stage stage;
    public Viewport viewport;


    //
    //private Actor newGame;
//    private Actor Instruction;
//     private Actor Credits;
//    private Actor Exit;

    private Label Title;
    private Label resume;
    private Label mainmenu;
    private Label Exit;
    private Image background;




    public Resumemenu(SpriteBatch spriteBatch){


        viewport = new FitViewport(HoA.screenWidth, HoA.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        background = new Image(new Texture("Menu/Pause screen.png"));

        /** the table is used to place the elements of the screen */
        Table table = new Table();
        table.top();
        /** This will se the table to the size of the screen */
        table.setFillParent(true);

        /** I don't understant what was done here still have to see the effects*/
        Title =new Label("Heart of Ages", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        resume =new Label("(R)esume", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mainmenu = new Label("(M)ain Menu", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Exit = new Label("(E)xit", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Title.setFontScale(2, 2);
        resume.setFontScale(1.2f, 1.2f);
        mainmenu.setFontScale(1.2f, 1.2f);
        Exit.setFontScale(1.2f, 1.2f);


//        table.add(Title).expandX().padTop(100);
//        table.row();
//        table.row();
//        table.add(resume).expandX().padTop(50);
//        table.row();
//        table.add(mainmenu).expandX().padTop(50);
//        table.row();
//        table.add(Exit).expandX().padTop(50);

        table.add(background).expandX();

        stage.addActor(table);

    }





//
//    newGame = new NewGame();
//    addActor(newGame);
//    setSize
//    setPosition
//Label.setText();
//
//    extends Button
//    set texture
//            super (new SpriteDrawable(new Sprite(texture)))

//    addListener(new InputListener)
//
//
//        addListener(new InputListener(){
//            public void touchDown() {
//                //...
//            }
//
//            private Image image = new Image();
//        })
//
//
//
//            Label name = new Label(text, Label.LabelStyle(font, size, color))

    @Override
    public void dispose() {
        stage.dispose();
    }

}
