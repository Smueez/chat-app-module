package com.example.chatapputil;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatHome extends AppCompatActivity {
    RecyclerView recyclerView_massages;
    MassageItemAdapter massageItemAdapter;
    List<MassageCLass> massageCLassesList = new ArrayList<>();
    DatabaseReference databaseReference;
    String MassageID,UserName,UserID,AppPackageName;
    EditText editText_massage;
    ImageButton imageButton_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_home);
        try {
            UserID = getIntent().getExtras().getString("UserID");
            UserName = getIntent().getExtras().getString("UserName");
            AppPackageName = getIntent().getExtras().getString("AppPackageName");
            MassageID = getIntent().getExtras().getString("MassageID");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        recyclerView_massages = findViewById(R.id.massages);
        massageItemAdapter = new MassageItemAdapter(massageCLassesList,getApplicationContext(),UserID);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,true);
        recyclerView_massages.setLayoutManager(linearLayoutManager);
        recyclerView_massages.setItemAnimator(new DefaultItemAnimator());
        recyclerView_massages.setAdapter(massageItemAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        if(!MassageID.isEmpty()) {

            databaseReference.child(MassageID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    massageCLassesList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        MassageCLass massageCLass = ds.getValue(MassageCLass.class);
                        massageCLassesList.add(massageCLass);
                        massageItemAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else {
            massageCLassesList.clear();
        }
        editText_massage = findViewById(R.id.editTextTextMultiLine);
        imageButton_send = findViewById(R.id.imageButton);
    }

    public void sendMassage(View view){
        String text;
        text = editText_massage.getText().toString();
        if(text.isEmpty()){
            imageButton_send.setClickable(false);
        }
        else {
            MassageCLass massageCLass = new MassageCLass(text,UserName,UserID);
            if(MassageID.isEmpty()){
                MassageID =  databaseReference.push().getKey();
                //databaseReference.child(MassageID).push().setValue(massageCLass);
            }

            databaseReference.child(MassageID).push().setValue(massageCLass);


        }
    }
    public void GoBack(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            Intent myIntent = new Intent(this,Class.forName(AppPackageName));
            myIntent.putExtra("AppPackageName",AppPackageName);
            startActivity(myIntent );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}