package assignment2;

public class User {

	private String id;
	private String username;
	
	
	public User(String id, String username) {
		this.setId(id);
		this.setUsername(username);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//
	public void changeName(String username) {	
	}
	
}
