package com.hoa.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hoa.game.screens.MainMenuScreen;
import com.hoa.game.screens.PlayScreen;
import com.hoa.game.screens.CombatScreen;

public class HoA extends Game {
	public static final int screenWidth = 800;
	public static final int screenHeight = 600;

	public SpriteBatch batch;

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
