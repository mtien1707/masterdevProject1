package com.cuminhtien;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

import static java.lang.Math.random;

public class connectMysql {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://172.17.80.20:3306/masterdev_tiencm8?serverTimezone=UTC&useSSL=false";
    static final String USER = "tiencm8";
    static final String PASS = "Aq9TaqZ5J5bdh4bf";


    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {

            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");

            String sql = "insert into student_class (student_id,class_id,scores)" + " values (?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);

            try {
                File myObj = new File("E:/java_mysql/src/main/java/data/student_id.txt");
                Scanner myReader = new Scanner(myObj);
                File myObj1 = new File("E:/java_mysql/src/main/java/data/class_id.txt");
                Scanner myReader1 = new Scanner(myObj1);
                File myObj2 = new File("E:/java_mysql/src/main/java/data/scores.txt");
                Scanner myReader2 = new Scanner(myObj2);

                int count = 0;

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String subject = myReader1.nextLine();
                    String scores = myReader2.nextLine();

                    preparedStmt.setString(1, data);

                    preparedStmt.setString(2, subject);

                    preparedStmt.setString(3,scores);



                    preparedStmt.execute();

                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
//                try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
//
//                    Class.forName(JDBC_DRIVER);
//                    System.out.println("Connecting to database...");
//
//                    String sql = "insert into class (subject_id,teacher_id,semester,scores)" + " values (?,?,?,?)";
//                    PreparedStatement preparedStmt = connection.prepareStatement(sql);
//
//                    try {
//                        File myObj = new File("E:/java_mysql/src/main/java/data/subject_id.txt");
//                        Scanner myReader = new Scanner(myObj);
//                        File myObj1 = new File("E:/java_mysql/src/main/java/data/teacher_id.txt");
//                        Scanner myReader1 = new Scanner(myObj1);
//                        File myObj2 = new File("E:/java_mysql/src/main/java/data/semester.txt");
//                        Scanner myReader2 = new Scanner(myObj2);
//                        File myObj3 = new File("E:/java_mysql/src/main/java/data/scores.txt");
//                        Scanner myReader3 = new Scanner(myObj3);
//
//                        int count = 0;
//
//                        while (myReader.hasNextLine()) {
//                            String subject_id= myReader.nextLine();
//                            String teacher_id = myReader1.nextLine();
//                            String semester = myReader2.nextLine();
//                            String scores = myReader3.nextLine();
//
//                            preparedStmt.setString (1, subject_id);
//
//                            preparedStmt.setString (2, teacher_id);
//
//                            preparedStmt.setString (3, semester);
//
//                            preparedStmt.setString (4, scores);
//
//                            preparedStmt.execute();
//
//
//                            System.out.println(subject_id);
//
//                        }
//                        myReader.close();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }



                System.out.println("Inserted records into the table...");
                System.out.println("Insert data success");
            } catch(SQLException | ClassNotFoundException se){
                se.printStackTrace();
            }
            System.out.println("Done!");

    }
}
