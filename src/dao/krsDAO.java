package dao;

/**
 *
 * @author pyade 
 */

import config.DBConnection;
import model.KRS; 
import model.Course;
import model.Lecturer;
import model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class krsDAO {
    private Connection connection;

    public krsDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(krsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int create(KRS krs) {
        try {
            String sql = "INSERT INTO krs (code, nim, nidn, semester, score, grade, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, krs.getCourse().getCode()); 
            stmt.setString(2, krs.getStudent().getNim()); 
            
            if (krs.getLecture() != null) {
                stmt.setString(3, krs.getLecture().getNidn());
            } else {
                stmt.setString(3, null);
            }
            
            stmt.setInt(4, krs.getSemester());
            stmt.setDouble(5, krs.getScore());
            stmt.setString(6, krs.getGrade());
            
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.err.println("Gagal Create KRS: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public List<KRS> getAllKRS() {
        List<KRS> listKrs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM krs";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Course dummyCourse = new Course(rs.getString("code"), "", 0, 0); 
                KRS krs = new KRS(dummyCourse, rs.getDouble("score"));
                
                krs.setSemester(rs.getInt("semester"));
                krs.setGrade(rs.getString("grade")); 

                String nim = rs.getString("nim");
                if (nim != null) {
                    Student dummyStudent = new Student("", "", nim, "");
                    dummyStudent.setNim(nim);
                    krs.setStudent(dummyStudent);
                }

                String nidn = rs.getString("nidn");
                if (nidn != null) {
                    Lecturer dummyLecturer = new Lecturer(); 
                    dummyLecturer.setNidn(nidn); 
                    krs.setLecture(dummyLecturer);
                }
                
                listKrs.add(krs);
            }
        } catch (SQLException e) {
            System.err.println("Gagal Read KRS: " + e.getMessage());
            e.printStackTrace();
        }
        return listKrs;
    }


    public int update(KRS krs) {
        try {
            String sql = "UPDATE krs SET score=?, grade=?, semester=?, nidn=?, updatedAt=NOW() WHERE code=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setDouble(1, krs.getScore());
            stmt.setString(2, krs.getGrade());
            stmt.setInt(3, krs.getSemester());
            
            if (krs.getLecture() != null) {
                stmt.setString(4, krs.getLecture().getNidn());
            } else {
                stmt.setString(4, null);
            }
            
            stmt.setString(5, krs.getCourse().getCode()); 
            
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.err.println("Gagal Update KRS: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }


    public int delete(String code) {
        try {
            String sql = "DELETE FROM krs WHERE code=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, code);
            stmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.err.println("Gagal Delete KRS: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

   public List<KRS> searchKRS(String keyword) {
        List<KRS> list = new ArrayList<>();
        try {

            String sql = "SELECT * FROM krs WHERE "+ "nim LIKE ? OR " + "nim IN (SELECT NIM FROM students WHERE name LIKE ?) OR "+ "code LIKE ?";                                             
                       
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%"); 
            stmt.setString(2, "%" + keyword + "%"); 
            stmt.setString(3, "%" + keyword + "%"); 
          
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Course dummyCourse = new Course(rs.getString("code"), "", 0, 0); 
                KRS krs = new KRS(dummyCourse, rs.getDouble("score"));
                
                krs.setSemester(rs.getInt("semester"));
                krs.setGrade(rs.getString("grade")); 

                String nim = rs.getString("nim");
                if (nim != null) {
                    Student dummyStudent = new Student("", "", nim, "");
                    dummyStudent.setNim(nim);
                    krs.setStudent(dummyStudent);
                }

                String nidn = rs.getString("nidn");
                if (nidn != null) {
                    Lecturer dummyLecturer = new Lecturer(); 
                    dummyLecturer.setNidn(nidn); 
                    krs.setLecture(dummyLecturer);
                }
                
                list.add(krs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
} 