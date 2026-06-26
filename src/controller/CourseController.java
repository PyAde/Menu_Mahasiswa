/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CourseDAO;
import java.util.List;
import model.Course;

/**
 *
 * @author pyade
 */
public class CourseController {

    private CourseDAO courseDAO;

    public CourseController() {
        this.courseDAO = new CourseDAO();
    }

    public int create(Course course) {
        return courseDAO.create(course);
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    public int update(Course course, String codeLama) {
        return courseDAO.update(course, codeLama);
    }

    public int deleteCourse(String code) {
        return courseDAO.delete(code);
    }

    public List<Course> searchCourse(String keyword) {
        return courseDAO.searchCourse(keyword);
    }

    public List<Course> getCourseByExpertise(String expertise) {
        return courseDAO.getCourseByExpertise(expertise);
    }
}
