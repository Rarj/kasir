package com.ivonkhalif.ragnarok.kasir_online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ivonkhalif.ragnarok.kasir_online.adapter.AdapterMenu;
import com.ivonkhalif.ragnarok.kasir_online.adapter.AdapterPesan;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelPesan;

import java.util.ArrayList;
import java.util.List;

public class DaftarMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterMenu adapterMenuPesan;
    private List<ModelPesan> scannerList;
    private int harga_paket;
    private String id_paket, nama_paket, detail_paket, gambar_paket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_menu);
        recyclerView = findViewById(R.id.recycler_menu);

        loadDataPesanan();
    }

    private void loadDataPesanan() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Nama_Pesanan");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                scannerList = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    ModelPesan value = dataSnapshot1.getValue(ModelPesan.class);
                    ModelPesan fire = new ModelPesan();

                    id_paket = value != null ? value.getId_paket() : null;
                    nama_paket = value != null ? value.getNama_paket() : null;
                    detail_paket = value != null ? value.getDetail_paket() : null;
                    harga_paket = value != null ? value.getHarga_paket() : 0;
                    gambar_paket = value != null ? value.getGambar_paket() : null;

                    fire.setNama_paket(nama_paket);
                    fire.setDetail_paket(detail_paket);
                    fire.setId_paket(id_paket);
                    fire.setGambar_paket(gambar_paket);
                    fire.setHarga_paket(harga_paket);

                    scannerList.add(fire);

                    adapterMenuPesan = new AdapterMenu(scannerList, DaftarMenuActivity.this)    ;
                    recyclerView.setLayoutManager(new LinearLayoutManager(DaftarMenuActivity.this));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapterMenuPesan);
                    adapterMenuPesan.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
