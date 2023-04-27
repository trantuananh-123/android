package com.example.ktra2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ktra2.Database.SQLiteHelper;
import com.example.ktra2.Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName, txtPhone, txtYear;
    private RadioButton txtMale, txtFemale;
    private CheckBox txtSkill1, txtSkill2, txtSkill3;
    private Button btnSave, btnCancel;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        initListener();
    }

    private void initView() {
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtYear = findViewById(R.id.txtYear);
        txtMale = findViewById(R.id.txtMale);
        txtFemale = findViewById(R.id.txtFemale);
        txtSkill1 = findViewById(R.id.txtSkill1);
        txtSkill2 = findViewById(R.id.txtSkill2);
        txtSkill3 = findViewById(R.id.txtSkill3);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        sqLiteHelper = new SQLiteHelper(this);
    }

    private void initListener() {
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSave) {
            String name = txtName.getText().toString();
            String phone = txtPhone.getText().toString();
            String year = txtYear.getText().toString();
            String gender = txtMale.isChecked() ? "Nam" : "Nu";
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
            String skill = String.join(";", skillList);

            if (name.isEmpty()) {
                Toast.makeText(this, "Ho va ten khong duoc de trong", Toast.LENGTH_SHORT).show();
            } else if (phone.isEmpty()) {
                Toast.makeText(this, "So dien thoai khong duoc de trong", Toast.LENGTH_SHORT).show();
            } else if (year.isEmpty()) {
                Toast.makeText(this, "Nam sinh khong duoc de trong", Toast.LENGTH_SHORT).show();
            } else if (Integer.parseInt(year) < 1980 || Integer.parseInt(year) > 1995) {
                Toast.makeText(this, "Nam sinh phai trong khoang tu 1980 - 1995", Toast.LENGTH_SHORT).show();
            } else {
                Employee employee = new Employee(name, phone, Integer.parseInt(year), gender, skill);
                long row = sqLiteHelper.add(employee);
                if (row != 0) {
                    Toast.makeText(this, "Them nhan vien thanh cong", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Them nhan vien that bai", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (view == btnCancel) {
            finish();
        }
    }
}