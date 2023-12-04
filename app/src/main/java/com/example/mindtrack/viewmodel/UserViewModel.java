package com.example.mindtrack.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mindtrack.model.Appointment;
import com.example.mindtrack.model.Message;
import com.example.mindtrack.model.User;
import com.example.mindtrack.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public LiveData<List<Appointment>> getAppointmentsByUsername(String username) {
        return userRepository.getAppointmentsByUsername(username);
    }

    public LiveData<User> getUserByUsernameAndPassword(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    public LiveData<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public void insertAppointment(Appointment appointment) {
        userRepository.insertAppointment(appointment);
    }

    public LiveData<List<Appointment>> getAllAppointments() {
        return userRepository.getAllAppointments();
    }

    public void insertMessage(Message message) {
        userRepository.insertMessage(message, new UserRepository.InsertCallback() {
            @Override
            public void onInsertSuccess() {
                Log.d("UserViewModel", "Message inserted successfully");
            }

            @Override
            public void onInsertFailure(Exception e) {
                Log.e("UserViewModel", "Error inserting message", e);
            }
        });
    }

    public LiveData<List<Message>> getMessagesByUserId(int userId) {
        return userRepository.getMessagesByUserId(userId);
    }

    public LiveData<List<Message>> getAllMessages() {
        return userRepository.getAllMessages();
    }

    public void logout() {
        // Clear the shared preferences
        SharedPreferences sharedPref = getApplication().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }
}