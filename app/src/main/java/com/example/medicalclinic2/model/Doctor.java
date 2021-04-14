package com.example.medicalclinic2.model;

public final class Doctor extends Person {
    private double salary;
    private String specialization;

    public Doctor(String name, String surname, int age, String address, String phoneNo, double salary, String specialization) {
        super(name, surname, age, address, phoneNo);
        this.salary = salary;
        this.specialization = specialization;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return super.toString() + "; Salary: " + salary + "; Specialization: " + specialization;
    }
}
