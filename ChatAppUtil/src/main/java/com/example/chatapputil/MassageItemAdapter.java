package com.example.chatapputil;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MassageItemAdapter extends RecyclerView.Adapter<MassageItemAdapter.MassageViewHolder> {

    private List<MassageCLass> itemList;
    private Context context;
    String CurrentUserID;

    public MassageItemAdapter(List<MassageCLass> List, Context context,String CurrentUserID){
        this.itemList = List;
        this.context = context;
        this.CurrentUserID = CurrentUserID;
    }

    public class MassageViewHolder extends RecyclerView.ViewHolder{

        TextView textView_userName, textView_textBody;
        MassageCLass massageCLass;
        ConstraintLayout constraintLayout_massageBody;
        public MassageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_textBody = itemView.findViewById(R.id.text_body);
            textView_userName = itemView.findViewById(R.id.user_name);
            constraintLayout_massageBody = itemView.findViewById(R.id.massageBody);
        }
        public MassageCLass getMassageCLass(){return massageCLass;}

        public void setMassageCLass(MassageCLass massageCLass) {
            this.massageCLass = massageCLass;
        }
    }
    @NonNull
    @Override
    public MassageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_field,parent,false);
        return new MassageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MassageItemAdapter.MassageViewHolder holder, int position) {
        MassageCLass massageCLass = itemList.get(position);
        holder.textView_userName.setText(massageCLass.getUserName());
        if(massageCLass.getUserID() == CurrentUserID){
            holder.constraintLayout_massageBody.setBackgroundColor(Color.GRAY);
        }
        holder.textView_textBody.setText(massageCLass.getText());
        holder.setMassageCLass(massageCLass);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
