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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class AdminViewController implements Initializable {
    private int AdminId;
    // Program data:
    private String[] accTypes={"Student","Lecturer","Admin"};
    List<Student> students=StudentsList.getStudents();
    List<Lecturer> lecturers=LecturersList.getLecturers();
    List<Admin> admins=AdminList.getAdmins();
    List<Subject> subjects=SubjectList.getSubjects();
    private Stage stage;
    private Scene scene;
    private Parent root;

    //Defining user table
    @FXML
    private TableView<Person> usertable;

    @FXML
    private TableColumn<Person,Integer> tableId;

    @FXML
    private TableColumn<Person,String> tableName;

    @FXML
    private TableColumn<Person,String> tableSurname;

    @FXML
    private TableColumn<Person,String> tableEmail;
    @FXML
    private ChoiceBox<String> tableAccType;

    // add new subjects
    @FXML
    private ChoiceBox<String> subjectList;
    @FXML
    private TextField newSubject;
    @FXML
    private TextField newSubjectVal;
    @FXML
    private  Label newSubjectFeedback;

    //adding user

    @FXML
    private TextField surname;
    @FXML
    private TextField name;

    @FXML
    private PasswordField password;
    @FXML
    private DatePicker bday;
    @FXML
    private TextField email;
    @FXML
    private ChoiceBox<String> newAccType;
    @FXML
    private Label feedback;

    //delete subject
    @FXML
    private ChoiceBox<String> deleteSubjectList;
    @FXML
    private Label dsubFeedback;

    //assign/remove subject from user
    @FXML
    private Label assFeedback;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Subject s:subjects){
            subjectList.getItems().add(s.getName());
            deleteSubjectList.getItems().add(s.getName());
        }
        newAccType.getItems().addAll(accTypes);
        newAccType.setValue("Student");
        tableAccType.getItems().addAll(accTypes);
        tableAccType.setOnAction(this::showTable);
        tableId.setCellValueFactory(new PropertyValueFactory<Person,Integer>("Id"));
        tableName.setCellValueFactory(new PropertyValueFactory<Person,String>("Name"));
        tableSurname.setCellValueFactory(new PropertyValueFactory<Person,String>("Surname"));
        tableEmail.setCellValueFactory(new PropertyValueFactory<Person,String>("Email"));


    }
    @FXML
    protected void addUser()  {
      /*  for (Student st: students){
            System.out.println("admin "+st);
        }
        for (Admin st: admins){
            System.out.println("admin "+st);
        }
        for (Lecturer st: lecturers){
            System.out.println("admin "+st);
        }*/
        if(surname.getText().equals("")|| name.getText().equals("")||password.getText().equals("")|| email.getText().equals("")||bday.getValue()==null){
            feedback.setText("Fields can't be empty");
            return;
        }
        if(newAccType.getValue().equals("Student")){
            for(Student a:students){
                if(a.getEmail().equals(email.getText()) && a.getPassword().equals(password.getText())){
                    feedback.setText("There already is user with such credentials");
                    return;
                }
            }
            students.add(new Student(name.getText(),surname.getText(),bday.getValue(),password.getText(),email.getText()));
            name.clear();
            surname.clear();
            password.clear();
            email.clear();
            showTable();
            feedback.setText("user added");
            return;
        }
        if(newAccType.getValue().equals("Admin")){
            for(Admin a:admins){
                if(a.getEmail().equals(email.getText()) && a.getPassword().equals(password.getText())){
                    feedback.setText("There already is user with such credentials");

                    return;
                }
            }
            admins.add(new Admin(name.getText(),surname.getText(),bday.getValue(),password.getText(),email.getText()));
            feedback.setText("user added");
            name.clear();
            surname.clear();
            password.clear();
            email.clear();
            showTable();
            return;
        }
        if(newAccType.getValue().equals("Lecturer")){
            for(Lecturer a:lecturers){
                if(a.getEmail().equals(email.getText()) && a.getPassword().equals(password.getText())){
                    feedback.setText("There already is user with such credentials");
                    return;
                }
            }
            lecturers.add(new Lecturer(name.getText(),surname.getText(),bday.getValue(),password.getText(),email.getText()));
            feedback.setText("user added");
            name.clear();
            surname.clear();
            password.clear();
            email.clear();
            showTable();
        }

    }
    @FXML
    protected void showTable(ActionEvent event){

        if(tableAccType.getValue().equals("Student")){
            usertable.getItems().removeAll(lecturers);
            usertable.getItems().removeAll(admins);
            usertable.getItems().removeAll(students);
            usertable.getItems().addAll(students);

        }
        if(tableAccType.getValue().equals("Lecturer")){
            usertable.getItems().removeAll(students);
            usertable.getItems().removeAll(admins);
            usertable.getItems().removeAll(lecturers);
            usertable.getItems().addAll(lecturers);
        }
        if(tableAccType.getValue().equals("Admin")){
            usertable.getItems().removeAll(lecturers);
            usertable.getItems().removeAll(students);
            usertable.getItems().removeAll(admins);
            usertable.getItems().addAll(admins);
        }


    }
    @FXML
    protected void showTable(){

        if(tableAccType.getValue()==null){
            return;
        }
        if(tableAccType.getValue().equals("Student")){
            usertable.getItems().removeAll(lecturers);
            usertable.getItems().removeAll(admins);
            usertable.getItems().removeAll(students);
            usertable.getItems().addAll(students);

        }
        if(tableAccType.getValue().equals("Lecturer")){
            usertable.getItems().removeAll(students);
            usertable.getItems().removeAll(admins);
            usertable.getItems().removeAll(lecturers);
            usertable.getItems().addAll(lecturers);
        }
        if(tableAccType.getValue().equals("Admin")){
            usertable.getItems().removeAll(lecturers);
            usertable.getItems().removeAll(students);
            usertable.getItems().removeAll(admins);
            usertable.getItems().addAll(admins);
        }


    }
    @FXML
    protected void  deleteUser(){
        int id=usertable.getSelectionModel().getSelectedIndex();
        var u=tableId.getCellData(id);
        if(tableAccType.getValue().equals("Student")){
            students.removeIf(s -> s.getId() == u);
        }
        if(tableAccType.getValue().equals("Lecturer")){
            lecturers.removeIf(s -> s.getId() == u);
        }
        if(tableAccType.getValue().equals("Admin")){
            admins.removeIf(s -> s.getId() == u);
        }

       usertable.getItems().remove(id);

    }
    @FXML
    protected void addSubject(){
        float val;
        try {
            val=Float.parseFloat(newSubjectVal.getText());
            if(newSubject.getText().isBlank() || val==0){
                newSubjectFeedback.setText("Fields can't be empty");
                return;

            }
            for (Subject s: subjects){
                if(s.getName().equals(newSubject.getText())){
                    newSubjectFeedback.setText("There already is such subject");
                    return;
                }
            }
            subjects.add(new Subject(newSubject.getText(),val));
            subjectList.getItems().add(newSubject.getText());
            deleteSubjectList.getItems().add(newSubject.getText());
            newSubjectFeedback.setText("Subject added");
            newSubject.clear();
            newSubjectVal.clear();
        }catch (Exception e){
            newSubjectFeedback.setText("Value field needs to be number");

        }



    }
    @FXML
    protected void deleteSubject(){
        var name=deleteSubjectList.getValue();
        if(name.isBlank()){
            dsubFeedback.setText("Please choose subject");
        }else{
            for (Student s:students){
                for(Map.Entry<Subject, Float> entry : s.getGrades().entrySet()) {
                    String key = entry.getKey().getName();
                    if(key.equals(name)){
                        s.getGrades().remove(entry.getKey());
                    }

                }
            }
            for (Lecturer s:lecturers){
                s.getSubjects().removeIf(sub -> sub.getName().equals(name));
            }
            subjects.removeIf(s -> s.getName().equals(name));
            subjectList.getItems().clear();
            deleteSubjectList.getItems().clear();
            for (Subject s:subjects){
                subjectList.getItems().add(s.getName());
                deleteSubjectList.getItems().add(s.getName());
            }
            dsubFeedback.setText("Subject deleted");


        }



    }
    @FXML
    protected void assignSubject(){
        int id=usertable.getSelectionModel().getSelectedIndex();
        var u=tableId.getCellData(id);
        if(tableAccType.getValue()==null){
            return;
        }
        if(tableAccType.getValue().equals("Student")){
            for (Student s:students){
                if(s.getId()==u){
                    Subject sub=new Subject("null",0);
                    var name=subjectList.getValue();
                    for (Subject d:subjects){
                        if(d.getName().equals(name)){
                            sub=d;
                        }
                    }
                    s.addSubject(sub);
                }
            }

        }

        if(tableAccType.getValue().equals("Lecturer")){
            for (Lecturer s:lecturers){
                if(s.getId()==u){
                    Subject sub=new Subject("null",0);
                    var name=subjectList.getValue();
                    for (Subject d:subjects){
                        if(d.getName().equals(name)){
                            sub=d;
                            break;
                        }
                    }
                    if(!s.getSubjects().contains(sub)){
                        s.addSubject(sub);
                    }

                }
            }

        }
        if(tableAccType.getValue().equals("Admin")){
            assFeedback.setText("Can't assign subject to admin ");
        }
        assFeedback.setText("Subject added");

    }
    @FXML
    protected void removeSubject(){
        int id=usertable.getSelectionModel().getSelectedIndex();
        var u=tableId.getCellData(id);
        if(tableAccType.getValue()==null){
            return;
        }
        if(tableAccType.getValue().equals("Student")){
            for (Student s:students){
                if(s.getId()==u){
                    Subject sub=new Subject("null",0);
                    var name=subjectList.getValue();
                    for (Subject d:subjects){
                        if(d.getName().equals(name)){
                            sub=d;
                        }
                    }
                    s.rmSubject(sub);
                    break;
                }
            }

        }

        if(tableAccType.getValue().equals("Lecturer")){
            for (Lecturer s:lecturers){
                if(s.getId()==u){
                    var name=subjectList.getValue();
                    s.rmSubject(name);
                    break;
                }
            }

        }
        if(tableAccType.getValue().equals("Admin")){
            assFeedback.setText("Can't remove subject from admin ");
        }
        assFeedback.setText("Subject removed from user ");



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
