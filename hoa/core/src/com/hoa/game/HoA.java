package com.hoa.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoa.game.screens.MainMenuScreen;

public class HoA extends Game {
	public static final int screenWidth = 800;
	public static final int screenHeight = 600;
	public int posx;
	public int posy;

	public SpriteBatch batch;

	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPos(int x, int y)
	{
		posx = x;
		posy = y;
	}


	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
;	}

	@Override

	public void render () {
		super.render();
	}
}
