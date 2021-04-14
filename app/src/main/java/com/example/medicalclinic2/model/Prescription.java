package com.example.medicalclinic2.model;

public class Prescription {
    private String date;
    private Medication[] medications;

    public Prescription(String date, Medication[] medications) {
        this.date = date;
        this.medications = medications;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Medication[] getMedications() {
        return medications;
    }

    public void setMedications(Medication[] medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        String str = "Date: " + date;
        if (medications != null)
            for (Medication m : medications)
                if (m != null) {
                    str += m.toString();
                    str += '\n';
                }
        return str;
    }
}
