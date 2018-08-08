package com.ivonkhalif.ragnarok.kasir_online;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ivonkhalif.ragnarok.kasir_online.adapter.AdapterMeja;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelMeja;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PelayanActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton, floatingActionButtonKurang;
    private RecyclerView recyclerView;
    private AdapterMeja adaptermejameja;
    private List<ModelMeja> mejaList;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().getRoot();
    String key_nomor;
    int nomor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelayan);
        recyclerView = findViewById(R.id.recycler_Pelayan);
        floatingActionButton = findViewById(R.id.tambah);
        floatingActionButtonKurang = findViewById(R.id.kurang);

        mDatabase = FirebaseDatabase.getInstance().getReference("Nomor_Meja");
        loadDataMeja();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(PelayanActivity.this);
                final EditText input = new EditText(PelayanActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                alert.setView(input);

                alert.setMessage("Add Table").setTitle("Yakin untuk menambah meja?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int jumlah = adaptermejameja.getItemCount() + 1;

                                key_nomor = input.getText().toString();

                                mDatabase = FirebaseDatabase.getInstance().getReference().child("Nomor_Meja").child(String.valueOf(jumlah));

                                try {
                                    HashMap<String, Object> data = new HashMap();
                                    data.put("nomor", jumlah);
                                    data.put("key_nomor", key_nomor);

                                    mDatabase.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });
                                } catch (Exception e) {

                                }

                            }
                        });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alert.show();
            }
        });

        floatingActionButtonKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(PelayanActivity.this);

                alert.setMessage("Reduce Table").setTitle("Yakin untuk kurang meja?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final int jumlah = adaptermejameja.getItemCount();


                                mDatabase = FirebaseDatabase.getInstance().getReference().child("Nomor_Meja").child(String.valueOf(jumlah));

                                try {
                                    HashMap<String, Object> data = new HashMap<>();
                                    data.put("nomor", jumlah);
                                    data.put("key_nomor", key_nomor);

                                    mDatabase.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(PelayanActivity.this, "removed: " + jumlah, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } catch (Exception e) {

                                }

                            }
                        });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alert.show();
            }
        });

    }

    private void loadDataMeja() {
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("nomor_meja");
        final Query ascending = mDatabase.orderByChild("nomor");
        ascending.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mejaList = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Log.w(PelayanActivity.class.getSimpleName(), "dapet: " + nomor);

                    ModelMeja value = dataSnapshot1.getValue(ModelMeja.class);
                    ModelMeja fire = new ModelMeja();

                    nomor = value.getNomor();
                    String key_nomor = value.getKey_nomor();

                    fire.setKey_nomor(key_nomor);
                    fire.setNomor(nomor);

                    mejaList.add(fire);

                    adaptermejameja = new AdapterMeja(mejaList, PelayanActivity.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adaptermejameja);
                    adaptermejameja.notifyDataSetChanged();
                    recyclerView.setLayoutManager(new GridLayoutManager(PelayanActivity.this, 2));

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
