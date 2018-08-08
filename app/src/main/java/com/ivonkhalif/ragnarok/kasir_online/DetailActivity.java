package com.ivonkhalif.ragnarok.kasir_online;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class DetailActivity extends AppCompatActivity {
    ImageView imageView, imageGambar;
    TextView tv, tv2, tv3;
    Button button;
    private String id, nama, detail;
    private int harga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView= findViewById(R.id.pict);
        button= findViewById(R.id.buttongen);
        tv= findViewById(R.id.namamakanan);
        tv2= findViewById(R.id.detailmakanan);
        tv3= findViewById(R.id.hargamakanan);
        imageGambar= findViewById(R.id.gambarmakanan);

        id = getIntent().getStringExtra("id_paket");
        nama = getIntent().getStringExtra("nama_paket");
        detail = getIntent().getStringExtra("detail_paket");
        harga = getIntent().getIntExtra("harga_paket", 0);


        String gmbr = getIntent().getStringExtra("gambar_paket");

        tv.setText(nama);
        tv2.setText(detail);
        tv3.setText(harga+"");
        Glide.with(DetailActivity.this).load(gmbr).into(imageGambar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = id;
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    imageView.setImageBitmap(bitmap);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
