package lwjgl_leren.engine.gfx.mesh;

import java.util.HashMap;

import lwjgl_leren.engine.gfx.BufferDataWrapper;

public class Mesh {
	
	private int vao_id;
	private int vertex_count;
	private HashMap<Integer, BufferDataWrapper<? extends Number>> buffer_data_map;
	
	public Mesh(int vao_id, int vertex_count) {
		this.vao_id = vao_id;
		this.vertex_count = vertex_count;
		buffer_data_map = new HashMap<>();
	}
	
	public int getVao_id() {
		return vao_id;
	}
	
	public int getVertex_count() {
		return vertex_count;
	}
	
	public HashMap<Integer, BufferDataWrapper<? extends Number>> getBuffer_data_map() {
		return buffer_data_map;
	}

}
