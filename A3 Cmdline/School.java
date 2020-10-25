import java.util.*;
import java.io.*;

public class School {

	public static void main(String[] args) throws IOException {

		/*************************************************************************************/
		/************************************
		 * PASS Level
		 ***************************************/
		/*************************************************************************************/
		/// START

		int n_Row_Col, row = 0, col = 0;
		int[][] scores = new int[10][10];
		String[] studentID = new String[10];
		String[] courseID = new String[10];

		// For Reading the scores file
		Scanner file_s = new Scanner(new File(args[0]));

		n_Row_Col = file_s.nextInt();
		col = n_Row_Col % 10;
		row = n_Row_Col / 10;

		// // The scores, coursesIds of the students and studentIds
		scores = new int[row][col];
		studentID = new String[row];
		courseID = new String[col];

		// Reading courseIds
		for (int ci = 0; ci < col; ci++) {
			courseID[ci] = file_s.next();
		}

		for (int i = 0; i < row && file_s.hasNextLine(); i++) {

			// Reading studentsIds at start of each line
			studentID[i] = file_s.next();
			for (int j = 0; j < col && file_s.hasNext(); j++) {

				// Reading Student scores
				if (file_s.hasNextInt()) {
					scores[i][j] = file_s.nextInt();
				} else if (file_s.hasNextFloat()) {
					scores[i][j] = (int) file_s.nextFloat();
				} else {
					int numflag = 0;
					String token = file_s.next();
					char c;
					for (int t = 0; t < token.length(); t++) {
						try {
							c = token.charAt(t);
							Integer.parseInt(String.valueOf(c));
							numflag = 1;
							break;
						} catch (NumberFormatException e) {
						}
						if (numflag == 1) {
							break;
						}
					}
					if (numflag == 1) {
						break;
					} else {
						scores[i][j] = -1;
					}
					numflag = 0;

				}
			}
		}

		Student stud = new Student(row, col, scores, studentID);
		stud.topStud();

		file_s.close();

		/// END

		/*************************************************************************************/
		/************************************
		 * CREDIT Level
		 ***************************************/
		/*************************************************************************************/
		/// START

		// For reading the courses file
		Scanner file_c = new Scanner(new File(args[1]));

		String[] courseTitle = new String[col];
		int[] creditPoints = new int[col];

		// Reading the courseIds, courseTitle, creditpoints
		int i = 0;

		while (file_c.hasNext()) {
			file_c.next();
			courseTitle[i] = file_c.next();
			creditPoints[i] = file_c.nextInt();
			i++;
		}

		Course cos = new Course(row, col, scores, courseID, courseTitle, creditPoints);
		cos.writeFile();

		file_c.close();

		/// END

		/*************************************************************************************/
		/************************************
		 * DI Level
		 ***************************************/
		/*************************************************************************************/

		/// START

		// For reading the students file
		Scanner file_stud = new Scanner(new File(args[2]));

		String[] studentName = new String[row];
		int[] age = new int[row];

		// Reading the Name and Age of students
		i = 0;

		while (file_stud.hasNext()) {
			file_stud.next();
			studentName[i] = file_stud.next();
			age[i] = file_stud.nextInt();
			i++;
		}

		Student stud_report = new Student(row, col, scores, studentID, studentName, age);
		stud_report.writeFile();

		file_stud.close();

		/// END

		/*************************************************************************************/
		/************************************
		 * HD Level
		 ***************************************/
		/*************************************************************************************/

		/// START

		Student ad_sreport = new Student(row, col, scores, studentID, studentName, age);
		ad_sreport.adjustGPA(cos.getCreditPoints());

		/// END

	}

}
