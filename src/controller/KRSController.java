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
        if(krs.getScore() < 0 || krs.getScore() > 100){
            System.err.println("Nilai KRS harus berada di rentang 0 hingga 100");
            return 0; 
        }
        if(krs.getCourse() == null){
            System.err.println("Mata kuliah belum dipilih");
            return 0;
        }
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

   
    public List<KRS> searchKRS(String keyword) {
        return krsDAO.searchKRS(keyword);
    }
} 