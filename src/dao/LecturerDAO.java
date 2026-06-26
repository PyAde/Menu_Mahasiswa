/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Lecturer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import config.DBConnection;

/**
 *
 * @author pyade 
 */
public class LecturerDAO {

    private Connection connection;

    public LecturerDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int create(Lecturer lecturer) {
        try {
            String sql = "INSERT INTO lecturer (name, cardID, nidn, expertise, createdAt, updatedAt) "
                       + "VALUES(? , ? , ? , ?, NOW(), NOW())";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, lecturer.getName());
            stmt.setString(2, lecturer.getIdCard());
            stmt.setString(3, lecturer.getNidn());
            stmt.setString(4, lecturer.getExpertise());
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.err.println("Gagal insert pada database: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public List<Lecturer> getLecturer() {
        List<Lecturer> lecturers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lecturer";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String cardId = rs.getString("cardID");
                String nidn = rs.getString("nidn");
                String expertise = rs.getString("expertise");

                Lecturer dosenBaru = new Lecturer(cardId, name, nidn, expertise);
                
                dosenBaru.setCreatedAt(rs.getTimestamp("createdAt"));
                dosenBaru.setUpdatedAt(rs.getTimestamp("updatedAt"));
                
                lecturers.add(dosenBaru);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecturers;
    }


    public int update(Lecturer lecturer, String nidnLama) {
        try {
            String sql = "UPDATE lecturer SET name=?, cardID=?, nidn=?, expertise=?, updatedAt=NOW() WHERE nidn=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, lecturer.getName());      
            stmt.setString(2, lecturer.getIdCard());     
            stmt.setString(3, lecturer.getNidn());      
            stmt.setString(4, lecturer.getExpertise());  
            stmt.setString(5, nidnLama);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }


    public int delete(String nidn) {
        try {
            String sql = "DELETE FROM lecturer WHERE nidn=?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, nidn);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }


    public List<Lecturer> seacrhLecturer(String keyword) { 
        List<Lecturer> lecturers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM lecturer WHERE name LIKE ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String cardId = rs.getString("cardID");
                String nidn = rs.getString("nidn");
                String expertise = rs.getString("expertise");

                Lecturer dosenBaru = new Lecturer(cardId, name, nidn, expertise);
                
                dosenBaru.setCreatedAt(rs.getTimestamp("createdAt"));
                dosenBaru.setUpdatedAt(rs.getTimestamp("updatedAt"));
                
                lecturers.add(dosenBaru);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecturers;
    }


    public ArrayList<Lecturer> getAllLecturer() {
        ArrayList<Lecturer> list = new ArrayList<>();
        String sql = "SELECT * FROM lecturer";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                String name = rs.getString("name");
                String cardId = rs.getString("cardID");
                String nidn = rs.getString("nidn");
                String expertise = rs.getString("expertise");

                Lecturer dosenBaru = new Lecturer(cardId, name, nidn, expertise);
                
                dosenBaru.setCreatedAt(rs.getTimestamp("createdAt"));
                dosenBaru.setUpdatedAt(rs.getTimestamp("updatedAt"));
                
                list.add(dosenBaru);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {

    }
}