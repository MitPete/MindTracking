package com.example.mindtrack.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import com.example.mindtrack.data.AppointmentDao;

import com.example.mindtrack.model.Appointment;

import com.example.mindtrack.data.AppDatabase;

import java.util.List;

public class AppointmentRepository {
    private AppointmentDao appointmentDao;
    private LiveData<List<Appointment>> allAppointments;

    public AppointmentRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        appointmentDao = db.appointmentDao();
        allAppointments = appointmentDao.getAllAppointments();
    }

    public LiveData<List<Appointment>> getAllAppointments() {
        return allAppointments;
    }

    public void insert(Appointment appointment) {
        new insertAsyncTask(appointmentDao).execute(appointment);
    }

    public LiveData<List<Appointment>> getAppointmentsByUserId(int userId) {
        return appointmentDao.getAppointmentsByUserId(userId);
    }

    private static class insertAsyncTask extends AsyncTask<Appointment, Void, Void> {

        private AppointmentDao asyncTaskDao;

        insertAsyncTask(AppointmentDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Appointment... params) {
            asyncTaskDao.insertAppointment(params[0]);
            return null;
        }
    }
}