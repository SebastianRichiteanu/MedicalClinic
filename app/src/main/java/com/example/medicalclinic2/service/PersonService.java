package com.example.medicalclinic2.service;

import com.example.medicalclinic2.model.MedicalOffice;
import com.example.medicalclinic2.model.Patient;
import com.example.medicalclinic2.model.Person;

public class PersonService {
    public void addPerson (MedicalOffice medicalOffice, Person person) {
        medicalOffice.getPeople().add(person);
    }

    public void updateName (Person person, String name) {
        person.setName(name);
    }

    public void updateSurname (Person person, String surname) {
        person.setSurname(surname);
    }

    public void updateAge (Person person, int age) {
        person.setAge(age);
    }

    public void updateAddress (Person person, String address) {
        person.setAddress(address);
    }

    public void updatePhonNo (Person person, String phoneNo) {
        person.setPhoneNo(phoneNo);
    }

    public Person searchPersonByFullName (MedicalOffice medicalOffice, String name, String surname) {
        Person src = null;
        for (Person p : medicalOffice.getPeople())
            if (p != null && p.getName().equals(name) && p.getSurname().equals(surname)) {
                src = p;
            }
        return src;
    }

    private int getNumberOfPeople(MedicalOffice medicalOffice) {
        int numberOfPeople = 0;
        for (Person p : medicalOffice.getPeople())
            if (p != null) {
                numberOfPeople++;
            }
        return numberOfPeople;
    }

    public void printPeople(MedicalOffice medicalOffice) {
        for (Person p : medicalOffice.getPeople())
            if (p != null) {
                System.out.println(p);
            }
    }
}
