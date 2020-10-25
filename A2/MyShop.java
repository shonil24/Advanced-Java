package assignment2;

import java.util.ArrayList;

public class MyShop {
	
	// Instance variables that you need.
	// They must all be marked as private
	
	// Declare a private variable (array or similar) to store your Content objects here
	private ArrayList<Content> content = new ArrayList<Content>();
	
	// Declare a private variable (array or similar) to store your User objects here
	private ArrayList<User> users = new ArrayList<User>();
	
	public MyShop() {
		// any code you need here
	}

	public void addContent(Content item) {
		// add the content into your content list
		content.add(item);
	}

	public void showContent() {
		// add your code here
		System.out.println("----------------Showing all the contents----------------");
		for (int i = 0; i < content.size(); i++) {
			System.out.println("ID: " +content.get(i).getID() +", Name: " +content.get(i).getName() +", No of downloads: " +content.get(i).getNoofdownloads() +", Price: " +content.get(i).getPrice());
		}
		System.out.println();
		
	}
	
	public void addUser(User user) {
		// add the user to your list of users
		users.add(user);
	}	
	
	public void showUser() {
		// add your code here
		System.out.println("----------------Showing all the users----------------");
		for (int i = 0; i < users.size(); i++) {
			System.out.println("ID: " +users.get(i).getId() +", Username: " +users.get(i).getUsername());
		}
		System.out.println();
		
	}
	
    public ArrayList<User> getUList() {
		return this.users;
    }
    
    public ArrayList<Content> getCList() {
		return this.content;
    }
	
}