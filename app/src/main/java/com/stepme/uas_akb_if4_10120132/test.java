package com.stepme.uas_akb_if4_10120132;

// NIM : 10120132
// NAMA : RESA KOMARA AKBARI
// KELAS : IF4

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class test extends AppCompatActivity {

    private EditText judul, catatan;
    private Button btn;
    private DatabaseReference ref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        judul = findViewById(R.id.judul);
        catatan = findViewById(R.id.catatan);
        btn = findViewById(R.id.btn);

        ref = FirebaseDatabase.getInstance().getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getJudul, getCatatan;
                getJudul = judul.getText().toString();
                getCatatan = catatan.getText().toString();

                ref.child("judul").setValue(getJudul).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(test.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(test.this, "FAILED", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//                Map<String, Object> map = new HashMap<>();
//                map.put("judul", getJudul);
//                map.put("catatan", getCatatan);
//
//                ref.child("data").setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(test.this, "SUCCESS", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(test.this, "FAILED", Toast.LENGTH_SHORT).show();
//                    }
//                });

            }
        });

    }
}
