package com.ivonkhalif.ragnarok.kasir_online.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ivonkhalif.ragnarok.kasir_online.PemesananActivity;
import com.ivonkhalif.ragnarok.kasir_online.R;
import com.ivonkhalif.ragnarok.kasir_online.ScannerActivity;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelMeja;

import java.util.List;

/**
 * Created by ragnarok on 03/07/18.
 */

public class AdapterMeja extends RecyclerView.Adapter<AdapterMeja.View_Holder> {
    List<ModelMeja> modelMejaList;
    Context context;

    public AdapterMeja(List<ModelMeja> modelMejaList, Context context) {
        this.modelMejaList = modelMejaList;
        this.context = context;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);

        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final View_Holder holder, int position) {

        final ModelMeja modelMeja = modelMejaList.get(position);

        holder.nomormeja.setText(String.valueOf(modelMeja.getNomor()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ScannerActivity.class);
                intent.putExtra("nomor_meja", modelMeja.getKey_nomor());
                intent.putExtra("nomor", modelMeja.getNomor());
//                intent.putExtra("id_paket", modelMeja.getId_paket());
//                intent1.putExtra("nomor_meja", modelMeja.getKey_nomor());
//                intent1.putExtra("nomor", modelMeja.getNomor());
                holder.itemView.getContext().startActivity(intent);

            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "long klik", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {

        if (modelMejaList == null) {
            return 0;
        } else {
            return modelMejaList.size();
        }
//        return modelMejaList.size();

    }

    public class View_Holder extends RecyclerView.ViewHolder {

        TextView nomormeja;
        CardView cardView;

        public View_Holder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card);
            nomormeja = itemView.findViewById(R.id.nomorMeja);
        }
    }
}
