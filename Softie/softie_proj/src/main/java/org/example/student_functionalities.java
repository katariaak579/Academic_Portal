package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class student_functionalities{
    public String enroll_course(Connection c, Statement stmt,String entry_num, String code) throws SQLException {
        ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9,rs10,rs11,rs12,rs13,rs14,rs15,rs16;
        String sql = "";
        int check=1;
        String yearis="";
        String semis="";
        String add= "_all_course";
        String add2="_current_course";
        String tname3 = "\""+entry_num+add+"\"";
        String curr_sem_name="\""+entry_num+add2+"\"";
        int prev_creds=0;
        double p_creds=0.0;
        int sem_creds=0;
        double sem_creds_d=0.0;
        int curse_credits=0;
        double cg = 0.0;
        double min_cg=0.0;
        String sqlsys = "select * from sysinfo";
        String sqlagn = "select min_cg from offering where offering.course_id='"+code+"';";

            rs=stmt.executeQuery(sqlsys);
            while (rs.next()) {
                yearis = rs.getString(1);
                semis = rs.getString(2);
            }
        int year = Integer.parseInt(yearis);
        int sem = Integer.parseInt(semis);
        String tablename1= "\""+code+"_"+year+"_"+sem+"\"";
//        sql = "select count(*) from catalog where catalog.course_code='" + code + "';";
        sql = "select count(*) from offering where offering.course_id='" + code + "';";
            rs1 = stmt.executeQuery(sql);
            String curr = "";
            while (rs1.next()) {
                curr = rs1.getString(1);
            }
            if (curr.equals("0")) {
                System.out.println("Course does not exist");
                return "Course does not exist";
            } else {
                String sqpast="select count(*) from "+tname3+" where courses='"+code+"' and grade<>'F';";

                    rs16=stmt.executeQuery(sqpast);
                    while (rs16.next()){
                        curr=rs16.getString(1);
                    }
                    if(!curr.equals("0")){
                        System.out.println("Student has already taken the course in past");
                        return "Student has already taken the course in past";
                    }
                    else{

                        String sqlen = "select count(*) from "+tablename1+" where roll_no='" + entry_num + "';";
                            rs2=stmt.executeQuery(sqlen);
                            while (rs2.next()) {
                                curr = rs2.getString(1);
                            }
                            if (!curr.equals("0")) {
                                System.out.println("Student already Enrolled");
                                return "Student already Enrolled";
                            }
                            else{
                                sql = "select prereq.prereq_id from prereq where prereq.course_id='" + code + "';";
                                System.out.println(sql);
                                    rs3 = stmt.executeQuery(sql);
                                    List<String> sql_list=new ArrayList<>();
                                    List<String> cname=new ArrayList<>();
                                System.out.println(sql);
                                    while (!rs3.isClosed() && rs3.next()) {
                                        curr = rs3.getString(1);
//                                System.out.println(curr);
                                        String sqla = " select count(*) from "+tname3+" where courses='"+curr+"' and grade<>'F' ";
//                                System.out.println(sqla);
                                        sql_list.add(sqla);
                                        cname.add(curr);
                                    }
                                    for(int i=0;i<sql_list.size();i++){
                                        String str=sql_list.get(i);
                                            rs4 = stmt.executeQuery(str);
                                            String curris = "";
                                            while (rs4.next()) {
                                                curris = rs4.getString(1);

                                            }
                                            if (curris.equals("0")) {
//                                                System.out.println(""+cname.get(i)+" Prerequsite not completed");
                                                System.out.println("Prerequsite not completed");
                                                check=0;
                                            }

                                    }

                                if(sem==1) {
                                    int prev_year=year-1;
                                    int prev_sem = 2;
                                    int c_sem1=0;
                                    int c_sem2=0;
                                    String sqlcred = "select courses from " + tname3 + " where year ='"+prev_year+"' and sem ='"+sem+"'; ";

                                        rs5=stmt.executeQuery(sqlcred);
                                        while (!rs5.isClosed() && rs5.next()) {
                                            curr = rs5.getString(1);
                                            String sqlcurse= "select c from course where course.id='"+curr+"';";

                                                rs6=stmt.executeQuery(sqlcurse);
                                                while (!rs6.isClosed() && rs6.next()){
                                                    String curr_new=rs6.getString(1);
                                                    int s= Integer.parseInt(curr_new);
                                                    c_sem1+=s;
                                                }


                                        }

                                    String sqlcred2 = "select courses from " + tname3 + " where year ='"+prev_year+"' and sem ='"+prev_sem+"'; ";

                                        rs5=stmt.executeQuery(sqlcred);
                                        while (!rs5.isClosed() && rs5.next()) {
                                            curr = rs5.getString(1);
                                            String sqlcurse= "select c from course where course.id='"+curr+"';";

                                                rs6=stmt.executeQuery(sqlcurse);
                                                while (!rs6.isClosed() && rs6.next()){
                                                    String curr_new=rs6.getString(1);
                                                    int s= Integer.parseInt(curr_new);
                                                    c_sem2+=s;
                                                }


                                        }

                                    if(c_sem1==0){
                                        c_sem1=6;
                                    }
                                    if(c_sem2==0){
                                        c_sem2=6;
                                    }
                                    prev_creds=c_sem1+c_sem2;
                                }
                                if(sem==2){
                                    int prev_year=year-1;
                                    int prev_sem=1;
                                    int c_sem1=0;
                                    int c_sem2=0;
                                    String sqlcred = "select courses from " + tname3 + " where year ='"+prev_year+"' and sem = '"+sem+"'; ";

                                        rs7=stmt.executeQuery(sqlcred);
                                        while (!rs7.isClosed() && rs7.next()) {
                                            curr = rs7.getString(1);
                                            String sqlcurse= "select c from course where course.id='"+curr+"';";

                                                rs8=stmt.executeQuery(sqlcurse);
                                                while (!rs8.isClosed() && rs8.next()){
                                                    String curr_new=rs8.getString(1);
                                                    int s= Integer.parseInt(curr_new);
                                                    c_sem2+=s;
                                                }


                                        }


                                    String sqlcredi = "select courses from " + tname3 + " where year ='"+year+"' and sem = '"+prev_sem+"'; ";

                                        rs9=stmt.executeQuery(sqlcredi);
                                        while (!rs9.isClosed() && rs9.next()) {
                                            curr = rs9.getString(1);
                                            String sqlcurse= "select c from course where course.id='"+curr+"';";

                                                rs10=stmt.executeQuery(sqlcurse);
                                                while (!rs10.isClosed() && rs10.next()){
                                                    String curr_new=rs10.getString(1);
                                                    int s= Integer.parseInt(curr_new);
                                                    c_sem1+=s;
                                                }


                                        }

                                    if(c_sem1==0){
                                        c_sem1=6;
                                    }
                                    if(c_sem2==0){
                                        c_sem2=6;
                                    }
                                    prev_creds=c_sem1+c_sem2;
                                }
                                p_creds=prev_creds/2;
                                p_creds=1.25*p_creds;

                                String newsql="select courses from "+curr_sem_name+" where remark <> 'DRP'; ";

                                    rs11=stmt.executeQuery(newsql);
                                    while (!rs11.isClosed() && rs11.next()) {
                                        curr = rs11.getString(1);
                                        String sqlcurse= "select c from course where course.id='"+curr+"';";

                                            rs12=stmt.executeQuery(sqlcurse);
                                            while (!rs12.isClosed() && rs12.next()){
                                                String curr_new=rs12.getString(1);
                                                int s= Integer.parseInt(curr_new);
                                                sem_creds+=s;
                                            }


                                    }


                                String sqlcg="select cgpa from student where student.entry_number='"+entry_num+"';";

                                    rs13=stmt.executeQuery(sqlcg);
                                    while (!rs13.isClosed() && rs13.next()) {
                                        curr=rs13.getString(1);
                                        cg=Double.parseDouble(curr);
                                    }


                                String course_creds = "select c from course where course.id='"+code+"';";

                                    rs14=stmt.executeQuery(course_creds);
                                    while (!rs14.isClosed() && rs14.next()) {
                                        curr=rs14.getString(1);
                                        curse_credits= Integer.parseInt(curr);
                                    }

                                String enr = "En";
                                sem_creds_d=sem_creds+curse_credits;
                                if(check==1 && cg>=min_cg && sem_creds_d<=p_creds){

                                    String sqladd = " insert into "+tablename1+" values('"+entry_num+"','"+enr+"');";

                                        stmt.execute(sqladd);
                                        System.out.println("Student Enrolled in course");

                                    String tablename2= "\""+entry_num+"_current_course\"";
                                    String sqlsadd = "insert into "+tablename2+" values ('"+code+"','"+enr+"','"+sem+"','"+year+"');";

                                        stmt.execute((sqlsadd));
                                        System.out.println("Course added in Student table");
                                        return "Student Enrolled in course";

                                }
                                else{
                                    if(check==0){
                                        System.out.println("Prerequisite Not Completed");
                                        return "Prerequsite Not Completed";
                                    }
                                    if(cg<min_cg){
//                                        System.out.println("Cgpa is less than min_cg("+min_cg+")");
                                        System.out.println("Cgpa is less");
                                        return "Cgpa is less";
                                    }
                                    if(sem_creds_d>p_creds){
                                        int x= (int) (sem_creds-p_creds);
//                                        System.out.println("Credits exceeded by "+x);
                                        System.out.println("Credits exceeded");
                                        return "Credits exceeded";
                                    }
                                }
                            }

                    }

            }
        return "";
    }

    public static void drop_course(Connection c, Statement stmt, String entry_num ,String code) throws SQLException {
        ResultSet rs, rs1,rs2;
        String stis="select count(*) from student where entry_number='"+entry_num+"';";
        String sql = "";
        int check=1;
        String yearis="";
        String semis="";
        String add= "_all_course";
        String add2="_current_course";
        String tname3 = "\""+entry_num+add+"\"";
        String curr_sem_name="\""+entry_num+add2+"\"";
        String statusis="";
        String sqlsys = "select * from sysinfo";
        String curr="";

        rs=stmt.executeQuery(sqlsys);
        while (rs.next()) {
            yearis = rs.getString(1);
            semis = rs.getString(2);
            statusis=rs.getString(3);
        }

        int year = Integer.parseInt(yearis);
        int sem = Integer.parseInt(semis);
        String tablename1 = "\"" + code + "_" + year + "_" + sem + "\"";

        rs=stmt.executeQuery(stis);
        while (rs.next()) {
            curr = rs.getString(1);
        }
            if(curr.equals("0")){
                System.out.println("Student Does not exist");
            }
            else{
                String cdis="select count(*) from course where course.id='"+code+"';";
                rs1=stmt.executeQuery(cdis);
                while (rs1.next()) {
                    curr = rs1.getString(1);
                }
                    if(curr.equals("0")){
                        System.out.println("Course not exist");
                    }
                    else{
                        String chk="select count(*) from "+curr_sem_name+" where courses='"+code+"'";
                        rs2=stmt.executeQuery(chk);
                        while (rs2.next()){
                            curr=rs2.getString(1);
                        }
                        if(curr.equals("0")){
                            System.out.println("Student did not take this course");
                        }
                        else{
                            String del="delete from "+tablename1+" where roll_no='"+entry_num+"';";
                            stmt.execute(del);
                            String rem="update "+curr_sem_name+" set remark='DRP' where courses='"+code+"';";
                            stmt.execute(rem);
                            System.out.println("Course Dropped");
                        }
                    }

            }


    }
    public String get_cg(Connection c, Statement stmt, String entry_num) throws SQLException {
        ResultSet rs, rs1;
        String sqlcg = "select cgpa from student where student.entry_number='" + entry_num + "'";
        double cgis = 0.0;
        String curr = "";
        String sql = "";
        sql = "select count(*) from student where student.entry_number='" + entry_num + "';";

            rs = stmt.executeQuery(sql);
            curr = "";
            while (rs.next()) {
                curr = rs.getString(1);
            }
            if (curr.equals("0")) {
                System.out.println("Student does not exist");
                return "Student does not exist";
            }
            String ans=null;
                    rs1 = stmt.executeQuery(sqlcg);
                    while (rs1.next()) {
                        curr = rs1.getString(1);
                        cgis = Double.parseDouble(curr);
                        System.out.println(cgis);
                        ans = String.valueOf(cgis);

                    }
                        return ans;
    }

    public static void view_grades(Connection c, Statement stmt, String entry_num) throws SQLException {
        ResultSet rs, rs1;
        String curr = "";
        String grd="";
        String sql = "";
        String add= "_all_course";
        String tname3 = "\""+entry_num+add+"\"";
        sql = "select count(*) from student where student.entry_number='" + entry_num + "';";
            rs = stmt.executeQuery(sql);
            curr = "";
            while (rs.next()) {
                curr = rs.getString(1);
            }
            if (curr.equals("0")) {
                System.out.println("Student does not exist");
            }
            else {
                String sqlcg=" select courses,grade from "+tname3+";";
                    rs1 = stmt.executeQuery(sqlcg);
                    while (rs1.next()) {
                        curr = rs1.getString(1);
                        grd = rs1.getString(2);
                        System.out.println(curr+": "+grd);
                    }
            }
    }
}