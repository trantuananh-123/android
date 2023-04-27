package com.example.ktra2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra2.Model.Employee;
import com.example.ktra2.R;
import com.example.ktra2.ViewHolder.RecyclerViewHolder;
import com.example.ktra2.ViewHolder.ViewHolderListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Employee> employeeList = new ArrayList<>();
    private ViewHolderListener viewHolderListener;

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new RecyclerViewHolder(view, viewHolderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Employee employee = this.getEmployee(position);
        holder.getTxtName().setText(String.format("Ho ten: %s", employee.getName()));
        holder.getTxtYear().setText(String.format("Nam sinh %d", employee.getYear()));
        holder.getTxtPhone().setText(String.format("Dien thoai: %s", employee.getPhone()));
        holder.getTxtGender().setText(String.format("Gioi tinh: %s", employee.getGender()));
        String[] skill = employee.getSkill().split(";");
        for (int i = 0; i < skill.length; i++) {
            if (skill[i].equalsIgnoreCase(holder.getTxtSkill1().getText().toString())) {
                holder.getTxtSkill1().setChecked(true);
            } else if (skill[i].equalsIgnoreCase(holder.getTxtSkill2().getText().toString())) {
                holder.getTxtSkill2().setChecked(true);
            } else if (skill[i].equalsIgnoreCase(holder.getTxtSkill3().getText().toString())) {
                holder.getTxtSkill3().setChecked(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.employeeList.size();
    }

    public void setList(List<Employee> list) {
        this.employeeList = list;
        notifyDataSetChanged();
    }

    public void setViewHolderListener(ViewHolderListener viewHolderListener) {
        this.viewHolderListener = viewHolderListener;
    }

    public Employee getEmployee(int position) {
        return this.employeeList.get(position);
    }
}
