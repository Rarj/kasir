package com.ivonkhalif.ragnarok.kasir_online.model;

/**
 * Created by ragnarok on 23/07/18.
 */

public class ModelLaporan {
    int total_harga;

    public ModelLaporan(int total_harga) {
        this.total_harga = total_harga;
    }

    public ModelLaporan() {
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }
}
