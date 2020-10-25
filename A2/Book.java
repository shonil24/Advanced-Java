package assignment2;

import java.util.ArrayList;

public class Book extends Publication{
	
	private String[] author;
	private static ArrayList<Comment> comments = new ArrayList<Comment>();
	
	public Book() {
		super();
	}

	public Book(String ID, String name, double price, String publisher, int Noofpages, String[] authors) {
		
		super(ID, name, 0, price, publisher, Noofpages);
		this.setAuthor(authors);
	}
	
	public Book(String ID, String name, int Noofdownloads, double price, String publisher, int Noofpages,
			String authors[]) {
		super(ID, name, Noofdownloads, price, publisher, Noofpages);
		this.setAuthor(authors);
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
	
	@Override
	public String getPublisher() {
		return super.getPublisher();
	}
	
	@Override
	public int getNoofpages() {
		return super.getNoofpages();
	}
	
	////
	public String[] getAuthor() {
		return author;
	}

	public void setAuthor(String[] author) {
		this.author = author;
	}

	//
	public void addReview(Comment comment) {
		Book.comments.add(comment);
	}

	
	public static ArrayList<Comment> getComments() {
		return comments;
	}

}
