package assignment2;

public class Admin extends User{
	
	private String password;
	private int level;
	
	public Admin(String id, String username, String password, int level) {
		super(id, username);
		this.setPassword(password);
		this.setLevel(level);
	}
	
	@Override
	public String getId() {
		return super.getId();
	}
	
	@Override
	public String getUsername() {
		return super.getUsername();
	}
	
	@Override
	public void changeName(String username) {
		super.setUsername(username);
	}
	
	////
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
