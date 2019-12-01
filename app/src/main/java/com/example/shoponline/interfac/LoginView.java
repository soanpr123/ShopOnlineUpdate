package com.example.shoponline.interfac;

import com.example.shoponline.modelgson.LoaiSp;
import com.example.shoponline.modelgson.SanphamNew;
import com.example.shoponline.modelgson.SptheoDM;

import java.util.List;

public interface LoginView {
    void LoginFail();
    void LoginSucessfull();
    void NavigateHome();
    void Sucessfull(List<SanphamNew> sanphamNews);
    void SucessfullLoaisp(List<LoaiSp> loaiSps);
    void Sucessfullsp(List<SptheoDM> sptheoDMS);
}
