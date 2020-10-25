package handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.StudProjPreferences;

public class PreferencesHandler extends FileHandler {

	static Map<String, StudProjPreferences> spmap = new LinkedHashMap<String, StudProjPreferences>();

	// run once before running GUI
	public static void main(String args[]) throws IOException {
		
		new PreferencesHandler().loadFromFile();

	}

	public void loadFromFile() throws IOException {

		File f = new File("preferences.txt");
		
		if (f.exists() && f.isFile()) {
			Stream<String> prerows = Files.lines(Paths.get("preferences.txt"));
			List<String> prelist = prerows.collect(Collectors.toList());

			try {
				prelist.stream().map(x -> x.replaceAll("[^a-zA-Z\\d]", " ").trim().split("\\s+")).forEachOrdered(x -> {
					spmap.put(x[0], new StudProjPreferences(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8]));
				});
			} catch (IndexOutOfBoundsException e) {
			}
			prerows.close();
		} else {
			System.out.println("FileNotFound or Doesnt Exists");
		}

	}

	public static Map<String, StudProjPreferences> getStudPref() {
		return spmap;
	}

	public static void setStudPref(Map<String, StudProjPreferences> spmap) {
		PreferencesHandler.spmap = spmap;
	}

}
