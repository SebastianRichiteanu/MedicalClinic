package com.example.medicalclinic2.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.medicalclinic2.model.MedicalOffice;
import com.example.medicalclinic2.model.Medication;
import com.example.medicalclinic2.model.Prescription;

public class PrescriptionService {
    public void addPrescription (MedicalOffice medicalOffice, Prescription prescription) {
        // int nextAvailableIndex = getNumberOfPrescriptions(medicalOffice);
        medicalOffice.getPrescriptions().add(prescription);
    }

    public void updateDate (Prescription prescription, String date) {
        prescription.setDate(date);
    }

    public boolean isMedicationOnPrescription (Medication medication, Prescription prescription) {
        for (Medication m : prescription.getMedications())
            if (m == medication) {
                return true;
            }
        return false;
    }

    public void addMedicationToPrescription (Medication medication, Prescription prescription) {
        int nextAvailableIndex = getNumberOfMedicationsOnPrescription(prescription);
        prescription.getMedications()[nextAvailableIndex] = medication;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int numberOfPrescriptionPerMedication (MedicalOffice medicalOffice, Medication medication) {
        int numberOfPrescriptions = 0;
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p != null) {
                for (Medication m : p.getMedications())
                    if (m != null && m.equals(medication)) {
                        numberOfPrescriptions++;
                    }
            }
        return numberOfPrescriptions;
    }

    private int getNumberOfPrescriptions(MedicalOffice medicalOffice) {
        int numberOfPrescriptions = 0;
        for (Prescription p : medicalOffice.getPrescriptions())
            if (p != null) {
                numberOfPrescriptions++;
            }
        return numberOfPrescriptions;
    }

    private int getNumberOfMedicationsOnPrescription(Prescription prescription) {
        int numberOfMedications = 0;
        for (Medication m : prescription.getMedications())
            if (m != null) {
                numberOfMedications++;
            }
        return numberOfMedications;
    }

}
