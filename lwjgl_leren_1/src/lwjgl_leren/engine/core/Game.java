package lwjgl_leren.engine.core;

import lwjgl_leren.engine.gfx.Renderer;

public abstract class Game {
	
	protected GameContainer game_container;
	public Game(GameContainer game_container) {
		this.game_container = game_container;
	}
	
	public abstract void init();
	public abstract void render(Renderer renderer);
	public abstract void update(double dt);
}
