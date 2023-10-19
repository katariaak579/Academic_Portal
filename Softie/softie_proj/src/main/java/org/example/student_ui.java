package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class student_ui {
    public static void test(Connection c, Statement stmt, String entry_num) throws SQLException {
        ResultSet rs, rs1;
        student_functionalities studentis = new student_functionalities();
        faculty_functionalities facultyis= new faculty_functionalities();
        admin_functionalities adminis=new admin_functionalities();
        String see = "select * from sysinfo;";
        String semis = "";
        String yearis = "";
        int sem = 0;
        int year = 0;
        String stsis="";
        rs = stmt.executeQuery(see);
        while (rs.next()) {
            yearis = rs.getString(1);
            semis = rs.getString(2);
            sem = Integer.parseInt(semis);
            year = Integer.parseInt(yearis);
            stsis=rs.getString(3);
        }
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Welcome to AIMS Student UI Page");
            System.out.println("Username: " + entry_num);
            System.out.println("Current Year: "+yearis);
            System.out.println("Current Semester: "+semis);
            System.out.println("Current Semester Status: "+stsis);
            System.out.println("");
            System.out.println("Press 1 to view all Grades");
            System.out.println("Press 2 to calculate cgpa");
            System.out.println("Press 3 to view all course");
            System.out.println("Press 4 to view all offered");
            System.out.println("Press 5 to enroll in a course");
            System.out.println("Press 6 to Drop Course");
            System.out.println("Press 7 to view current Registrations");
            System.out.println("Press 8 to Change Password");
            System.out.println("Press 9 to Logout");
            Scanner sc = new Scanner(System.in);

            String pick = sc.nextLine();
            if (pick.equals("1")) {
                studentis.view_grades(c, stmt, entry_num);
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("2")) {
                studentis.get_cg(c, stmt, entry_num);
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("3")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: "+entry_num);
                System.out.println("Current Year: "+yearis);
                System.out.println("Current Semester: "+semis);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                String abc="select * from course;";
                rs1=stmt.executeQuery(abc);
                System.out.println("|    Course Code     |        Name        |  L----T----P----C  |    Department_ID   |     Category       |");
                System.out.println(" ******************** ******************** ******************** ******************** ********************");
                while (rs1.next()){
                    String c_code=rs1.getString(1);
                    String c_name=rs1.getString(2);
                    String c_l=rs1.getString(3);
                    String c_t=rs1.getString(4);
                    String c_p=rs1.getString(5);
                    String c_c=rs1.getString(6);
                    String c_dept=rs1.getString(7);
                    String c_cat=rs1.getString(8);
                    System.out.printf("|%-20s|%-20s|%-5s|%-5s|%-5s|%-5s|%-20s|%-20s|\n",c_code,c_name,c_l,c_t,c_p,c_c,c_dept,c_cat);
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();

            } else if (pick.equals("4")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: "+entry_num);
                System.out.println("Current Year: "+yearis);
                System.out.println("Current Semester: "+semis);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                String view="select * from offering ;";
                rs1=stmt.executeQuery(view);
                System.out.println("|    Course Code     |        Faculty ID  |      Semester      |        Year        |     Minimum Cgpa   |");
                System.out.println(" ******************** ******************** ******************** ******************** ********************");
                while (rs1.next()){
                    String crs=rs1.getString(1);
                    String c_name=rs1.getString(2);
                    String c_l=rs1.getString(3);
                    String c_t=rs1.getString(4);
                    String c_p=rs1.getString(5);
                    System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s|\n",crs,c_name,c_l,c_t,c_p);
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("5")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                if(stsis.equals("Course Enrollment Period")) {
                    System.out.println("Select a course to enroll from all the courses offered ");
                    String crs = sc.nextLine();
                    studentis.enroll_course(c, stmt, entry_num, crs);
                }
                else{
                        System.out.println("Course Enrollment Period not going on. Current Semester status is "+stsis);
                    }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("6")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: "+entry_num);
                System.out.println("Current Year: "+yearis);
                System.out.println("Current Semester: "+semis);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                if(stsis.equals("Course Enrollment Period")) {
                    System.out.print("Enter the course: ");
                    String zxc=sc.nextLine();
                    studentis.drop_course(c, stmt, entry_num, zxc);
                }
                else{
                    System.out.println("Cannot Drop out as the status is not Course Enrollment Period. Current Status "+stsis);
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("7")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: "+entry_num);
                System.out.println("Current Year: "+yearis);
                System.out.println("Current Semester: "+semis);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                String add= "_all_course";
                String add2="_current_course";
                String tname3 = "\""+entry_num+add2+"\"";
                String ert="select courses,remark from "+tname3+";";
                rs=stmt.executeQuery(ert);
                System.out.println("|    Course Code     |        Remark      |");
                System.out.println(" ******************** ******************** ");
                while (rs.next()){
                    String cr=rs.getString(1);
                    String gr=rs.getString(2);
                    System.out.printf("|%-20s|%-20s|\n",cr,gr);
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("8")) {

                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Loggen in as: " + entry_num);
                System.out.println("Current Year: "+yearis);
                System.out.println("Current Semester: "+semis);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");

                System.out.print("Enter the old password: ");
                String qwe=sc.nextLine();
                String iop="select login_key from student where entry_number='"+entry_num+"';";
                rs=stmt.executeQuery(iop);
                String adf="";
                while (rs.next()){
                    adf=rs.getString(1);
                }
                if (!adf.equals(qwe)){
                    System.out.println("Password Does not match with the actual password");
                }
                else{
                    System.out.print("Enter the new password: ");
                    String newp=sc.nextLine();
                    String chng="update student set login_key='"+newp+"' where entry_number='"+entry_num+"';";
                    stmt.execute(chng);

                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();

            } else{
                break;
            }
        }
    }

}