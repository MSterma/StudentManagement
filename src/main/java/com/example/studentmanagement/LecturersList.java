package com.example.studentmanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LecturersList implements Serializable {
    private static List<Lecturer> lecturers;
    static {
        try {
            lecturers = initializelecturers();
            for (Lecturer l:lecturers){
                if(l.getId()>=Lecturer.nextId){
                    Lecturer.nextId=l.getId()+1;
                }
            }
        } catch (IOException e) {
            lecturers =new ArrayList<>();
        } catch (ClassNotFoundException e) {

            lecturers =new ArrayList<>();
        }
    }
    public static List<Lecturer> getLecturers(){
        return lecturers;
    }
    private static List<Lecturer> initializelecturers() throws IOException, ClassNotFoundException {
        ObjectInputStream sts=new ObjectInputStream(new FileInputStream("lecturers.txt"));
        return (List<Lecturer>) sts.readObject();
    }
}
