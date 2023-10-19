package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class admin_functionalitiesTest {

    @Test
    void add_course() {

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
            adminfn.add_course(c,stmt,"CSts1","CrsT1",1,1,1,1,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts2","CrsT1",1,1,1,1,"CSE","PCE","CSts1");
//            assertEquals("course does not exist",facultyfn.offer_course(c,stmt,"no","Tst_f1",1).toLowerCase());
//            assertEquals("course offered successfully",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
//            assertEquals("course already offered",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts2");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");
            adminfn.remove_student(c,stmt,"2020tst111");


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}



    }

    @Test
    void remove_course() {


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
            adminfn.add_course(c,stmt,"CSts1","CrsT1",1,1,1,1,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts2","CrsT1",1,1,1,1,"CSE","PCE","CSts1");
//            assertEquals("course does not exist",facultyfn.offer_course(c,stmt,"no","Tst_f1",1).toLowerCase());
//            assertEquals("course offered successfully",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
//            assertEquals("course already offered",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts2");
            adminfn.remove_course(c,stmt,"CSts3");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");
            adminfn.remove_student(c,stmt,"2020tst111");


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}



    }

    @Test
    void add_student() {



        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst111@iitrpr.ac.in","CSE2020","1234",8.0,10);

            adminfn.remove_student(c,stmt,"2020tst1111");
            adminfn.remove_student(c,stmt,"2020tst111");


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}



    }

    @Test
    void remove_student() {



        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst111@iitrpr.ac.in","CSE2020","1234",8.0,10);

            adminfn.remove_student(c,stmt,"2020tst1111");
            adminfn.remove_student(c,stmt,"2020tst111");


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}



    }

    @Test
    void add_faculty() {


        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");
            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");

//              assertEquals("course does not exist",facultyfn.offer_course(c,stmt,"no","Tst_f1",1).toLowerCase());
//            assertEquals("course offered successfully",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
//            assertEquals("course already offered",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
            adminfn.remove_faculty(c,stmt,"Tst_f1");


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}



    }

    @Test
    void remove_faculty() {

        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");
            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");

//              assertEquals("course does not exist",facultyfn.offer_course(c,stmt,"no","Tst_f1",1).toLowerCase());
//            assertEquals("course offered successfully",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
//            assertEquals("course already offered",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_faculty(c,stmt,"Tst_f2");


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}


    }

    @Test
    void prereqis() {

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
            adminfn.add_course(c,stmt,"CSts1","CrsT1",1,1,1,1,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts2","CrsT1",1,1,1,1,"CSE","PCE","CSts1");
            adminfn.prereqis(c,stmt,"asd","as");
            adminfn.prereqis(c,stmt,"CSts2","CSts1-as");
            adminfn.prereqis(c,stmt,"CSts2","as1");

//            assertEquals("course does not exist",facultyfn.offer_course(c,stmt,"no","Tst_f1",1).toLowerCase());
//            assertEquals("course offered successfully",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
//            assertEquals("course already offered",facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1).toLowerCase());
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts2");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");
            adminfn.remove_student(c,stmt,"2020tst111");


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());}



    }

    @Test
    void move_courses() {

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
//            assertEquals("student does not exist",studentfn.get_cg(c,stmt,"2020tst11").toLowerCase());
//            assertEquals("cgpa",studentfn.get_cg(c,stmt,"2020tst1111").toLowerCase());

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
    void upt_cgpa() {

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
            adminfn.add_course(c,stmt,"CSts2","CrsT1",1,1,1,1,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts3","CrsT1",1,1,1,1,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts4","CrsT1",1,1,1,1,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts5","CrsT1",1,1,1,1,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts6","CrsT1",1,1,1,1,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts7","CrsT1",1,1,1,1,"CSE","PCE","null");
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1.0);
            facultyfn.offer_course(c,stmt,"CSts2","Tst_f1",1.0);
            facultyfn.offer_course(c,stmt,"CSts3","Tst_f1",1.0);
            facultyfn.offer_course(c,stmt,"CSts4","Tst_f1",1.0);
            facultyfn.offer_course(c,stmt,"CSts5","Tst_f1",1.0);
            facultyfn.offer_course(c,stmt,"CSts6","Tst_f1",1.0);
            facultyfn.offer_course(c,stmt,"CSts7","Tst_f1",1.0);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts1");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts2");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts3");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts4");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts5");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts6");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts7");

            facultyfn.enter_grade(c,stmt,"CSts1","Tst_f1","2020tst1111","A");
            facultyfn.enter_grade(c,stmt,"CSts2","Tst_f1","2020tst1111","A-");
            facultyfn.enter_grade(c,stmt,"CSts3","Tst_f1","2020tst1111","B");
            facultyfn.enter_grade(c,stmt,"CSts4","Tst_f1","2020tst1111","B-");
            facultyfn.enter_grade(c,stmt,"CSts5","Tst_f1","2020tst1111","C");
            facultyfn.enter_grade(c,stmt,"CSts6","Tst_f1","2020tst1111","C-");
            facultyfn.enter_grade(c,stmt,"CSts7","Tst_f1","2020tst1111","D");
            adminfn.upt_cgpa(c,stmt);
//            assertEquals("student does not exist",studentfn.get_cg(c,stmt,"2020tst11").toLowerCase());
//            assertEquals("cgpa",studentfn.get_cg(c,stmt,"2020tst1111").toLowerCase());

            facultyfn.deoffer_course(c,stmt,"CSts1");
            facultyfn.deoffer_course(c,stmt,"CSts2");
            facultyfn.deoffer_course(c,stmt,"CSts3");
            facultyfn.deoffer_course(c,stmt,"CSts4");
            facultyfn.deoffer_course(c,stmt,"CSts5");
            facultyfn.deoffer_course(c,stmt,"CSts6");
            facultyfn.deoffer_course(c,stmt,"CSts7");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts2");
            adminfn.remove_course(c,stmt,"CSts3");
            adminfn.remove_course(c,stmt,"CSts4");
            adminfn.remove_course(c,stmt,"CSts5");
            adminfn.remove_course(c,stmt,"CSts6");
            adminfn.remove_course(c,stmt,"CSts7");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");
        }catch(SQLException e){System.out.println("Can't connect to db"+e.getMessage());}



    }

    @Test
    void student_graduate() {
        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        ResultSet rs;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,0);
            adminfn.add_student(c,stmt,"2020tst11","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);

            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");
            adminfn.add_faculty(c,stmt,"Tst_f2","FT1","FT1@iitrpr.ac.in","EE","1234");
            adminfn.add_course(c,stmt,"CSts1","CrsT1",1,1,1,6,"CSE","BTP","null");
            adminfn.add_course(c,stmt,"CSts2","CrsT1",1,1,1,6,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts3","CrsT1",1,1,1,6,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts4","CrsT1",1,1,1,6,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts5","CrsT2",1,1,1,6,"EE","PCE","null");
            adminfn.add_course(c,stmt,"CSts6","CrsT2",1,1,1,6,"EE","PCE","null");
            adminfn.add_course(c,stmt,"CSts7","CrsT2",1,1,1,6,"EE","PCE","null");
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1.0);
            facultyfn.offer_course(c,stmt,"CSts2","Tst_f1",1.0);
            facultyfn.offer_course(c,stmt,"CSts3","Tst_f1",1.0);
            facultyfn.offer_course(c,stmt,"CSts4","Tst_f1",1.0);
            facultyfn.offer_course(c,stmt,"CSts5","Tst_f2",1.0);
            facultyfn.offer_course(c,stmt,"CSts6","Tst_f2",1.0);
            facultyfn.offer_course(c,stmt,"CSts7","Tst_f2",1.0);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts1");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts2");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts3");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts4");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts5");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts6");
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts7");

            facultyfn.enter_grade(c,stmt,"CSts1","Tst_f1","2020tst1111","A");
            facultyfn.enter_grade(c,stmt,"CSts2","Tst_f1","2020tst1111","A-");
            facultyfn.enter_grade(c,stmt,"CSts3","Tst_f1","2020tst1111","B");
            facultyfn.enter_grade(c,stmt,"CSts4","Tst_f1","2020tst1111","B-");
            facultyfn.enter_grade(c,stmt,"CSts5","Tst_f2","2020tst1111","C");
            facultyfn.enter_grade(c,stmt,"CSts6","Tst_f2","2020tst1111","C-");
            facultyfn.enter_grade(c,stmt,"CSts7","Tst_f2","2020tst1111","D");
            adminfn.upt_cgpa(c,stmt);

            adminfn.move_courses(c,stmt);


            adminfn.student_graduate(c,stmt,"2020tst1111");
            adminfn.student_graduate(c,stmt,"2020tst11");
            adminfn.student_graduate(c,stmt,"nope");

            facultyfn.deoffer_course(c,stmt,"CSts1");
            facultyfn.deoffer_course(c,stmt,"CSts2");
            facultyfn.deoffer_course(c,stmt,"CSts3");
            facultyfn.deoffer_course(c,stmt,"CSts4");
            facultyfn.deoffer_course(c,stmt,"CSts5");
            facultyfn.deoffer_course(c,stmt,"CSts6");
            facultyfn.deoffer_course(c,stmt,"CSts7");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts2");
            adminfn.remove_course(c,stmt,"CSts3");
            adminfn.remove_course(c,stmt,"CSts4");
            adminfn.remove_course(c,stmt,"CSts5");
            adminfn.remove_course(c,stmt,"CSts6");
            adminfn.remove_course(c,stmt,"CSts7");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_faculty(c,stmt,"Tst_f2");
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
            stmt.execute(dp);

            crs="CSts2";
            tname="\""+crs+"_"+y+"_"+s+"\"";
            dp="drop table "+tname+"";
            stmt.execute(dp);

            crs="CSts3";
            tname="\""+crs+"_"+y+"_"+s+"\"";
            dp="drop table "+tname+"";
            stmt.execute(dp);

            crs="CSts4";
            tname="\""+crs+"_"+y+"_"+s+"\"";
            dp="drop table "+tname+"";
            stmt.execute(dp);

            crs="CSts5";
            tname="\""+crs+"_"+y+"_"+s+"\"";
            dp="drop table "+tname+"";
            stmt.execute(dp);
            crs="CSts6";
            tname="\""+crs+"_"+y+"_"+s+"\"";
            dp="drop table "+tname+"";
            stmt.execute(dp);

            crs="CSts7";
            tname="\""+crs+"_"+y+"_"+s+"\"";
            dp="drop table "+tname+"";
            stmt.execute(dp);



        }catch(SQLException e){System.out.println("Can't connect to db"+e.getMessage());}



    }
    @Test
    void add_student_csv() {
        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        ResultSet rs;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            String pthis="/Users/anubhavkataria/Desktop/Softie/softie_proj/src/main/resources/InputFiles/std_csv.csv";
            System.out.println(pthis);
            adminfn.add_student_csv(c,stmt,pthis);
            adminfn.remove_student(c,stmt,"2020tst1111");
            adminfn.add_student_csv(c,stmt,"Nope");

        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());
        }
    }

    @Test
    void add_faculty_csv() throws IOException {
        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        ResultSet rs;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            String pthis="/Users/anubhavkataria/Desktop/Softie/softie_proj/src/main/resources/InputFiles/flt_csv.csv";
            System.out.println(pthis);
            adminfn.add_faculty_csv(c,stmt,pthis);
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.add_faculty_csv(c,stmt,"Nope");

        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());
        }

    }
    @Test
    void add_course_csv() {
        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        ResultSet rs;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            String pthis="/Users/anubhavkataria/Desktop/Softie/softie_proj/src/main/resources/InputFiles/crs_csv.csv";
            System.out.println(pthis);
            adminfn.add_course_csv(c,stmt,pthis);
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.add_course_csv(c,stmt,"Nope");

        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());
        }

    }

    @Test
    void generate_transcript() {
        admin_functionalities adminfn=new admin_functionalities();
        student_functionalities studentfn= new student_functionalities();
        faculty_functionalities facultyfn = new faculty_functionalities();
        Statement stmt=null;
        String sql=null;
        ResultSet rs1;
        try (Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "anubhavkataria", "1234")) {
            stmt=c.createStatement();
            adminfn.add_student(c,stmt,"2020tst1111","Test","2020tst1111@iitrpr.ac.in","CSE2020","1234",8.0,10);
            adminfn.add_faculty(c,stmt,"Tst_f1","FT1","FT1@iitrpr.ac.in","CSE","1234");
            adminfn.add_course(c,stmt,"CSts1","CrsT1",1,1,1,1,"CSE","PCE","null");
            adminfn.add_course(c,stmt,"CSts2","CrsT1",1,1,1,1,"CSE","PCE","null");
            facultyfn.offer_course(c,stmt,"CSts1","Tst_f1",1.0);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts1");
            facultyfn.enter_grade(c,stmt,"CSts1","Tst_f1","2020tst1111","A");
            adminfn.move_courses(c,stmt);
            facultyfn.offer_course(c,stmt,"CSts2","Tst_f1",1.0);
            studentfn.enroll_course(c,stmt,"2020tst1111","CSts2");
            adminfn.upt_cgpa(c,stmt);
            adminfn.generate_transcript(c,stmt,"2020tst1111");

//            assertEquals("student does not exist",studentfn.get_cg(c,stmt,"2020tst11").toLowerCase());
//            assertEquals("cgpa",studentfn.get_cg(c,stmt,"2020tst1111").toLowerCase());

            facultyfn.deoffer_course(c,stmt,"CSts1");
            adminfn.remove_course(c,stmt,"CSts1");
            adminfn.remove_faculty(c,stmt,"Tst_f1");
            adminfn.remove_student(c,stmt,"2020tst1111");
            String asd="select * from sysinfo ";
            rs1=stmt.executeQuery(asd);
            String y="";
            String s="";
            while (rs1.next()){
                y=rs1.getString(1);
                s=rs1.getString(2);
            }
            String crs="CSts1";
            String tname="\""+crs+"_"+y+"_"+s+"\"";
            String dp="drop table "+tname+"";
            stmt.execute((dp));


        }catch(SQLException e){System.out.println("Can't connect to db "+e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

}