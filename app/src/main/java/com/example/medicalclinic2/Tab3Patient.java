package com.example.medicalclinic2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalclinic2.model.DatabaseHandler;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class Tab3Patient extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    public SharedPreferences sp;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public Tab3Patient() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static Tab3Patient newInstance(int columnCount) {
        Tab3Patient fragment = new Tab3Patient();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3_patient_list, container, false);

        DatabaseHandler databaseHandler = new DatabaseHandler(getContext());
        sp = getActivity().getSharedPreferences("login", 0);
        Cursor searchUserInPatients = databaseHandler.searchUserInPatients(sp.getString("username",""));
        int idPatient = 0;
        while (searchUserInPatients.moveToNext()) {
            idPatient = searchUserInPatients.getInt(0);
        }
        ArrayList<String> appointmentsList = new ArrayList<>();
        Cursor getNewAppointmentsByPatient = databaseHandler.getOldAppointmentsByPatient(idPatient);
        while(getNewAppointmentsByPatient.moveToNext()) {
            int idDoctor = getNewAppointmentsByPatient.getInt(0);
            Cursor getDoctorNameById = databaseHandler.getDoctorNameById(idDoctor);
            String name = "";
            while (getDoctorNameById.moveToNext()) {
                name = getDoctorNameById.getString(0) + " " + getDoctorNameById.getString(1);
            }
            String date = getNewAppointmentsByPatient.getString(1);
            appointmentsList.add(date + " | " + name);
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyAppointmentRecyclerViewAdapter4(appointmentsList));
        }
        return view;
    }
}