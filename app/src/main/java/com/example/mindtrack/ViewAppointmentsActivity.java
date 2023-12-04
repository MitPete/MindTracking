package com.example.mindtrack;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mindtrack.model.Appointment;
import com.example.mindtrack.viewmodel.UserViewModel;

public class ViewAppointmentsActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private RecyclerView appointmentsRecyclerView;
    private AppointmentAdapter appointmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        appointmentsRecyclerView = findViewById(R.id.appointments_recycler_view);
        appointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // Added this line
        appointmentAdapter = new AppointmentAdapter();
        appointmentsRecyclerView.setAdapter(appointmentAdapter);

        String username = getIntent().getStringExtra("username");
        if (username != null) {
            userViewModel.getAppointmentsByUsername(username).observe(this, appointments -> {
                // Use the list of appointments here
                appointmentAdapter.submitList(appointments);
            });
        }
    }
}