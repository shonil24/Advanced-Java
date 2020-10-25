package assignment2;

public class Magazine extends Publication{

	private int volume;
	
	public Magazine(String ID, String name, double price, String publisher, int Noofpages, int volume) {
		
		super(ID, name, 0, price, publisher, Noofpages);
		this.setVolume(volume);
	}
	
	public Magazine(String ID, String name, int Noofdownloads, double price, String publisher, int Noofpages, int volume) {
		super(ID, name, Noofdownloads, price, publisher, Noofpages);
		this.setVolume(volume);
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
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void showDownloads() {
		// TODO Auto-generated method stub
		
	}

}
