package model;

import java.util.HashMap;
import java.util.Map;

public class Project {
	
	private String title;
	private String projectID;
	private String description;
	private String pownerID;
	private Map<String, Integer> sortedByValueDesc = new HashMap<String, Integer>();

	public Project() {
		
	}

	public Project(String projectID, String title, String description, String pownerID, String skill1, String Value4,
			String skill2, String Value3, String skill3, String Value2,
			String skill4, String Value1) {
		
		this.setProjectID(projectID);
		sortedByValueDesc.put(skill1, Integer.parseInt(Value4));
		sortedByValueDesc.put(skill2, Integer.parseInt(Value3));
		sortedByValueDesc.put(skill3, Integer.parseInt(Value2));
		sortedByValueDesc.put(skill4, Integer.parseInt(Value1));	
		this.setSortedByValueDesc(sortedByValueDesc);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPownerID() {
		return pownerID;
	}

	public void setPownerID(String pownerID) {
		this.pownerID = pownerID;
	}

	public Map<String, Integer> getSortedByValueDesc() {
		return sortedByValueDesc;
	}

	public void setSortedByValueDesc(Map<String, Integer> sortedByValueDesc) {
		this.sortedByValueDesc = sortedByValueDesc;
	}

}
