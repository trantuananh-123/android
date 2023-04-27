package com.example.ktra2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ktra2.Database.SQLiteHelper;
import com.example.ktra2.Model.Job;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName, txtContent, txtDate;
    private Spinner txtStatus;
    private RadioButton txt1Minh, txtLamChung;
    private Button btnSave, btnCancel;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }

    private void initView() {
        txtName = findViewById(R.id.txtName);
        txtContent = findViewById(R.id.txtContent);
        txtDate = findViewById(R.id.txtDate);
        txtStatus = findViewById(R.id.txtStatus);
        txtStatus.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner, getResources().getStringArray(R.array.status)));
        txt1Minh = findViewById(R.id.txt1Minh);
        txtLamChung = findViewById(R.id.txtLamChung);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        sqLiteHelper = new SQLiteHelper(this);
        txtDate.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == txtDate) {
            Calendar calendar = Calendar.getInstance();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                String date = "";

                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    if (m >= 9) {
                        if (d < 10) {
                            date = "0" + d + "/" + (m - 1) + "/" + y;
                        } else {
                            date = d + "/" + (m - 1) + "/" + y;
                        }
                    } else {
                        if (d < 10) {
                            date = "0" + d + "/0" + (m - 1) + "/" + y;
                        } else {
                            date = d + "/0" + (m - 1) + "/" + y;
                        }
                    }
                    txtDate.setText(date);
                }
            }, y, m, d);
            datePickerDialog.show();
        } else if (view == btnSave) {
            String name = txtName.getText().toString();
            String content = txtContent.getText().toString();
            String date = txtDate.getText().toString();
            String status = txtStatus.getSelectedItem().toString();
            Boolean type = !txt1Minh.isChecked();
            if (name.isEmpty()) {
                Toast.makeText(this, "Ten khong duoc bo trong", Toast.LENGTH_SHORT).show();
            } else if (content.isEmpty()) {
                Toast.makeText(this, "Noi dung khong duoc bo trong", Toast.LENGTH_SHORT).show();
            } else if (date.isEmpty()) {
                Toast.makeText(this, "Ngay hoan thanh khong duoc bo trong", Toast.LENGTH_SHORT).show();
            } else if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
                Toast.makeText(this, "Ngay hoan thanh phai co dinh dang(dd/MM/yyy)", Toast.LENGTH_SHORT).show();
            } else {
                Job job = new Job(name, content, date, status, type);
                long row = sqLiteHelper.add(job);
                if (row != 0) {
                    Toast.makeText(this, "Them moi cong viec thanh cong", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Them moi cong viec that bai", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (view == btnCancel) {
            finish();
        }
    }
}