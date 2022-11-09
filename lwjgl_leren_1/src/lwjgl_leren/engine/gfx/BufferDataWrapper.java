package lwjgl_leren.engine.gfx;

public class BufferDataWrapper <T> {
	
	private T[] data;
	private int dim;
	private String var_name;
	
	public BufferDataWrapper(T[] data, int dim, String var_name) {
		this.data = data;
		this.dim = dim;
		this.var_name = var_name;
	}

	public T[] getData() {
		return data;
	}

	public int getDim() {
		return dim;
	}

	public String getVar_name() {
		return var_name;
	}
	
}
