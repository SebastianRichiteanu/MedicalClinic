package com.example.medicalclinic2.model;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class MedicalOffice {

    private Set<Person> people = new TreeSet<>();
    private Set<Doctor> doctors = new TreeSet<>();
    private Set<Patient> patients = new TreeSet<>();
    private List<Medication> medications = new ArrayList<>();
    private List<Prescription> prescriptions = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();
    private List<Supplier> suppliers = new ArrayList<>();

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

}
