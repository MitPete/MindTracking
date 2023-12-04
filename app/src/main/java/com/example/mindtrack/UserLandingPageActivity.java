package com.example.mindtrack;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.mindtrack.model.Appointment;
import com.example.mindtrack.viewmodel.UserViewModel;
import java.util.Calendar;

public class UserLandingPageActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private TextView welcomeTextView;
    private Button makeAppointmentButton;
    private Button viewAppointmentsButton;
    private Button sendMessageButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing_page);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        welcomeTextView = findViewById(R.id.welcome_text_view);
        makeAppointmentButton = findViewById(R.id.make_appointment_button);
        viewAppointmentsButton = findViewById(R.id.view_appointments_button);
        sendMessageButton = findViewById(R.id.send_message_button);
        logoutButton = findViewById(R.id.logout_button);

        String username = getIntent().getStringExtra("username"); // Retrieve the username from the Intent
        welcomeTextView.setText("Hello, " + username + "!"); // Display the welcome message with the username

        userViewModel.getUserByUsername(username).observe(this, user -> {
            if (user != null) {
                int userId = user.getId();

                makeAppointmentButton.setOnClickListener(v -> {
                    DateTimePickerFragment newFragment = new DateTimePickerFragment();
                    newFragment.setOnDateTimeSelectedListener(new DateTimePickerFragment.OnDateTimeSelectedListener() {
                        @Override
                        public void onDateTimeSelected(int year, int month, int day, int hour, int minute) {
                            // Save the selected date and time to the database
                            String date = year + "-" + (month + 1) + "-" + day;
                            String time = hour + ":" + minute;
                            Appointment appointment = new Appointment();
                            appointment.setAppointmentDate(date);
                            appointment.setAppointmentTime(time);
                            appointment.setUserId(userId); // assuming Appointment has a userId field
                            // set other fields as needed
                            userViewModel.insertAppointment(appointment); // assuming UserViewModel has an insertAppointment method
                        }
                    });
                    newFragment.show(getSupportFragmentManager(), "datePicker");
                });
            }
        });

        viewAppointmentsButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserLandingPageActivity.this, ViewAppointmentsActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        sendMessageButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserLandingPageActivity.this, SendMessageActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            // Clear the user session
            userViewModel.logout(); // Assuming UserViewModel has a logout method that clears the user session

            // Navigate back to LoginActivity
            Intent intent = new Intent(UserLandingPageActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // This call is needed to prevent the user going back to this activity on back press
        });
    }
}