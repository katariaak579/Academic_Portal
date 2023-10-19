package org.example;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class faculty_functionalitiesTest {

    @Test
    void offer_course() {
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
            facultyfn.offer_course(c,stmt,"no","Tst_f1",1);
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1);
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1);
            facultyfn.offer_course(c,stmt,"CSts11111111","Tst_f1",1);

//            assertEquals("course does not exist",facultyfn.offer_course(c,stmt,"no","Tst_f1",1).toLowerCase());
//            assertEquals("course offered successfully",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
//            assertEquals("course already offered",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
            adminfn.remove_student(c,stmt,"2020tst1111");
            facultyfn.deoffer_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_faculty(c,stmt,"Tst_f1");


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}

    }

    @Test
    void enter_grade() {

        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_student(c,stmt,"2020tst111","Test","2020tst111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");
            adminfn.add_course(c,stmt,"CSts1","CrsT1",1,1,1,1,"CSE","PCE","null");
            facultyfn.offer_course(c,stmt,"no","Tst_f1",1);
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1);
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1);
            facultyfn.offer_course(c,stmt,"CSts11111111","Tst_f1",1);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts1");
//            assertEquals("course does not exist",facultyfn.offer_course(c,stmt,"no","Tst_f1",1).toLowerCase());
//            assertEquals("course offered successfully",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
//            assertEquals("course already offered",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
            facultyfn.enter_grade(c,stmt,"CSts1","Tst_f1","2020tst1111","A");
            facultyfn.enter_grade(c,stmt,"no","Tst_f1","2020tst1111","A");
            facultyfn.enter_grade(c,stmt,"CSts1","Tst_f1","2020tst111","A");
            facultyfn.deoffer_course(c,stmt,"CSts1");
            facultyfn.deoffer_course(c,stmt,"qwe");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");
            adminfn.remove_student(c,stmt,"2020tst111");


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}



    }

    @Test
    void enter_grade_csv(){
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
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1);

//            assertEquals("course does not exist",facultyfn.offer_course(c,stmt,"no","Tst_f1",1).toLowerCase());
//            assertEquals("course offered successfully",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
//            assertEquals("course already offered",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
            facultyfn.enter_grade_csv(c,stmt,"yee.csv");
            String pthis="/Users/anubhavkataria/Desktop/Softie/softie_proj/src/main/java/org/example/Input_cases.csv";
            facultyfn.enter_grade_csv(c,stmt,pthis);
            facultyfn.deoffer_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");

        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());
        }
    }

    @Test
    void deoffer_course() {
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
            facultyfn.offer_course(c,stmt,"no","Tst_f1",1);
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1);
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1);
            facultyfn.offer_course(c,stmt,"CSts11111111","Tst_f1",1);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts1");
//            assertEquals("course does not exist",facultyfn.offer_course(c,stmt,"no","Tst_f1",1).toLowerCase());
//            assertEquals("course offered successfully",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
//            assertEquals("course already offered",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());

            facultyfn.deoffer_course(c,stmt,"CSts1");
            facultyfn.deoffer_course(c,stmt,"qwe");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}

    }
}