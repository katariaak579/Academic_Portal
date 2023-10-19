package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class student_functionalitiesTest {

    @Test
    void enroll_course() {
        ResultSet rs;
        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_student(c,stmt,"2020tst2222","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");
            adminfn.add_course(c,stmt,"CSts1","CrsT1",1,1,1,1,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts2","CrsT2",1,1,1,1,"CSE","PCE","null");
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1.0);

            studentfn.enroll_course(c,stmt,"2020tst1111","CSts1");
//            assertEquals("student enrolled in course",studentfn.enroll_course(c,stmt,"2020tst1111","CSts1").toLowerCase());

//            studentfn.enroll_course(c,stmt,"2020tst1111","asd");
            assertEquals("course does not exist",studentfn.enroll_course(c,stmt,"2020tst1111","asd").toLowerCase());

            facultyfn.enter_grade(c,stmt,"CSts1","Tst_f1","2020tst1111","A");

            adminfn.add_course(c,stmt,"CSts3","CrsT3",1,1,1,1,"CSE","PCE","CStst1-CStst2");
            adminfn.move_courses(c,stmt);
            String asd="select * from sysinfo ";
            rs=stmt.executeQuery(asd);
            String y="";
            String s="";
            while (rs.next()){
                y=rs.getString(1);
                s=rs.getString(2);
            }
            String crs="CSts1";
            String tname="\""+crs+"_"+y+"_"+s+"\"";
            String dp="drop table "+tname+"";
//            stmt.execute((dp));


            System.out.println("here");

            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1.0);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts1");

            String n="update sysinfo set sem=2";
            stmt.execute(n);
            facultyfn.offer_course(c,stmt,"CSts2","Tst_f1",1.0);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts2");
//            assertEquals("course does not exist",studentfn.enroll_course(c,stmt,"2020tst1111","CSts2").toLowerCase());
            facultyfn.enter_grade(c,stmt,"CSts2","Tst_f1","2020tst1111","B");
            adminfn.move_courses(c,stmt);
            String asd1="select * from sysinfo ";
            rs=stmt.executeQuery(asd);
            while (rs.next()){
                y=rs.getString(1);
                s=rs.getString(2);
            }
            String crs1="CSts2";
            String tname1="\""+crs1+"_"+y+"_"+s+"\"";
            String dp1="drop table "+tname+"";
            stmt.execute((dp1));


            facultyfn.offer_course(c,stmt,"CSts3","Tst_f1",2);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts3");
//            assertEquals("course does not exist",studentfn.enroll_course(c,stmt,"2020tst1111","CSts3").toLowerCase());



            facultyfn.deoffer_course(c,stmt,"CSts2");
            facultyfn.deoffer_course(c,stmt,"CSts1");
            facultyfn.deoffer_course(c,stmt,"CSts3");
            adminfn.remove_course(c,stmt,"CSts3");
            adminfn.remove_course(c,stmt,"CSts2");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");
            adminfn.remove_student(c,stmt,"2020tst2222");
        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}



    }

    @Test
    void get_cg() {
        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");
            adminfn.add_course(c,stmt,"CSts1","CrsT1",1,1,1,1,"CSE","PCE","null");
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1.0);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts1");
            studentfn.get_cg(c,stmt,"2020tst111");
            studentfn.get_cg(c,stmt,"2020tst1111");
//            assertEquals("student does not exist",studentfn.get_cg(c,stmt,"2020tst11").toLowerCase());
//            assertEquals("cgpa",studentfn.get_cg(c,stmt,"2020tst1111").toLowerCase());
            facultyfn.deoffer_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");
        }catch(SQLException e){System.out.println("Can't connect to db"+e.getMessage());}


    }

    @Test
    void view_grades() {

        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        ResultSet rs;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");
            adminfn.add_course(c,stmt,"CSts1","CrsT1",1,1,1,1,"CSE","PCE","null");
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1.0);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts1");
            facultyfn.enter_grade(c,stmt,"CSts1","Tst_f1","2020tst1111","A");
            adminfn.move_courses(c,stmt);
            studentfn.view_grades(c,stmt,"2020tst111");
            studentfn.view_grades(c,stmt,"2020tst1111");
//            assertEquals("student does not exist",studentfn.get_cg(c,stmt,"2020tst11").toLowerCase());
//            assertEquals("cgpa",studentfn.get_cg(c,stmt,"2020tst1111").toLowerCase());
            System.out.println("View Grades");
            facultyfn.deoffer_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");
            String asd="select * from sysinfo ";
            rs=stmt.executeQuery(asd);
            String y="";
            String s="";
            while (rs.next()){
                y=rs.getString(1);
                s=rs.getString(2);
            }
            String crs="CSts1";
            String tname="\""+crs+"_"+y+"_"+s+"\"";
            String dp="drop table "+tname+"";
            stmt.execute((dp));
        }catch(SQLException e){System.out.println("Can't connect to db"+e.getMessage());}

    }
    @Test
    void drop_course() {
        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_student(c,stmt,"2020tst11","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");
            adminfn.add_course(c,stmt,"CSts1","CrsT1",1,1,1,1,"CSE","PCE","null");
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1.0);
//            facultyfn.offer_course(c,stmt,"CSts2","Tst_f1",1.0);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts1");
            studentfn.get_cg(c,stmt,"2020tst111");
            studentfn.get_cg(c,stmt,"2020tst1111");
//            assertEquals("student does not exist",studentfn.get_cg(c,stmt,"2020tst11").toLowerCase());
//            assertEquals("cgpa",studentfn.get_cg(c,stmt,"2020tst1111").toLowerCase());
            studentfn.drop_course(c,stmt,"2020tst1111","CSts1");
            studentfn.drop_course(c,stmt,"2020tst111","CSts1");
            studentfn.drop_course(c,stmt,"2020tst1111","CSt1");
            studentfn.drop_course(c,stmt,"2020tst1111","CSt2");
            studentfn.drop_course(c,stmt,"2020tst11","CSts1");
            facultyfn.deoffer_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");
            adminfn.remove_student(c,stmt,"2020tst11");

        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}



    }
}