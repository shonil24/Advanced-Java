package model;

import java.util.Set;

public class Team {

	private String projectID;
	private String teamID;
	private Set<String> tempMembers;
	private String member1, member2, member3, member4;
	
    public Team(String projectID, String teamID, Set<String> tempMembers) {
		this.setProjectID(projectID);
		this.setTeamID(teamID);
		this.setTempMembers(tempMembers);
	}
	
	public Team(String teamID, String projectID, String member1, String member2, String member3, String member4) {
		
		this.setTeamID(teamID);
		this.setProjectID(projectID);
		this.setMember1(member1);
		this.setMember2(member2);
		this.setMember3(member3);
		this.setMember4(member4);
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public Set<String> getTempMembers() {
		return tempMembers;
	}

	public void setTempMembers(Set<String> tempMembers) {
		this.tempMembers = tempMembers;
	}

	public String getMember1() {
		return member1;
	}

	public void setMember1(String member1) {
		this.member1 = member1;
	}

	public String getMember2() {
		return member2;
	}

	public void setMember2(String member2) {
		this.member2 = member2;
	}

	public String getMember3() {
		return member3;
	}

	public void setMember3(String member3) {
		this.member3 = member3;
	}

	public String getMember4() {
		return member4;
	}

	public void setMember4(String member4) {
		this.member4 = member4;
	}

}
