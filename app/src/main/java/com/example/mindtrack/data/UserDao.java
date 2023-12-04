package com.example.mindtrack.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mindtrack.model.Appointment;
import com.example.mindtrack.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    LiveData<User> getUserByUsernameAndPassword(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username")
    LiveData<User> getUserByUsername(String username);

    @Query("SELECT * FROM appointments")
    LiveData<List<Appointment>> getAllAppointments();
}