import java.util.*;
import java.io.*;

public class MySchool{
    public static void main(String[] args) throws IOException {
        
        
        // ***************   PASS LEVEL  ***************
        // *********************************************
        ///*
        Scanner input = new Scanner(new File(args[0]));
        int number = input.nextInt();
        int col = number % 10;
        int row = number / 10;
        int[][] score = new int[row][col];
        String[] courseID = new String[col];
        String[] studentID = new String[row];


        for (int i = 0; i < col; i++) {
            courseID[i] = input.next();
        }

        for (int i = 0; i < row; i++) {
            studentID[i] = input.next();
            for (int j = 0; j < col; j++) {
                score[i][j] = input.nextInt();
            }
        }
        Student stu = new Student(score, studentID, row, col);
        stu.topStu();

        // ***************   CREDIT LEVEL  ***************
        // *********************************************
        ///*
        String[] c_Title = new String[col];
        Scanner input1 = new Scanner(new File(args[1]));
        int count = 0;
        int[] credit = new int[col];

        while(input1.hasNext()){
            input1.next();
            c_Title[count] = input1.next();
            credit[count] = input1.nextInt();
            count++;
        }
        Course co = new Course(score, c_Title, credit, courseID, row, col);
        co.writeFile();


        // ***************   DI LEVEL  ***************
        // *********************************************
        ///*
        String[] stu_name = new String[row];
        Scanner input2 = new Scanner(new File(args[2]));
        int[] age = new int[row];
        int di_count = 0;

        while(input2.hasNext()){
            input2.next();
            stu_name[di_count] = input2.next();
            age[di_count] = input2.nextInt();
            di_count++;
        }
        Student stu1 = new Student(score, studentID, row, col, stu_name, age);
        stu1.writeFile();


        // ***************   HD LEVEL  ***************
        // *********************************************
        ///*
        Student stu2 = new Student(score, studentID, row, col, stu_name, age);
        stu2.calGPA_HD(credit);

    }


}