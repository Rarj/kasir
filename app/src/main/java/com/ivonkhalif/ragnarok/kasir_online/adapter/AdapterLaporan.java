package com.ivonkhalif.ragnarok.kasir_online.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivonkhalif.ragnarok.kasir_online.R;
import com.ivonkhalif.ragnarok.kasir_online.model.ModelLaporan;

import java.util.List;

/**
 * Created by ragnarok on 23/07/18.
 */

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.View_Holder>{
    List<ModelLaporan> modelLaporanList;
    Context context;

    public AdapterLaporan(List<ModelLaporan> modelLaporanList, Context context) {
        this.modelLaporanList = modelLaporanList;
        this.context = context;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_list_laporan, parent, false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final View_Holder holder, int position) {

        ModelLaporan modelLaporan= modelLaporanList.get(position);

        holder.totalhargaa.setText(Integer.valueOf(modelLaporan.getTotal_harga()));
        holder.cvlaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(holder.itemView.getContext(),)
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelLaporanList.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {

        TextView totalhargaa;
        CardView cvlaporan;

        public View_Holder(View itemView) {
            super(itemView);
            cvlaporan=itemView.findViewById(R.id.cardlapor);
            totalhargaa=itemView.findViewById(R.id.totalpayment);
        }
    }
}
