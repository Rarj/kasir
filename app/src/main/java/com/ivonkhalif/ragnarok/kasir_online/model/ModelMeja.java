package com.ivonkhalif.ragnarok.kasir_online.model;

/**
 * Created by ragnarok on 03/07/18.
 */

public class ModelMeja {
    int harga_paket, nomor;
    private String detail_paket, key_nomor, id_paket ;

    public ModelMeja(int harga_paket, int nomor, String detail_paket, String key_nomor, String id_paket) {
        this.harga_paket = harga_paket;
        this.nomor = nomor;
        this.detail_paket = detail_paket;
        this.key_nomor = key_nomor;
        this.id_paket = id_paket;
    }

    public ModelMeja() {
    }

    public int getHarga_paket() {
        return harga_paket;
    }

    public void setHarga_paket(int harga_paket) {
        this.harga_paket = harga_paket;
    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    public String getDetail_paket() {
        return detail_paket;
    }

    public void setDetail_paket(String detail_paket) {
        this.detail_paket = detail_paket;
    }

    public String getKey_nomor() {
        return key_nomor;
    }

    public void setKey_nomor(String key_nomor) {
        this.key_nomor = key_nomor;
    }

    public String getId_paket() {
        return id_paket;
    }

    public void setId_paket(String id_paket) {
        this.id_paket = id_paket;
    }
}
