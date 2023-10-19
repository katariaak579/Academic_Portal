package org.example;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public  static void main(String[] args) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        Statement stmt=null;
        ResultSet ars,ars1;
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "anubhavkataria", "1234");
            stmt= c.createStatement();
            Scanner sc= new Scanner(System.in);
            student_functionalities studentis= new student_functionalities();
            faculty_functionalities facultyis = new faculty_functionalities();
            admin_functionalities adminis = new admin_functionalities();

            student_ui stdui=new student_ui();
            faculty_ui fltui=new faculty_ui();
            admin_ui admui=new admin_ui();
            String curr="";


            while (true){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Welcome to AIMS Home Page");
                String chstd="";
                String chflt="";
                System.out.print("Enter Username: ");
                String uname=sc.nextLine();
                System.out.print("Enter Password: ");
                String psswd=sc.nextLine();
                String asds="select count(*) from student where entry_number='"+uname+"';";
                ars1=stmt.executeQuery(asds);
                while (ars1.next()){
                    chstd=ars1.getString(1);
                }
                String asdf="select count(*) from instructor where id='"+uname+"';";
                ars1=stmt.executeQuery(asdf);
                while (ars1.next()){
                    chflt=ars1.getString(1);
                }
                System.out.println(uname);
                if(uname.equals("admin")){
                    String chdadm="select password from admin";
                    ars=stmt.executeQuery(chdadm);
                    while (ars.next()){
                        curr=ars.getString(1);
                    }
                    if(curr.equals(psswd)){
                        admui.test(c,stmt);
                    }
                    else{
                        System.out.println("Wrong Password for Admin");
                        System.out.println("Press any key to continue");
                        String x=sc.nextLine();
                    }
                }
                else if (!chstd.equals("0")){
                    String chdadm="select login_key from student where entry_number='"+uname+"';";
                    ars=stmt.executeQuery(chdadm);
                    while (ars.next()){
                        curr=ars.getString(1);
                    }
                    if(curr.equals(psswd)){
                        stdui.test(c,stmt,uname);
                    }
                    else{
                        System.out.println("Wrong Password for "+uname);
                        System.out.println("Press any key to continue");
                        String x=sc.nextLine();
                    }
                } else if (!chflt.equals("0")) {
                    String chdadm="select login_key from instructor where id='"+uname+"';";
                    ars=stmt.executeQuery(chdadm);
                    while (ars.next()){
                        curr=ars.getString(1);
                    }
                    if(curr.equals(psswd)){
                        fltui.test(c,stmt,uname);
                    }
                    else{
                        System.out.println("Wrong Password for "+uname);
                        System.out.println("Press any key to continue");
                        String x=sc.nextLine();
                    }

                }
                else{
                    System.out.println("Username not found");
                    System.out.println("Press any key to continue");
                    String x=sc.nextLine();
                }

            }


    }
    }

