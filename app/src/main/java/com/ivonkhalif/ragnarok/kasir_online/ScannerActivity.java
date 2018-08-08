package com.ivonkhalif.ragnarok.kasir_online;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.ivonkhalif.ragnarok.kasir_online.adapter.AdapterMeja;
import com.ivonkhalif.ragnarok.kasir_online.adapter.AdapterPesan;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelMeja;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelPesan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    TextView textViewPesanan;
    private static final String TAG = ScannerActivity.class.getSimpleName();
    private ZXingScannerView mScannerView;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().getRoot();
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().getRoot();
    String detail_paket, nama_paket, gambar_paket, key_nomor, id_paket;
    int harga_paket, nomor_meja;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scanner);
        mScannerView = findViewById(R.id.zxscan);   // Programmatically initialize the scanner view
//        setContentView(mScannerView);                // Set the scanner view as the content view
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        key_nomor = getIntent().getStringExtra("nomor_meja");
        nomor_meja = getIntent().getIntExtra("nomor", 0);
//        id_paket = getIntent().getStringExtra("id_paket");

//        textViewPesanan = findViewById(R.id.btndaftarpesan);
//
//        textViewPesanan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final AlertDialog.Builder alert = new AlertDialog.Builder(ScannerActivity.this);
//
//                alert.setMessage("Menuju Daftar Pesanan").setTitle("Sudah selesai?").setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(ScannerActivity.this, PemesananActivity.class);
//                        startActivity(intent);
//                    }
//                    });
//            }
//
//
////                return false;
//
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int Id = item.getItemId();

        if (Id == R.id.action_pemesanan){

            final AlertDialog.Builder alert = new AlertDialog.Builder(ScannerActivity.this);
            alert.setMessage("Menuju Daftar Pesanan").setTitle("Sudah selesai?").setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent inten = new Intent(ScannerActivity.this, PemesananActivity.class);
                    inten.putExtra("key", key_nomor);
                    startActivity(inten);
                }
            }).setNegativeButton("Belum", null).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(final Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

//        Intent intent = new Intent(this, PemesananActivity.class);
//        intent.putExtra("result", rawResult.getText());
        Toast.makeText(this, "raw result : "+rawResult, Toast.LENGTH_SHORT).show();
//        startActivity(intent);
//        finish();

        mDatabase = FirebaseDatabase.getInstance().getReference("Nama_Pesanan").child(rawResult.getText());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ModelPesan modelPesan = dataSnapshot.getValue(ModelPesan.class);

                Toast.makeText(ScannerActivity.this, "Nama pesanan: "+dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();

                db = FirebaseDatabase.getInstance().getReference("PESANAN").child(key_nomor).child(modelPesan.getId_paket());

                final HashMap<String, Object> data = new HashMap<>();

                data.put("detail_paket", modelPesan.getDetail_paket());
                data.put("harga_paket", modelPesan.getHarga_paket());
                data.put("nama_paket", modelPesan.getNama_paket());
                data.put("gambar_paket", modelPesan.getGambar_paket());

                db.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ScannerActivity.this, "Dapet Id "+modelPesan.getId_paket(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        mDatabase = FirebaseDatabase.getInstance().getReference("Nama_Pesanan").child(String.valueOf(rawResult.getText()));
//
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                ModelPesan value = dataSnapshot.getValue(ModelPesan.class);
//
//                detail_paket = value.getDetail_paket();
//                harga_paket = value.getHarga_paket();
//                nama_paket = value.getNama_paket();
//                gambar_paket = value.getGambar_paket();
//
//                db = FirebaseDatabase.getInstance().getReference("PESANAN").child(key_nomor);
//
//                final HashMap<String, Object> data =  new HashMap<>();
//                data.put("detail_paket", detail_paket);
//                data.put("harga_paket", harga_paket);
//                data.put("nama_paket", nama_paket);
//                data.put("gambar_paket", gambar_paket);
//                data.put("nomor", nomor_meja);
//
//                db.child(String.valueOf(nomor_meja)).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(ScannerActivity.this, "berhasil input pesanan: "+data, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                Toast.makeText(ScannerActivity.this, "dtail paket: "+detail_paket, Toast.LENGTH_SHORT).show();
//
////                db = FirebaseDatabase.getInstance().getReference("PESANAN").child(key_nomor);
////                try {
////                    final HashMap<String, Object> data =  new HashMap<>();
////                    data.put("detail", detail_paket);
//////                    data.put("harga_paket", harga_paket);
//////                    data.put("nama_paket", nama_paket);
//////                    data.put("gambar_paket", gambar_paket);
//////                    data.put("nomor", nomor_meja);
////
////                    db.child(String.valueOf(nomor_meja)).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
////                        @Override
////                        public void onComplete(@NonNull Task<Void> task) {
////                            Toast.makeText(ScannerActivity.this, "berhasil input pesanan: "+data, Toast.LENGTH_SHORT).show();
////                        }
////                    });
////                } catch (Exception e){
////                    e.printStackTrace();
////                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        Intent intent = new Intent(ScannerActivity.this, PemesananActivity.class);
//        intent.putExtra("result", rawResult.getText());
//        startActivity(intent);
//        finish();

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
//
//    @Override
//    public void handleResult(final Result result) {
//        Log.v("Scanner", result.getText());
//        Log.v(TAG, String.valueOf(result.getBarcodeFormat()));
//
//        Toast.makeText(this, "resulScanner: "+result, Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(ScannerActivity.this, PemesananActivity.class);
//        intent.putExtra("result", String.valueOf(result));
//        startActivity(intent);
//        finish();
////        mDatabase = FirebaseDatabase.getInstance().getReference("Nama_Pesanan").child(String.valueOf(result));
////
////        mDatabase.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                ModelPesan value = dataSnapshot.getValue(ModelPesan.class);
////
////                detail_paket = value.getDetail_paket();
////
////                Toast.makeText(ScannerActivity.this, "value: " +detail_paket, Toast.LENGTH_SHORT).show();
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
////        mDatabase.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////
////                ModelPesan value = dataSnapshot.getValue(ModelPesan.class);
////
////                detail_paket = value.getDetail_paket();
////
////                Toast.makeText(ScannerActivity.this, "detail paket: " + detail_paket, Toast.LENGTH_SHORT).show();
//////                try {
//////                    HashMap<String, Result> data= new HashMap<>();
//////                    data.put("id_paket", result);
//////                    data.put("nama_paket",result);
//////                    data.put("detail_paket", result);
//////                    data.put("gambar_paket", result);
//////                    data.put("harga_paket", result);
//////
//////
//////                } catch (Exception e){
//////                    e.printStackTrace();
//////                }
////
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
////        Intent intent = new Intent(ScannerActivity.this, PemesananActivity.class);
////        intent.putExtra("hasil", String.valueOf(result));
//
////        if (result)
//
////        startActivity(intent);
//        scannerView.resumeCameraPreview(this);
//    }

}
