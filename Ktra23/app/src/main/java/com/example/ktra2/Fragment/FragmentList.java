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
import androidx.viewpager.widget.ViewPager;

import com.example.ktra2.Adapter.RecyclerViewAdapter;
import com.example.ktra2.Database.SQLiteHelper;
import com.example.ktra2.Model.Employee;
import com.example.ktra2.R;
import com.example.ktra2.UpdateDeleteActivity;
import com.example.ktra2.ViewHolder.ViewHolderListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment implements ViewHolderListener {

    private SQLiteHelper sqLiteHelper;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Employee employee;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter();
        sqLiteHelper = new SQLiteHelper(getContext());

        List<Employee> employeeList = sqLiteHelper.getAll();
//        employeeList.add(new Employee("Tuan Anh", "0346724858", 22, "Nam", "android;ios"));
        recyclerViewAdapter.setList(employeeList);
        recyclerViewAdapter.setViewHolderListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Employee> employeeList = sqLiteHelper.getAll();
//        employeeList.add(new Employee("Tuan Anh", "0346724858", 22, "Nam", "android;ios"));
        recyclerViewAdapter.setList(employeeList);
    }

    @Override
    public void onViewHolderClick(View view, int position) {
        this.employee = recyclerViewAdapter.getEmployee(position);
        Intent intent = new Intent(this.getContext(), UpdateDeleteActivity.class);
        intent.putExtra("employee", employee);
        startActivity(intent);
    }
}
