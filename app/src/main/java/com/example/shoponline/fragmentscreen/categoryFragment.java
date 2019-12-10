package com.example.shoponline.fragmentscreen;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoponline.R;
import com.example.shoponline.adapter.categoryAdapter;
import com.example.shoponline.interfac.ClickListener;
import com.example.shoponline.interfac.LoginView;
import com.example.shoponline.modelgson.LoaiSp;
import com.example.shoponline.modelgson.SanphamNew;
import com.example.shoponline.modelgson.SptheoDM;
import com.example.shoponline.presenter.DataPresenter;
import com.example.shoponline.utils.CheckConection;

import java.util.ArrayList;
import java.util.List;


public class categoryFragment extends Fragment implements ClickListener, LoginView {

    private ArrayList<LoaiSp> arrDanhmuc;
    RecyclerView recyclerView;
    private categoryAdapter adapter;
    private LoaiSp post;
    int id = 0;
    int iduser = 0;
    String username = "";
    String phone = "";
    String email = "";
    String location = "";
    private DataPresenter dataPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.rv_category);
        dataPresenter = new DataPresenter(this, getActivity());
        if (CheckConection.haveNetworkConnection(getContext())) {

        } else {
            CheckConection.Show_toast(getContext(), "kiểm tra mạng internet");
            DialogMessegerInternet();
        }
        arrDanhmuc = new ArrayList<>();
        adapter = new categoryAdapter(arrDanhmuc, getActivity(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        getDuLieu();
        Bundle bundle = getArguments();
        iduser = bundle.getInt("id");
        username = bundle.getString("name");
        phone = bundle.getString("phone");
        email = bundle.getString("email");
        location = bundle.getString("loation");
        return view;
    }

    private void getDuLieu() {
        dataPresenter.getLoaisp();
    }

    private void DialogMessegerInternet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thông Báo");
        builder.setMessage("Vui Lòng Kiểm Tra Kết Nối Internet!");
        builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public void onClick(int position) {
        post = new LoaiSp();
        post = arrDanhmuc.get(position);
        String namecate = post.getTenloaisp();
        int ID = post.getId();
        ChitietlDanhmucFragment fragment = new ChitietlDanhmucFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ID", ID);
        bundle.putInt("IDUs", iduser);
        bundle.putString("names", username);
        bundle.putString("phones", phone);
        bundle.putString("emails", email);
        bundle.putString("locations", location);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        getActivity().setTitle(namecate);
        transaction.commit();
    }

    @Override
    public boolean onLongClick(int position) {
        return false;
    }

    @Override
    public void onClick2(int position) {

    }


    @Override
    public void LoginFail() {

    }

    @Override
    public void LoginSucessfull() {

    }

    @Override
    public void NavigateHome() {

    }

    @Override
    public void Sucessfull(List<SanphamNew> sanphamNews) {

    }

    @Override
    public void SucessfullLoaisp(List<LoaiSp> loaiSps) {
        arrDanhmuc.addAll(loaiSps);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void Sucessfullsp(List<SptheoDM> sptheoDMS) {

    }
}
