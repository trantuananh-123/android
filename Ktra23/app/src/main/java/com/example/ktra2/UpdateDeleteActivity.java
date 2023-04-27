package com.example.ktra2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ktra2.Database.SQLiteHelper;
import com.example.ktra2.Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName, txtPhone, txtYear;
    private RadioButton txtMale, txtFemale;
    private CheckBox txtSkill1, txtSkill2, txtSkill3;
    private Button btnSave, btnDelete, btnCancel;
    private SQLiteHelper sqLiteHelper;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        initData();
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
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        sqLiteHelper = new SQLiteHelper(this);
    }

    private void initData() {
        employee = (Employee) getIntent().getSerializableExtra("employee");
        txtName.setText(employee.getName());
        txtPhone.setText(employee.getPhone());
        txtYear.setText(employee.getYear().toString());
        if (employee.getGender().equalsIgnoreCase("Nam")) {
            txtMale.setChecked(true);
        } else {
            txtFemale.setChecked(true);
        }
        String[] skill = employee.getSkill().split(";");
        for (int i = 0; i < skill.length; i++) {
            if (skill[i].equalsIgnoreCase("web")) {
                txtSkill1.setChecked(true);
            } else if (skill[i].equalsIgnoreCase("android")) {
                txtSkill2.setChecked(true);
            } else if (skill[i].equalsIgnoreCase("ios")) {
                txtSkill3.setChecked(true);
            }
        }
    }

    private void initListener() {
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
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
                Employee employee = new Employee(this.employee.getId(), name, phone, Integer.parseInt(year), gender, skill);
                long row = sqLiteHelper.update(employee);
                if (row != 0) {
                    Toast.makeText(this, "Them nhan vien thanh cong", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Them nhan vien that bai", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (view == btnDelete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Xoa nhan vien?");
            builder.setMessage("Ban co chac chan muon xoa nhan vien: " + employee.getName() + " khong?");
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    long row = sqLiteHelper.detele(employee.getId());
                    if (row != 0) {
                        Toast.makeText(UpdateDeleteActivity.this, "Xoa nhan vien thanh cong", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UpdateDeleteActivity.this, "Xoa nhan vien that bai", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            finish();
        } else if (view == btnCancel) {
            finish();
        }
    }
}