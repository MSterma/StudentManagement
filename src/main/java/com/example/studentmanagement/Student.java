package com.example.studentmanagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Student extends Person implements Serializable{
    private HashMap<Subject, Float> Grades;
    public Student(String name, String surname, LocalDate birthday, String password, String email){
        super(name,surname,birthday,password, email);
        Grades=new HashMap<>();
    }
    public void addSubject(Subject sub){
        Grades.putIfAbsent(sub,0.0F);
    }
    public void rmSubject(Subject s){
      Grades.remove(s);
    }

    public HashMap<Subject, Float> getGrades() {
        return Grades;
    }

    @Override
    public String toString() {
        return super.toString()+" "+Grades;
    }
}
