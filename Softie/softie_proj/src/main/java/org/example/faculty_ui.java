package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class faculty_ui{

    public static void test(Connection c, Statement stmt, String f_id) throws SQLException {
        ResultSet rs, rs1,rs2;
        student_functionalities studentis = new student_functionalities();
        faculty_functionalities facultyis= new faculty_functionalities();
        admin_functionalities adminis=new admin_functionalities();
        String see = "select * from sysinfo;";
        String cur_sem = "";
        String cur_year = "";
        String stsis="";
        rs = stmt.executeQuery(see);
        while (rs.next()) {
            cur_year = rs.getString(1);
            cur_sem = rs.getString(2);
            stsis=rs.getString(3);
        }

        while(true){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Welcome to AIMS Faculty UI Page");
            System.out.println("Loggen in as: " + f_id);
            System.out.println("Current Year: "+cur_year);
            System.out.println("Current Semester: "+cur_sem);
            System.out.println("Current Semester Status: "+stsis);
            System.out.println("");
            System.out.println("Functionalities of Faculty Page");
            System.out.println("Press 1 to View my offered courses");
            System.out.println("Press 2 to View all offered courses");
            System.out.println("Press 3 to View Catalog");
            System.out.println("Press 4 to Offer a course");
            System.out.println("Press 5 to de-Offer a course");
            System.out.println("Press 6 to Views Students in my course");
            System.out.println("Press 7 to Enter Grades");
            System.out.println("Press 8 to Change Password");
            System.out.println("Press 9 Logout");
            System.out.print("Input: ");
            Scanner sc = new Scanner(System.in);
            String pick = sc.nextLine();
            if (pick.equals("1")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Faculty UI Page");
                System.out.println("Loggen in as: " + f_id);
                System.out.println("Current Year: "+cur_year);
                System.out.println("Current Semester: "+cur_sem);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                String view="select * from offering where fid='"+f_id+"';";
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

            } else if (pick.equals("2")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: "+f_id);
                System.out.println("Current Year: "+cur_year);
                System.out.println("Current Semester: "+cur_sem);
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

            } else if (pick.equals("3")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Faculty UI Page");
                System.out.println("Loggen in as: " + f_id);
                System.out.println("Current Year: "+cur_year);
                System.out.println("Current Semester: "+cur_sem);
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
                System.out.println("Welcome to AIMS Faculty UI Page");
                System.out.println("Loggen in as: " + f_id);
                System.out.println("Current Year: "+cur_year);
                System.out.println("Current Semester: "+cur_sem);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                if(stsis.equals("Course Offering Period")) {
                    System.out.print("Enter the Course Code of the offering Course: ");
                    String crs = sc.nextLine();
                    System.out.print("Enter the minimum cgpa to enroll in the course: ");
                    double m_cg = sc.nextDouble();
                    facultyis.offer_course(c, stmt, crs, f_id, m_cg);
                }
                else{
                    System.out.println("Course Offering Period not going on. Current Semester status is "+stsis);
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("5")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Faculty UI Page");
                System.out.println("Loggen in as: " + f_id);
                System.out.println("Current Year: "+cur_year);
                System.out.println("Current Semester: "+cur_sem);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                if(stsis.equals("Course Offering Period")) {
                System.out.print("Enter the Course Code for de-Offering: ");
                String crs=sc.nextLine();
                facultyis.deoffer_course(c,stmt,crs);
                }
                else{
                    System.out.println("Course Offering Period not going on. Current Semester status is "+stsis);
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("6")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Faculty UI Page");
                System.out.println("Loggen in as: " + f_id);
                System.out.println("Current Year: "+cur_year);
                System.out.println("Current Semester: "+cur_sem);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                System.out.print("Enter the Course:");
                String ans=sc.nextLine();
                String chk="select count(*) from offering where course_id='"+ans+"';";
                rs=stmt.executeQuery(chk);
                String cu="";
                while (rs.next()){
                    cu=rs.getString(1);
                }
                if(cu.equals("0")){
                    System.out.println("No such Course exist");
                }
                else{
                    String dob="select fid from offering where course_id='"+ans+"';";
                    rs1=stmt.executeQuery(dob);
                    String akl="";
                    while (rs1.next()){
                        akl=rs1.getString(1);
                    }
                    if(!akl.equals(f_id)){
                        System.out.println(f_id+" does not teach this course");
                    }
                    else{
                        String dash="_";
                        String tname="\""+ans+dash+cur_year+dash+cur_sem+"\"";
                        String qwe="select * from "+tname+"";
                        rs2=stmt.executeQuery(qwe);
                        System.out.println("|    Course Code     |        Faculty ID  |");
                        System.out.println(" ******************** ******************** ");
                        while (rs2.next()){
                            String cr=rs2.getString(1);
                            String gr=rs2.getString(2);
                            System.out.printf("|%-20s|%-20s|\n",cr,gr);
                        }
                    }
                    System.out.println("Press any key to continue");
                    String x=sc.nextLine();
                }

            } else if (pick.equals("7")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Faculty UI Page");
                System.out.println("Loggen in as: " + f_id);
                System.out.println("Current Year: "+cur_year);
                System.out.println("Current Semester: "+cur_sem);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                if(stsis.equals("Grading Period")) {
                System.out.print("Do you want to add grades manually or via csv file( Enter csv for csv else press any key): ");
                String csv=sc.nextLine();
                if(csv.equals("csv")){
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Welcome to AIMS Faculty UI Page");
                    System.out.println("Loggen in as: " + f_id);
                    System.out.println("Current Year: "+cur_year);
                    System.out.println("Current Semester: "+cur_sem);
                    System.out.println("Current Semester Status: "+stsis);
                    System.out.println("");
                    System.out.println("Enter the path");
                    String pth=sc.nextLine();
                    facultyis.enter_grade_csv(c,stmt,pth);
                }
                else{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Welcome to AIMS Faculty UI Page");
                    System.out.println("Loggen in as: " + f_id);
                    System.out.println("Current Year: "+cur_year);
                    System.out.println("Current Semester: "+cur_sem);
                    System.out.println("Current Semester Status: "+stsis);
                    System.out.println("");
                    System.out.print("Enter Student Entry number: ");
                    String en=sc.nextLine();
                    System.out.print("Enter the code of course you wanna grade: ");
                    String crsid=sc.nextLine();
                    System.out.print("Enter the Grade: ");
                    String grd=sc.nextLine();
                    facultyis.enter_grade(c,stmt,crsid,f_id,en,grd);
                }
            }
            else{
                System.out.println("Grading Period not going on. Current Semester status is "+stsis);

            }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("8")) {

                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Faculty UI Page");
                System.out.println("Loggen in as: " + f_id);
                System.out.println("Current Year: "+cur_year);
                System.out.println("Current Semester: "+cur_sem);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");

                System.out.print("Enter the old password: ");
                String qwe=sc.nextLine();
                String iop="select login_key from instructor where id='"+f_id+"';";
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
                    String chng="update instructor set login_key='"+newp+"' where id='"+f_id+"';";
                    stmt.execute(chng);

                }


            } else {
                break;
            }

        }

    }

}