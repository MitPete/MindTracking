package com.example.mindtrack.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mindtrack.repository.AppointmentRepository;

public class AppointmentViewModelFactory implements ViewModelProvider.Factory {
    private AppointmentRepository mRepository;

    public AppointmentViewModelFactory(AppointmentRepository repository) {
        mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AppointmentViewModel(mRepository);
    }
}