package handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.*;

public class TeamHandler extends FileHandler {

	static Map<String, Team> team = new LinkedHashMap<String, Team>();
	
	//run once before running GUI
	public static void main(String args[]) throws IOException {
		new TeamHandler().loadFromFile();
		
		// To keep team id at start
		new TeamHandler().writeCleanedFile();
	}

	public void loadFromFile() throws IOException {

		File f = new File("selections.txt");
		if (f.exists() && f.isFile()) {
			Stream<String> trows = Files.lines(Paths.get("selections.txt"));
			List<String> tlist = trows.collect(Collectors.toList());

			try {
				tlist.stream().map(x -> x.replaceAll("[^a-zA-Z\\d]", " ").trim().split("\\s+")).forEachOrdered(x -> {
					team.put(x[0], new Team(x[0], x[1], x[2], x[3], x[4], x[5]))
					;
				});
			} catch (IndexOutOfBoundsException e) {} 
			trows.close();
		} else {
			System.out.println("FileNotFound or Doesnt Exists");
		}
		
	}

	public void writeCleanedFile() throws IOException {
		
		/// Declare only after file manipulation. This will overwrite
		BufferedWriter btout = new BufferedWriter(new FileWriter("selections.txt"));

		try {
			// iterate map entries
			for (Map.Entry<String, Team> entry : team.entrySet()) {

				// put key and value separated by a colon
				btout.write(entry.getValue().getTeamID() + " "+entry.getValue().getProjectID()+" "
						+ entry.getValue().getMember1()+" "+entry.getValue().getMember2()+" "+
						entry.getValue().getMember3()+" "+entry.getValue().getMember4());
				btout.newLine();

			}
			btout.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				// always close the writer
				btout.close();
			} catch (Exception e) {
			}
		}
		
	}

	public static Map<String, Team> getTeams() {
		return team;
	}

	public static void setTeams(Map<String, Team> team) {
		TeamHandler.team = team;
	}

	public String findUnfilledTeam() {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("selections.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String temp[] = line.split(" ");
			if (temp.length < 6) {
				return line;
			}
		}
		return null;
	}

	public void appendReplace(String filePath, String oldLine, String newLine) {
		super.appendReplace(filePath, oldLine, newLine);
	}

	public String checkIfLineExists(String filePath, String keyword) {
		return super.checkIfLineExists(filePath, keyword);
	}

	public String checkIfValueExists(String filePath, String keyword) {
		return super.checkIfValueExists(filePath, keyword);
    }
	
    public boolean checkIfFileExists(String fileName){
        return super.checkIfFileExists(fileName);
	}

}
