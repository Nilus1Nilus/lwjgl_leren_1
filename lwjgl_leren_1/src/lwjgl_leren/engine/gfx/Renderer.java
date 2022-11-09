package lwjgl_leren.engine.gfx;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;

import org.lwjgl.opengl.GL33;

import lwjgl_leren.engine.core.Game;
import lwjgl_leren.engine.io.WindowManager;

public class Renderer {
	
	public void init() {
		
	}
	
	public void render(WindowManager window_manager, Game game) {
		GL33.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);
		game.render(this);
		glfwSwapBuffers(window_manager.getGlfw_window());
	}
	
	public static void renderMesh() {
		
	}

}
