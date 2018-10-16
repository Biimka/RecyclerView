package com.startandroid.biimka.recyclerview;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Listener listener;
    private Student[] students;

    MyAdapter(Student[] students) {
        this.students = students;
    }

    void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onItemClicked(Student item);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView, ageTextView;

        private MyViewHolder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.name);
            ageTextView = (TextView) v.findViewById(R.id.age);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(students[getAdapterPosition()]);
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Student studentPosition = students[position];
        holder.nameTextView.setText(studentPosition.getName());
        holder.ageTextView.setText(holder.ageTextView.getResources().getQuantityString(R.plurals.pluralsAge, studentPosition.getAge(), studentPosition.getAge()));
    }

    @Override
    public int getItemCount() {
        return students.length;
    }
}