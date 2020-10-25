import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Course {

	private int row, col;
	private int[][] score;
	private String[] courseID;
	private String[] courseTitle;
	private int[] creditPoints;
	private int[] n_Enrol_Students;
	private int[] avg_Score;
	private int[] c_sum;

	public Course(int row, int col, int[][] scores, String[] courseID, String[] courseTitle, int[] creditPoints) {

		this.row = row;
		this.col = col;
		this.courseID = courseID;
		this.score = scores;
		this.courseTitle = courseTitle;
		this.creditPoints = creditPoints;
		this.n_Enrol_Students = new int[col];
		this.avg_Score = new int[col];
		this.c_sum = new int[col];
		this.setEnrollStudents(score);
		this.setAvgScore();

	}

	private void setAvgScore() {
		
		// Calculating the average score of all students for each course 
		for (int i = 0; i < col; i++) {
			avg_Score[i] = c_sum[i] / n_Enrol_Students[i];
		}
	}

	private void setEnrollStudents(int[][] score) {
		
		// Getting the No of Enrolled students for each course
		for (int j = 0; j < row; j++) {
			for (int k = 0; k < col; k++) {

				if (score[j][k] >= 0 && score[j][k] <= 100) {
					c_sum[k] += score[j][k];
					n_Enrol_Students[k]++;
				}
			}
		}
	}

	public void writeFile() throws IOException {

		// Writing the course_report file
		// writing the courseIds, courseTitle, creditpoints, No of enrol students and average score to course_report file
		PrintWriter p_creport = new PrintWriter(new BufferedWriter(new FileWriter("course_report.txt")));
		for (int i = 0; i < col; i++) {
			p_creport.println(this.courseID[i] + "  " + this.courseTitle[i] + "   \t" + this.creditPoints[i] + "\t"
					+ this.n_Enrol_Students[i] + "\t" + this.avg_Score[i]);
		}

		System.out.println("course_report.txt generated!");

		p_creport.close();

	}

	public int[] getCreditPoints() {
		return creditPoints;
	}

}
