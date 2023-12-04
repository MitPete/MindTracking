package com.example.mindtrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mindtrack.model.Message;

public class MessageListAdapter extends ListAdapter<Message, MessageListAdapter.MessageViewHolder> {

    protected MessageListAdapter(@NonNull DiffUtil.ItemCallback<Message> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message current = getItem(position);
        holder.bind(current.messageText, current.username); // Pass the username here
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageItemView;
        private final TextView usernameItemView; // Add this line

        private MessageViewHolder(View itemView) {
            super(itemView);
            messageItemView = itemView.findViewById(R.id.textView);
            usernameItemView = itemView.findViewById(R.id.usernameTextView); // Initialize the TextView for the username
        }

        public void bind(String text, String username) { // Add a parameter for the username
            messageItemView.setText(text);
            usernameItemView.setText(username); // Set the username text
        }
    }

    static class MessageDiff extends DiffUtil.ItemCallback<Message> {

        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.messageText.equals(newItem.messageText);
        }
    }
}