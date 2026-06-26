/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.krsDAO;
import java.util.List;
import model.KRS;

/**
 *
 * @author pyade
 */
public class KRSController {

    private final krsDAO krsDAO;

    public KRSController() {
        this.krsDAO = new krsDAO();
    }

    public int create(KRS krs) {
        return krsDAO.create(krs);
    }

    public List<KRS> getAllKRS() {
        return krsDAO.getAllKRS();
    }

    public int update(KRS krs) {
        return krsDAO.update(krs);
    }

    public int delete(String kodeMk) {
        return krsDAO.delete(kodeMk);
    }
}
