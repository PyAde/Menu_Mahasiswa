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
        if(lecturer.getNidn() == null || lecturer.getName() == null){
            return 0;
        }
        
        lecturer.setName(lecturer.getName().trim());
        lecturer.setNidn(lecturer.getNidn().trim());
        
        return lecturerDAO.create(lecturer);
    }

    public List<Lecturer> getLecturer() {
        return lecturerDAO.getLecturer();
    }

    public int update(Lecturer lecturer, String nidnLama) {
        lecturer.setName(lecturer.getName().trim());
        lecturer.setNidn(lecturer.getNidn().trim());
        
        return lecturerDAO.update(lecturer, nidnLama);
    }

    public int delete(String nidn) {
        return lecturerDAO.delete(nidn);
    }


    public List<Lecturer> searchLecturer(String keyword) {
        String keywordBersih = keyword.trim();   
        return lecturerDAO.seacrhLecturer(keywordBersih);
    }
    
    public ArrayList<Lecturer> getAllData() {
        return lecturerDAO.getAllLecturer();
    }
}
