/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.sql.Timestamp;

/**
 *
 * @author pyade
 */
public class Student extends Person {

    private String nim;
    private String studyProgram;
    private ArrayList<KRS> krsList;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Student(String idCard, String name,
            String nim, String studyProgram) {

        super(idCard, name);

        this.nim = nim;
        this.studyProgram = studyProgram;
        krsList = new ArrayList<>();
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getNim() {
        return nim;
    }

    public String getNIK() {
        return getIdCard();
    }

    public String getName() {
        return super.getName();
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void addKRS(KRS krs) {
        krsList.add(krs);
    }

}
