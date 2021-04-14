package com.example.medicalclinic2.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Medication {
    private String name;
    private double price;
    private Supplier supplier;

    public Medication(String name, double price, Supplier supplier) {
        this.name = name;
        this.price = price;
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Supplier getSupplier() { return supplier; }

    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    @Override
    public String toString() {
        return "Name: " + name + "; Price: " + price + "\nSupplier: " + supplier;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication medication = (Medication) o;
        return Objects.equals(name, medication.getName()) && price == medication.getPrice() &&
                supplier.equals(medication.supplier);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, price, supplier);
    }


}
