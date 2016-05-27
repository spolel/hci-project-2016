package com.hoa.game.Tools;

import com.badlogic.gdx.physics.box2d.*;
import com.hoa.game.Sprites.InteractiveTile;

/**
 * Created by BMW on 10/05/2016.
 */
public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (a.getUserData() == "top" || a.getUserData() == "bot" || a.getUserData() == "left" || a.getUserData() == "right" ||
                b.getUserData() == "top" || b.getUserData() == "bot" || b.getUserData() == "left" || b.getUserData() == "right") {

            Fixture player = (a.getUserData() == "top" || a.getUserData() == "bot" || a.getUserData() == "left" || a.getUserData() == "right") ? a : b;
            Fixture object = (b.getUserData() == "top" || b.getUserData() == "bot" || b.getUserData() == "left" || b.getUserData() == "right") ? a : b;

            //checks if objects is from class interactive tile
            if (object.getUserData() != null && InteractiveTile.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTile) object.getUserData()).onCollision();
                float x = player.getBody().getPosition().x;
                float y = player.getBody().getPosition().y;
            }
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
