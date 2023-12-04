package com.example.mindtrack.data;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mindtrack.model.Message;

import java.util.List;

@Dao
public interface MessageDao {
    @Insert
    void insertMessage(Message message);

    @Query("SELECT * FROM messages WHERE userId = :userId")
    LiveData<List<Message>> getMessagesByUserId(int userId);

    @Query("SELECT * FROM messages")
    LiveData<List<Message>> getAllMessages();
}