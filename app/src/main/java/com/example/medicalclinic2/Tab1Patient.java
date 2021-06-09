package com.example.medicalclinic2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.example.medicalclinic2.model.DatabaseHandler;
import com.example.medicalclinic2.ui.main.SectionsPagerAdapter;

import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import android.content.SharedPreferences;
import android.widget.TabHost;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab1Patient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1Patient extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Spinner dropdown;
    public SharedPreferences sp;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab1Patient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1Patient.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1Patient newInstance(String param1, String param2) {
        Tab1Patient fragment = new Tab1Patient();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sp = getActivity().getSharedPreferences("login", 0);
        View view = inflater.inflate(R.layout.fragment_tab1_patient, container, false);
        DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());
        dropdown = view.findViewById(R.id.spinner_appointment);
        ArrayList<String> idDoctors = new ArrayList();

        Cursor cursorNameAllDoctors = databaseHandler.nameAllDoctors();

        while(cursorNameAllDoctors.moveToNext()) {
            String nameDoctor = cursorNameAllDoctors.getString(0) + " " + cursorNameAllDoctors.getString(1);
            idDoctors.add(nameDoctor);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, idDoctors);
        dropdown.setAdapter(adapter);

        Button appointment_submit = (Button) view.findViewById(R.id.appointment_submit);
        CalendarView appointment_calendar = (CalendarView) view.findViewById(R.id.appointment_calendar);
        final String[] date = {""};

        appointment_submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                String nameDoctor = dropdown.getSelectedItem().toString();
                String[] namesDoctor = nameDoctor.split(" ");
                Cursor getDoctorIdByName = databaseHandler.getDoctorIdByName(namesDoctor[0], namesDoctor[1]);
                int idDoctor = -1;
                while (getDoctorIdByName.moveToNext()) {
                    idDoctor = getDoctorIdByName.getInt(0);
                }
                Cursor getPatientIdByUsername = databaseHandler.getPatientIdByUsername(sp.getString("username",""));
                int idPatient = -1;
                while (getPatientIdByUsername.moveToNext()) {
                    idPatient = getPatientIdByUsername.getInt(0);
                }

                if (date[0].equals("")) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDateTime now = LocalDateTime.now();
                    String current_date = dtf.format(now);
                    String[] current = current_date.split("/");
                    date[0] = current[0] + "-" + current[1] + "-" + current[2];
                }
                databaseHandler.insertAppointment(idDoctor, idPatient, Date.valueOf(date[0]));
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });

        appointment_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                month += 1;
                date[0] = year + "-" + month + "-" + dayOfMonth;
            }
        });

        return view;
    }
}