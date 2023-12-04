package com.example.mindtrack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mindtrack.model.User;
import com.example.mindtrack.viewmodel.UserViewModel;
import com.example.mindtrack.model.Message;

public class SendMessageActivity extends AppCompatActivity {
    private EditText messageEditText;
    private Button sendButton;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        messageEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_button);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SendMessageActivity", "Send button clicked");

                String message = messageEditText.getText().toString();
                Log.d("SendMessageActivity", "Message: " + message); // Log the message

                if (!message.isEmpty()) {
                    // Create a new Message object
                    Message newMessage = new Message();
                    newMessage.messageText = message;

                    // Get the logged-in user's ID and username from shared preferences
                    SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
                    int loggedInUserId = prefs.getInt("userId", -1);
                    String loggedInUsername = prefs.getString("username", null);
                    Log.d("SendMessageActivity", "User ID: " + loggedInUserId); // Log the user ID

                    if (loggedInUserId != -1 && loggedInUsername != null) {
                        newMessage.userId = loggedInUserId;
                        newMessage.username = loggedInUsername; // Set the username here

                        // Save the message to the database
                        try {
                            userViewModel.insertMessage(newMessage);
                        } catch (Exception e) {
                            Log.e("SendMessageActivity", "Error inserting message", e);
                        }
                    } else {
                        Log.d("SendMessageActivity", "User's ID or username not found in SharedPreferences");
                    }
                } else {
                    Log.d("SendMessageActivity", "Message is empty");
                }
            }
        });
    }
}