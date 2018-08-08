package com.ivonkhalif.ragnarok.kasir_online.adapter;

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

import com.bumptech.glide.Glide;
import com.ivonkhalif.ragnarok.kasir_online.DetailActivity;
import com.ivonkhalif.ragnarok.kasir_online.R;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelPesan;

import java.util.List;

/**
 * Created by ragnarok on 30/07/18.
 */

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.View_Holder> {
    List<ModelPesan> modelMenu;
    Context context;

    public AdapterMenu(List<ModelPesan> modelMenu, Context context) {
        this.modelMenu = modelMenu;
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
        final ModelPesan modelmenu = modelMenu.get(position);

        holder.id_paket.setText(modelmenu.getId_paket());
        holder.nama_paket.setText(modelmenu.getNama_paket());
        holder.isipaket.setText(modelmenu.getDetail_paket());
        holder.harga.setText(String.valueOf(modelmenu.getHarga_paket()));
        Glide.with(context).load(modelmenu.getGambar_paket()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);

                intent.putExtra("id_paket", modelmenu.getId_paket());
                intent.putExtra("gambar_paket",modelmenu.getGambar_paket());
                intent.putExtra("nama_paket",modelmenu.getNama_paket());
                intent.putExtra("detail_paket", modelmenu.getDetail_paket());
                intent.putExtra("harga_paket", modelmenu.getHarga_paket());

                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelMenu.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {
        TextView nama_paket, isipaket, harga, id_paket;
        ImageView imageView;
        CardView cardView;

        public View_Holder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardmenu);
            nama_paket = itemView.findViewById(R.id.menu);
            isipaket = itemView.findViewById(R.id.detail);
            harga = itemView.findViewById(R.id.hargamenu);
            imageView = itemView.findViewById(R.id.gambar);
        }
    }
}
