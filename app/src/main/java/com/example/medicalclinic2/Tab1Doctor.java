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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.medicalclinic2.dummy.DummyContent;
import com.example.medicalclinic2.model.Appointment;
import com.example.medicalclinic2.model.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class Tab1Doctor extends Fragment {
    private SharedPreferences sp;
    private DatabaseHandler databaseHandler;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public Tab1Doctor() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static Tab1Doctor newInstance(int columnCount) {
        Tab1Doctor fragment = new Tab1Doctor();
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
        View view = inflater.inflate(R.layout.fragment_tab1_doctor_list, container, false);//
        sp = getActivity().getSharedPreferences("login", 0);
        databaseHandler = new DatabaseHandler(getContext());
        Cursor searchUserInDoctors = databaseHandler.searchUserInDoctors(sp.getString("username",""));
        int idDoctor = 0;
        while (searchUserInDoctors.moveToNext()) {
            idDoctor = searchUserInDoctors.getInt(0);
        }
        System.out.println(idDoctor);
        Cursor c = databaseHandler.getNewAppointmentByDoctor(idDoctor);
        List<String> data = new ArrayList<>();
            while(c.moveToNext()) {
                System.out.println(c.getInt(2));
                Cursor c2 = databaseHandler.getPatientName(c.getInt(2));
                String name = "";
                String surname = "";
                while (c2.moveToNext()){
                    name = c2.getString(0);
                    surname = c2.getString(1);
                    System.out.println(name+" "+surname+"\n");
                }
                data.add(c.getString(3) + " | " + name + " " + surname);
            }
        System.out.println("!!!!!!");
        System.out.println();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyAppointmentRecyclerViewAdapter(data));
        }
        return view;

    }
}