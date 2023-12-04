package com.example.mindtrack.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mindtrack.model.Appointment;
import com.example.mindtrack.repository.AppointmentRepository;

import java.util.List;

public class AppointmentViewModel extends ViewModel {
    private LiveData<List<Appointment>> appointments;
    private AppointmentRepository appointmentRepository;

    public AppointmentViewModel(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public LiveData<List<Appointment>> getAppointmentsByUserId(int userId) {
        if (appointments == null) {
            appointments = appointmentRepository.getAppointmentsByUserId(userId);
        }
        return appointments;
    }

    public LiveData<List<Appointment>> getAllAppointments() {
        if (appointments == null) {
            appointments = appointmentRepository.getAllAppointments();
        }
        return appointments;
    }

    public void insert(Appointment appointment) {
        appointmentRepository.insert(appointment);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final AppointmentRepository mRepository;

        public Factory(AppointmentRepository repository) {
            mRepository = repository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new AppointmentViewModel(mRepository);
        }
    }
}