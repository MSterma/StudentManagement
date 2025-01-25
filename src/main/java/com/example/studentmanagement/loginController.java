package com.example.studentmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class loginController implements Initializable {


    private List<Student> students=StudentsList.getStudents();
    private List<Lecturer> lecturers=LecturersList.getLecturers();
    private List<Admin>  admins=AdminList.getAdmins();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label loginFeedback;
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordField;
    @FXML
    ChoiceBox<String>  accType;

    private String[] types={"Admin","Student","Lecturer"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accType.getItems().addAll(types);
        accType.setValue("Admin");
    }


    @FXML
    protected void login(ActionEvent event) throws IOException {

        String username=usernameTextField.getText();
        String password=passwordField.getText();
        String type = accType.getValue();
       /*if(true){
            Parent root =  FXMLLoader.load(StudentManagement.class.getResource("admin-view.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setWidth(1000);
            stage.setHeight(500);
            stage.setResizable(true);

            stage.setScene(scene);


        }*/
        if(type.equals("Admin")){
            for(Admin a: admins){
                //a.getEmail().equals(username) && a.getPassword().equals(password)
                if(a.getEmail().equals(username) && a.getPassword().equals(password)){
                    Parent root =  FXMLLoader.load(StudentManagement.class.getResource("admin-view.fxml"));
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
//                    stage.setWidth(1000);
//                    stage.setHeight(600);
                    stage.setResizable(true);

                    stage.setScene(scene);
                    break;

                }
            }

        }else if(type.equals("Student")){
            for(Student a: students){
                //a.getEmail().equals(username) && a.getPassword().equals(password)
                if(a.getEmail().equals(username) && a.getPassword().equals(password)){
                    ObjectOutputStream wyj3=new ObjectOutputStream(new FileOutputStream("loggedIn.txt"));
                    wyj3.writeObject(a);
                    wyj3.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("student-view.fxml"));
                    root=loader.load();
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
//                    stage.setWidth(1000);
//                    stage.setHeight(600);
                    stage.setResizable(true);

                    stage.setScene(scene);
                    break;

                }
            }


        }else if(type.equals("Lecturer")){
            for(Lecturer a: lecturers){
                //a.getEmail().equals(username) && a.getPassword().equals(password)
                if(a.getEmail().equals(username) && a.getPassword().equals(password)){
                    ObjectOutputStream wyj3=new ObjectOutputStream(new FileOutputStream("loggedIn.txt"));
                    wyj3.writeObject(a);
                    wyj3.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("lecturer-view.fxml"));
                    root=loader.load();
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
//                    stage.setWidth(1000);
//                    stage.setHeight(600);
                    stage.setResizable(true);

                    stage.setScene(scene);
                    break;

                }
            }


        }
        loginFeedback.setText("Wrong password or email");



    }
}