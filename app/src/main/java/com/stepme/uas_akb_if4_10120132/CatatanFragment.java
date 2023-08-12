package com.stepme.uas_akb_if4_10120132;

// NIM : 10120132
// NAMA : RESA KOMARA AKBARI
// KELAS : IF4

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stepme.uas_akb_if4_10120132.Adapter.CatatanAdapter;
import com.stepme.uas_akb_if4_10120132.Model.CatatanModel;

import java.util.ArrayList;

public class CatatanFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton btnFloatAdd;
    private DatabaseReference ref;
    private CatatanAdapter adapter;
    private ArrayList<CatatanModel> mList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catatan, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        btnFloatAdd = view.findViewById(R.id.btnFloatAdd);

        ref = FirebaseDatabase.getInstance().getReference("data");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        btnFloatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewCatatan.newInstance().show(getActivity().getSupportFragmentManager(), AddNewCatatan.TAG);
            }
        });

        mList = new ArrayList<>();
        recyclerView.setLayoutManager((new LinearLayoutManager(getContext())));

        adapter = new CatatanAdapter(getContext(), mList);

        recyclerView.setAdapter(adapter);
        showData();

        return view;
    }

    private void showData(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    CatatanModel catatanModel = dataSnapshot.getValue(CatatanModel.class);
                    String key = dataSnapshot.getKey();
                    mList.add(catatanModel);
                    catatanModel.setKey(key);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}