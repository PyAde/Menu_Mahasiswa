/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Course;

/**
 *
 * @author pyade
 */
public class KRS {

    private Course course;
    private double score;
    private String grade;
    private Lecturer lecture;
    private int semester;
    private Student student;

    public KRS(Course course, double score) {

        this.course = course;
        this.score = score;
        this.grade = setGrade();
    }

    public Lecturer getLecture() {
        return lecture;
    }

    public Course getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public double getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setLecture(Lecturer lecture) {
        this.lecture = lecture;
    }
    public void setGrade(String grade){
        this.grade = grade;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String setGrade() {

        if (score >= 85) {
            return "A";
        } else if (score >= 75) {
            return "B";
        } else if (score >= 60) {
            return "C";
        } else {
            return "D";
        }
    }

}
