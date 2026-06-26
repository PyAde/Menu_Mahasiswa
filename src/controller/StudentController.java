/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.StudentDAO;
import java.util.ArrayList;
import java.util.List;
import model.Student;

/**
 *
 * @author pyade
 */
public class StudentController {

    private final StudentDAO studentDAO = new StudentDAO();

    public int create(Student student) {
        return studentDAO.create(student);
    }

    public List<Student> getStudent() {
        return studentDAO.getStudent();
    }
    public int update(Student student, String nimLama) {
        return studentDAO.update(student, nimLama);
    }

    public int delete(String nim) {
        return studentDAO.delete(nim);
    }
    
    public ArrayList<Student> getAllData() {
        return studentDAO.getAllStudents();
    }
}
