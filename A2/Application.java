package assignment2;

import java.util.ArrayList;

public class Application extends Content{
	
	private String OSver;
	static ArrayList<Comment> comments = new ArrayList<Comment>();

	public Application() {
		super();
	}
	
	public Application(String ID, String name, int Noofdownloads, double price, String OSver) {
		
		super(ID, name, Noofdownloads, price);
		this.setOSver(OSver);
	}
	
	public Application(String ID, String name, double price, String OSver) {
		
		super(ID, name, 0, price);
		this.setOSver(OSver);
		
	}
	
	public Application(String ID, String name, int Noofdownloads, String OSver) {
		
		super(ID, name, Noofdownloads, 0);
		this.setOSver(OSver);
		
	}

	public Application(String ID, String name, String OSver) {
		
		super(ID, name, 0, 0);
		this.setOSver(OSver);
		
	}

	@Override
	public String getID() {
		return super.getID();
	}
	
	@Override
	public String getName() {
		return super.getName();
	}
	
	@Override
	public int getNoofdownloads() {
		return super.getNoofdownloads();
	}
	
	@Override
	public double getPrice() {
		return super.getPrice();
	}
	
	////
	public String getOSver() {
		return OSver;
	}

	public void setOSver(String oSver) {
		this.OSver = oSver;
	}
	
	//
	public void addReview(Comment comment) {
		Application.comments.add(comment);	
		
	}
	
	public static ArrayList<Comment> getComments() {
		return comments;
	}
}
