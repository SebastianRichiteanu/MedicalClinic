package com.example.medicalclinic2.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.medicalclinic2.model.MedicalOffice;
import com.example.medicalclinic2.model.*;

// import javax.print.Doc;

public class MedicalOfficeService {
    private final PersonService personService = new PersonService();
    private final DoctorService doctorService = new DoctorService();
    private final PatientService patientService = new PatientService();
    private final MedicationService medicationService = new MedicationService();
    private final PrescriptionService prescriptionService = new PrescriptionService();
    private final AppointmentService appointmentService = new AppointmentService();
    private final SupplierService supplierService = new SupplierService();

    public void addPerson (MedicalOffice medicalOffice, Person person) {
        personService.addPerson(medicalOffice, person);
    }

    public void addDoctor (MedicalOffice medicalOffice, Doctor doctor) {
        personService.addPerson(medicalOffice, doctor);
        doctorService.addDoctor(medicalOffice, doctor);
    }

    public void addPatient (MedicalOffice medicalOffice, Patient patient) {
        personService.addPerson(medicalOffice, patient);
        patientService.addPatient(medicalOffice, patient);
    }

    public void addMedication (MedicalOffice medicalOffice, Medication medication) {
        medicationService.addMedication(medicalOffice, medication);
    }

    public void addPrescription (MedicalOffice medicalOffice, Prescription prescription) {
        prescriptionService.addPrescription(medicalOffice, prescription);
    }

    public void addAppointment (MedicalOffice medicalOffice, Appointment appointment) {
        appointmentService.addAppointment(medicalOffice, appointment);
    }

    public void addSupplier (MedicalOffice medicalOffice, Supplier supplier) {
        supplierService.addSupplier(medicalOffice, supplier);
    }

    public Person searchPersonByFullName (MedicalOffice medicalOffice, String name, String surname) {
        return personService.searchPersonByFullName(medicalOffice, name, surname);
    }

    public Doctor searchDoctorByFullName (MedicalOffice medicalOffice, String name, String surname) {
        return doctorService.searchDoctorByFullName(medicalOffice, name, surname);
    }

    public Patient searchPatientByFullName (MedicalOffice medicalOffice, String name, String surname) {
        return patientService.searchPatientByFullName(medicalOffice, name, surname);
    }

    public void updateName (Person person, String name) {
        personService.updateName(person, name);
    }

    public void updateSurname (Person person, String surname) {
        personService.updateSurname(person, surname);
    }

    public void updateAge (Person person, int age) {
        personService.updateAge(person, age);
    }

    public void updateAddress (Person person, String address) {
        personService.updateAddress(person, address);
    }

    public void updatePhoneNo (Person person, String phoneNo) {
        personService.updatePhonNo(person, phoneNo);
    }

    public void updateSalary (Doctor doctor, double salary) {
        doctorService.updateSalary(doctor, salary);
    }

    public void updateSpecialization (Doctor doctor, String specialization) {
        doctorService.updateSpecialization(doctor,specialization);
    }

    public void updateCondition (Patient patient, String condition) {
        patientService.updateCondition(patient, condition);
    }

    public void updateName (Medication medication, String name) {
        medicationService.updateName(medication, name);
    }

    public void updatePrice (Medication medication, double price) {
        medicationService.updatePrice(medication, price);
    }

    public void updateDate (Prescription prescription, String date) {
        prescriptionService.updateDate(prescription, date);
    }

    public void updateName (Supplier supplier, String name) { supplierService.updateName(supplier, name); }

    public void updateLocation (Supplier supplier, String location) { supplierService.updateLocation(supplier, location); }

    public boolean isMedicationOnPrescription (Medication medication, Prescription prescription) {
        return prescriptionService.isMedicationOnPrescription(medication, prescription);
    }

    public void addMedicationToPrescription (Medication medication, Prescription prescription) {
        prescriptionService.addMedicationToPrescription(medication, prescription);
    }

    public int numberOfAppointmentsPerDoctor (MedicalOffice medicalOffice, Doctor doctor) {
        return appointmentService.numberOfAppointmentsPerDoctor(medicalOffice, doctor);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int numberOfPrescriptionPerMedication (MedicalOffice medicalOffice, Medication medication) {
        return prescriptionService.numberOfPrescriptionPerMedication(medicalOffice, medication);
    }

    public void printPeople(MedicalOffice medicalOffice) {
        personService.printPeople(medicalOffice);
    }

    public void printDoctors(MedicalOffice medicalOffice) {
        doctorService.printDoctors(medicalOffice);
    }

    public void printPatients(MedicalOffice medicalOffice) {
        patientService.printPatients(medicalOffice);
    }
}
