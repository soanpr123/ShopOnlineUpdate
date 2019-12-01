package com.example.shoponline.fragmentscreen;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.shoponline.modelgson.LoaiSp;
import com.example.shoponline.modelgson.SanphamNew;
import com.example.shoponline.modelgson.SptheoDM;
import com.example.shoponline.presenter.DataPresenter;
import com.example.shoponline.interfac.ClickListener;
import com.example.shoponline.R;
import com.example.shoponline.adapter.sanphamnewAdapter;
import com.example.shoponline.interfac.LoginView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class TrangChinh extends Fragment implements ClickListener, LoginView {
    ViewFlipper viewFlipper;
    public static RecyclerView rv_spNew;
    private ArrayList<SanphamNew> arrSanpham;
    ArrayList<SanphamNew> sanphamNews;
    private SanphamNew post;
    public static sanphamnewAdapter adapter;
    int ID = 0;
    String tensp = "";
    Integer Giasp = 0;
    String Hinhanhsp = "";
    String motasp = "";
    int IDSP = 0;
    int id1s = 0;
    String usernamess = "";
    String phoness = "";
    String emailss = "";
    String locationss = "";
    int soluong = 0;
    private DataPresenter data;

    public TrangChinh() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chinh, container, false);
        data = new DataPresenter(this, getActivity());
        Bundle bundle = getArguments();
        id1s = bundle.getInt("idss");
        usernamess = bundle.getString("namess");
        phoness = bundle.getString("phoness");
        emailss = bundle.getString("emailss");
        locationss = bundle.getString("loationss");
        Toast.makeText(getContext(), "id : " + id1s + "\n" + "name" + usernamess + "\n" + "phone" + phoness + "\n" + "email" + emailss + "\n" + "vị trí " + locationss, Toast.LENGTH_LONG).show();
        viewFlipper = view.findViewById(R.id.vfber);
        rv_spNew = view.findViewById(R.id.rv_spnew);
        arrSanpham = new ArrayList<>();

        adapter = new sanphamnewAdapter(arrSanpham, getContext(), this);
        rv_spNew.setHasFixedSize(true);
        rv_spNew.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_spNew.setAdapter(adapter);
        ArrayList<String> quangcao = new ArrayList<>();
        quangcao.add("https://www.asus.com/websites/IN/products/YHbcnTAG8qt4B47T/ROG_STORE_PAGE/Mobile/1_KV.jpg");
        quangcao.add("https://image1.thegioitiepthi.vn/2019/03/19/chiec-dien-thoai-choi-game-khung-cua-xiaomi-black-shark-2-duoc-ban-tai-trung-quoc-vao-18-3.jpg");
        quangcao.add("https://www.sapo.vn/blog/wp-content/uploads/2014/10/banner-quang-cao-du-khach-hang-hieu-qua-3.jpg");
        quangcao.add("https://cellphones.com.vn/sforum/wp-content/uploads/2019/08/Microsoft-che-macbook-face.jpg");
        for (int i = 0; i < quangcao.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            Picasso.with(getContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slie = AnimationUtils.loadAnimation(getContext(), R.anim.slide_show);
        Animation animation_out = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out);
        viewFlipper.setInAnimation(animation_slie);
        viewFlipper.setOutAnimation(animation_out);
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.GETSANPHAMNEW, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if (response != null) {
//
//                    for (int i = 0; i < response.length(); i++) {
//                        try {
//                            JSONObject object = response.getJSONObject(i);
//                            ID = object.getInt("id");
//                            tensp = object.getString("tensanpham");
//                            Giasp = object.getInt("giasp");
//                            Hinhanhsp = object.getString("hinhanh");
//                            motasp = object.getString("motasp");
//                            IDSP = object.getInt("idsanpham");
//                            soluong=object.getInt("soluong");
//                            arrSanpham.add(new SanphamMD(ID, tensp, Giasp, motasp, Hinhanhsp, IDSP,soluong));
//                            adapter.notifyDataSetChanged();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                CheckConection.Show_toast(getContext(), error.toString());
//                Log.e("eror", error.toString());
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
//        DataClient dataClient= APIUtils.DataPresenter();
//        Call<List<SanphamNew>> listCall =dataClient.getAllSanpham();
//
//        listCall.enqueue(new Callback<List<SanphamNew>>() {
//            @Override
//            public void onResponse(Call<List<SanphamNew>> call, Response<List<SanphamNew>> response) {
//                if (response!=null){
//                    ArrayList<SanphamNew>sanphamNews= (ArrayList<SanphamNew>) response.body();
//                    for (int i=0;i<sanphamNews.size();i++){
//                        ID = sanphamNews.get(i).getId();
//                        tensp = sanphamNews.get(i).getTensanpham();
//                        Giasp = sanphamNews.get(i).getGiasp();
//                        Hinhanhsp = sanphamNews.get(i).getHinhanh();
//                        motasp = sanphamNews.get(i).getMotasp();
//                        IDSP = sanphamNews.get(i).getIdsanpham();
//                        soluong=sanphamNews.get(i).getSoluong();
//                        arrSanpham.add(new SanphamMD(ID, tensp, Giasp, motasp, Hinhanhsp, IDSP,soluong));
//                        adapter.notifyDataSetChanged();
//
//
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<SanphamNew>> call, Throwable t) {
//
//            }
//        });
//        DataClient dataClient = APIUtils.dataClient();
//        Call<List<SanphamNew>> listCall = dataClient.getAllSanpham();
//
//        listCall.enqueue(new Callback<List<SanphamNew>>() {
//            @Override
//            public void onResponse(Call<List<SanphamNew>> call, Response<List<SanphamNew>> response) {
//
//                if (response!=null) {
//                    sanphamNews = (ArrayList<SanphamNew>) response.body();
//                    for (int i = 0; i <sanphamNews.size(); i++) {
//                        ID = sanphamNews.get(i).getId();
//                            tensp = sanphamNews.get(i).getTensanpham();
//                            Giasp = sanphamNews.get(i).getGiasp();
//                            Hinhanhsp = sanphamNews.get(i).getHinhanh();
//                            motasp = sanphamNews.get(i).getMotasp();
//                            IDSP = sanphamNews.get(i).getIdsanpham();
//                            soluong=sanphamNews.get(i).getSoluong();
//                            arrSanpham.add(new SanphamNew(ID,tensp,Giasp,Hinhanhsp,motasp,IDSP,soluong));
//                            adapter.notifyDataSetChanged();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<SanphamNew>> call, Throwable t) {
//
//            }
//        });
        data.GetspNew();
        return view;
    }

    @Override
    public void onClick(int position) {
        post = new SanphamNew();
        post = arrSanpham.get(position);
        int ID = post.getId();
        int Giachitiet = post.getGiasp();
        String Tenchitiet = post.getTensanpham();
        String HinhChiTiet = post.getHinhanh();
        String Mota = post.getMotasp();
        int idsanoham = post.getIdsanpham();
        chitietHang fragment = new chitietHang();
        Bundle bundle = new Bundle();
        bundle.putInt("iduss", id1s);
        bundle.putInt("ID", ID);
        bundle.putInt("Gia", Giachitiet);
        bundle.putString("name", Tenchitiet);
        bundle.putString("Hinhanh", HinhChiTiet);
        bundle.putString("Mota", Mota);
        bundle.putInt("Idsanpham", idsanoham);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        getActivity().setTitle("Chi tiết sản phẩm ");
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
    public void Sucessfull(List<SanphamNew> arrSanphams) {

            arrSanpham.addAll(arrSanphams);
adapter.notifyDataSetChanged();
    }

    @Override
    public void SucessfullLoaisp(List<LoaiSp> loaiSps) {

    }

    @Override
    public void Sucessfullsp(List<SptheoDM> sptheoDMS) {

    }


}
