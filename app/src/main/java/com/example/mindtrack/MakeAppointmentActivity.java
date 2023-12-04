package com.example.mindtrack;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mindtrack.model.Appointment;
import com.example.mindtrack.viewmodel.AppointmentViewModel;

public class MakeAppointmentActivity extends AppCompatActivity {
    private EditText appointmentDetailsEditText;
    private Button submitButton;
    private Button makeAppointmentButton;
    private AppointmentViewModel appointmentViewModel;
    private String appointmentDate;
    private String appointmentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        appointmentDetailsEditText = findViewById(R.id.appointment_details_edit_text);
        submitButton = findViewById(R.id.submit_button);
        makeAppointmentButton = findViewById(R.id.make_appointment_button);

        appointmentViewModel = new ViewModelProvider(this).get(AppointmentViewModel.class);

        // Get the user's ID
        SharedPreferences sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        int userId = sharedPref.getInt("userId", -1);

        makeAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickerFragment newFragment = new DateTimePickerFragment();
                newFragment.setOnDateTimeSelectedListener(new DateTimePickerFragment.OnDateTimeSelectedListener() {
                    @Override
                    public void onDateTimeSelected(int year, int month, int day, int hour, int minute) {
                        appointmentDate = day + "/" + (month + 1) + "/" + year;
                        appointmentTime = hour + ":" + minute;
                    }
                });
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appointmentDetails = appointmentDetailsEditText.getText().toString();
                Appointment appointment = new Appointment(userId, appointmentDate, appointmentTime, appointmentDetails);
                appointmentViewModel.insert(appointment);
            }
        });
    }
}