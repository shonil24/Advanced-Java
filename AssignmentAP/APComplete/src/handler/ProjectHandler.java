package handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import model.*;

public class ProjectHandler extends FileHandler {
	
	static Map<String, Project> pr = new LinkedHashMap<String, Project>();
	
	public static Map<String, Project> getProjects() {
		return pr;
	}

	public static void setProjects(Map<String, Project> pr) {
		ProjectHandler.pr = pr;
	}
	
	public void loadFromFile() throws IOException {
		
		File f = new File("projects.txt"); 
		if(f.exists() && f.isFile()) {
			Stream<String> prows = Files.lines(Paths.get("projects.txt"));
			prows.map(x -> x.replaceAll("[^a-zA-Z\\d]", " ").trim().split("\\s+")).forEachOrdered(x -> {
				pr.put(x[0], new Project(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10], x[11]));
			});
			
			prows.close();
		} else {
			System.out.println("FileNotFound");
		}
	}
}
