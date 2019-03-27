import java.util.ArrayList;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) throws Exception {
		var modullist = new ArrayList<Modules>();
		var toDelete = new ArrayList<Modules>();
		int counter = 1;
		String output = "";

		try (var reader = new CatalogueReader("StudyCatalogue.txt")) {
			String[] names;
			while ((names = reader.readNexteLine()) != null) {
				Modules newModule = new Modules(names[0]);
				for (int i = 1; i < names.length; i++) {
					newModule.addPreModule(names[i]);
				}
				modullist.add(newModule);
			}
			System.out.println(sort(modullist, output, counter, toDelete));
		}
	}

	public static String sort (ArrayList<Modules> modullist, String output, int counter, ArrayList<Modules> toDelete) {
		if (modullist.isEmpty()) {
			return output;
		}
		
		output += "Semester " + counter + ": ";
		
		modullist.stream()
			     .filter(f -> f.getPrerequistiteModules().isEmpty())
			     .forEach(toDelete::add);
		
		if (toDelete.isEmpty()) {
			throw new IllegalArgumentException("Kein Sortierung möglich! Bitte File überprüfen.");
		}
		
		output += toDelete.stream()
					      .map(x -> x.getName())
					      .collect(Collectors.joining(",")) + "\n";

		modullist.forEach(m -> toDelete.forEach(d -> m.deletePreModule(d.getName())));
		toDelete.forEach(modullist::remove);
		toDelete.clear();
		
		return sort(modullist, output, counter + 1, toDelete);
	}
}
