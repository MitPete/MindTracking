package com.example.mindtrack.repository;

import android.app.Application;

import com.example.mindtrack.data.AppDatabase;
import com.example.mindtrack.data.UserDao;
import com.example.mindtrack.data.MessageDao;
import com.example.mindtrack.data.AppointmentDao;
import com.example.mindtrack.model.User;
import com.example.mindtrack.model.Message;
import com.example.mindtrack.model.Appointment;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MindTrackerRepository {
    private final UserDao userDao;
    private final MessageDao messageDao;
    private final AppointmentDao appointmentDao;

    public MindTrackerRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        messageDao = db.messageDao();
        appointmentDao = db.appointmentDao();
    }

    // User-related operations
    public void insertUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> userDao.insertUser(user));
    }

    public LiveData<User> getUserByUsernameAndPassword(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username, password);
    }

    // Message-related operations
    public void insertMessage(Message message) {
        AppDatabase.databaseWriteExecutor.execute(() -> messageDao.insertMessage(message));
    }

    public LiveData<List<Message>> getMessagesByUserId(int userId) {
        return messageDao.getMessagesByUserId(userId);
    }

    // Appointment-related operations
    public void insertAppointment(Appointment appointment) {
        AppDatabase.databaseWriteExecutor.execute(() -> appointmentDao.insertAppointment(appointment));
    }

    public LiveData<List<Appointment>> getAppointmentsByUserId(int userId) {
        return appointmentDao.getAppointmentsByUserId(userId);
    }
}