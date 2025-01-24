package com.example.studentmanagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsList  implements Serializable {
    private  static List<Student> students;

    static {
        try {
            students = initializeStudents();
            for (Student l:students){
                if(l.getId()>=Student.nextId){
                    Student.nextId=l.getId()+1;
                }
            }
        } catch (IOException e) {
           students =new ArrayList<>();
        } catch (ClassNotFoundException e) {

            students =new ArrayList<>();
        }
    }

    public static List<Student> getStudents() {
        return students;
    }
    private static List<Student> initializeStudents() throws IOException, ClassNotFoundException {
        ObjectInputStream sts=new ObjectInputStream(new FileInputStream("students.txt"));
        return (List<Student>) sts.readObject();
    }

}
