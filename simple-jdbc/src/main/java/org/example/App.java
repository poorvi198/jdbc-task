package org.example;

import org.example.dao.EmployeeDao;
import org.example.dao.EmployeeDaoImpl;
import org.example.entity.Employee;
import org.example.jdbc.ConnectionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App
{

    public static void main( String[] args ) throws IOException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        System.out.println("Select--->");
        System.out.println("1. Save Employee");
        System.out.println("2. Get Employee");
        System.out.println("3. Get all Employees");
        System.out.println("4. Delete Employee");
        System.out.println("5. update Employee name");
        System.out.println("6. Exit");

        boolean doMore = true;
        while(doMore)
        {
            System.out.println("Enter choice : ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int c = Integer.parseInt(br.readLine());

            switch (c)
            {
                case 1:
                    System.out.println("enter name : ");
                    String name = br.readLine();
                    System.out.println("enter city : ");
                    String city = br.readLine();
                    System.out.println("enter salary :");
                    double sal = Double.parseDouble(br.readLine());
                    try {
                        int recordUpdated = employeeDao.save(new Employee(name,sal,city));
                        System.out.println(recordUpdated +" records updated");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("enter id : ");
                    int id = Integer.parseInt(br.readLine());
                    try {
                        Employee employee = employeeDao.get(id);
                        if(employee==null)
                            System.out.println("no record found");
                        else {
                            System.out.println(employee);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try
                    {
                        List<Employee> employees= employeeDao.getEmployees();
                        Iterator<Employee> iterator = employees.iterator();
                        if(employees.isEmpty())
                        {
                            System.out.println("No data");
                            break;
                        }
                        while(iterator.hasNext())
                        {
                            Employee employee = iterator.next();
                            System.out.println(employee);
                        }
                        break;
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }

                case 4:
                    System.out.println("enter id : ");
                    id = Integer.parseInt(br.readLine());
                    try {
                        int rowsDeleted = employeeDao.remove(id);
                        System.out.println(rowsDeleted+" row deleted");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("enter name : ");
                    name = br.readLine();
                    System.out.println("enter id : ");
                    id = Integer.parseInt(br.readLine());
                    try {
                        int rowsUpdated = employeeDao.updateName(id,name);
                        System.out.println(rowsUpdated+" row updated");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                        doMore = false;

                    break;
                default:
                    System.out.println("invalid input");
            }
        }
    }
}
