package com.example.mindtrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mindtrack.model.Appointment;
import com.example.mindtrack.repository.AppointmentRepository;
import com.example.mindtrack.viewmodel.AppointmentViewModel;
import com.example.mindtrack.viewmodel.UserViewModel;

import java.util.List;

public class AdminLandingPageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AppointmentAdapter adapter;
    private AppointmentViewModel appointmentViewModel;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing_page);

        recyclerView = findViewById(R.id.appointments_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new AppointmentAdapter();
        recyclerView.setAdapter(adapter);

        Button viewAppointmentsButton = findViewById(R.id.view_appointments_button);
        viewAppointmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log a message to confirm that the button click is being detected
                Log.d("AdminLandingPageActivity", "View Appointments button clicked");
                // Fetch and display appointments
                fetchAppointments();
            }
        });

        Button viewMessagesButton = findViewById(R.id.view_messages_button);
        viewMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log a message to confirm that the button click is being detected
                Log.d("AdminLandingPageActivity", "View Messages button clicked");
                // Start the MessageListActivity
                Intent intent = new Intent(AdminLandingPageActivity.this, MessageListActivity.class);
                startActivity(intent);
            }
        });

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the user session
                userViewModel.logout();

                // Navigate back to LoginActivity
                Intent intent = new Intent(AdminLandingPageActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        // Create the AppointmentRepository and AppointmentViewModelFactory
        AppointmentRepository repository = new AppointmentRepository(getApplication());
        AppointmentViewModel.Factory factory = new AppointmentViewModel.Factory(repository);

        // Use the factory to create the AppointmentViewModel
        appointmentViewModel = new ViewModelProvider(this, factory).get(AppointmentViewModel.class);

        // Initialize UserViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void fetchAppointments() {
        // Fetch all appointments from your database
        appointmentViewModel.getAllAppointments().observe(this, new Observer<List<Appointment>>() {
            @Override
            public void onChanged(List<Appointment> appointments) {
                // Log the appointments
                Log.d("AdminLandingPageActivity", "Appointments: " + appointments);
                // Update the cached copy of the appointments in the adapter.
                adapter.submitList(appointments);
                adapter.notifyDataSetChanged();
            }
        });
    }
}