package com.example.mindtrack.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.mindtrack.model.Appointment;
import com.example.mindtrack.model.Message;
import com.example.mindtrack.model.User;
import com.example.mindtrack.data.AppointmentDao;
import com.example.mindtrack.data.MessageDao;
import com.example.mindtrack.data.UserDao;
import com.example.mindtrack.data.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao userDao;
    private AppointmentDao appointmentDao;
    private MessageDao messageDao;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        appointmentDao = db.appointmentDao();
        messageDao = db.messageDao();
    }

    public interface InsertCallback {
        void onInsertSuccess();
        void onInsertFailure(Exception e);
    }

    public LiveData<User> getUserByUsernameAndPassword(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username, password);
    }

    public LiveData<User> getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public LiveData<List<Appointment>> getAllAppointments() {
        return appointmentDao.getAllAppointments();
    }

    public void insertUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insertUser(user);
        });
    }

    public LiveData<List<Appointment>> getAppointmentsByUsername(String username) {
        LiveData<User> userLiveData = userDao.getUserByUsername(username);
        return Transformations.switchMap(userLiveData, user -> {
            if (user != null) {
                return appointmentDao.getAppointmentsByUserId(user.getId());
            } else {
                return new MutableLiveData<>(new ArrayList<>());
            }
        });
    }

    public LiveData<List<Appointment>> getAppointmentsWithUsernames() {
        return appointmentDao.getAppointmentsWithUsernames();
    }

    public void insertAppointment(Appointment appointment) {
        executorService.execute(() -> appointmentDao.insertAppointment(appointment));
    }

    public void insertMessage(Message message, InsertCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            try {
                messageDao.insertMessage(message);
                callback.onInsertSuccess();
            } catch (Exception e) {
                callback.onInsertFailure(e);
            }
        });
    }

    public LiveData<List<Message>> getMessagesByUserId(int userId) {
        return messageDao.getMessagesByUserId(userId);
    }

    public LiveData<List<Message>> getAllMessages() {
        return messageDao.getAllMessages();
    }
}