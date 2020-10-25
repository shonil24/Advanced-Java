package assignment2;

public class Comment {
	
	private String review;
	private int rating;
	private String custID;
	
	public Comment(Customer customer, String review) {
		
		this.setCustID(customer);
		this.setReview(review);	
		this.setRating(0);
		
	}
	
	public Comment(Customer customer, int rating) {
	
		this.setCustID(customer);
		this.setReview("review not given:)");
		this.setRating(rating);

	}
	
	public Comment(Customer customer, int rating, String review) {
	
		this.setCustID(customer);
		this.setReview(review);
		this.setRating(rating);
	
	}
	
	public Comment(Customer customer, String review, int rating) {
	
		this.setCustID(customer);
		this.setReview(review);
		this.setRating(rating);
		
	}
	
	///
	public String getCustID() {
		return custID;
	}
	public void setCustID(Customer custID) {
		this.custID = custID.getId();
	}
	
	////
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	

}
