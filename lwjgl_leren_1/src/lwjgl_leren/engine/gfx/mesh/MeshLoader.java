package lwjgl_leren.engine.gfx.mesh;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL33;

import lwjgl_leren.engine.gfx.BufferDataWrapper;

public class MeshLoader {
	
	private static MeshLoader instance = null;
	public static MeshLoader getInstance() {
		if(instance == null)
			instance = new MeshLoader();
		return instance;
	}
	
	private ArrayList<Integer> vao_ids, bo_ids;
	
	private MeshLoader() {
		vao_ids = new ArrayList<>();
		bo_ids = new ArrayList<>();
	}
	
	public Mesh loadMesh(
			HashMap<Integer, BufferDataWrapper<Float>> buffer_data_map,
			int[] indices
			)
	{
		int vao_id = GL33.glGenVertexArrays();
		vao_ids.add(vao_id);
		GL33.glBindVertexArray(vao_id);
		
		
				
		GL33.glBindVertexArray(0);
		return null;
	}

	
	public void clean() {
		vao_ids.forEach(GL33::glDeleteVertexArrays);
		bo_ids.forEach(GL33::glDeleteBuffers);
	}

}
