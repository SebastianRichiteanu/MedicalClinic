package com.example.medicalclinic2.model;

public final class Patient extends Person{
    private String condition;

    public Patient(String name, String surname, int age, String address, String phoneNo, String condition) {
        super(name, surname, age, address, phoneNo);
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return super.toString() + "; Condition: " + condition;
    }
}
