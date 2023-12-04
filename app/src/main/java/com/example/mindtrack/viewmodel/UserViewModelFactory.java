package com.example.mindtrack.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mindtrack.repository.MindTrackerRepository;

public class UserViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public UserViewModelFactory(Application application) {
        this.application = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new UserViewModel(application);
    }
}