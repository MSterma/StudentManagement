package com.example.studentmanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdminList implements Serializable {
    private static List<Admin> admins;
    static {
        try {
            admins = initializeAdmins();
            for (Admin l:admins){
                if(l.getId()>=Admin.nextId){
                    Admin.nextId=l.getId()+1;
                }
            }
        } catch (IOException e) {
            admins =new ArrayList<>();
        } catch (ClassNotFoundException e) {

            admins =new ArrayList<>();
        }
    }
    public static List<Admin> getAdmins() {
        return admins;
    }
    private static List<Admin> initializeAdmins() throws IOException, ClassNotFoundException {
        ObjectInputStream sts=new ObjectInputStream(new FileInputStream("admins.txt"));
        return (List<Admin>) sts.readObject();
    }
}
