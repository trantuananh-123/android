package com.example.ktra2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra2.Model.Job;
import com.example.ktra2.R;
import com.example.ktra2.ViewHolder.RecyclerViewHolder;
import com.example.ktra2.ViewHolder.ViewHolderListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Job> jobList = new ArrayList<>();

    private ViewHolderListener viewHolderListener;

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
        notifyDataSetChanged();
    }

    public Job getJob(int position) {
        return this.jobList.get(position);
    }

    public void setViewHolderListener(ViewHolderListener viewHolderListener) {
        this.viewHolderListener = viewHolderListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new RecyclerViewHolder(view, viewHolderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Job job = this.getJob(position);
        holder.getTxtName().setText(job.getName());
        holder.getTxtDate().setText(job.getDate());
        holder.getTxtStatus().setText(job.getStatus());
        holder.getTxtType().setText(!job.getType() ? "1 minh" : "Lam chung");
        holder.getTxtContent().setText(job.getContent());
    }

    @Override
    public int getItemCount() {
        return this.jobList.size();
    }
}
