package com.example.ktra2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra2.Adapter.RecyclerViewAdapter;
import com.example.ktra2.Database.SQLiteHelper;
import com.example.ktra2.Model.Employee;
import com.example.ktra2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FragmentSearch extends Fragment implements View.OnClickListener {

    private CheckBox txtSkill1, txtSkill2, txtSkill3;
    private Button btnSearch, btnStatistic;
    private TextView total;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SQLiteHelper sqLiteHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtSkill1 = view.findViewById(R.id.txtSkill1);
        txtSkill2 = view.findViewById(R.id.txtSkill2);
        txtSkill3 = view.findViewById(R.id.txtSkill3);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnStatistic = view.findViewById(R.id.btnStatistic);
        total = view.findViewById(R.id.total);
        recyclerView = view.findViewById(R.id.recyclerView);

        btnSearch.setOnClickListener(this);
        btnStatistic.setOnClickListener(this);

        recyclerViewAdapter = new RecyclerViewAdapter();
        sqLiteHelper = new SQLiteHelper(view.getContext());
    }

    @Override
    public void onClick(View view) {
        if (view == btnSearch) {
            List<String> skillList = new ArrayList<>();
            if (txtSkill1.isChecked()) {
                skillList.add("web");
            }
            if (txtSkill2.isChecked()) {
                skillList.add("android");
            }
            if (txtSkill3.isChecked()) {
                skillList.add("ios");
            }
            List<Employee> employeeList = sqLiteHelper.searchBySkill(String.join(";", skillList));
            recyclerViewAdapter.setList(employeeList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
        } else if (view == btnStatistic) {
            HashMap<String, Integer> hashMap = sqLiteHelper.statistic();
            List<Map.Entry<String, Integer>> list = new LinkedList<>(hashMap.entrySet());
            list.sort(Comparator.comparingInt(Map.Entry::getValue));
            Collections.reverse(list);
            String totalString = "";
            for (Map.Entry<String, Integer> aa : list) {
                totalString += aa.getKey() + ": " + aa.getValue() + "\n";
            }
            total.setText(totalString);
        }
    }
}
