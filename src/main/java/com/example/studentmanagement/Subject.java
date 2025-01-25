package com.example.studentmanagement;

import java.io.Serializable;

public class Subject implements Serializable {
    private String name;
    private float value;
    public Subject(String name, float value){
        this.name=name;
        this.value=value;
    }

    public float getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj.getClass()!= getClass()){
            return false;
        }
        Subject s=(Subject) obj;
        return this.name.equals(s.name);
    }

    @Override
    public String toString() {
        return name+" "+value;
    }
}
