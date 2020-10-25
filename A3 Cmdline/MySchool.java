 import java.util.*; 
 import java.io.*; 
 public class MySchool { 
	
	public static void main(String[] args) throws IOException{ 
		 
		 /*************************************************************************************/
		 /************************************PASS Level***************************************/
		 /*************************************************************************************/
		 ///	START
		 
		 int n_Row_Col, row = 0, col = 0, pos = 0;
		 int [][] scores = new int[10][10];
		 String [] studentID = new String[10];
		 String [] courseID = new String[10];
		 
		 // For storing the highest marks in all courses 
		 int [] s_sum; 
		 int highest = 0; 
		
		// For Reading the scores file
		Scanner file_s = new Scanner(new File(args[0]));
		 
		// Reading the no of rows and columns
		n_Row_Col = file_s.nextInt(); 
		
		col = n_Row_Col % 10;
		row = n_Row_Col / 10;
		
		// The scores, coursesIds of the students and studentIds
		scores = new int[row][col];
		studentID = new String[row];
		courseID = new String[col];
		s_sum = new int[row];
				
		// Reading courseIds
		for (int ci = 0; ci < col; ci++) {
			courseID[ci] = file_s.next();
		}	
				
		for (int i = 0; i < row && file_s.hasNextLine(); i++) {
			
			// Reading studentsIds at start of each line 		
			studentID[i] = file_s.next();
            for (int j = 0; j < col && file_s.hasNext(); j++) {
					
				// Reading Student scores 
				if(file_s.hasNextInt()) {
					scores[i][j] = file_s.nextInt() ;
					s_sum[i] += scores[i][j];
				}
				else if(file_s.hasNextFloat()){
					scores[i][j] = (int) file_s.nextFloat() ;
					s_sum[i] += scores[i][j];
				}
				else {
					int numflag = 0;
					String token = file_s.next();
					char c;
					for (int t = 0; t < token.length(); t++){
						try { 
							c = token.charAt(t);
							Integer.parseInt(String.valueOf(c)); 
							numflag = 1;
							break; 
						} catch(NumberFormatException e) {}   
						if(numflag == 1) {
							break;
						} 
					}
					if(numflag == 1) {
						break;
					} else {
						scores[i][j] = -1;
					}
					numflag = 0; 
						
				}
            }
		}
			
		// Calculating highest score of all courses among the students
		highest = s_sum[0];
		for(int k = 1; k < s_sum.length; k++) {
				if(s_sum[k] > highest) {
					highest = s_sum[k];
					pos = k;
				}
		}	 			
				
		System.out.println("The top student is " +studentID[pos]+ " with an average " +highest/col); 
		
		///	END
		
		/*************************************************************************************/
		/************************************CREDIT Level***************************************/
		/*************************************************************************************/
	    ///	START
		
		// For reading the courses file
		Scanner file_c = new Scanner(new File(args[1]));
		
		// Writing the course_report file
		PrintWriter p_creport = new PrintWriter(new BufferedWriter (new FileWriter("course_report.txt"))); 		
		
		String [] courseTitle = new String[col];
		int [] creditPoints = new int[col];
		int [] n_Enrol_Students = new int[col];
		int [] avg_Score = new int[col];
		int [] c_sum = new int[col];
	
		// Getting the No of Enrolled students for each course	
		for (int j = 0; j < row; j++) {
			for ( int k = 0; k < col; k++) {
				
				if (scores[j][k] >= 0 && scores[j][k] <= 100) {
					c_sum[k] += scores[j][k];
					n_Enrol_Students[k]++;
				}
			}	
		}
			
		// Calculating the average score of all students for each course 
		for (int i = 0; i < col; i++) {
			avg_Score[i] = c_sum[i] / n_Enrol_Students[i];
		}
		
		// Reading the courseIds, courseTitle, creditpoints and writing the courseIds, courseTitle, creditpoints, No of enrol students and average score to course_report file
		int i = 0;
		
		while(file_c.hasNext()){ 
			file_c.next(); 
			courseTitle[i] = file_c.next(); 
			creditPoints[i] = file_c.nextInt(); 
				
			p_creport.println(courseID[i]+"  "+courseTitle[i]+"   \t"+creditPoints[i]+"\t"+n_Enrol_Students[i]+"\t"+avg_Score[i]); 
			i++;
		}
		
		System.out.println("course_report.txt generated!");
		p_creport.close();
		file_c.close();
		
		///	END
		
		/*************************************************************************************/
		/************************************DI Level***************************************/
		/*************************************************************************************/
	    ///	START
		
		// For reading the students file	
		Scanner file_stud = new Scanner(new File(args[2]));
		
		// writing the student_report file
		PrintWriter p_sreport = new PrintWriter(new BufferedWriter (new FileWriter("student_report.txt"))); 
		
		String [] studentName = new String[row];
		int [] age = new int[row];
		int [] n_course_enroll = new int[row];
		int [] total_gpa = new int[row];
		float [] avg_gpa = new float[row];
			
		// Getting the No of Enrolled courses for each student
		for (int j = 0; j < row; j++) {
			for ( int k = 0; k < col; k++) {
					
				if (scores[j][k] >= 0 && scores[j][k] <= 100) {
					n_course_enroll[j]++;
				}
			} 
		}
		
		// Calculating the total gpa for each student
		for (int j = 0; j < row; j++) {
						
			for ( int k = 0; k < col; k++) {
							
				if(scores[j][k] >= 80) {
					total_gpa[j] += 4;
				}
				else if (scores[j][k] <= 79 && scores[j][k] >= 70) {
					total_gpa[j] += 3;
				}
				else if (scores[j][k] <= 69 && scores[j][k] >= 60) {
					total_gpa[j] += 2;
				}
				else if (scores[j][k] <= 59 && scores[j][k] >= 50) {
					total_gpa[j] += 1;
				}
				else {
					total_gpa[j] += 0;
					// 0 as student did enrolled but failed to receive any mark or less than 50 score
				    // Dont consider -1 as student hasn't enrolled in that course. 
				}
			}						
		}
		
		// Reading the Name and Age of students and writing the StudentIds, studentName, age, No of enrolled courses and the average gpa of each student 
		// Calculating the average gpa of students by their no of enrolled courses
		i = 0;	
			
		while(file_stud.hasNext()){ 
			file_stud.next();
			studentName[i] = file_stud.next(); 
			age[i] = file_stud.nextInt(); 
			
			avg_gpa[i] = (float) total_gpa[i] / n_course_enroll[i];
			p_sreport.println(studentID[i]+"\t"+studentName[i]+"\t"+age[i]+"\t"+n_course_enroll[i]+"\t"+avg_gpa[i]); 
			i++;
		}
		
		System.out.println("student_report.txt generated!");
		p_sreport.close();	
		file_stud.close();
		
		///	END
		
	} 

}