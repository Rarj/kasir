package com.ivonkhalif.ragnarok.kasir_online;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelPesan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PenampungActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().getRoot();
    DatabaseReference db = FirebaseDatabase.getInstance().getReference().getRoot();

    String result, detail_paket, nama_paket, gambar_paket;
    int harga_paket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penampung);

        result = getIntent().getStringExtra("result");
        Toast.makeText(this, "dapeettttttt: " + result, Toast.LENGTH_SHORT).show();

        databaseReference = FirebaseDatabase.getInstance().getReference("Nama_Pesanan").child(String.valueOf(result));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ModelPesan value = dataSnapshot.getValue(ModelPesan.class);

//                Toast.makeText(PenampungActivity.this, "getChilcrenCount" + dataSnapshot.getChildren(), Toast.LENGTH_SHORT).show();

//                detail_paket = value.getDetail_paket();
//                nama_paket = value.getNama_paket();
//                gambar_paket = value.getGambar_paket();
//                harga_paket = value.getHarga_paket();

                db = FirebaseDatabase.getInstance().getReference("PESANAN").child(result);
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("detail_paket", detail_paket);
                            hashMap.put("nama_paket", nama_paket);
                            hashMap.put("gambar_paket", gambar_paket);
                            hashMap.put("harga_paket", String.valueOf(harga_paket));

                            db.setValue(hashMap);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
