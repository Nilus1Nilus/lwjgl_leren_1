package lwjgl_leren.engine.core;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.reflections.Reflections;

import lwjgl_leren.engine.gfx.Renderer;
import lwjgl_leren.engine.gfx.mesh.MeshLoader;
import lwjgl_leren.engine.io.WindowManager;
import lwjgl_leren.engine.util.Logger;

@SuppressWarnings("unused")
public class GameContainer {
	
	private static GameContainer instance = null;
	public static GameContainer getInstance() {
		if(instance == null)
			instance = new GameContainer();
		return instance;
	}
	
	private Logger logger;
	private WindowManager window_manager;
	private Renderer renderer;
	private MeshLoader loader;
	
	private Game game;
	
	private GameContainer() {
		logger = Logger.getInstance();
		window_manager = new WindowManager();
		renderer = new Renderer();
		loader = MeshLoader.getInstance();
		Reflections reflections = new Reflections("lwjgl_leren");
		Set<Class<? extends Game>> sub_types = reflections.getSubTypesOf(Game.class);
		if(sub_types.size() > 1)
			logger.log("Can't have more than 1 game.", System.err);
		sub_types.forEach(i -> {
			try {
				Constructor<? extends Game> constructor = i.getConstructor(this.getClass());
				game = constructor.newInstance(this);
				logger.log("Game has been instantiated.", System.out);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void init() {
		logger.write_log = true;
		logger.init();
		
		glfwSetErrorCallback(new GLFWErrorCallback() {
			@Override
			public void invoke(int error, long description) {
				logger.log(error + ": " + getDescription(description), System.err);
			}
		});
		
		glfwInit();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		
		window_manager.createWindow();
		renderer.init();
		game.init();
	}
	
	public void run() {
		double unprocessed = 0d;
		double max_unprocessed = 1d / 60d;
		double timer = 0d;
		int ups = 0;
		int fps = 0;
		
		logger.log("Test started.", System.out);
		double last_time = 0d;
		while(!glfwWindowShouldClose(window_manager.getGlfw_window())) {
			double first_time = glfwGetTime();
			double delta_time = first_time - last_time;
			last_time = first_time;

			unprocessed += delta_time;
			timer += delta_time;
			
			while(unprocessed > max_unprocessed) {
				unprocessed -= max_unprocessed;
				// TODO: update
				glfwPollEvents();
				ups++;
				game.update(delta_time);
			}
			// TODO: render
			renderer.render(window_manager, game);
			fps++;
			
			if(timer >= 1) {
				timer = 0;
				glfwSetWindowTitle(
						window_manager.getGlfw_window(), 
						window_manager.getWINDOW_TITLE() + " | FPS: " + fps + ", UPS:" + ups);
				ups = 0;
				fps = 0;
			}
		}
		dispose();
	}
	
	private void dispose() {
		logger.clean();
		window_manager.destroyWindow();
		loader.clean();
		glfwTerminate();
		logger.log("Test ended.", System.out);
	}

}
