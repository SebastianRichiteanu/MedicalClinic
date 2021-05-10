package com.example.medicalclinic2.model;

public final class Doctor extends Person {
    private String specialization;

    public Doctor(String name, String surname, int age, String address, String phoneNo, double salary, String specialization) {
        super(name, surname, age, address, phoneNo);
        this.specialization = specialization;
    }



    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return super.toString() + "Specialization: " + specialization;
    }
}
