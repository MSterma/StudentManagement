package com.example.studentmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;



public class StudentManagement extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent root =  FXMLLoader.load(StudentManagement.class.getResource("login-view.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("StudentMangement");
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(500);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        var students= StudentsList.getStudents();
        var lecturers=LecturersList.getLecturers();
        var admins=AdminList.getAdmins();
        if(admins.isEmpty()){
            admins.add(new Admin("admin","admin",LocalDate.now(),"123","admin"));
        }
      /* for (Student st: students){
            System.out.println("student "+st);
        }
        for (Admin st: admins){
            System.out.println(st);
        }


        for (Lecturer st: lecturers){
            System.out.println("lecturer "+st);
        }
        /*sts.add(new Student("name","surname",LocalDate.now(),"123","321"));
        sts.add(new Student("name1","surname",LocalDate.now(),"123","321"));
        sts.add(new Student("name2","surname",LocalDate.now(),"123","321"));*/
       // admins.add(new Admin("admin","surname",LocalDate.now(),"123","admin"));
        //admins.add(new Admin("admin","surname",LocalDate.now(),"123","admin"));
        launch();
    }

    @Override
    public void stop() throws Exception {
        var sts= StudentsList.getStudents();
        var lrs=LecturersList.getLecturers();
        var ams=AdminList.getAdmins();
        var subs=SubjectList.getSubjects();

        ObjectOutputStream wyj=new ObjectOutputStream(new FileOutputStream("students.txt"));
        wyj.writeObject(sts);
        wyj.close();
        ObjectOutputStream wyj1=new ObjectOutputStream(new FileOutputStream("lecturers.txt"));
        wyj1.writeObject(lrs);
        wyj1.close();
        ObjectOutputStream wyj2=new ObjectOutputStream(new FileOutputStream("admins.txt"));
        wyj2.writeObject(ams);
        wyj2.close();

        ObjectOutputStream wyj3=new ObjectOutputStream(new FileOutputStream("subjects.txt"));
        wyj3.writeObject(subs);
        wyj3.close();
        ObjectOutputStream wyj4=new ObjectOutputStream(new FileOutputStream("loggedIn.txt"));
        wyj4.close();

        super.stop();
    }
}