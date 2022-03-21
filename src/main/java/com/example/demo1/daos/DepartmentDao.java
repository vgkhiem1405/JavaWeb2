package com.example.demo1.daos;

import com.example.demo1.util.ConnectDB;
import com.example.demo1.models.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {

    public static List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();

        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM departments"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department e = new Department();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setDescription(rs.getString(3));

                list.add(e);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static Department getDepartmentById(int id) {
        Department department = new Department();

        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("SELECT * FROM departments WHERE departments.id = " + String.valueOf(id) + "limit 1");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                department.setId(rs.getInt(1));
                department.setName(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }

    public static int save(Department e) {
        int status = 0;

        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into departments (name,description) values ('" + e.getName() + "', '" + e.getDescription() + ")");

            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static int update(Department e) {
        int status = 0;
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con
                    .prepareStatement(
                            "update departments set name = '" + e.getName() + "' where id = " + String.valueOf(e.getId()));

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
            PreparedStatement ps = con.prepareStatement("delete from departments where id = " + String.valueOf(id));
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
