/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Course;
import config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author pyade
 */
public class CourseDAO {

    private Connection connection;

    public CourseDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public int create(Course course) {
        try {

            String sql = "INSERT INTO courses (code, name, sks, semester, createdAt, updatedAt) VALUES (?, ?, ?, ?, NOW(), NOW())";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, course.getCode()); 
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getSKS());
            stmt.setInt(4, course.getSemester()); 
            
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.err.println("Gagal insert pada database: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }


    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM courses";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                String code = rs.getString("code");
                String name = rs.getString("name");
                int sks = rs.getInt("sks");
                int semester = rs.getInt("semester");
                Course crsNew = new Course(code, name, sks, semester);
                
                crsNew.setCreatedAt(rs.getString("createdAt"));
                crsNew.setUpdatedAt(rs.getString("updatedAt"));
                
                courses.add(crsNew);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }


    public int update(Course course, String codeMataKuliahLama) {
        try {

            String sql = "UPDATE courses SET code=?, name=?, sks=?, semester=?, updatedAt=NOW() WHERE code=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, course.getCode());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getSKS());
            stmt.setInt(4, course.getSemester());
            stmt.setString(5, codeMataKuliahLama);
            
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    // 4. Delete Course
    public int delete(String courseCode) {
        try {
   
            String sql = "DELETE FROM courses WHERE code=?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, courseCode);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    // 5. Search Course
    public List<Course> searchCourse(String keyword) {
        List<Course> courses = new ArrayList<>();
        try {

            String sql = "SELECT * FROM courses WHERE name LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                int sks = rs.getInt("sks");
                int semester = rs.getInt("semester");

                courses.add(new Course(code, name, sks, semester));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    

    public List<Course> getCourseByExpertise(String expertise) {
        List<Course> matchedCourses = new ArrayList<>();
        try {

            String sql = "SELECT * FROM courses WHERE name LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + expertise + "%");
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                int sks = rs.getInt("sks");
                int semester = rs.getInt("semester");

                matchedCourses.add(new Course(code, name, sks, semester));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchedCourses;
    }



}