package com.example.shoponline.modelgson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoaiSp {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tenloaisp")
    @Expose
    private String tenloaisp;
    @SerializedName("hinh")
    @Expose
    private String hinh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloaisp() {
        return tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        this.tenloaisp = tenloaisp;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

}