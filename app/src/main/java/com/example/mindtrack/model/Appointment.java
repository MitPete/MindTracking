package com.example.mindtrack.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "appointments",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE))
public class Appointment {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;
    public String appointmentDate;
    public String appointmentTime;
    public String notes;
    public String username; // Added username field

    // Existing constructor
    public Appointment(int userId, String appointmentDate, String appointmentTime, String notes, String username) {
        this.userId = userId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.notes = notes;
        this.username = username; // Added username to constructor
    }

    // New constructor
    public Appointment(int userId, String appointmentDate, String appointmentTime, String notes) {
        this.userId = userId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.notes = notes;
    }

    // No-argument constructor
    public Appointment() {
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", userId=" + userId +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", notes='" + notes + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment appointment = (Appointment) obj;
        return id == appointment.id &&
                userId == appointment.userId &&
                Objects.equals(appointmentDate, appointment.appointmentDate) &&
                Objects.equals(appointmentTime, appointment.appointmentTime) &&
                Objects.equals(notes, appointment.notes) &&
                Objects.equals(username, appointment.username);
    }
}