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
import model.Owner;

public class OwnerHandler extends FileHandler {

	static Map<String, Owner> po = new HashMap<String, Owner>();
	
	public Map<String, Owner> getOwners() {
		return po;
	}

	public void setOwners(Map<String, Owner> po) {
		OwnerHandler.po = po;
	}

	public void loadFromFile() throws IOException {
		
		File f = new File("owners.txt"); 
		if(f.exists() && f.isFile()) {
			Stream<String> ownrows = Files.lines(Paths.get("owners.txt"));
			List<String> ownlist = ownrows.collect(Collectors.toList());
			
			try {
				ownlist.stream().map(x -> x.trim().split("\\s+")).forEachOrdered(x -> {
					po.put(x[0], new Owner(x[0], x[1], x[2], x[3], x[4], x[5]));
				});
			} catch (IndexOutOfBoundsException e) {
				
			}
			ownrows.close();
		} else {
			System.out.println("FileNotFound or Doesnt Exists");
		}
		
	}
}
