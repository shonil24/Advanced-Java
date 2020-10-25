package assignment2;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(sum(100));
	}
	
	public static int sum(int n) {
		
		if(n==1)
			return 1;
		else
			return n+sum(n-1);
	}

}
