package com.example.studentmanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class SubjectList {
    private static List<Subject> subjects;
    static {
        try {
            subjects = initializeSubjects();
        } catch (IOException e) {
            subjects =new ArrayList<>();
        } catch (ClassNotFoundException e) {

            subjects =new ArrayList<>();
        }
    }
    public static List<Subject> getSubjects(){
        return subjects;
    }
    private static List<Subject> initializeSubjects() throws IOException, ClassNotFoundException {
        ObjectInputStream sts=new ObjectInputStream(new FileInputStream("subjects.txt"));
        return (List<Subject>) sts.readObject();
    }
}
