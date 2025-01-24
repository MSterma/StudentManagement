package com.example.studentmanagement;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class StudentViewConroller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public class TableData{
        public String name;
        public Float value;
        public String grade;
        TableData(String name, Float value, Float grade){
         this.name=name;
         this.grade=grade>=2.0F?Float.toString(grade):"";
         this.value=value;
        }

        public String getName() {
            return name;
        }

        public String getGrade() {
            return grade;
        }

        public Float getValue() {
            return value;
        }
    }
    private static Student currentLoggedIn;
    static {
        try {
            currentLoggedIn = initializeLoggedIn();
        } catch (IOException | ClassNotFoundException e) {
            currentLoggedIn=null;
        }
    }

    @FXML
    private Label stName;

    @FXML
    private Label stSurname;

    @FXML
    private Label stEmail;
    @FXML
    private Label stId;
    @FXML
    private Label stAvg;

    @FXML
    private TableView<TableData> gradesTable;

    @FXML
    private TableColumn<TableData,String> subjectName;
    @FXML
    private TableColumn<TableData,Float> value;
    @FXML
    private TableColumn<TableData,String> grade;
    @FXML
    private PasswordField old;

    @FXML
    private PasswordField newPass;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stName.setText(currentLoggedIn.getName());
        stSurname.setText(currentLoggedIn.getSurname());
        stEmail.setText(currentLoggedIn.getEmail());
        stId.setText(Integer.toString(currentLoggedIn.getId()));


        List<TableData> tabData=new ArrayList<>();
        Float weights=0.0F;
        Float sum=0.0F;
        for(Map.Entry<Subject, Float> entry : currentLoggedIn.getGrades().entrySet()) {
            Subject key = entry.getKey();
            Float value = entry.getValue();
            sum+= key.getValue()*value;
            weights+= key.getValue();
            tabData.add(new TableData(key.getName(), key.getValue(),value));
        }
        if(weights>0){
            float avg=sum/weights;
            if(avg>=2.0){
                stAvg.setText(Float.toString(avg));
            }else{
                stAvg.setText("2.0");
            }


        }


        subjectName.setCellValueFactory(new PropertyValueFactory<TableData,String>("name"));
        value.setCellValueFactory(new PropertyValueFactory<TableData,Float>("value"));
        grade.setCellValueFactory(new PropertyValueFactory<TableData,String>("grade"));
        gradesTable.getItems().addAll(tabData);




    }

    private static Student initializeLoggedIn() throws IOException, ClassNotFoundException {
        ObjectInputStream sts=new ObjectInputStream(new FileInputStream("loggedIn.txt"));
        return (Student) sts.readObject();
    }
    @FXML
    protected  void changePassword(){
        if(!old.getText().equals(currentLoggedIn.getPassword())){
            return;
        }
        if(newPass.getText().isBlank()){
            return;
        }
        currentLoggedIn.setPassword(newPass.getText());
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
