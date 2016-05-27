package com.hoa.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.hoa.game.screens.MainLand;
import com.badlogic.gdx.utils.Array;

/**
 * Created by lorenzo on 28/04/16.
 */
public class Player extends Sprite{
    public enum State {IDLE,RUNNING_RIGHT,RUNNING_LEFT,RUNNING_UP,RUNNING_DOWN};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion playerIdle;
    private Animation playerRun;
    private float stateTimer;
    private boolean runningRight;

    public Player(World world, MainLand screen, int x, int y){

        super(screen.getAtlas().findRegion("running_down"));

        this.world = world;

        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        runningRight = true;

//        Array<TextureRegion> frames = new Array<TextureRegion>();
//        for(int i = 1; i < 4; i++)
//            frames.add(new TextureRegion(getTexture(),i * 32, 0, 32 ,32));
//
//        playerRun = new Animation(0.1f, frames);
//        frames.clear();


        playerIdle = new TextureRegion(getTexture(), 0, 0, 32, 32);
        setBounds(0, 0, 32, 32);
        setRegion(playerIdle);

        definePlayer(x, y);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2,b2body.getPosition().y - getHeight() / 2);

//        setRegion(getFrame(dt));
    }

//    public TextureRegion getFrame(float dt){
//        currentState = getState();
//
//        TextureRegion region;
//        switch(currentState){
//            case RUNNING_UP:
//                region = playerRun
//        }
//    }

//    public State getState(){
//        if(b2body.getLinearVelocity().y > 0)
//            return State.RUNNING_UP;
//        else if(b2body.getLinearVelocity().y < 0)
//            return State.RUNNING_DOWN;
//        else if(b2body.getLinearVelocity().x != 0)
//            return State.RUNNING_RIGHT;
//        else
//            return State.IDLE;
//    }

    /** Defining the player */
    public void definePlayer(int x, int y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape top = new EdgeShape();
        top.set(new Vector2(-15, 15), new Vector2(15, 15));
        EdgeShape bot = new EdgeShape();
        bot.set(new Vector2(-15, -15), new Vector2(15, -15));
        EdgeShape left = new EdgeShape();
        left.set(new Vector2(-15, +15), new Vector2(-15, -15));
        EdgeShape right = new EdgeShape();
        right.set(new Vector2(15, +15), new Vector2(15, -15));

        fdef.shape = top;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("top");

        fdef.shape = bot;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("bot");

        fdef.shape = left;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("left");

        fdef.shape = right;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("right");


    }
}
