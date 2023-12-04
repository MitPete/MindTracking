package com.example.mindtrack.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mindtrack.model.Message;
import com.example.mindtrack.repository.MindTrackerRepository;

import java.util.List;

public class MessageViewModel extends ViewModel {
    private LiveData<List<Message>> messages;
    private MindTrackerRepository mindTrackerRepository;

    public MessageViewModel(MindTrackerRepository mindTrackerRepository) {
        this.mindTrackerRepository = mindTrackerRepository;
    }

    public LiveData<List<Message>> getMessagesByUserId(int userId) {
        if (messages == null) {
            messages = mindTrackerRepository.getMessagesByUserId(userId);
        }
        return messages;
    }
}
