package com.example.demo1.daos;

import com.example.demo1.util.ConnectDB;
import com.example.demo1.models.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PositionDao {
    public static List<Position> getAllPositions() {
        List<Position> list = new ArrayList<>();

        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM positions"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Position e = new Position();
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

    public static Position getPositionById(int id) {
        Position position = new Position();

        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("SELECT * FROM positions WHERE positions.id = " + String.valueOf(id) + "limit 1");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                position.setId(rs.getInt(1));
                position.setName(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }

    public static int save(Position e) {
        int status = 0;

        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into positions (name,description) values ('" + e.getName() + "', '" + e.getDescription() + ")");

            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static int update(Position e) {
        int status = 0;
        try {
            Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con
                    .prepareStatement(
                            "update positions set name = '" + e.getName() + "' where id = " + String.valueOf(e.getId()));

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
            PreparedStatement ps = con.prepareStatement("delete from positions where id = " + String.valueOf(id));
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
