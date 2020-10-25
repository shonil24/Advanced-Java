package assignment2;

//My name: Shonil Dabreo
//My student ID: s3835204
//The highest level I've completed is: DI (select ONLY one)

//Add the packages that you need
import java.util.Scanner;

public class MyShopMain {
 public static void main(String[] args) {
	 
	 MyShop shop = new MyShop();
     
     // ***************   PASS LEVEL  ***************
     // *********************************************
     ///*
             
     // create new publications
     String[] authors1 = {"L. Tolstoy"};
     Book b1 = new Book ("b1", "War and Peace", 12.55, "The Russian Messenger", 1225, authors1);
             
     String[] authors2 = {"F. Scott Fitzgerald"};
     Book b2 = new Book ("b2", "The great gatsby", 10, "Charles Scribner's Sons", 180, authors2);
             
     String[] authors3 = {"Thomas H. Cormen", "Charles E. Leiserson", "Ronald L. Rivest", "Clifford Stein"};
     Book b3 = new Book ("b3", "Introduction to algorithms", 100, "MIT Press", 1312, authors3);
             
     Magazine m1 = new Magazine("m1", "Forbes", 8.99, "Forbes Media", 50, 201904);
             
     // add new publications to the shop      
     shop.addContent(b1);
     shop.addContent(b2);
     shop.addContent(b3);
     shop.addContent(m1);
             
     // create new applications        
     Application g1 = new Application("g1", "Pokemon", 5.3, "androidV4");    
     Application g2 = new Application("g2", "Pokemon", 5, "iOSV10");
     //a free application
     Application app1 = new Application("app1", "Calendar", "androidV3"); 

     // add new applications to the shop         
     shop.addContent(g1);
     shop.addContent(g2);
     shop.addContent(app1);

  
     //*/
     // ***************  CREDIT LEVEL ***************
     // ******** disable me if not completed ********
     ///*
             
     // Adding new customers
     Customer c1 = new Customer("c1", "coolguy", "0412000", 200);
     Customer c2 = new Customer("c2", "neversaynever", "0433191");  
     Customer c3 = new Customer("c3", "Hello 123", "0413456", 1000);
     Customer c4 = new Customer("c4", "Jackie Chan", "0417654");
             
     shop.addUser(c1);
     shop.addUser(c2);
     shop.addUser(c3);
     shop.addUser(c4);
     
     // Adding new admins
     Admin a1 = new Admin("a1", "SuperBlack", "opensesame", 10);
     Admin a2 = new Admin("a2", "Adele", "kitty123", 3);  
             
     shop.addUser(a1);
     shop.addUser(a2);
     
     c2.changeName("neversaybye");  // change the user name
     a1.changeName("superblack");   // make the user name in lowercase
     
     //*/
     // ************* DISTINCTION LEVEL *************
     // ******** disable me if not completed ********
     ///*
     
     
     Comment comment1 = new Comment(c1, "This is a fantastic game!");
     g1.addReview(comment1);
             
     Comment comment2 = new Comment(c2, 5);
     g1.addReview(comment2);
     
     Comment comment3 = new Comment(c3, 3, "This is an average game!"); 
     g1.addReview(comment3);
     
     Comment comment4 = new Comment(c4, "I quite like this game!", 4); 
     g1.addReview(comment4);
             
     g1.addReview(new Comment(c3, "The game crashes frequently"));

     b1.addReview(new Comment(c2, "I love Tolstoy!"));

                           
     // Simulating transactions, showing content etc etc.  
     if (c1.download(b1)) {
         System.out.println(c1.getUsername() + " bought " + b1.getName());
         if(c1.getAvailfunds() >= b1.getPrice()) {
        	 c1.setAvailfunds(c1.getAvailfunds()-b1.getPrice());
         }
         else {
        	 System.out.println("Not enough fund"); 
         }	 
     }
     else {
         System.out.println("Not enough fund"); 
     }   
     c1.download(b3);
     c1.download(m1);
     
     c1.showDownloads();  // show c1's downloads
     
     c2.download(m1);
     c3.download(m1);
     
     //m1.showDownloads();    //show number of downloads of m1
     
     //shop.showDownloads();  //show all comments ever made


     //*/
     // **************   HD  LEVEL   **************
     // ******** disable me if not completed ********
     ///*
     
     /*
     c3.becomePremium();
     c3.download(b1);
     c3.download(g1);
     shop.showDownloads(); 
     
     c4.download(g1);
     c4.becomePremium();
     c4.download(m1);
     shop.showDownloads(); 
     

     Customer c5 = new Customer("c5", "neverenough", "0486734", 2000);
     Content[] list = {b1, b2, b3, m1, g1, g2, app1};
     
     // download many items in one go
     c5.download(list); 
     shop.showDownloads();
     
     // an admin can reset the price for an item
     b1.setPrice(a2.login(), 14.25);  
     
     // an admin with level > 5 bulk reduces all prices by 10% 
     shop.setPrice(a1.login(), a1.getLevel(), -0.10);
 
     //*/   
                 
     // other necessary code to test the required functionalities. 
     
     // ************************ Menu ***************************         
     //  Write a menu driven part to allow keyboard input
     //  Input validation is a must
     //  You may define method(s) for menu handling
	 
	// Menu section
	// Flag used to quit from menu
	
	boolean quit = false;
	 
	 Scanner in = new Scanner(System.in);
     // ********************************************************** 
	 do {

			System.out.println("               MENU               ");
			System.out.println("----------------------------------");
			System.out.println("1. Add a Book");
			System.out.println("2. Add a magazine");
			System.out.println("3. Add an application");
			System.out.println("4. Show all the contents");
			System.out.println("5. Add a Admin");
			System.out.println("6. Add a Customer");
			System.out.println("7. Show all the Users");
			System.out.println("8. Show the application reviews");
			System.out.println("8. Show the book reviews");
			System.out.println("10. Quit the menu");

			System.out.println("\n Select a Menu option:");
			
			// User choice in number  
			int choice = in.nextInt();

			// While loop. Until menu choice option is a valid integer number from 1-4 the program will prompt user for input
			// Infinite loop
			while (true) {

				// Checking for invalid values and prompting user to enter a valid choice number
				if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7 && choice != 8 && choice != 9 && choice != 10) {

					System.out.println("Invalid! Please enter a valid menu number option:");
					choice = in.nextInt();

				} else {

					// Switch case to execute statement sequence for each menu block
					switch (choice) {
					case 1:
						String id, name, publisher;
						String[] author;
						double price;
						int noofpages, noofdownloads, length;
						
						System.out.println("Enter the Book id for e.g b1:");
						id = in.next();
						
						System.out.println("Enter the Book name:");
						name = in.next();
						
						System.out.println("Enter the No of downloads:");
						noofdownloads = in.nextInt();
						
						System.out.println("Enter the price:");
						price = in.nextDouble();
						
						System.out.println("Enter the publisher name:");
						publisher = in.next();			
						
						System.out.println("Enter the No of pages:");
						noofpages = in.nextInt();
						
						System.out.println("Enter the no of authors:");
						length = in.nextInt();
						
						System.out.println("Enter author names:");
						author = new String[length];
						for(int i=0; i<author.length; i++) {
							author[i] = in.next();
						}
						
						Book bk = new Book(id, name, noofdownloads, price, publisher, noofpages, author);
						shop.addContent(bk);
						break;
					case 2:
						String mid, mname, mpublisher;
						double mprice;
						int mnoofpages, mnoofdownloads, mvolume;
						
						System.out.println("Enter the Magazine id for e.g m1:");
						mid = in.next();
						
						System.out.println("Enter the Magazine name:");
						mname = in.next();
						
						System.out.println("Enter the No of downloads:");
						mnoofdownloads = in.nextInt();
						
						System.out.println("Enter the price:");
						mprice = in.nextDouble();
						
						System.out.println("Enter the publisher name:");
						mpublisher = in.next();			
						
						System.out.println("Enter the No of pages:");
						mnoofpages = in.nextInt();
						
						System.out.println("Enter the volume:");
						mvolume = in.nextInt();
						
						Magazine magz = new Magazine(mid, mname, mnoofdownloads, mprice, mpublisher, mnoofpages, mvolume);
						shop.addContent(magz);
						break;
					case 3:
						String aid, aname, aOSver;
						double aprice;
						int anoofdownloads;
						
						System.out.println("Enter the Application id for e.g a1:");
						aid = in.next();
						
						System.out.println("Enter the Application name:");
						aname = in.next();
						
						System.out.println("Enter the No of downloads:");
						anoofdownloads = in.nextInt();
						
						System.out.println("Enter the price:");
						aprice = in.nextDouble();	
						
						System.out.println("Enter the OS version:");
						aOSver = in.next();
						
						Application apk = new Application(aid, aname, anoofdownloads, aprice, aOSver);
						shop.addContent(apk);
						break;
					case 4:
						shop.showContent();
						break;
					case 5:
						String Id, username, password;
						int level;
						
						System.out.println("Enter the Admin id for e.g a1:");
						Id = in.next();
						
						System.out.println("Enter the Admin username:");
						username = in.next();
						
						System.out.println("Enter the password:");
						password = in.next();			
						
						System.out.println("Enter the level between 0-5:");
						level = in.nextInt();
						
						Admin ad = new Admin(Id, username, password, level);
						shop.addUser(ad);
						break;
					case 6:
						String cusid, cusname, cusphnno;
						int cvailfunds;
						
						System.out.println("Enter the Customer id for e.g c1:");
						cusid = in.next();
						
						System.out.println("Enter the Customer username:");
						cusname = in.next();
						
						System.out.println("Enter the phone no:");
						cusphnno = in.next();			
						
						System.out.println("Enter the availfunds:");
						cvailfunds = in.nextInt();
						
						if(cvailfunds == 0) {
							cvailfunds = 50;
						}
						
						Customer cus = new Customer(cusid, cusname, cusphnno, cvailfunds);
						shop.addUser(cus);
						break;
					case 7:
						shop.showUser();
						break;
					case 8:
						String s_aid;
						int flag = 0;
						
						System.out.println("Enter the Application id to check the reviews of:");
						s_aid = in.next();
						
						for(int i=0; i<shop.getCList().size(); i++) {
							if (shop.getCList().get(i).getID().equals(s_aid)) {
								flag = 1;
								break;
							}
						}
						if(flag == 1) {
							System.out.println("----------------Application reviews----------------");
							for(int i=0; i<Application.getComments().size(); i++) {
							    System.out.println("CustomerID: " +Application.getComments().get(i).getCustID() +", Review: " +Application.getComments().get(i).getReview() +", Rating: " +Application.getComments().get(i).getRating());
								System.out.println();
							}
						}
						else {
							System.out.println("There is no such application!");
						}
						break;
					case 9:
						String boid;
						int f = 0;
						
						System.out.println("Enter the Book id to check the reviews of:");
						boid = in.next();
						
						for(int j=0; j<shop.getCList().size(); j++) {
							if (shop.getCList().get(j).getID().equals(boid)) {
								f = 1;
								break;
							}
						}
						if(f == 1) {
							System.out.println("----------------Book reviews----------------");
							for(int i=0; i<Book.getComments().size(); i++) {
							    System.out.println("CustomerID: " +Book.getComments().get(i).getCustID() +", Review: " +Book.getComments().get(i).getReview() +", Rating: " +Book.getComments().get(i).getRating());
								System.out.println();
							}
						}
						else {
							System.out.println("There is no such book!");
						}
						break;
					case 10:
						
						// quit is set to true which the program flow will then exit from the menu
						quit = true;
						break;
					}

					// to break from infinite while loop
					break;
				}
			}

		// continue displaying the menu until the user enters choice for exit  
		} while (quit == false);
	 
	 // Closing scanner object as there is no input to read
	 in.close();

     }
}