package com.stepme.uas_akb_if4_10120132.Model;

// NIM : 10120132
// NAMA : RESA KOMARA AKBARI
// KELAS : IF4

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

public class CatatanId {
    @Exclude
    public String CatatanId;

    public <T extends CatatanId> T withId(@NonNull final String id){
        this.CatatanId = id;
        return (T) this;
    }

}
