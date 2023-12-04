package com.example.mindtrack;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mindtrack.model.Appointment;

public class AppointmentAdapter extends ListAdapter<Appointment, AppointmentAdapter.AppointmentViewHolder> {
    protected AppointmentAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Appointment> DIFF_CALLBACK = new DiffUtil.ItemCallback<Appointment>() {
        @Override
        public boolean areItemsTheSame(@NonNull Appointment oldItem, @NonNull Appointment newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Appointment oldItem, @NonNull Appointment newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_item, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment currentAppointment = getItem(position);
        Log.d("AppointmentAdapter", "Appointment date: " + currentAppointment.getAppointmentDate());
        Log.d("AppointmentAdapter", "Appointment time: " + currentAppointment.getAppointmentTime()); // Log the time
        Log.d("AppointmentAdapter", "Username: " + currentAppointment.getUsername());
        holder.textViewDate.setText(currentAppointment.getAppointmentDate());
        holder.textViewTime.setText(currentAppointment.getAppointmentTime()); // Set the time
        holder.textViewUsername.setText(currentAppointment.getUsername());
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDate;
        private TextView textViewTime; // TextView for the time
        private TextView textViewUsername;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.appointment_date);
            textViewTime = itemView.findViewById(R.id.appointment_time); // Find the TextView for the time
            textViewUsername = itemView.findViewById(R.id.username);
        }
    }
}