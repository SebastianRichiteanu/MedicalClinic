package com.example.medicalclinic2.service;

import com.example.medicalclinic2.model.MedicalOffice;
import com.example.medicalclinic2.model.Medication;

public class MedicationService {
    public void addMedication (MedicalOffice medicalOffice, Medication medication) {
        medicalOffice.getMedications().add(medication);
    }

    public void updateName (Medication medication, String name) {
        medication.setName(name);
    }

    public void updatePrice (Medication medication, double price) {
        medication.setPrice(price);
    }

    private int getNumberOfMedications(MedicalOffice medicalOffice) {
        int numberOfMedications = 0;
        for (Medication m : medicalOffice.getMedications())
            if (m != null) {
                numberOfMedications++;
            }
        return numberOfMedications;
    }
}
