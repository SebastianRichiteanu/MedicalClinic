package com.example.medicalclinic2.service;

import com.example.medicalclinic2.model.*;


public class AppointmentService {
    public void addAppointment (MedicalOffice medicalOffice, Appointment appointment) {
        medicalOffice.getAppointments().add(appointment);
    }


    public int numberOfAppointmentsPerDoctor (MedicalOffice medicalOffice, Doctor doctor) {
        int numberOfAppointments = 0;
        for (Appointment ap : medicalOffice.getAppointments())
            if (ap != null && ap.getDoctor() == doctor) {
                numberOfAppointments++;
            }
        return numberOfAppointments;
    }

    private int getNumberOfAppointments(MedicalOffice medicalOffice) {
        int numberOfAppointments = 0;
        for (Appointment a : medicalOffice.getAppointments())
            if (a != null) {
                numberOfAppointments++;
            }
        return numberOfAppointments;
    }
}
