package com.example.mindtrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.mindtrack.model.User;
import com.example.mindtrack.viewmodel.UserViewModel;
import com.example.mindtrack.viewmodel.UserViewModelFactory;
import com.example.mindtrack.repository.MindTrackerRepository;

public class SignUpActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MindTrackerRepository repository = new MindTrackerRepository(getApplication());
        UserViewModelFactory factory = new UserViewModelFactory(getApplication());
        userViewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);

        EditText usernameEditText = findViewById(R.id.signup_username_edit_text);
        EditText passwordEditText = findViewById(R.id.signup_password_edit_text);
        Button signupButton = findViewById(R.id.signup_submit_button);

        signupButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            User newUser = new User();
            newUser.username = username;
            newUser.password = password;

            userViewModel.insertUser(newUser);

            // Start LoginActivity
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}