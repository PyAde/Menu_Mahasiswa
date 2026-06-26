/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LecturerDAO;
import java.util.List;
import model.Lecturer;
import java.util.ArrayList;

/**
 *
 * @author pyade
 */
public class LecturerController {
    private LecturerDAO lecturerDAO =new LecturerDAO();
    
    public int create(Lecturer lecturer){
        return lecturerDAO.create(lecturer);
    }

    public List<Lecturer> getLecturer() {
        return lecturerDAO.getLecturer();
    }

    public int update(Lecturer lecturer, String nidnLama) {
        return lecturerDAO.update(lecturer, nidnLama);
    }

    public int delete(String nidn) {
        return lecturerDAO.delete(nidn);
    }

    public ArrayList<Lecturer> getAllData() {
        return lecturerDAO.getAllLecturer();
    }
}
