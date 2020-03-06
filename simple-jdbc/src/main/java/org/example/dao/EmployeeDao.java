package org.example.dao;

import org.example.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

    int save(Employee e) throws SQLException;

    Employee get(int id) throws SQLException;

    int remove(int id) throws SQLException;

    int updateName(int id,String updatedName) throws SQLException;

    List<Employee> getEmployees() throws SQLException;


}
