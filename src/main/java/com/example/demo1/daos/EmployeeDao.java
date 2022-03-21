package com.example.demo1.daos;

import com.example.demo1.util.ConnectDB;
import com.example.demo1.models.Employee;

import java.sql.*;
import java.util.*;

public class EmployeeDao {

    public static List<Employee> getAllEmployees(Integer page) {
        if (page == null) page = 1;
        Integer per = 5;

        List<Employee> list = new ArrayList<>();

        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT emp.id, emp.name, emp.age, emp.image, emp.salary, dep.id, dep.name, pos.id, pos.name\n"
                            + "FROM employees as emp "
                            + "	left join departments as dep on emp.department_id = dep.id"
                            + "	left join positions as pos on emp.position_id = pos.id "
                            + "ORDER BY emp.id DESC LIMIT ? OFFSET ?"
            );

            ps.setObject(1, per);
            ps.setObject(2, per * (page - 1));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setAge(rs.getInt(3));
                e.setImage(rs.getString(4));
                e.setSalary(rs.getInt(5));
                e.setDepartmentId(rs.getInt(6));
                e.setDepartmentName(rs.getString(7));
                e.setPositionId(rs.getInt(8));
                e.setPositionName(rs.getString(9));
                list.add(e);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Employee> getAllEmployeesWithSuper() {
        List<Employee> list = new ArrayList<>();

        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT emp.id, super.id, super.name "
                            + "FROM employees as emp left join employees as super on emp.employee_id = super.id"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt(1));
                e.setEmployeeId(rs.getInt(2));
                e.setEmployeeName(rs.getString(3));

                list.add(e);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static Employee getEmployeeById(int id) {
        Employee employee = new Employee();

        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("SELECT * FROM employees WHERE employees.id = " + String.valueOf(id) + "limit 1");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setAge(rs.getInt(3));
                employee.setImage(rs.getString(4));
                employee.setSalary(rs.getInt(5));
                employee.setEmployeeId(rs.getInt(6));
                employee.setDepartmentId(rs.getInt(7));
                employee.setPositionId(rs.getInt(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static int save(Employee e) {
        int status = 0;

        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into employees "
                    + "(name, age, image, salary, employee_id, department_id, position_id) "
                    + "values (?,?,?,?,?,?,?)"
            );

            ps.setString(1, e.getName());
            ps.setObject(2, e.getAge());
            ps.setString(3, e.getImage());
            ps.setObject(4, e.getSalary());
            ps.setObject(5, e.getEmployeeId());
            ps.setObject(6, e.getDepartmentId());
            ps.setObject(7, e.getPositionId());

            System.out.println(ps);
            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return status;
    }

    public static int update(Employee e) {
        int status = 0;
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con
                    .prepareStatement(
                            "update employees set "
                                    + "name = ?, "
                                    + "age = ?, "
                                    + "image = ?, "
                                    + "salary = ?, "
                                    + "employee_id = ?, "
                                    + "department_id = ?, "
                                    + "position_id = ? "
                                    + "where id = " + String.valueOf(e.getId()));

            ps.setString(1, e.getName());
            ps.setObject(2, e.getAge());
            ps.setString(3, e.getImage());
            ps.setObject(4, e.getSalary());
            ps.setObject(5, e.getEmployeeId());
            ps.setObject(6, e.getDepartmentId());
            ps.setObject(7, e.getPositionId());

            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static int delete(int id) {
        int status = 0;
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from employees where id = " + String.valueOf(id));
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static void main(String[] args) {
        List<Employee> list = getAllEmployeesWithSuper();

//    Employee employee = getEmployeeById(2);
//    employee.setName("alo123");
        System.out.println(list);
    }
}
