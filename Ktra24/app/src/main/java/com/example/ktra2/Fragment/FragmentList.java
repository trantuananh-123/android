package com.example.ktra2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra2.Adapter.RecyclerViewAdapter;
import com.example.ktra2.Database.SQLiteHelper;
import com.example.ktra2.Model.Job;
import com.example.ktra2.R;
import com.example.ktra2.UpdateDeleteActivity;
import com.example.ktra2.ViewHolder.ViewHolderListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment implements ViewHolderListener {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SQLiteHelper sqLiteHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Job> jobList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        sqLiteHelper = new SQLiteHelper(this.getContext());

        jobList = sqLiteHelper.getAll();
        recyclerViewAdapter.setJobList(jobList);
        recyclerViewAdapter.setViewHolderListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Job> jobList = sqLiteHelper.getAll();
        recyclerViewAdapter.setJobList(jobList);
    }

    @Override
    public void onClickViewHolder(View view, int position) {
        Job job = recyclerViewAdapter.getJob(position);
        Intent intent = new Intent(getContext(), UpdateDeleteActivity.class);
        intent.putExtra("job", job);
        startActivity(intent);
    }
}
