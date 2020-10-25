package handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Company;

public class CompanyHandler extends FileHandler {

	static Map<String, Company> mc = new HashMap<String, Company>();
	
	public static Map<String, Company> getCompanies() {
		return mc;
	}

	public static void setCompanies(Map<String, Company> mc) {
		CompanyHandler.mc = mc;
	}

	public void loadFromFile() throws IOException {
		
		File f = new File("companies.txt"); 
		if(f.exists() && f.isFile()) {
			Stream<String> comrows = Files.lines(Paths.get("companies.txt"));
			List<String> comlist = comrows.collect(Collectors.toList());
			
			try {
				comlist.stream().map(x -> x.trim().split("\\s+")).forEachOrdered(x -> {
					mc.put(x[0], new Company(x[0], x[1], x[2], x[3], x[4]));
				});
			} catch (IndexOutOfBoundsException e) {
				
			}
			comrows.close();
		} else {
			System.out.println("FileNotFound or Doesnt Exists");
		}
	}
}
