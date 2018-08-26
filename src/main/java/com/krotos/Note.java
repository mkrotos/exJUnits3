package com.krotos;

public class Note {
    private String name;
    private double grade;

    public Note(String name, double grade) {
        if(name==null){
            throw new IllegalArgumentException();
        }
        if(name.trim().isEmpty()){
            throw new IllegalArgumentException();
        }
        if(grade>5.0||grade<2.0){
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.grade = grade;
    }
    public static Note of(String name, double grade){
        return new Note(name,grade);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}
