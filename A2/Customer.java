package assignment2;

import java.util.ArrayList;

public class Customer extends User{
	
	private String phoneno;
	private double availfunds;
	private ArrayList<Content> con= new ArrayList<Content>();
	
	public Customer(String id, String username, String phoneno, double availfunds)	{
		super(id, username);
		this.setPhoneno(phoneno);
		this.setAvailfunds(availfunds);
		
	}
	
	public Customer(String id, String username, String phoneno) {
		
		super(id, username);
		this.setPhoneno(phoneno);
		this.setAvailfunds(50);
		
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
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public double getAvailfunds() {
		return availfunds;
	}
	public void setAvailfunds(double availfunds) {
		this.availfunds = availfunds;
	}

	public boolean download(Book b) {
		return false;
		
		
	}


	public void download(Magazine m) {
		// TODO Auto-generated method stub
		
	}

	public void showDownloads() {
		// TODO Auto-generated method stub
		System.out.println();
		
	}
	
}
