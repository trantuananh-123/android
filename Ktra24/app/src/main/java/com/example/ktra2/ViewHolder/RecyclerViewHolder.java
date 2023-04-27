package com.example.ktra2.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktra2.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ViewHolderListener viewHolderListener;
    private TextView txtName, txtDate, txtStatus, txtType, txtContent;

    public RecyclerViewHolder(@NonNull View itemView, ViewHolderListener viewHolderListener) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtDate = itemView.findViewById(R.id.txtDate);
        txtStatus = itemView.findViewById(R.id.txtStatus);
        txtType = itemView.findViewById(R.id.txtType);
        txtContent = itemView.findViewById(R.id.txtContent);
        this.viewHolderListener = viewHolderListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (this.viewHolderListener != null) {
            this.viewHolderListener.onClickViewHolder(view, getAdapterPosition());
        }
    }

    public TextView getTxtName() {
        return txtName;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(TextView txtDate) {
        this.txtDate = txtDate;
    }

    public TextView getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(TextView txtStatus) {
        this.txtStatus = txtStatus;
    }

    public TextView getTxtType() {
        return txtType;
    }

    public void setTxtType(TextView txtType) {
        this.txtType = txtType;
    }

    public TextView getTxtContent() {
        return txtContent;
    }

    public void setTxtContent(TextView txtContent) {
        this.txtContent = txtContent;
    }
}
