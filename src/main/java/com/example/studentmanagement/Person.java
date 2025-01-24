package com.example.studentmanagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Person implements Serializable {
    public static int nextId=160;
    private int id;
    private String name;
    private String email;
    private String surname;
    private LocalDate birthday;
    private String password;
    public Person(String name, String surname, LocalDate birthday, String password, String email){
        this.id=nextId;
        nextId++;
        this.name=name;
        this.surname=surname;
        this.birthday=birthday;
        this.password=password;
        this.email=email;

    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name+" "+surname+" "+id;
    }
}
