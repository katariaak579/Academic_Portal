package org.example;
import com.opencsv.CSVReader;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class admin_functionalities{
    public static void add_course(Connection c, Statement stmt, String code, String name, int l, int t, int p, int creds, String dept, String type,String pre) throws SQLException {
        ResultSet rs,rs1;
        Scanner sc= new Scanner(System.in);
        String sql = "";
        String statusis="";
        String sys="select * from sysinfo;";

        rs1=stmt.executeQuery(sys);
        while (rs1.next()){
            statusis=rs1.getString(3);
        }
        sql = "select count(*) from course where course.id='" + code + "';";
        rs = stmt.executeQuery(sql);
        String curr = "";
        while (rs.next()) {
            curr = rs.getString(1);
        }
        if (!curr.equals("0")) {
            System.out.println("Course already added");
        } else {
            sql = "insert into course values ('" + code + "','" + name + "','" + l + "','" + t + "','" + p + "','" + creds + "','" + dept + "','" + type + "');";
            stmt.execute(sql);
            int x=0;
            if(!pre.equals("null")){
                String[] split=pre.split("-");
                for(String s:split){
                    prereqis(c,stmt,code,s);
                }
            }
            System.out.println("Course successfully added");
        }
    }

    public static void remove_course(Connection c, Statement stmt, String code) throws SQLException {
        ResultSet rs,rs1;
        String sql = "";
        String statusis="";
        String sys="select * from sysinfo;";

        rs1=stmt.executeQuery(sys);
        while (rs1.next()){
            statusis=rs1.getString(3);
        }
        sql = "select count(*) from course where course.id='" + code + "';";
        rs = stmt.executeQuery(sql);
        String curr = "";
        while (rs.next()) {
            curr = rs.getString(1);
        }
        if (curr.equals("0")) {
            System.out.println("Course does not exist");
        } else {
            sql = "delete from course where course.id='" + code + "';";
            String del="delete from prereq where prereq.course_id='"+code+"';";
            String del2="delete from prereq where prereq.prereq_id='"+code+"';";

            stmt.execute(del);
            stmt.execute(del2);
            stmt.execute(sql);
            System.out.println("Course successfully deleted");
        }


    }

    public static void add_student(Connection c, Statement stmt, String entry_num, String name, String email, String batch_id , String login_k, double cgpa, int creds) throws SQLException {
        ResultSet rs,rs1;
        String sql = "";
        String sqlis ="";
        String sqlnew = "";
        String statusis="";
        String sys="select * from sysinfo;";

        rs1=stmt.executeQuery(sys);
        while (rs1.next()){
            statusis=rs1.getString(3);
        }


//        sql = "select count(*) from catalog where catalog.course_code='" + code + "';";
        sql = "select count(*) from student where student.entry_number='" + entry_num + "';";

        rs = stmt.executeQuery(sql);
        String curr = "";
        while (rs.next()) {
            curr = rs.getString(1);
        }
        if (!curr.equals("0")) {
            System.out.println("Student already registered");
        } else {
            sql = "insert into student values ('" + entry_num + "','" + name + "','" + email + "','" + batch_id + "','" + login_k + "','" + cgpa + "','" + creds + "');";
            String studis = entry_num + "_all_course";
            sqlis = "select student_register('" + studis + "');";
            String studnew = entry_num + "_current_course";
            sqlnew = "select student_current('" + studnew + "');";

            stmt.execute(sql);
            stmt.execute(sqlis);
            stmt.execute(sqlnew);
            System.out.println("Student has been registered");

        }

    }

    public static void remove_student(Connection c, Statement stmt, String code) throws SQLException {
        ResultSet rs,rs1,rs2;
        String sql = "";
        String statusis="";
        String sys="select * from sysinfo;";
        String add= "_all_course";
        String add2="_current_course";
        String tname3 = "\""+code+add+"\"";
        String curr_sem_name="\""+code+add2+"\"";

        rs1=stmt.executeQuery(sys);
        while (rs1.next()){
            statusis=rs1.getString(3);
        }


        sql = "select count(*) from student where student.entry_number='" + code + "';";

        rs = stmt.executeQuery(sql);
        String curr = "";
        while (rs.next()) {
            curr = rs.getString(1);
        }
        if (curr.equals("0")) {
            System.out.println("Student does not exist");
        } else {
            sql = "delete from student where student.entry_number='" + code + "';";

            stmt.execute(sql);
            String drop="drop table "+tname3+","+curr_sem_name+";";

            stmt.execute(drop);
            System.out.println("Student successfully removed");
        }

    }

    public static void add_faculty(Connection c, Statement stmt, String code, String name, String email, String dept, String psswd) throws SQLException {
        ResultSet rs,rs1;
        String sql = "";
        String statusis="";
        String sys="select * from sysinfo;";
        rs1=stmt.executeQuery(sys);
        while (rs1.next()){
            statusis=rs1.getString(3);
        }
        sql = "select count(*) from instructor where id='" + code + "';";
        rs = stmt.executeQuery(sql);
        String curr = "";
        while (rs.next()) {
            curr = rs.getString(1);
        }
        if (!curr.equals("0")) {
            System.out.println("Instructor already present");
        } else {
            sql = "insert into instructor values ('" + code + "','" + name + "','" + email + "','" + dept + "','" + psswd + "');";

            stmt.execute(sql);
            System.out.println("Instructor successfully added");
        }

    }
    public static void remove_faculty(Connection c, Statement stmt, String code) throws SQLException {
        ResultSet rs,rs1;
        String sql = "";
        String statusis="";
        String sys="select * from sysinfo;";
        rs1=stmt.executeQuery(sys);
        while (rs1.next()){
            statusis=rs1.getString(3);
        }

        sql = "select count(*) from instructor where instructor.id='" + code + "';";
        rs = stmt.executeQuery(sql);
        String curr = "";
        while (rs.next()) {
            curr = rs.getString(1);
        }
        if (curr.equals("0")) {
            System.out.println("Instructor does not exist");
        } else {
            sql = "delete from instructor where instructor.id='" + code + "';";
            stmt.execute(sql);
            System.out.println("Instructor successfully removed");
//                    sqlcreate = "  "

        }
    }


    public static void prereqis(Connection c, Statement stmt, String code, String codepre) throws SQLException {
        ResultSet rs;
        String sql = "";
        sql = "select count(*) from course where course.id='" + code + "';";
        rs = stmt.executeQuery(sql);
        String curr = "";
        while (rs.next()) {
            curr = rs.getString(1);
        }
        if (curr.equals("0")) {
            System.out.println("Course does not exist");
        } else {

            String[] header = codepre.split("-");
            for(String s:header){
            String sqlis= "select count(*) from course where course.id='" + s + "';";
            rs=stmt.executeQuery(sqlis);
            String curris ="";
            while(rs.next()){
                curris = rs.getString(1);
            }
            if(curris.equals("0")){
                System.out.println("Prerequisite Course does not exist");
            }
            else{
                String sqlch= "select count(*) from prereq where prereq.course_id='" + code + "' and prereq.prereq_id='"+s+"';";
                rs=stmt.executeQuery(sqlch);
                while(rs.next()){
                    curris = rs.getString(1);
                }
                if(!curris.equals("0")){
                    System.out.println("Prerequisite already Added");
                }
                else{
                    String sqladd = "insert into prereq values ('"+code+"','"+s+"');";
                    stmt.execute(sqladd);
                    System.out.println("Prerequisite Added");

                }
                }
            }
        }
    }

    public static void move_courses(Connection c, Statement stmt) throws SQLException {
        ResultSet rs,rs1,rs2,rs3,rs4;
        String sql = "";
        String statusis="";
        String sys="select * from sysinfo;";
            rs2=stmt.executeQuery(sys);
            while (!rs2.isClosed() & rs2.next()){
                statusis=rs2.getString(3);
            }
        String allstd="select entry_number from student;";
            rs3= stmt.executeQuery(allstd);
            List<String> qwerty = new ArrayList<>();
            while (!rs3.isClosed() && rs3.next()){
                qwerty.add(rs3.getString(1));
            }
            for(String s:qwerty) {
                String entry_num = s;
                String add = "_all_course";
                String add2 = "_current_course";
                String tname3 = "\"" + entry_num + add + "\"";
                String curr_sem_name = "\"" + entry_num + add2 + "\"";
                int x = 0;
                sql = "select count(*) from student where student.entry_number='" + entry_num + "';";
                List<String> sql_lst = new ArrayList<>();
                rs = stmt.executeQuery(sql);
                String curr = "";
                while (!rs.isClosed() && rs.next()) {
                    curr = rs.getString(1);
                }
                    String a1 = "";
                    String b1 = "";
                    String c1 = "";
                    String d1 = "";
                    String sqlslt = "select * from " + curr_sem_name + " where remark<>'DRP';";
                    rs1 = stmt.executeQuery(sqlslt);
                    while (!rs1.isClosed() && rs1.next()) {
                        a1 = rs1.getString(1);
                        b1 = rs1.getString(2);
                        c1 = rs1.getString(3);
                        d1 = rs1.getString(4);
                        String addin = "insert into " + tname3 + " values('" + a1 + "','" + b1 + "','" + c1 + "','" + d1 + "');";
                        sql_lst.add(addin);
                    }

                    for (int i = 0; i < sql_lst.size(); i++) {
                        stmt.execute(sql_lst.get(i));
                        x++;
                    }
                    if (x == sql_lst.size()) {
                        System.out.println("All data transferred");
                    }
                    String sqldel = "delete from " + curr_sem_name + ";";
                    stmt.execute(sqldel);
                    System.out.println("All data deleted from current sem");

            }
            String delo="delete from offering;";
            stmt.execute(delo);
            System.out.println("Offer table emptied");
    }


    public static void upt_cgpa(Connection c, Statement stmt) throws SQLException {
        ResultSet rs,rs1,rs2,rs3,rs4,rs5,sys,nms;
        String sql = "";
        int creds_prev=0;
        int creds_sem=0;
        int creds_total=0;
        double cg_prev=0.0;
        double cg_imp=0.0;
        double new_cg=0.0;
        String statusis="";
        String add2="_current_course";

        String abc="select * from sysinfo;";
        sys=stmt.executeQuery(abc);
        while (sys.next()){
            statusis= sys.getString(3);
        }

        List<String> enums=new ArrayList<>();
        String naam="select entry_number from student";
        nms=stmt.executeQuery(naam);
        while (nms.next() && !nms.isClosed()){
            enums.add(nms.getString(1));
        }
        for(String s:enums) {
            String entry_num=s;
            System.out.println(entry_num);
            String tname = "\""+entry_num+add2+"\"";
            sql = "select count(*) from student where student.entry_number='" + entry_num + "';";
            rs = stmt.executeQuery(sql);
            String curr = "";
            while (rs.next()) {
                curr = rs.getString(1);
            }
                String sqlcreds = "select credits_total from student where student.entry_number='" + entry_num + "';";
                rs1 = stmt.executeQuery(sqlcreds);
                while (rs1.next()) {
                    curr = rs1.getString(1);
                    creds_prev = Integer.parseInt(curr);
                }
                String sqlcg = "select cgpa from student where student.entry_number='" + entry_num + "'";
                rs2 = stmt.executeQuery(sqlcg);
                while (rs2.next()) {
                    curr = rs2.getString(1);
                    cg_prev = Double.parseDouble(curr);
                }
                String sql_curr_creds = "select courses from " + tname + ";";
                rs3 = stmt.executeQuery(sql_curr_creds);
                List<String> sql_clist = new ArrayList<>();
                while (rs3.next()) {
                    curr = rs3.getString(1);
                    sql_clist.add(curr);
                }
                for (int i = 0; i < sql_clist.size(); i++) {
                    String get_cred = "select c from course where course.id='" + sql_clist.get(i) + "';";
                    int credis = 0;
                    String grd = "";
                    int gtoc = 0;
                    rs4 = stmt.executeQuery(get_cred);
                    while (rs4.next()) {
                        curr = rs4.getString(1);
                        credis = Integer.parseInt(curr);
                        creds_sem += credis;
                    }
                    String get_grade = "select remark from " + tname + " where courses='" + sql_clist.get(i) + "';";
                    rs5 = stmt.executeQuery(get_grade);
                    while (rs5.next()) {
                        grd = rs5.getString(1);
                    }


                    if (grd.equals("A")) {
                        gtoc = 10;
                    } else if (grd.equals("A-")) {
                        gtoc = 9;
                    } else if (grd.equals("B")) {
                        gtoc = 8;
                    } else if (grd.equals("B-")) {
                        gtoc = 7;
                    } else if (grd.equals("C")) {
                        gtoc = 6;
                    } else if (grd.equals("C-")) {
                        gtoc = 5;
                    } else if (grd.equals("D")) {
                        gtoc = 4;
                    }
                    cg_imp = gtoc * credis;
                }
                creds_total = creds_prev + creds_sem;
                new_cg = cg_prev * creds_prev;
                new_cg += cg_imp;
                new_cg /= creds_total;
                String uptcg = "update student set cgpa=" + new_cg + " where entry_number='" + entry_num + "';";
                stmt.execute(uptcg);
            String uptcreds = "update student set credits_total=" + creds_total + " where entry_number='" + entry_num + "';";
            stmt.execute(uptcreds);
//            System.out.println(new_cg);
                System.out.println("Cg Updated Succesfully");
            }
    }

    public static void student_graduate(Connection c, Statement stmt, String entry_num) throws SQLException {
        ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7;
        String sql = "";
        String add= "_all_course";
        String tname3 = "\""+entry_num+add+"\"";
        List<String> crs_list=new ArrayList<>();
        String curr="";
        int elec_creds=0;
        int core_creds=0;
        int btp=0;
        String dept="";
        String statusis="";
        String sys="select * from sysinfo;";
        rs2=stmt.executeQuery(sys);
        while (rs2.next()){
            statusis=rs2.getString(3);
        }

//        sql = "select count(*) from catalog where catalog.course_code='" + code + "';";
        sql = "select count(*) from student where student.entry_number='" + entry_num + "';";
        rs = stmt.executeQuery(sql);
        curr = "";
        while (rs.next()) {
            curr = rs.getString(1);
        }
        if (curr.equals("0")) {
            System.out.println("Student does not exist");
        } else {
            String d_id = "select dept_id from batch,student where student.entry_number='" + entry_num + "' and batch.id=student.batch_id;";

            rs2 = stmt.executeQuery(d_id);
            while (rs2.next()) {
                dept = rs2.getString(1);
            }
            String pass = "select courses from " + tname3 + " where grade<>'F';";

            rs1 = stmt.executeQuery(pass);
            while (rs1.next()) {
                curr = rs1.getString(1);
                crs_list.add(curr);
            }

            for (int i = 0; i < crs_list.size(); i++) {
                String ecreds = "select sum(c) from course where course.id='" + crs_list.get(i) + "' and course.category='PE';";

                rs3 = stmt.executeQuery(ecreds);
                while (rs3.next()) {
                    curr = rs3.getString(1);
                    System.out.println(curr);
                    int x = 0;
                    if (curr == null) {
                        x = 0;
                    } else {
                        x = Integer.parseInt(curr);
                    }
                    elec_creds += x;
                }

                String ecreds2 = "select sum(c) from course where course.id='" + crs_list.get(i) + "' and course.category='PCE' and course.dept_id<>'" + dept + "';";
                rs4 = stmt.executeQuery(ecreds2);
                while (rs4.next()) {
                    curr = rs4.getString(1);
                    int x = 0;
                    if (curr == null) {
                        x = 0;
                    } else {
                        x = Integer.parseInt(curr);
                    }
                    elec_creds += x;
                }

                String ccreds = "select sum(c) from course where course.id='" + crs_list.get(i) + "' and course.category='PCE' and course.dept_id='" + dept + "'; ";

                rs5 = stmt.executeQuery(ccreds);
                while (rs5.next()) {
                    curr = rs5.getString(1);
                    int x = 0;
                    if (curr == null) {
                        x = 0;
                    } else {
                        x = Integer.parseInt(curr);
                    }
                    core_creds += x;
                }

                String cbtp = "select sum(c) from course where course.id='" + crs_list.get(i) + "' and course.category='BTP' and course.dept_id='" + dept + "'; ";

                rs6 = stmt.executeQuery(cbtp);
                while (rs6.next()) {
                    curr = rs6.getString(1);
                    int x = 0;
                    if (curr == null) {
                        x = 0;
                    } else {
                        x = Integer.parseInt(curr);
                    }
                    btp += x;
                }

            }
            System.out.println("Core: " + core_creds + " Electives: " + elec_creds + " BTP: " + btp);
            if(core_creds>=3 && elec_creds>=3 && btp>=3){
                System.out.println("Student can be Graduated from the College");
                remove_student(c,stmt,entry_num);
            }
            else {
                System.out.println("Student can not Graduate yet");
            }
        }
    }

    public static void add_student_csv(Connection c, Statement stmt,String path) throws SQLException {
        String all="";
        String spl=",";
        try {
            FileReader filereader = new FileReader(path);
            CSVReader csvreader= new CSVReader(filereader);
            String[] nextrecord;
            String rollno,name,email,batch_id,password,cgpa,credits;

            while((nextrecord=csvreader.readNext())!=null)
            {
                rollno = nextrecord[0].toLowerCase();
                name = nextrecord[1];
                email=nextrecord[2];
                batch_id=nextrecord[3];
                password = nextrecord[4];
                cgpa=nextrecord[5];
                credits=nextrecord[6];
                int cre=Integer.parseInt(credits);
                double cg=Double.parseDouble(cgpa);
            add_student(c,stmt,rollno,name,email,batch_id,password,cg,cre);
            }
            System.out.println("Added All");
        }catch (Exception e){
            System.out.println("File not read"+e);
        }
    }

    public static void add_faculty_csv(Connection c, Statement stmt,String path) throws SQLException {
        try {
            FileReader filereader = new FileReader(path);
            CSVReader csvreader= new CSVReader(filereader);
            String[] nextrecord;
            String rollno,name,email,batch_id,password;

            while((nextrecord=csvreader.readNext())!=null)
            {
                rollno = nextrecord[0];
                name = nextrecord[1];
                email=nextrecord[2];
                batch_id=nextrecord[3];
                password = nextrecord[4];
                add_faculty(c,stmt,rollno,name,email,batch_id,password);
            }
            System.out.println("Added All");
        }catch (Exception e){
            System.out.println("File not read"+e);
        }
    }

    public static void add_course_csv(Connection c, Statement stmt,String path) throws SQLException {
        try {
            FileReader filereader = new FileReader(path);
            CSVReader csvreader= new CSVReader(filereader);
            String[] nextrecord;
            String a1,a2,a3,a4,a5,a6,a7,a8,a9;

            while((nextrecord=csvreader.readNext())!=null)
            {
                a1 = nextrecord[0];
                a2 = nextrecord[1];
                a3=nextrecord[2];
                a4=nextrecord[3];
                a5 = nextrecord[4];
                a6=nextrecord[5];
                a7=nextrecord[6];
                a8=nextrecord[7];
                a9=nextrecord[8];
                int l=Integer.parseInt((a3));
                int t=Integer.parseInt((a4));
                int p=Integer.parseInt((a5));
                int ca=Integer.parseInt((a6));

                add_course(c,stmt,a1,a2,l,t,p,ca,a7,a8,a9);

            }
            System.out.println("Added All");
        }catch (Exception e){
            System.out.println("File not read"+e);
        }
    }

    public static void generate_transcript(Connection c, Statement stmt, String s) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        ResultSet rs,rs1,rs2,rs3,rs4;
        String sql = "";
        String statusis="";
        String aadd = "_transcript.txt";
        String filename = "" + s + aadd + "";
        String sys="select * from sysinfo;";
        rs2=stmt.executeQuery(sys);
        while (!rs2.isClosed() & rs2.next()){
            statusis=rs2.getString(3);
        }
            String add= "_all_course";
            String add2="_current_course";
            String tname3 = "\""+s+add+"\"";
            String curr_sem_name="\""+s+add2+"\"";
            String proc="select * from "+tname3+";";
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println("Entry number of the  student: " + s);
            writer.println();
            writer.println(
                    "Courses the student has completed" + "      " + "Grades" + "    " + "Semester" + "    " + "Year");
            rs = stmt.executeQuery(proc);
            writer.println();
            while (rs.next()) {
                String courses = rs.getString(1);
                String grades = rs.getString(2);
                String year = rs.getString(4);
                String sem = rs.getString(3);
                writer.println("             " + courses + "                        " + grades + "           " + sem
                        + "        " + year);
            }
            writer.println();
            writer.println("Courses the student is currently enrolled in");
            writer.println();
            proc = "select * from "+ curr_sem_name +" where remark<>'DRP';";
            rs = stmt.executeQuery(proc);
            while (rs.next()) {
                String courses = rs.getString(1);
                writer.println("                  " + courses);
            }
            writer.println();
            System.out.println("generated");
            String cgisis=" select cgpa from student where entry_number='"+s+"'; ";
            rs2=stmt.executeQuery(cgisis);
            String hee="";
            while (rs2.next()){
                hee=rs2.getString(1);
            }
            double cg=Double.parseDouble(hee);
            writer.println("Current CG is: " + cg);
            String credis="select credits_total from student where entry_number ='"+s+"';";
            rs2=stmt.executeQuery(credis);
            String awd="";
            while (rs2.next()){
                awd=rs2.getString(1);
            }
        writer.println("Total Credits: " + awd);
            writer.close();
        }


}
