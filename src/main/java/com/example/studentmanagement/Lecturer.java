package com.example.studentmanagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Lecturer extends Person implements Serializable {
    private List<Subject> subjects;
    public Lecturer(String name, String surname, LocalDate birthday, String password, String email){
        super(name,surname,birthday,password, email);
        subjects=new ArrayList<>();
    }
    public void addSubject(Subject sub){
        subjects.add(sub);
    }
    public void rmSubject(String s){
        subjects.removeIf(subject -> subject.getName().equals(s));//może nie działać
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return super.toString()+subjects.toString();
    }
}
