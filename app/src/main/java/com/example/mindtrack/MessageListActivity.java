package com.example.mindtrack;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mindtrack.model.Message;
import com.example.mindtrack.viewmodel.UserViewModel;

import java.util.List;

import android.util.Log; // Add this import at the top of your file

public class MessageListActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private RecyclerView recyclerView;
    private MessageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        recyclerView = findViewById(R.id.message_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageListAdapter(new MessageListAdapter.MessageDiff());
        recyclerView.setAdapter(adapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getAllMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                // Update the cached copy of the messages in the adapter.
                adapter.submitList(messages);
                adapter.notifyDataSetChanged();

                // Add this log statement
                Log.d("MessageListActivity", "Number of messages: " + messages.size());
            }
        });
    }
}