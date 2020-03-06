package org.example.dao;

import org.example.entity.Employee;
import org.example.jdbc.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {


    private static Connection con = null;



    public EmployeeDaoImpl() {

    }

    private final String saveEmployeeQuery = "insert into employee_info (employee_name,salary,city) values(?,?,?)";
    private final String getEmployeeQuery = "select * from employee_info where employee_id = ?";
    private final String deleteEmployeeQuery = "delete from employee_info where employee_id = ?";
    private final String updateEmployeeQuery = "update employee_info set employee_name = ? WHERE employee_id = ?";
    private final String getEmployeesQuery = "select * from employee_info";

    @Override
    public int save(Employee e) throws SQLException {
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(saveEmployeeQuery);
            preparedStatement.setString(1, e.getEmployeeName());
            preparedStatement.setDouble(2, e.getSalary());
            preparedStatement.setString(3, e.getCity());
            int rowsSaved = preparedStatement.executeUpdate();
            ConnectionFactory.closeConnection();
            return rowsSaved;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public Employee get(int id) throws SQLException {

        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(getEmployeeQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int empId = resultSet.getInt(1);
                String empName = resultSet.getString(2);
                double sal = resultSet.getDouble(3);
                String city = resultSet.getString(4);
                ConnectionFactory.closeConnection();
                return new Employee(empId, empName, sal, city);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ConnectionFactory.closeConnection();
        return null;

    }


    @Override
    public int remove(int id) throws SQLException {

        try{
            con = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(deleteEmployeeQuery);
            preparedStatement.setInt(1,id);
            int rowsRemoved = preparedStatement.executeUpdate();
            ConnectionFactory.closeConnection();
            return rowsRemoved;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  0;
    }

    @Override
    public int updateName(int id,String updatedName) throws SQLException {

        try{
            con = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(updateEmployeeQuery);
            preparedStatement.setString(1,updatedName);
            preparedStatement.setInt(2,id);
            int rowsUpdated = preparedStatement.executeUpdate();
            ConnectionFactory.closeConnection();
            return rowsUpdated;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        try{
            con = ConnectionFactory.getConnection();
            List<Employee> employees= new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet resultSet= stmt.executeQuery(getEmployeesQuery);
            while(resultSet.next()){
                int empId = resultSet.getInt(1);
                String empName = resultSet.getString(2);
                double sal = resultSet.getDouble(3);
                String city = resultSet.getString(4);
                employees.add(new Employee(empId,empName,sal,city));
            }
            ConnectionFactory.closeConnection();
            return employees;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
