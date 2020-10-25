package handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class FileHandler {

	public void writeCleanedFile() throws IOException {}
	public void loadFromFile() throws IOException {}
	
	public void appendReplace(String filePath, String oldLine, String newLine) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		while (sc.hasNextLine()) {
			buffer.append(sc.nextLine() + System.lineSeparator());
		}
		
		String fileContents = buffer.toString();
		sc.close();
		fileContents = fileContents.replaceAll(oldLine, newLine);
		
		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath, false);
			writer.write(fileContents);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String checkIfLineExists(String filePath, String keyword) {
		Scanner sc = null;
        try {
            sc = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.startsWith(keyword)) {
                return line;
            }
        }
        return null;
		
	}
	
	public String checkIfValueExists(String filePath, String keyword) {
		Scanner sc = null;
        try {
            sc = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
      
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.contains(keyword)) {
                return line;
            }
        }
        return null;
	}
	
	boolean checkIfFileExists(String fileName) {
		if(new File(fileName).exists()){
            return true;
        }
        return false;
	}
	
}
