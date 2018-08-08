package com.ivonkhalif.ragnarok.kasir_online.model;

/**
 * Created by ragnarok on 10/07/18.
 */

public class ModelPesan {
    private int harga_paket, total_harga;
    private String detail_paket, nama_paket, gambar_paket, id_paket, key_nomor;

    public ModelPesan(int harga_paket, int total_harga, String detail_paket, String nama_paket, String gambar_paket, String id_paket, String key_nomor) {
        this.harga_paket = harga_paket;
        this.total_harga = total_harga;
        this.detail_paket = detail_paket;
        this.nama_paket = nama_paket;
        this.gambar_paket = gambar_paket;
        this.id_paket = id_paket;
        this.key_nomor = key_nomor;
    }

    public ModelPesan() {
    }

    public ModelPesan(String nama_paket, String gambar_paket){
        if (nama_paket.trim().equals("")){
            nama_paket = "No Name";
        }
    }

    public int getHarga_paket() {
        return harga_paket;
    }

    public void setHarga_paket(int harga_paket) {
        this.harga_paket = harga_paket;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }

    public String getDetail_paket() {
        return detail_paket;
    }

    public void setDetail_paket(String detail_paket) {
        this.detail_paket = detail_paket;
    }

    public String getNama_paket() {
        return nama_paket;
    }

    public void setNama_paket(String nama_paket) {
        this.nama_paket = nama_paket;
    }

    public String getGambar_paket() {
        return gambar_paket;
    }

    public void setGambar_paket(String gambar_paket) {
        this.gambar_paket = gambar_paket;
    }

    public String getId_paket() {
        return id_paket;
    }

    public void setId_paket(String id_paket) {
        this.id_paket = id_paket;
    }

    public String getKey_nomor() {
        return key_nomor;
    }

    public void setKey_nomor(String key_nomor) {
        this.key_nomor = key_nomor;
    }
    //    @Override
//    public String toString() {
//        return "ModelPesan{" +
//                "harga_paket='" + harga_paket + '\'' +
//                ", detail_paket='" + detail_paket + '\'' +
//                '}';
//    }
//
}