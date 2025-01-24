package com.example.studentmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class LecturerViewController implements Initializable {
    private static Lecturer currentLoggedIn;
    private Stage stage;
    private Scene scene;
    private Parent root;
    List<Lecturer> lecturers=LecturersList.getLecturers();
    List<Student> students=StudentsList.getStudents();
    static {
        try {
            currentLoggedIn = initializeLoggedIn();
        } catch (IOException | ClassNotFoundException e) {
            currentLoggedIn=null;
        }
    }
    public class TableRow{
        public int id;
        public String name;
        public String surname;
        public String grade;
        TableRow(int id,String name, String surname, Float grade){
            this.id=id;
            this.name=name;
            this.surname=surname;
            this.grade=grade>=2.0F?Float.toString(grade):"";

        }

        public String getName() {
            return name;
        }

        public String getGrade() {
            return grade;
        }

        public String getSurname() {
            return surname;
        }

        public int getId() {
            return id;
        }
    }
    private static Lecturer initializeLoggedIn() throws IOException, ClassNotFoundException {
        ObjectInputStream sts=new ObjectInputStream(new FileInputStream("loggedIn.txt"));
        return (Lecturer) sts.readObject();
    }
    //user's data
    @FXML
    private Label lName;
    @FXML
    private Label lSurname;
    @FXML
    private Label lEmail;
    @FXML
    private Label lld;




    //change password fields
    @FXML
    private PasswordField old;
    @FXML
    private PasswordField newPass;
    @FXML
    private ChoiceBox<String> subjectList;

    // Table of students grouped by subject fields
    @FXML
    private ChoiceBox<String> gradesScale;
    @FXML
    private TableView<TableRow>  studentTable;
    @FXML
    private TableColumn<TableRow,Integer> studentId;
    @FXML
    private TableColumn<TableRow,String> studentName;
    @FXML
    private TableColumn<TableRow,String> studentSurname;
    @FXML
    private TableColumn<TableRow,String> studentGrade;
    @FXML
    private Label passFeedback;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lEmail.setText(currentLoggedIn.getEmail());
        lld.setText(Integer.toString(currentLoggedIn.getId()));
        lName.setText(currentLoggedIn.getName());
        lSurname.setText(currentLoggedIn.getSurname());

        List<String>  subs=new ArrayList<>();
        for (Subject s:currentLoggedIn.getSubjects()){
            subs.add(s.getName());
        }
        subjectList.getItems().addAll(subs);
        String[] tab={"2.0","3.0","3.5","4.0","4.5","5.0"};
        gradesScale.getItems().addAll(tab);
        studentId.setCellValueFactory(new PropertyValueFactory<TableRow,Integer>("Id"));
        studentName.setCellValueFactory(new PropertyValueFactory<TableRow,String>("Name"));
        studentSurname.setCellValueFactory(new PropertyValueFactory<TableRow,String>("Surname"));
        studentGrade.setCellValueFactory(new PropertyValueFactory<TableRow,String>("Grade"));
    }
    @FXML
    protected void showTable(){
        if(subjectList.getValue()==null){
            return;
        }
        studentTable.getItems().clear();
        List<TableRow> tableData=new ArrayList<>();
        for(Student s:students){
            for(Map.Entry<Subject, Float> entry : s.getGrades().entrySet()) {
                Subject key = entry.getKey();
                Float value = entry.getValue();
               if(key.getName().equals(subjectList.getValue())){
                   tableData.add(new TableRow(s.getId(),s.getName(),s.getSurname(),value ));
                   break;
               }
            }
        }
        studentTable.getItems().addAll(tableData);



    }
    @FXML
    protected void  setGrade(){
        if(gradesScale.getValue()==null) return;
        if(subjectList.getValue()==null) return;
        int id=studentTable.getSelectionModel().getSelectedIndex();
        var u=studentId.getCellData(id);
        for (Student s:students){
            if(s.getId()==u){
                for(Map.Entry<Subject, Float> entry : s.getGrades().entrySet()) {
                    Subject key = entry.getKey();
                    if(key.getName().equals(subjectList.getValue())){
                        s.getGrades().replace(key, Float.parseFloat( gradesScale.getValue()))
;                        break;
                    }
                }
            }
        }
        showTable();

    }

    @FXML
    protected  void changePassword(){
        if(!old.getText().equals(currentLoggedIn.getPassword())){

            passFeedback.setText("Incorrect old password");
            return;
        }
        if(newPass.getText().isBlank()){
            passFeedback.setText("Password can't be empty");
            return;
        }
        if(old.getText().equals(newPass.getText())){
            passFeedback.setText("Passwords can't be the same");
            return;
        }
        passFeedback.setText("Passwords changed");

        for (Lecturer l:lecturers){
            if(Objects.equals(l.getPassword(), currentLoggedIn.getPassword()) && Objects.equals(l.getEmail(), currentLoggedIn.getEmail())){
                l.setPassword(newPass.getText());
                currentLoggedIn.setPassword(newPass.getText());
                break;
            }
        }

    }

    @FXML
    protected void logOut(ActionEvent event) throws IOException {
        ObjectOutputStream wyj4=new ObjectOutputStream(new FileOutputStream("loggedIn.txt"));
        wyj4.close();
        Parent root =  FXMLLoader.load(StudentManagement.class.getResource("login-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setWidth(300);
        stage.setHeight(500);
        stage.setResizable(false);
        stage.setScene(scene);

    }
}
