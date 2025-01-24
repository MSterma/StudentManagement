package com.example.studentmanagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Admin extends  Person implements Serializable {
    public Admin(String name, String surname, LocalDate birthday, String password, String email){
        super(name,surname,birthday,  password, email);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
