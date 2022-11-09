package lwjgl_leren.engine.io;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

public class WindowManager {
	
	private long glfw_window;
	private int window_width, window_height;
	private final String WINDOW_TITLE;
	private GLFWVidMode video_mode;
	private final float SCALE = 2.0f;
	
	public WindowManager() {
		WINDOW_TITLE = "lwjgl-leren";
	}
	
	public void createWindow() {
		video_mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		window_width = (int) (video_mode.width() / SCALE);
		window_height = (int) (video_mode.height() / SCALE);
		
		glfw_window = glfwCreateWindow(window_width, window_height, WINDOW_TITLE, 0, 0);
		glfwMakeContextCurrent(glfw_window);
		GL.createCapabilities();
		
		glfwSetWindowPos(
				glfw_window, 
				(video_mode.width() / 2) - (window_width / 2), 
				(video_mode.height() / 2) - (window_height / 2));
		
		glfwSetFramebufferSizeCallback(glfw_window, new GLFWFramebufferSizeCallback() {
			@Override
			public void invoke(long glfw_window, int width, int height) {
				window_width = width;
				window_height = height;
				GL33.glViewport(0, 0, width, height);
			}
		});
		
		//glfwSwapInterval(1);
	}
	
	public void destroyWindow() {
		glfwDestroyWindow(glfw_window);
	}

	public long getGlfw_window() {
		return glfw_window;
	}

	public int getWindow_width() {
		return window_width;
	}

	public int getWindow_height() {
		return window_height;
	}

	public String getWINDOW_TITLE() {
		return WINDOW_TITLE;
	}		

}
