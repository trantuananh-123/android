package com.example.ktra2.ViewHolder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra2.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView txtName, txtYear, txtPhone, txtGender;
    private CheckBox txtSkill1, txtSkill2, txtSkill3;
    private ViewHolderListener viewHolderListener;

    public RecyclerViewHolder(@NonNull View itemView, ViewHolderListener viewHolderListener) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtYear = itemView.findViewById(R.id.txtYear);
        txtPhone = itemView.findViewById(R.id.txtPhone);
        txtGender = itemView.findViewById(R.id.txtGender);
        txtSkill1 = itemView.findViewById(R.id.txtSkill1);
        txtSkill2 = itemView.findViewById(R.id.txtSkill2);
        txtSkill3 = itemView.findViewById(R.id.txtSkill3);
        this.viewHolderListener = viewHolderListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onViewHolderClick(view, getAdapterPosition());
        }
    }

    public TextView getTxtName() {
        return txtName;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public TextView getTxtYear() {
        return txtYear;
    }

    public void setTxtYear(TextView txtYear) {
        this.txtYear = txtYear;
    }

    public TextView getTxtPhone() {
        return txtPhone;
    }

    public void setTxtPhone(TextView txtPhone) {
        this.txtPhone = txtPhone;
    }

    public TextView getTxtGender() {
        return txtGender;
    }

    public void setTxtGender(TextView txtGender) {
        this.txtGender = txtGender;
    }

    public CheckBox getTxtSkill1() {
        return txtSkill1;
    }

    public void setTxtSkill1(CheckBox txtSkill1) {
        this.txtSkill1 = txtSkill1;
    }

    public CheckBox getTxtSkill2() {
        return txtSkill2;
    }

    public void setTxtSkill2(CheckBox txtSkill2) {
        this.txtSkill2 = txtSkill2;
    }

    public CheckBox getTxtSkill3() {
        return txtSkill3;
    }

    public void setTxtSkill3(CheckBox txtSkill3) {
        this.txtSkill3 = txtSkill3;
    }
}
