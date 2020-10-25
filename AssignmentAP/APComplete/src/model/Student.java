package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Student {

	private String studentID;
	private String persona;
	private String con1;
	private String con2;
	private String skillnames[] = new String[4];
	private int P;
	private int N;
	private int A;
	private int W;
	private Map<String, Integer> sskill = new HashMap<String, Integer>();
	
	public Student() {
		
	}
	
	// Student file
	public Student(String studentID, String skill1, String Value4, String skill2, String Value3, String skill3,
			String Value2, String skill4, String Value1) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		this.setStudentID(studentID);
		
		skillnames[0] = skill1;
		skillnames[1] = skill2;
		skillnames[2] = skill3;
		skillnames[3] = skill4;
		
		int Value = 4;
		
		// for each student ID setting 4 skills. Rank nos are sorted descending. So doing it manually.
		for(int i = 0; i < 4; i++) {
			
			sskill.put(skillnames[i], Value);
			this.setSskill(sskill);
			Value--;
			
		}
		Value = 0;
		
		// Passing the object though which the constructor was created
		setSepSkills("P", "setP", skillnames, this);
		setSepSkills("N", "setN", skillnames, this);
		setSepSkills("A", "setA", skillnames, this);
		setSepSkills("W", "setW", skillnames, this);
		
	}

	// Studentsinfo file
	public Student(String studentID, String skill1, String Value4, String skill2, String Value3, String skill3,
			String Value2, String skill4, String Value1, String persona, String con1, String con2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		this.setStudentID(studentID);
		
		skillnames[0] = skill1;
		skillnames[1] = skill2;
		skillnames[2] = skill3;
		skillnames[3] = skill4;
		
		int Value = 4;
		
		// for each student ID setting 4 skills. Rank nos are sorted descending. So doing it manually.
		for(int i = 0; i < 4; i++) {
			
			sskill.put(skillnames[i], Value);
			this.setSskill(sskill);
			Value--;
			
		}
		Value = 0;
		
		setSepSkills("P", "setP", skillnames, this);
		setSepSkills("N", "setN", skillnames, this);
		setSepSkills("A", "setA", skillnames, this);
		setSepSkills("W", "setW", skillnames, this);
		
		this.setPersonality(persona);
		this.setConflict_1(con1);
		this.setConflict_2(con2);
	}

	private void setSepSkills(String skill, String methodName, String[] skillArr, Student student) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		// calling methods by string. Java Reflection
		Method m = student.getClass().getDeclaredMethod(methodName, int.class);
		
		if(skillArr[0].equals(skill)) {
			m.invoke(student, 4);
		} else if(skillArr[1].equals(skill)) {
			m.invoke(student, 3);
		} else if(skillArr[2].equals(skill)) {
			m.invoke(student, 2);
		} else if(skillArr[3].equals(skill)) {
			m.invoke(student, 1);
		}
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public Map<String, Integer> getSskill() {
		return sskill;
	}

	public void setSskill(Map<String, Integer> sskill) {
		this.sskill = sskill;
	}

	public String getPersonality() {
		return persona;
	}

	public void setPersonality(String persona) {
		this.persona = persona;
	}

	public String getConflict_1() {
		return con1;
	}
	
	public void setConflict_1(String con1) {
		this.con1 = con1;
	}

	public String getConflict_2() {
		return con2;
	}
	
	public void setConflict_2(String con2) {
		this.con2 = con2;	
	}

	public int getP() {
		return P;
	}

	public void setP(int p) {
		this.P = p;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		this.N = n;
	}

	public int getA() {
		return A;
	}

	public void setA(int a) {
		this.A = a;
	}

	public int getW() {
		return W;
	}

	public void setW(int w) {
		this.W = w;
	}

}
