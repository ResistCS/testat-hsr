import java.util.ArrayList;

public class Modules {
	private String name;
	private ArrayList<String> prerequistiteModules = new ArrayList<String>();

	public Modules(String name) {
		this.name = name;
	}

	public void addPreModule(String module) {
		prerequistiteModules.add(module);
	}

	public void deletePreModule(String module) {
		prerequistiteModules.remove(module);
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getPrerequistiteModules() {
		return prerequistiteModules;
	}

};