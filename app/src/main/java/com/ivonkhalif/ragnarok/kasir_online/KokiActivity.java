package com.ivonkhalif.ragnarok.kasir_online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ivonkhalif.ragnarok.kasir_online.adapter.AdapterMeja;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelMeja;

import java.util.ArrayList;
import java.util.List;

public class KokiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterMeja adaptermejameja;
    private List<ModelMeja> mejaList;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().getRoot();
    int nomor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koki);
        recyclerView = findViewById(R.id.recycler_Pelayan);

        mDatabase = FirebaseDatabase.getInstance().getReference("Nomor_Meja");
        loadDataMeja();

    }

    private void loadDataMeja() {
        final Query ascending = mDatabase.orderByChild("nomor");
        ascending.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mejaList = new ArrayList<>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Log.w(PelayanActivity.class.getSimpleName(), "dapet: "+nomor);

                    ModelMeja value = dataSnapshot1.getValue(ModelMeja.class);
                    ModelMeja fire = new ModelMeja();


                    mejaList.add(fire);

                    adaptermejameja = new AdapterMeja(mejaList, KokiActivity.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(KokiActivity.this, 2));
                    recyclerView.setAdapter(adaptermejameja);
                    adaptermejameja.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
