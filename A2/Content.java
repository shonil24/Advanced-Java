package assignment2;

public abstract class Content {

	private String ID;
	private String name;
	private int Noofdownloads;
	private double price;
	
	public Content() {
		
	}
	
	public Content (String ID, String name, int Noofdownloads, double price) {
		
		this.setID(ID);
		this.setName(name);
		this.setNoofdownloads(Noofdownloads);
		this.setPrice(price);
		
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoofdownloads() {
		return Noofdownloads;
	}

	public void setNoofdownloads(int noofdownloads) {
		this.Noofdownloads = noofdownloads;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
}
