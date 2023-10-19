package org.example;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class faculty_functionalities{

    public static void offer_course(Connection c, Statement stmt, String code, String fid, double min_cg) throws SQLException {
        ResultSet rs;
        String sql = "";
        String sqlis = "";
        String yearis="";
        String semis="";
        String sqlsys = "select * from sysinfo";
        String statusis = "";
            rs=stmt.executeQuery(sqlsys);
            while (rs.next()) {
                yearis = rs.getString(1);
                semis = rs.getString(2);
                statusis=rs.getString(3);
            }
            int year = Integer.parseInt(yearis);
            int sem = Integer.parseInt(semis);
        sql = "select count(*) from course where course.id='" + code + "';";
            rs = stmt.executeQuery(sql);
            String curr = "";
            while (rs.next()) {
                curr = rs.getString(1);
            }
            if (curr.equals("0")) {
                System.out.println("Course does not exist");
            } else {
                sql = "select count(*) from offering where offering.course_id='" + code + "' and offering.sem='"+sem+"' and offering.year='"+year+"';";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        curr = rs.getString(1);
                    }
                    if (!curr.equals("0")) {
                        System.out.println("Course already offered");
                    } else {
                            sql = "insert into offering values ('" + code + "','" + fid + "','" + sem + "','" + year + "'," + min_cg + ");";
//                            System.out.println(sql);
//                            sql_add = "select offer_course_table()";
                            stmt.execute(sql);
//                            stmt.execute(sql_add);
                            String tablename = code+"_"+year+"_"+sem;
                            sqlis = "select offer_course_table('"+tablename+"');";
                            stmt.execute(sqlis);
                            System.out.println("Course offered successfully");
                    }
            }
    }

    public static void enter_grade(Connection c, Statement stmt, String code, String fid, String entry_num,String grade) throws SQLException {
        ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6;
        String yearis="";
        String semis="";
        String sqlsys = "select * from sysinfo";
        String curr="";
        String add2="_current_course";
        String curr_sem_name="\""+entry_num+add2+"\"";

            rs=stmt.executeQuery(sqlsys);
            while (rs.next()) {
                yearis = rs.getString(1);
                semis = rs.getString(2);
            }

        String tablename = "\""+code+ "_" +yearis+ "_" +semis+"\"";
        int year = Integer.parseInt(yearis);
        int sem = Integer.parseInt(semis);
        String sqlcode="select count(*) from offering where course_id='"+code+"' and fid='"+fid+"' and sem='"+sem+"' and year='"+year+"';";

            rs1=stmt.executeQuery(sqlcode);
            while (rs1.next()){
                curr=rs1.getString(1);
            }
            if(curr.equals("0")){
                System.out.println("No such course exist for grading");
            }
            else{
                String sqlstd="select count(*) from "+curr_sem_name+" where courses='"+code+"' and remark<>'DRP';";
                    rs2=stmt.executeQuery(sqlstd);
                    while (rs2.next()){
                        curr=rs2.getString(1);
                    }
                    if(curr.equals("0")){
                        System.out.println("No such student in the course to grade");
                    }
                    else{
                        String sqlgrd="update "+curr_sem_name+" set remark='"+grade+"' where courses='"+code+"';";
                            stmt.execute(sqlgrd);
                            String crs_chg="update "+tablename+" set grade='"+grade+"' where roll_no='"+entry_num+"';";
                                stmt.execute(crs_chg);

                            System.out.println("Grade updated succesfully");


                    }

            }


    }

    public static void enter_grade_csv(Connection c, Statement stmt, String path) throws SQLException {
        try {
            FileReader filereader = new FileReader(path);
            CSVReader csvreader= new CSVReader(filereader);
            String[] nextrecord;
            String rollno,name,email,batch_id;

            while((nextrecord=csvreader.readNext())!=null)
            {
                rollno = nextrecord[0].toLowerCase();
                name = nextrecord[1];
                email=nextrecord[2];
                batch_id=nextrecord[3];

                enter_grade(c,stmt,rollno,name,email,batch_id);
            }
            System.out.println("Added All");
        }catch (Exception e){
            System.out.println("File not read"+e);
        }

    }

    public static void deoffer_course(Connection c, Statement stmt, String code) throws SQLException {
        ResultSet rs, rs1, rs2, rs3;
        String sql = "";
        String yearis = "";
        String semis = "";
        String sqlsys = "select * from sysinfo";
            rs = stmt.executeQuery(sqlsys);
            String currsys = "";
            while (rs.next()) {
                yearis = rs.getString(1);
                semis = rs.getString(2);
            }

        int year = Integer.parseInt(yearis);
        int sem = Integer.parseInt(semis);
        int rows = 0;
//        sql = "select count(*) from catalog where catalog.course_code='" + code + "';";
        sql = "select count(*) from offering where offering.course_id='" + code + "';";
            rs1 = stmt.executeQuery(sql);
            String curr = "";
            while (rs1.next()) {
                curr = rs1.getString(1);
            }
            if (curr.equals("0")) {
                System.out.println("Course does not exist");
            } else {
                String tname = "\""+ code +"_"+ year +"_"+ sem +"\"";
                String sqldef = "select roll_no from " + tname + ";";
                    rs3 = stmt.executeQuery(sqldef);
                    stmt.setFetchSize(rows);
                    List<String> sqls = new ArrayList<>();
                    while (rs3.next()) {
                        curr = rs3.getString(1);
                        String tname1 = "\"" + curr + "_current_course\"";
                        String sqlstd = "update " + tname1 + " set remark='DRP' where courses='" + code + "';";
                        sqls.add(sqlstd);
                    }

                    for (String s : sqls) {
                            stmt.execute(s);
                            System.out.println("Course Successfully deoffered from Student");

                    }
                    String dropis = "drop table " + tname + ";";
                System.out.println(dropis);

                        stmt.execute(dropis);
                        System.out.println("Table deleted successfully");

                    String rmv = "delete from offering where course_id='" + code + "';";

                        stmt.execute(rmv);
                        System.out.println("Course deleted from offering");
//                        return "Course deleted from offering";

            }
    }


}
