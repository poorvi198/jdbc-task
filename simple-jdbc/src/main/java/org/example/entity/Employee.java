package org.example.entity;

public class Employee {

    private int employeeId;

    private String employeeName;

    private double salary;

    private String city;

    public Employee(int employeeId, String employeeName, double salary, String city) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.city = city;
    }


    public Employee(String employeeName, double salary, String city) {
        this.employeeName = employeeName;
        this.salary = salary;
        this.city = city;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", city='" + city + '\'' +
                '}';
    }
}
