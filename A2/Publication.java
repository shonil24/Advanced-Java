package assignment2;

public class Publication extends Content{
	
	private String publisher;
	private int Noofpages;
	
	public Publication() {
		super();
	}

	public Publication(String ID, String name, int Noofdownloads, double price, String publisher, int Noofpages) {
		
		super(ID, name, Noofdownloads, price);
		this.setPublisher(publisher);
		this.setNoofpages(Noofpages);
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
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public int getNoofpages() {
		return Noofpages;
	}
	
	public void setNoofpages(int noofpages) {
		this.Noofpages = noofpages;
	}

}
