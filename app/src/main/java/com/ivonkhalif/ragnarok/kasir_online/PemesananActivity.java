package com.ivonkhalif.ragnarok.kasir_online;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ivonkhalif.ragnarok.kasir_online.adapter.AdapterPesan;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelPesan;

import java.util.ArrayList;
import java.util.List;

public class PemesananActivity extends AppCompatActivity {

    private Button buttonmakanan;
    private RecyclerView recyclerViewPesan;
    private AdapterPesan adapterPesan;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().getRoot();
    private List<ModelPesan> pesanList;
    private String nomor_pesanan, nama_paket, gambar_paket, detail_paket;
    private int harga_pesanan, harga_paket;
    //    private int nomot_pesanan, harga_paket;
    private String key_nomor;
    Context context;
    private TextView textViewTotal;

    public PemesananActivity() {
    }

    public String getKey_nomor() {
        return key_nomor = getIntent().getStringExtra("key");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        key_nomor = getIntent().getStringExtra("key");

        textViewTotal = findViewById(R.id.totalPay);

        recyclerViewPesan = findViewById(R.id.recycler_Pemesan);
//        buttonmakanan = findViewById(R.id.scanmakan);
//        Toast.makeText(this, "intExtra : " + key_nomor
//                , Toast.LENGTH_SHORT).show();

//        Intent intent = new Intent(PemesananActivity.this, AdapterPesan.class);
//        intent.putExtra("nomorkey", key_nomor);
//        startActivityForResult(intent, 12);

        mDatabase = FirebaseDatabase.getInstance().getReference("PESANAN");
        loadDataPesanan();

//
//        buttonmakanan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PemesananActivity.this, ScannerActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    //
    private void loadDataPesanan() {
        mDatabase = FirebaseDatabase.getInstance().getReference("PESANAN").child(key_nomor);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pesanList = new ArrayList<>();
                int total = 0;
                int count = 0;

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ModelPesan value = dataSnapshot1.getValue(ModelPesan.class);
                    ModelPesan fire = new ModelPesan();

//                    int totalBayar = value.getHarga_paket();
//                    total = total + totalBayar;
//                    count = count + 1;

//                    Toast.makeText(PemesananActivity.this, "total: " + total, Toast.LENGTH_SHORT).show();

                    nama_paket = value.getNama_paket();
                    detail_paket = value.getDetail_paket();
                    harga_paket = value.getHarga_paket();
                    gambar_paket = value.getGambar_paket();

                    total = total + harga_paket;
                    count = count + 1;

                    textViewTotal.setText("" + total);

                    fire.setDetail_paket(detail_paket);
                    fire.setNama_paket(nama_paket);
                    fire.setHarga_paket(harga_paket);
                    fire.setGambar_paket(gambar_paket);

//                    Toast.makeText(PemesananActivity.this, "bundle: " + dataSnapshot.getChildren(), Toast.LENGTH_SHORT).show();
                    pesanList.add(fire);

                    adapterPesan = new AdapterPesan(pesanList, PemesananActivity.this);
                    recyclerViewPesan.setHasFixedSize(true);
                    recyclerViewPesan.setLayoutManager(new LinearLayoutManager(PemesananActivity.this));
                    recyclerViewPesan.setAdapter(adapterPesan);
                    adapterPesan.notifyDataSetChanged();

                }

//                textViewTotal.setText(""+total);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        mDatabase = FirebaseDatabase.getInstance().getReference("PESANAN").child(key_nomor);
//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                pesanList = new ArrayList<>();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                    ModelPesan value = dataSnapshot1.getValue(ModelPesan.class);
//                    ModelPesan fire = new ModelPesan();
//
//                    Log.e(PemesananActivity.class.getSimpleName(), String.valueOf(dataSnapshot.getKey()));
//
//                    detail_paket = value != null ? value.getDetail_paket() : null;
//                    harga_pesanan = value.getHarga_paket();
//
//                    fire.setDetail_paket(detail_paket);
//                    fire.setHarga_paket(harga_pesanan);
//
//                    pesanList.add(fire);
//
//                    adapterPesan = new AdapterPesan(pesanList, PemesananActivity.this);
//                    recyclerViewPesan.setLayoutManager(new LinearLayoutManager(PemesananActivity.this));
//                    recyclerViewPesan.setHasFixedSize(true);
//                    recyclerViewPesan.setAdapter(adapterPesan);
//                    adapterPesan.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                databaseError.getMessage();
//            }
//        });
    }
}
