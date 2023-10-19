package org.example;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class admin_ui{
    public static void test(Connection c, Statement stmt) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        ResultSet ab,rs,rs1;
        student_functionalities studentis = new student_functionalities();
        faculty_functionalities facultyis= new faculty_functionalities();
        admin_functionalities adminis=new admin_functionalities();
        String sts="select * from sysinfo;";
        ab=stmt.executeQuery(sts);
        String stsis="";
        String yr="";
        String semis="";
        while (ab.next()){
            yr=ab.getString(1);
            semis=ab.getString(2);
            stsis=ab.getString(3);
        }


        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Welcome to AIMS Student UI Page");
            System.out.println("Username: Admin");
            System.out.println("Current Year: "+yr);
            System.out.println("Current Semester: "+semis);
            System.out.println("Current Semester Status: "+stsis);

            System.out.println("");
            System.out.println("Press 1 to Change Semester Status");
            System.out.println("Press 2 to Add/Drop Student");
            System.out.println("Press 3 to Add/Drop Faculty");
            System.out.println("Press 4 to Add/Drop Course");
            System.out.println("Press 5 to View Student List");
            System.out.println("Press 6 to View Faculty List");
            System.out.println("Press 7 to View Course Catalog");
            System.out.println("Press 8 to View Courses Offered");
            System.out.println("Press 9 for View Student Registration");
            System.out.println("Press 10 for Student Graduation");
            System.out.println("Press 11 for Student Transcript Generation");
            System.out.println("Press 12 to Logout");
            Scanner sc = new Scanner(System.in);
            System.out.print("Input: ");
            String pick=sc.nextLine();

            if(pick.equals("1")){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: Admin");
                System.out.println("Current Year: "+yr);
                System.out.println("Current Semester: "+semis);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                System.out.println("Press 1 for Pre Semester Begin Period");
                System.out.println("Press 2 for Course Offering Period");
                System.out.println("Press 3 for Course Enrollment Period");
                System.out.println("Press 4 for Semester Period");
                System.out.println("Press 5 for Grading Period");
                System.out.println("Press 6 for Updating Student Record Period");
                System.out.println("Press 7 for Semester Ended Period");
                System.out.print("Input: ");
                String st=sc.nextLine();
                if(st.equals("1")){
                    stsis="Pre Semester Begin Period";
                    String chng="update sysinfo set status='"+stsis+"';";
                    stmt.execute(chng);
                }
                else if (st.equals("2")) {
                    stsis="Course Offering Period";
                    String chng="update sysinfo set status='"+stsis+"';";
                    stmt.execute(chng);
                }
                else if (st.equals("3")) {
                    stsis="Course Enrollment Period";
                    String chng="update sysinfo set status='"+stsis+"';";
                    stmt.execute(chng);
                }
                else if (st.equals("4")) {
                    stsis="Semester Period";
                    String chng="update sysinfo set status='"+stsis+"';";
                    stmt.execute(chng);
                }
                else if (st.equals("5")) {
                    stsis="Grading Period";
                    String chng="update sysinfo set status='"+stsis+"';";
                    stmt.execute(chng);
                }
                else if (st.equals("6")) {
                    stsis="Updating Student Record Period";
                    String chng="update sysinfo set status='"+stsis+"';";
                    stmt.execute(chng);
                    adminis.upt_cgpa(c,stmt);
                    adminis.move_courses(c,stmt);
                }
                else  {
                    stsis="Semester Ended Period";
                    String chng="update sysinfo set status='"+stsis+"';";
                    stmt.execute(chng);
                    String axo="select * from sysinfo";
                    rs=stmt.executeQuery(axo);
                    String yr1="";
                    String sm="";
                    while (rs.next()){
                        yr=rs.getString(1);
                        sm=rs.getString(2);
                    }
                    int ye=Integer.parseInt(yr);
                    int se=Integer.parseInt(sm);
                    if(se==1){
                        se++;
                    }
                    else{
                        se--;
                        ye++;
                    }
                    String upty="update sysinfo set year='"+ye+"';";
                    stmt.execute(upty);
                    String upts="update sysinfo set sem='"+se+"';";
                    stmt.execute(upts);
                    yr=String.valueOf(ye);
                    semis=String.valueOf(se);
                }
                System.out.println("Updated Succesfully");
                System.out.println("Press any key to continue");
                String x=sc.nextLine();


            } else if (pick.equals("2")) {
                if(stsis.equals("Pre Semester Begin Period")) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Welcome to AIMS Student UI Page");
                    System.out.println("Username: Admin");
                    System.out.println("Current Year: " + yr);
                    System.out.println("Current Semester: " + semis);
                    System.out.println("Current Semester Status: " + stsis);
                    System.out.println("");
                    System.out.print("Enter a to add and r to remove student: ");
                    String ar = sc.nextLine();
                    if (ar.equals("a")) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Welcome to AIMS Student UI Page");
                        System.out.println("Username: Admin");
                        System.out.println("Current Year: " + yr);
                        System.out.println("Current Semester: " + semis);
                        System.out.println("Current Semester Status: " + stsis);
                        System.out.println("");
                        System.out.print("Enter csv for csv and m for manually: ");
                        String cs = sc.nextLine();
                        if (cs.equals("m")) {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Welcome to AIMS Student UI Page");
                            System.out.println("Username: Admin");
                            System.out.println("Current Year: " + yr);
                            System.out.println("Current Semester: " + semis);
                            System.out.println("Current Semester Status: " + stsis);
                            System.out.println("");
                            System.out.print("Enter Entry number: ");
                            String std = sc.nextLine();
                            System.out.print("Enter Name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter email: ");
                            String email = sc.nextLine();
                            System.out.print("Enter BatchID(Format:DepartmentYear): ");
                            String bid = sc.nextLine();
                            System.out.print("Enter Login_key add: ");
                            String key = sc.nextLine();
                            adminis.add_student(c, stmt, std, name, email, bid, key, 0.0, 0);
                        } else {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Welcome to AIMS Student UI Page");
                            System.out.println("Username: Admin");
                            System.out.println("Current Year: " + yr);
                            System.out.println("Current Semester: " + semis);
                            System.out.println("Current Semester Status: " + stsis);
                            System.out.println("");
                            System.out.print("Enter file path: ");
                            String pth = sc.nextLine();
                            adminis.add_student_csv(c, stmt, pth);
                        }
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Welcome to AIMS Student UI Page");
                        System.out.println("Username: Admin");
                        System.out.println("Current Year: " + yr);
                        System.out.println("Current Semester: " + semis);
                        System.out.println("Current Semester Status: " + stsis);
                        System.out.println("");
                        System.out.print("Enter Entry number of student to remove: ");
                        String std = sc.nextLine();
                        adminis.remove_student(c, stmt, std);
                    }
                }
                else{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Welcome to AIMS Student UI Page");
                    System.out.println("Username: Admin");
                    System.out.println("Current Year: "+yr);
                    System.out.println("Current Semester: "+semis);
                    System.out.println("Current Semester Status: "+stsis);
                    System.out.println("");
                    System.out.println("Current Status is not Pre Semester Begin Period. Cannot add or remove Student");
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();


            }
            else if (pick.equals("3")) {
                if(stsis.equals("Pre Semester Begin Period")) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Welcome to AIMS Student UI Page");
                    System.out.println("Username: Admin");
                    System.out.println("Current Year: " + yr);
                    System.out.println("Current Semester: " + semis);
                    System.out.println("Current Semester Status: " + stsis);
                    System.out.println("");
                    System.out.print("Enter a to add and r to remove faculty: ");

                    String ar = sc.nextLine();
                    if (ar.equals("a")) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Welcome to AIMS Student UI Page");
                        System.out.println("Username: Admin");
                        System.out.println("Current Year: " + yr);
                        System.out.println("Current Semester: " + semis);
                        System.out.println("Current Semester Status: " + stsis);
                        System.out.println("");
                        System.out.print("Enter csv for csv and m for manually: ");
                        String cs = sc.nextLine();
                        if (cs.equals("m")) {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Welcome to AIMS Student UI Page");
                            System.out.println("Username: Admin");
                            System.out.println("Current Year: " + yr);
                            System.out.println("Current Semester: " + semis);
                            System.out.println("Current Semester Status: " + stsis);
                            System.out.println("");
                            System.out.print("Enter Faculty_Id: ");
                            String std = sc.nextLine();
                            System.out.print("Enter Name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter email: ");
                            String email = sc.nextLine();
                            System.out.print("Enter Department: ");
                            String bid = sc.nextLine();
                            System.out.print("Enter Login_key: ");
                            String key = sc.nextLine();
                            adminis.add_faculty(c, stmt, std, name, email, bid, key);
                        } else {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Welcome to AIMS Student UI Page");
                            System.out.println("Username: Admin");
                            System.out.println("Current Year: " + yr);
                            System.out.println("Current Semester: " + semis);
                            System.out.println("Current Semester Status: " + stsis);
                            System.out.println("");
                            System.out.print("Enter file path: ");
                            String pth = sc.nextLine();
                            adminis.add_faculty_csv(c, stmt, pth);
                        }
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Welcome to AIMS Student UI Page");
                        System.out.println("Username: Admin");
                        System.out.println("Current Year: " + yr);
                        System.out.println("Current Semester: " + semis);
                        System.out.println("Current Semester Status: " + stsis);
                        System.out.println("");
                        System.out.print("Enter Faculty_id of faculty to remove: ");
                        String std = sc.nextLine();
                        adminis.remove_faculty(c, stmt, std);
                    }
                }
                else{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Welcome to AIMS Student UI Page");
                    System.out.println("Username: Admin");
                    System.out.println("Current Year: "+yr);
                    System.out.println("Current Semester: "+semis);
                    System.out.println("Current Semester Status: "+stsis);
                    System.out.println("");
                    System.out.println("Current Status is not Pre Semester Begin Period. Cannot add or remove Faculty");
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();

            } else if (pick.equals("4")) {
                if(stsis.equals("Course Offering Period")) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Welcome to AIMS Student UI Page");
                    System.out.println("Username: Admin");
                    System.out.println("Current Year: " + yr);
                    System.out.println("Current Semester: " + semis);
                    System.out.println("Current Semester Status: " + stsis);
                    System.out.println("");
                    System.out.print("Enter a to add and r to remove course: ");
                    String ar = sc.nextLine();
                    if (ar.equals("a")) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Welcome to AIMS Student UI Page");
                        System.out.println("Username: Admin");
                        System.out.println("Current Year: " + yr);
                        System.out.println("Current Semester: " + semis);
                        System.out.println("Current Semester Status: " + stsis);
                        System.out.println("");
                        System.out.print("Enter csv for csv and m for manually: ");
                        String cs = sc.nextLine();
                        if (cs.equals("m")) {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Welcome to AIMS Student UI Page");
                            System.out.println("Username: Admin");
                            System.out.println("Current Year: " + yr);
                            System.out.println("Current Semester: " + semis);
                            System.out.println("Current Semester Status: " + stsis);
                            System.out.println("");
                            System.out.print("Enter Course_Id: ");
                            String std = sc.nextLine();
                            System.out.print("Enter Name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter Department: ");
                            String key2 = sc.nextLine();
                            System.out.print("Enter Category: ");
                            String key3 = sc.nextLine();
                            System.out.print("Enter Prerequisite(null or Courses sepated by (-)): ");
                            String pre = sc.nextLine();
                            System.out.print("Enter l: ");
                            int email = sc.nextInt();
                            System.out.print("Enter t: ");
                            int bid = sc.nextInt();
                            System.out.print("Enter p: ");
                            int key = sc.nextInt();
                            System.out.print("Enter c: ");
                            int key1 = sc.nextInt();
                            adminis.add_course(c, stmt, std, name, email, bid, key, key1, key2, key3, pre);
                        } else {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Welcome to AIMS Student UI Page");
                            System.out.println("Username: Admin");
                            System.out.println("Current Year: " + yr);
                            System.out.println("Current Semester: " + semis);
                            System.out.println("Current Semester Status: " + stsis);
                            System.out.println("");
                            System.out.print("Enter file path: ");
                            String pth = sc.nextLine();
                            adminis.add_course_csv(c, stmt, pth);

                        }
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Welcome to AIMS Student UI Page");
                        System.out.println("Username: Admin");
                        System.out.println("Current Year: " + yr);
                        System.out.println("Current Semester: " + semis);
                        System.out.println("Current Semester Status: " + stsis);
                        System.out.println("");
                        System.out.print("Enter Faculty_id of course to remove: ");
                        String std = sc.nextLine();
                        adminis.remove_course(c, stmt, std);
                    }
                }
                else{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Welcome to AIMS Student UI Page");
                    System.out.println("Username: Admin");
                    System.out.println("Current Year: "+yr);
                    System.out.println("Current Semester: "+semis);
                    System.out.println("Current Semester Status: "+stsis);
                    System.out.println("");
                    System.out.println("Current Status is not Course Offering Period. Cannot add or remove course");
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();

            } else if (pick.equals("5")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: Admin");
                System.out.println("Current Year: "+yr);
                System.out.println("Current Semester: "+semis);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                String alls="select * from student";
                rs1=stmt.executeQuery(alls);
                System.out.println("|    Entry Number     |        Name        |          Email           |    Batch_ID        |     Login_key       |     Cgpa           |     Credits_total  |");
                System.out.println(" ******************** ******************** *************************** ******************** ********************* ******************** ********************");

                while (rs1.next()){
                    String c_code=rs1.getString(1);
                    String c_name=rs1.getString(2);
                    String c_l=rs1.getString(3);
                    String c_t=rs1.getString(4);
                    String c_p=rs1.getString(5);
                    String c_c=rs1.getString(6);
                    String c_dept=rs1.getString(7);

                    System.out.printf("|%-20s|%-20s|%-26s|%-20s|%-20s|%-20s|%-20s|\n",c_code,c_name,c_l,c_t,c_p,c_c,c_dept);
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("6")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: Admin");
                System.out.println("Current Year: "+yr);
                System.out.println("Current Semester: "+semis);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                String alls="select * from instructor";
                rs1=stmt.executeQuery(alls);
                System.out.println("|    Faculty ID     |        Name        |          Email           |    Department        |     Login_key       |");
                System.out.println(" ******************** ******************** *************************** ******************** ********************* ");

                while (rs1.next()){
                    String c_code=rs1.getString(1);
                    String c_name=rs1.getString(2);
                    String c_l=rs1.getString(3);
                    String c_t=rs1.getString(4);
                    String c_p=rs1.getString(5);

                    System.out.printf("|%-20s|%-20s|%-26s|%-20s|%-20s|\n",c_code,c_name,c_l,c_t,c_p);
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();

            } else if (pick.equals("7")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: Admin");
                System.out.println("Current Year: "+yr);
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

            } else if (pick.equals("8")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: Admin");
                System.out.println("Current Year: "+yr);
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
            }
            else if (pick.equals("9")) {

                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: Admin");
                System.out.println("Current Year: "+yr);
                System.out.println("Current Semester: "+semis);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                System.out.print("Enter Entry Number of the Student:");
                String ent=sc.nextLine();
                String add2="_current_course";
                String tname3 = "\""+ent+add2+"\"";
                String chk="select count(*) from student where entry_number='"+ent+"';";
                rs=stmt.executeQuery(chk);
                String curr="";
                while (rs.next()){
                    curr=rs.getString(1);
                }
                if(curr.equals("0")){
                    System.out.println("Student not exist");
                }
                else{
                    String crsis="select courses from "+tname3+";";
                    rs1=stmt.executeQuery(crsis);
                    while (rs1.next()){
                        String a=rs.getString(1);
                        System.out.println("Course Codes: "+a);
                    }
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();
            } else if (pick.equals("10")) {
                if(stsis.equals("Semester Ended Period")) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Welcome to AIMS Student UI Page");
                    System.out.println("Username: Admin");
                    System.out.println("Current Year: " + yr);
                    System.out.println("Current Semester: " + semis);
                    System.out.println("Current Semester Status: " + stsis);
                    System.out.println("");
                    if (stsis.equals("Semester Ended Period")) {
                        System.out.print("Enter the Entry Number of Student: ");
                        String ent = sc.nextLine();
                        adminis.student_graduate(c, stmt, ent);
                    }
                }
                else{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Welcome to AIMS Student UI Page");
                    System.out.println("Username: Admin");
                    System.out.println("Current Year: "+yr);
                    System.out.println("Current Semester: "+semis);
                    System.out.println("Current Semester Status: "+stsis);
                    System.out.println("");
                    System.out.println("Student cannot graduate as it is not Semester Ended Period");
                }
                System.out.println("Press any key to continue");
                String x=sc.nextLine();

            } else if (pick.equals("11")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Student UI Page");
                System.out.println("Username: Admin");
                System.out.println("Current Year: "+yr);
                System.out.println("Current Semester: "+semis);
                System.out.println("Current Semester Status: "+stsis);
                System.out.println("");
                System.out.println("Enter Entry Number: ");
                String alk=sc.nextLine();
                adminis.generate_transcript(c,stmt,alk);
            } else {
                break;
            }

        }
    }
}