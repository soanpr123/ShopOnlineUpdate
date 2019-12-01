package com.example.shoponline.modelgson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SanphamNew {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tensanpham")
    @Expose
    private String tensanpham;
    @SerializedName("giasp")
    @Expose
    private int giasp;
    @SerializedName("hinhanh")
    @Expose
    private String hinhanh;
    @SerializedName("motasp")
    @Expose
    private String motasp;
    @SerializedName("idsanpham")
    @Expose
    private int idsanpham;
    @SerializedName("soluong")
    @Expose
    private int soluong;

    public SanphamNew(int id, String tensanpham, int giasp, String hinhanh, String motasp, int idsanpham, int soluong) {
        this.id = id;
        this.tensanpham = tensanpham;
        this.giasp = giasp;
        this.hinhanh = hinhanh;
        this.motasp = motasp;
        this.idsanpham = idsanpham;
        this.soluong = soluong;
    }

    public SanphamNew() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }



    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMotasp() {
        return motasp;
    }

    public void setMotasp(String motasp) {
        this.motasp = motasp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}