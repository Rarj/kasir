package com.ivonkhalif.ragnarok.kasir_online.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ivonkhalif.ragnarok.kasir_online.PemesananActivity;
import com.ivonkhalif.ragnarok.kasir_online.R;
import com.ivonkhalif.ragnarok.kasir_online.ScannerActivity;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelMeja;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelPesan;

import java.util.HashMap;
import java.util.List;

import edu.counterview.CounterListner;
import edu.counterview.CounterView;

/**
 * Created by ragnarok on 10/07/18.
 */

public class AdapterPesan extends RecyclerView.Adapter<AdapterPesan.View_Holder> {
    List<ModelPesan> modelPesanList;
    List<ModelMeja> modelMejaList;
    Context context;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().getRoot();

    public AdapterPesan(List<ModelPesan> modelPesanList, Context context) {
        this.modelPesanList = modelPesanList;
        this.context = context;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_pesanan, parent, false);

        return new View_Holder(view);
    }

    @SuppressLint({"SetTextI18n", "ResourceType"})
    @Override
    public void onBindViewHolder(@NonNull final View_Holder holder, int position) {
        final ModelPesan modelPesan = modelPesanList.get(position);
        final String nama_paket = modelPesan.getNama_paket();
        final String detail_paket = modelPesan.getDetail_paket();
        final PemesananActivity pemesananActivity = new PemesananActivity();

//        holder.nomor_pesanan.setText(""+modelPesan.getNomor_pesanan());
        holder.nama_paket.setText(modelPesan.getNama_paket());
//        holder.harga_pesanan.setText(modelPesan.getHarga_paket());
        holder.detail_paket.setText(modelPesan.getDetail_paket());
        holder.harga_paket.setText("" + modelPesan.getHarga_paket());
        Glide.with(context).load(modelPesan.getGambar_paket()).into(holder.imageView);

        holder.cv.setCounterListener(new CounterListner() {

            @Override
            public void onIncClick(String s) {

//                int a = Integer.parseInt(s);
//
//                final int asdw = a * modelPesan.getHarga_paket();
//                holder.harga_paket.setText("" + asdw);

                Toast.makeText(context, "dapet key "+pemesananActivity.getKey_nomor(), Toast.LENGTH_SHORT).show();

//                mDatabase = FirebaseDatabase.getInstance().getReference("PESANAN").child();
//                mDatabase.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        final HashMap<String, Object> data = new HashMap<>();
//
//                        data.put("detail_paket", detail_paket);
//                        data.put("harga_paket", asdw);
//                        data.put("nama_paket", nama_paket);
//
//                        mDatabase.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(context, "updated!", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

//                ModelPesan harga = new ModelPesan();
//                String id = harga.getId_paket();

//                Toast.makeText(context, "id paket" + asdwdw, Toast.LENGTH_SHORT).show();

//                mDatabase = FirebaseDatabase.getInstance().getReference("Nama_Pesanan").child(modelPesan.getId_paket());

//                mDatabase.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        final ModelPesan modelPesan = dataSnapshot.getValue(ModelPesan.class);
//                        db = FirebaseDatabase.getInstance().getReference("PESANAN").child(modelPesan.getKey_nomor()).child(modelPesan.getId_paket());

//                        final HashMap<String, Object> data = new HashMap<>();

//                        data.put("detail_paket", modelPesan.getDetail_paket());
//                        data.put("harga_paket", modelPesan.getHarga_paket());
//                        data.put("nama_paket", modelPesan.getNama_paket());
//                        data.put("gambar_paket", modelPesan.getGambar_paket());
//
//                        db.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(context, "Dapet Id "+modelPesan.getId_paket(), Toast.LENGTH_LONG).show();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

//                mDatabase.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        ModelPesan modelPesan = dataSnapshot.getValue(ModelPesan.class);
//
//
//                        HashMap<String, Object> data = new HashMap<>();
//                        data.put("detail_paket", modelPesan.getDetail_paket());
//                        data.put("harga_paket", asdw);
//                        data.put("nama_paket", modelPesan.getNama_paket());
//                        data.put("gambar_paket", modelPesan.getGambar_paket());
//                        mDatabase.setValue(data);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
                //Toast.makeText(context, "counter: "+asdw , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDecClick(String s) {

                int asd = Integer.parseInt(holder.harga_paket.getText().toString()) - modelPesan.getHarga_paket();

                //Toast.makeText(context, "kurang: "+asd, Toast.LENGTH_SHORT).show();

                if (asd <= modelPesan.getHarga_paket()) {
                    holder.harga_paket.setText("" + modelPesan.getHarga_paket());
                } else {
                    holder.harga_paket.setText("" + asd);
                }

            }
        });

        holder.cardViewPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelPesanList.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {

        CardView cardViewPesan;
        ImageView imageView;
        TextView detail_paket, harga_pesanan, nama_paket, harga_paket;
        CounterView cv;

        public View_Holder(View itemView) {
            super(itemView);
            cardViewPesan = itemView.findViewById(R.id.cardpesanan);
            detail_paket = itemView.findViewById(R.id.detail);
//            harga_pesanan = itemView.findViewById(R.id.hargamenu);
            nama_paket = itemView.findViewById(R.id.menu);
            harga_paket = itemView.findViewById(R.id.hargamenu);
            imageView = itemView.findViewById(R.id.picpesan);
            cv = itemView.findViewById(R.id.cv);

        }
    }
}
