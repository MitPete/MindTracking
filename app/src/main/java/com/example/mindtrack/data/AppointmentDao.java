package com.example.mindtrack.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.lifecycle.LiveData;

import com.example.mindtrack.model.Appointment;

import java.util.List;

@Dao
public interface AppointmentDao {
    @Insert
    void insertAppointment(Appointment appointment);

    @Query("SELECT * FROM appointments WHERE userId = :userId")
    LiveData<List<Appointment>> getAppointmentsByUserId(int userId);

    @Query("SELECT appointments.*, users.username AS username FROM appointments JOIN users ON appointments.userId = users.id")
    LiveData<List<Appointment>> getAllAppointments();

    @Query("SELECT appointments.*, users.username FROM appointments JOIN users ON appointments.userId = users.id")
    LiveData<List<Appointment>> getAppointmentsWithUsernames();
}