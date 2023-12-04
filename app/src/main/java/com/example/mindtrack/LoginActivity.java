package com.example.mindtrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mindtrack.viewmodel.UserViewModel;
import com.example.mindtrack.viewmodel.UserViewModelFactory;

public class LoginActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserViewModelFactory factory = new UserViewModelFactory(getApplication());
        userViewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);

        EditText usernameEditText = findViewById(R.id.username_edit_text);
        EditText passwordEditText = findViewById(R.id.password_edit_text);
        Button loginButton = findViewById(R.id.login_button);
        Button signUpButton = findViewById(R.id.signup_button);
        Button adminLoginButton = findViewById(R.id.admin_login_button); // Admin login button

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            userViewModel.getUserByUsernameAndPassword(username, password).observe(this, user -> {
                if (user != null) {
                    // The user has successfully logged in
                    SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("username", username);
                    editor.putInt("userId", user.getId());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, UserLandingPageActivity.class);
                    intent.putExtra("username", user.username);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            });
        });

        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        adminLoginButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, AdminLoginActivity.class);
            startActivity(intent);
        });
    }
}