/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Student;
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
public class StudentDAO {

    private Connection connection;

    public StudentDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Membuat object student
    public int create(Student student) {
        try {

            String sql = "INSERT INTO students (NIK, NIM, name, studiProgram, CreatedAt, UpdatedAt) "
                    + "VALUES (?, ?, ?, ?, NOW(), NOW())";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, student.getNIK()); 
            stmt.setString(2, student.getNim());
            stmt.setString(3, student.getName());
            stmt.setString(4, student.getStudyProgram());
            stmt.executeUpdate();
            return 1;

        } catch (SQLException e) {
            System.err.println("Gagal insert database: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

// Membaca data student
    public List<Student> getStudent() {
        List<Student> students = new ArrayList<>();

        try {
            String sql = "SELECT * FROM students";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String cardID = rs.getString("NIK"); 
                String NIM = rs.getString("NIM");
                String name = rs.getString("name");
                String studiProgram = rs.getString("studiProgram");

                Student mhsBaru = new Student(cardID, name, NIM, studiProgram);
                
                mhsBaru.setCreatedAt(rs.getTimestamp("CreatedAt"));
                mhsBaru.setUpdatedAt(rs.getTimestamp("UpdatedAt"));

                students.add(mhsBaru);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

// Update
    public int update(Student student, String nimLama) {
        try {

            String sql = "UPDATE students SET NIK=?, NIM=?, name=?, studiProgram=?, UpdatedAt=NOW() "
                    + "WHERE NIM=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, student.getNIK());
            stmt.setString(2, student.getNim());
            stmt.setString(3, student.getName());
            stmt.setString(4, student.getStudyProgram());
            stmt.setString(5, nimLama); 
            
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

//Delete
    public int delete(String nim) {
        try {
            String sql = "DELETE FROM students WHERE NIM=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nim);
            stmt.executeUpdate();
            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

// Search
    public List<Student> searchStudent(String keyword) {
        List<Student> students = new ArrayList<>();

        try {
            String sql = "SELECT * FROM students WHERE name LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String cardID = rs.getString("NIK");
                String NIM = rs.getString("NIM");
                String name = rs.getString("name");
                String studiProgram = rs.getString("studiProgram");

                Student mhsBaru = new Student(cardID, name, NIM, studiProgram);
                
                mhsBaru.setCreatedAt(rs.getTimestamp("CreatedAt"));
                mhsBaru.setUpdatedAt(rs.getTimestamp("UpdatedAt"));

                students.add(mhsBaru);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

// Mengambil seluruh data
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String cardID = rs.getString("NIK");
                String NIM = rs.getString("NIM");
                String name = rs.getString("name");
                String studiProgram = rs.getString("studiProgram");
                
                Student mhsBaru = new Student(cardID, name, NIM, studiProgram);
                
                mhsBaru.setCreatedAt(rs.getTimestamp("CreatedAt"));
                mhsBaru.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                
                list.add(mhsBaru);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}