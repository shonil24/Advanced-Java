import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Student {

	private int row, col;
	private int[][] scores;
	private String[] studentID;
	private String[] studentName;
	private int[] age;
	private int[] s_sum;
	private int highest = 0, pos = 0;
	private int[] n_course_enroll;
	private int[] total_gpa;
	private float[] avg_gpa;
	private int[] adjust_gpa;
	private int[] stud_credits;
	private float[] adjust_avg;

	public Student(int row, int col, int[][] scores, String[] studentID) {
		this.row = row;
		this.col = col;
		this.scores = scores;
		this.studentID = studentID;
		this.s_sum = new int[row];
	}

	public Student(int row, int col, int[][] scores, String[] studentID, String[] studentName, int[] age) {
		this.row = row;
		this.col = col;
		this.scores = scores;
		this.studentID = studentID;
		this.studentName = studentName;
		this.age = age;
		this.n_course_enroll = new int[row];
		this.total_gpa = new int[row];
		this.avg_gpa = new float[row];
		this.adjust_gpa = new int[row];
		this.stud_credits = new int[row];
		this.adjust_avg = new float[row];
		this.NoofcoursesEnroll(scores);
		this.CalculateGPA(scores);
	}

	private void CalculateGPA(int[][] scores) {

		// Calculating the total gpa for each student
		for (int j = 0; j < row; j++) {

			for (int k = 0; k < col; k++) {

				if (scores[j][k] >= 80) {
					total_gpa[j] += 4;
				} else if (scores[j][k] <= 79 && scores[j][k] >= 70) {
					total_gpa[j] += 3;
				} else if (scores[j][k] <= 69 && scores[j][k] >= 60) {
					total_gpa[j] += 2;
				} else if (scores[j][k] <= 59 && scores[j][k] >= 50) {
					total_gpa[j] += 1;
				} else {
					total_gpa[j] += 0;
					// 0 as student did enrolled but failed to receive any mark or less than 50
					// score
					// Dont consider -1 as student hasn't enrolled in that course.
				}
			}
		}

		for (int i = 0; i < row; i++) {

			// calculating the average gpa of students by their no of enroled courses
			avg_gpa[i] = (float) total_gpa[i] / n_course_enroll[i];
		}

	}

	private void NoofcoursesEnroll(int[][] scores) {

		// Getting the No of Enrolled courses for each student
		for (int j = 0; j < row; j++) {
			for (int k = 0; k < col; k++) {

				if (scores[j][k] >= 0 && scores[j][k] <= 100) {
					n_course_enroll[j]++;
				}
			}
		}

	}

	public void topStud() {

		// For storing the highest marks in all courses
		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {
				s_sum[i] += scores[i][j];
			}
		}

		// Calculating highest score of all courses among the students
		highest = s_sum[0];
		for (int k = 1; k < s_sum.length; k++) {
			if (s_sum[k] > highest) {
				highest = s_sum[k];
				pos = k;
			}
		}

		System.out.println("The top student is " + studentID[pos] + " with an average " + highest / col);

	}

	public void writeFile() throws IOException {

		// writing the student_report file
		// writing the StudentIds, studentName, age, No of enrolled courses and the
		// average gpa of each student
		PrintWriter p_sreport = new PrintWriter(new BufferedWriter(new FileWriter("student_report.txt")));

		// Calculating the average gpa of students by their no of enrolled courses
		for (int i = 0; i < row; i++) {
			p_sreport.println(this.studentID[i] + "\t" + this.studentName[i] + "\t" + this.age[i] + "\t"
					+ this.n_course_enroll[i] + "\t" + this.avg_gpa[i]);
		}

		System.out.println("student_report.txt generated!");

		p_sreport.close();

	}

	public void adjustGPA(int[] creditPoints) throws IOException {

		// calculating the total gpa of course credit points with the students gpa score
		for (int j = 0; j < row; j++) {

			for (int k = 0; k < col; k++) {

				if (scores[j][k] >= 0) {

					stud_credits[j] += creditPoints[k];

					if (scores[j][k] >= 80) {

						adjust_gpa[j] = adjust_gpa[j] + creditPoints[k] * 4;

					} else if (scores[j][k] <= 79 && scores[j][k] >= 70) {

						adjust_gpa[j] = adjust_gpa[j] + creditPoints[k] * 3;

					} else if (scores[j][k] <= 69 && scores[j][k] >= 60) {

						adjust_gpa[j] = adjust_gpa[j] + creditPoints[k] * 2;

					} else if (scores[j][k] <= 59 && scores[j][k] >= 50) {

						adjust_gpa[j] = adjust_gpa[j] + creditPoints[k] * 1;

					} else {

						adjust_gpa[k] = adjust_gpa[k] + creditPoints[k] * 0;
						// 0 as student did enrolled but failed to receive any mark or less than 50
						// score
					}

				} else {
					// Do nothing. Credit points shouldn't be considered for students who hasn't
					// enrolled (i.e. -1) in a course
				}
			}
		}

		// calculating the adjust average gpa score
		for (int i = 0; i < row; i++) {
			adjust_avg[i] = (float) adjust_gpa[i] / stud_credits[i];
		}

		writeAdFile(stud_credits, adjust_avg);
	}

	private void writeAdFile(int[] stud_credits, float[] adjust_avg) throws IOException {

		// writing students_report file
		PrintWriter p_sreport = new PrintWriter(new BufferedWriter(new FileWriter("student_report.txt")));

		// writing the student Ids, studentName, age, student total credits of enrolled
		// courses and the adjusted average
		for (int i = 0; i < row; i++) {
			p_sreport.println(this.studentID[i] + "\t" + this.studentName[i] + "\t" + this.age[i] + "\t"
					+ this.stud_credits[i] + "\t" + this.adjust_avg[i]);
		}

		System.out.println("student_report.txt generated!");

		p_sreport.close();

	}

}
