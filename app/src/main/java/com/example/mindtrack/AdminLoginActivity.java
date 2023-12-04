package com.example.mindtrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mindtrack.repository.AppointmentRepository;
import com.example.mindtrack.viewmodel.AppointmentViewModel;

public class AdminLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        EditText usernameEditText = findViewById(R.id.username_edit_text);
        EditText passwordEditText = findViewById(R.id.password_edit_text);
        Button loginButton = findViewById(R.id.login_button);

        AppointmentRepository repository = new AppointmentRepository(getApplication());
        AppointmentViewModel.Factory factory = new AppointmentViewModel.Factory(repository);
        AppointmentViewModel viewModel = new ViewModelProvider(this, factory).get(AppointmentViewModel.class);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (username.equals("admin") && password.equals("admin")) { // Replace "admin" and "admin" with your actual admin username and password
                Intent intent = new Intent(AdminLoginActivity.this, AdminLandingPageActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(AdminLoginActivity.this, "Invalid admin username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}