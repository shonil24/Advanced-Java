package handler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import model.Student;

public class StudentHandler extends FileHandler {
	
	static Map<String, Student> smap = new LinkedHashMap<String, Student>();
	
	public Map<String, Student> getStudents() {
		return smap;
	}

	public void setStudents(Map<String, Student> smap) {
		StudentHandler.smap = smap;
	}

	public void loadFromFile() throws IOException {
		
		File f = new File("studentinfo.txt"); 
		if(f.exists() && f.isFile()) {
			Stream<String> studrows = Files.lines(Paths.get("studentinfo.txt"));
			studrows.map(x -> x.replaceAll("[^a-zA-Z\\d]", " ").trim().split("\\s+")).forEachOrdered(x -> {
				try {
					smap.put(x[0],
							new Student(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10], x[11]));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					
					e.printStackTrace();
				}
			});
			studrows.close();
		} else {
			System.out.println("FileNotFound or Doesnt Exists");
		}
		
	}

	public static Map<String, Student> getSmap() {
		return smap;
	}

	public static void setSmap(Map<String, Student> smap) {
		StudentHandler.smap = smap;
	}

}
