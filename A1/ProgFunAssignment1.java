// importing needed packages
import java.util.Scanner;

public class ProgFunAssignment1 {

	public static void main(String[] args) {
		
		// Create a Scanner object for input
		Scanner in = new Scanner(System.in);

		// Prompting user to enter values to between 3 to 10 (both inclusive)
		System.out.println("The number of rows and columns should be as 10 <= Number of rows > 2");

		System.out.println("Enter the number of rows for a block:");
		int rows = in.nextInt();

		System.out.println("Enter the number of columns for a block :");
		int columns = in.nextInt();

		// Until the user enters valid rows and columns the loop will continue or else will exit
		while (true) {
			
			// Checking if the user has entered invalid row values such as less than 3 or greater than 10 
			if (rows <= 2 || rows > 10) {
				System.out.println("Enter the valid number of rows for a block:");
				rows = in.nextInt();
				
			// Checking if the user has entered invalid column values such as less than 3 or greater than 10
			} else if (columns <= 2 || columns > 10) {
				System.out.println("Enter the valid number of columns for a block :");
				columns = in.nextInt();

			} else {
				break;
			}
		}

		// Create an object of class MyBlock using the 'new' operator, calling on the
		// MyBlock constructor.
		MyBlock myObj = new MyBlock(rows, columns);

		// Menu section
		// Flag used to quit from menu
		boolean quit = false;

		// do-while loop, To print the menu initially and then according to the user condition the loop will exit
		do {

			System.out.println("               MENU               ");
			System.out.println("----------------------------------");
			System.out.println("1. Add a House");
			System.out.println("2. Display the block");
			System.out.println("3. Clear the block");
			System.out.println("4. Quit");

			System.out.println("\n Select a Menu option:");
			
			// User choice in number  
			int choice = in.nextInt();

			// While loop. Until menu choice option is a valid integer number from 1-4 the program will prompt user for input
			// Infinite loop
			while (true) {

				// Checking for invalid values and prompting user to enter a valid choice number
				if (choice != 1 && choice != 2 && choice != 3 && choice != 4) {

					System.out.println("Invalid! Please enter a valid menu number option:");
					choice = in.nextInt();

				} else {

					// Switch case to execute statement sequence for each menu block
					switch (choice) {
					case 1:
						
						// Adding a house section
						System.out.println("Enter the X position of the House:");
						int rowPos = in.nextInt();

						System.out.println("Enter the Y position of the House:");
						int colPos = in.nextInt();

						System.out.println("Enter the no of rows of the House:");
						int nRow = in.nextInt();

						System.out.println("Enter the no of columns of the House:");
						int nCol = in.nextInt();

						// flag is used to either display the house or an error after checking all the rules
						boolean check = myObj.addHouse(rowPos, colPos, nRow, nCol);

						// Call the display block if all rules are passed
						if (check) {
							myObj.displayBlock();
							break;
							
						// Display an error if any one of the rules fails
						} else {
							System.out.println("Sorry! Couldnt add house, Please try again");
							break;
						}
					case 2:
						
						// Calling for display block
						myObj.displayBlock();
						break;
					case 3:
						
						// Calling for clear block
						myObj.clearBlock();
						break;
					case 4:
						
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

// MyBlock class 
class MyBlock {
	private int[][] block;

	int row;
	int col;
	int count = 1;
	boolean vacant;

	// Constructor...
	public MyBlock(int rows, int columns) {

		// creating a block with the no of rows and columns specified by the user
		block = new int[rows][columns];

		// Initializing the vacant variable to false
		// No of rows and columns of block are assigned to new variables (row, col) to be used in this section
		this.vacant = false;
		this.row = rows;
		this.col = columns;

		// Initializing the rows and columns of the block to 0
		// First for loop is for the rows, while Second for loop is for the columns
		for (int r = 0; r < row; r++) {

			for (int c = 0; c < col; c++) {
				block[r][c] = 0;

			}
		}

	}

	// 2. Display section 
	// Display the entire block as a matrix
	public void displayBlock() {

		for (int r = 0; r < row; r++) {

			for (int c = 0; c < col; c++) {
				// Printing blank space after each value for a matrix
				System.out.print(block[r][c] + " ");
			}

			// Printing lines for each row
			System.out.println();
		}
	}

	// 3. Clear section
	// Clear out the block. This involves setting each cell to 0
	public void clearBlock() {

		// Clearing the values of rows and columns of the block to 0
		// First for loop is for the rows, while Second for loop is for the columns
		for (int crow = 0; crow < row; crow++) {

			for (int ccol = 0; ccol < col; ccol++) {
				block[crow][ccol] = 0;
			}
		}
		vacant = true;
		// resetting the count which is used to assign numbers to houses
		count = 1;

	}

	public boolean addHouse(int rowPos, int colPos, int rows, int columns) {

		// Flags will be used for different rules
		int flag = 0;
		int check = 0;

		// As index in java starts from 0. Mapping the user house (x, y) position to index 0 for displaying the matrix
		rowPos = rowPos - 1;
		colPos = colPos - 1;
 
		// Rule 1 and 3. Checking for 0's at the boundaries of block, considering only those houses which are within the matrix 
		// First for loop is for iterating through rows, while Second for loop is for iterating through columns
		for (int i = 0; i < row; i++)
			
			for (int j = 0; j < col; j++) {
				
				// Below are the conditions for 4 sides of the boundaries
				// 1. Top 2. Right 3. Bottom 4. Left
				if (block[0][j] == 0 || block[i][col - 1] == 0 || block[row - 1][j] == 0 || block[i][0] == 0) {
					
					// flag is set if Rule 1 and 3 are passed
					check = 0;
					
				} else {
					
					// if there is atleast one value other than 0 while checking, then exit as Rule 1 and 3 failed
					System.out.println("Rule 1 and 3 failed");
					check = 1;
					vacant = false;
					break;

				}
			}

		// flag is used to identify whether the Rule 1 and 3 was passed or not
		if (check == 0) {

			// Rule 1 and 3 are passed
			// Rule 4: checking for the size of house block. House block should have rows and columns greater than 1
			if (rows >= 1 && columns >= 1) {

				// Rule 2.1: House to be added one row and one column away
				// House Boundary for loop
				// First for loop represent rows and Second for loop represents columns of House block
				for (int r = (rowPos - 1); r < ((rowPos - 1) + rows + 2); r++) {

					for (int c = (colPos - 1); c < ((colPos - 1) + columns + 2); c++) {

						// Without this condition the program will crash for some values
						
						// Considering if the user mistakenly enters random values (row position, column position, rows, columns) for house block and the block lies 
						// outside the land block. As there is a limit for block matrix, so while checking for house boundary beyond the block, throws an error
						
						// checking that the house block doesn't lie beyond the land block 
						if ((rowPos+rows) < row && (colPos+columns) < col) {

							// condition to check for 0's at House block boundaries
							// 1. Top 2. Left 3. Right 4. Bottom
							if (block[rowPos - 1][c] == 0 || block[r][colPos - 1] == 0
									|| block[r][colPos - 1 + columns + 2] == 0
									|| block[rowPos - 1 + rows + 2][c] == 0) {
								
								// if the boundary check is done that means the Rule 2.1 is passed i.e only boundary values are checked, checking for previous house(all values) is not done yet
								flag = 1;

							} else {
								vacant = false;
								flag = 0;
								System.out.println("Rule 2.1 failed");
								break;
							}
						}
						else {
							
							flag = 0;
						    vacant = false;
						    System.out.println("Index out of bounds");
						    break;

						}
						
						// if there is atleast one value other than 0 while checking the boundaries that means there maybe a house previously build.
						// BREAK : checking after each loop / condition so that the program flow exits to directly return to menu and display an error
						if (flag == 0) {
							break;
						}
					}

					if (flag == 0) {
						break;
					}
				}

				// Rule 2.2
				// Checking whether the Rule 2.1 was passed or not
				if (flag == 1) {
					
					// Rule 1, 2.1, 3, 4 passed
					// First for loop represents the rows and Second for loop represents the columns
					for (int rowm = (rowPos); rowm < (rowPos + rows); rowm++) {

						for (int colm = (colPos); colm < (colPos + columns); colm++) {

							// to check if there is no house previously build
							if (block[rowm][colm] == 0) {
								
								// Rule 2.2 passed
								vacant = true;
							} else {
								vacant = false;
								System.out.println("Rule 2.2 failed");
								break;
							}

							// Exit, if there is atleast any one value other than 0 while checking for previous houses
							if (vacant == false) {
								break;
							}
						}

						if (vacant == false) {
							break;
						}
					}
					
					// resetting the flag i.e to check Rule 2.1 again 
					flag = 0;
				} else {
					vacant = false;
				}

				//  Checking whether Rule 2.1 was passed or not 
				// building house only if all the rules are passed
				if (vacant) {
					
					// All Rules passed
					// Add the number for the house block
					for (int rowm = (rowPos); rowm < (rowPos + rows); rowm++) {

						for (int colm = (colPos); colm < (colPos + columns); colm++) {

							block[rowm][colm] = count;
						}
					}
					
					// Assign a sequence of numbers to each new house been built
					count++;
				}
			} else {
	            
				// Any one of the rules failed
				vacant = false;
			}
		}

		return vacant;
	}

}
