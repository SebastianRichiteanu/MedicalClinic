package com.example.medicalclinic2.model;

public abstract class Person implements Comparable<Person> {
    private String name;
    private String surname;
    private int age;
    private String address;
    private String phoneNo;

    public Person(String name, String surname, int age, String address, String phoneNo) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "Name: " + name + "; Surname: " + surname + "; Age: " +
                age + "; Address: " + address + "; phoneNo: " + phoneNo;
    }

    @Override
    public int compareTo(Person o) {
        if (this.surname == null && ((Person) o).getSurname() == null)
            return 0;
        if (this.surname == null)
            return 1;
        if (((Person) o).getSurname() == null)
            return -1;
        if (this.surname.compareTo(((Person) o).getSurname()) == 0) {
            if (this.name == null && ((Person) o).getName() == null)
                return 0;
            if (this.name == null)
                return 1;
            if (((Person) o).getName() == null)
                return -1;
            return this.name.compareTo(((Person) o).getName());
        }
        return this.surname.compareTo(((Person) o).getSurname());
    }
}
