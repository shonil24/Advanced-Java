package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class StudProjPreferences {
	
	private String StudentID;
	private Map<String, Integer> pref_list = new LinkedHashMap<String, Integer>();
	
	public StudProjPreferences() {
		
	}
	

	public StudProjPreferences(String studentID, String pro1, String value4, String pro2, String value3,
			String pro3, String value2, String pro4, String value1) {
		
		this.setStudentID(studentID);
		pref_list.put(pro1, Integer.parseInt(value4));
		pref_list.put(pro2, Integer.parseInt(value3));
		pref_list.put(pro3, Integer.parseInt(value2));
		pref_list.put(pro4, Integer.parseInt(value1));
		this.setPreflist(pref_list);
		
	}


	public Map<String, Integer> getPreflist() {
		return pref_list;
	}

	public void setPreflist(Map<String, Integer> pref_list) {
		this.pref_list = pref_list;
	}


	public String getStudentID() {
		return StudentID;
	}


	public void setStudentID(String studentID) {
		StudentID = studentID;
	}


}
