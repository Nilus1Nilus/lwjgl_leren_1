package lwjgl_leren.game.main;

import lwjgl_leren.engine.core.GameContainer;

public class Launcher {
	
	public static void main(String[] args) {
		GameContainer game_container = GameContainer.getInstance();
		game_container.init();
		game_container.run();
	}

}
