package com.example.shoponline.fragmentscreen;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoponline.Retrofit2.APIUtils;
import com.example.shoponline.Retrofit2.DataClient;
import com.example.shoponline.interfac.ClickListener;
import com.example.shoponline.R;
import com.example.shoponline.adapter.itemDetailAdapter;
import com.example.shoponline.interfac.LoginView;
import com.example.shoponline.model.SanphamMD;
import com.example.shoponline.modelgson.LoaiSp;
import com.example.shoponline.modelgson.SanphamNew;
import com.example.shoponline.modelgson.SptheoDM;
import com.example.shoponline.presenter.DataPresenter;
import com.example.shoponline.utils.CheckConection;
import com.example.shoponline.utils.Sever;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChitietlDanhmucFragment extends Fragment implements ClickListener, LoginView {
    private itemDetailAdapter itemDetailAdapter;
    public static ArrayList<SptheoDM> mdArrayList;
    private RecyclerView recyclerView;
    private SptheoDM post;
    DataPresenter dataPresenter;
    int idd = 0;
    int page = 1;
    int idsp = 0;
    String tensp = "";
    Integer Giasp = 0;
    String Hinhanhsp = "";
    String motasp = "";
    int IDSP = 0;

    GridLayoutManager manager;

    int currentItems, totalItems, scrollOutItems;
    private ProgressBar progressBar;
    boolean limitData = false;
    int idusers = 0;
    String usernames = "";
    String phones = "";
    String emails = "";
    String locations = "";
    int soluong = 0;
    private boolean limitdt = false;
    private mHander mHander;
    FloatingActionButton floatingActionButton;
    private boolean isloading = false;

View view2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_danhmuc, container, false);
        recyclerView = view.findViewById(R.id.rv_spham);
        floatingActionButton = view.findViewById(R.id.fab_tim);
        dataPresenter = new DataPresenter(this, getContext());
        progressBar = view.findViewById(R.id.progress);
        mdArrayList = new ArrayList<>();
        mHander = new mHander();
        itemDetailAdapter = new itemDetailAdapter(mdArrayList, getContext(), this);
        manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(itemDetailAdapter);
//        view = inflater.inflate(R.layout.progressbar, null);
        final Bundle bundle = getArguments();
        idd = bundle.getInt("ID");
        idusers = bundle.getInt("IDUs");
        usernames = bundle.getString("names");
        phones = bundle.getString("phones");
        emails = bundle.getString("emails");
        locations = bundle.getString("locations");
        Toast.makeText(getContext(), "id : " + idd + "\n" + "name" + usernames + "\n" + "phone" + phones + "\n" + "email" + emails + "\n" + "vị trí " + locations, Toast.LENGTH_LONG).show();
        GetData(idd, page);
        Loadmore();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timkiem timkiem = new Timkiem();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("iduser", idusers);
                timkiem.setArguments(bundle1);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, timkiem);
                getActivity().setTitle("Tìm Kiếm Sản phẩm ");
                transaction.commit();

            }
        });
        return view;

    }

    private void Loadmore() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isloading = false;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();
                if (limitData == false && isloading == false && (currentItems + scrollOutItems == totalItems)) {
                    isloading = true;
                    ThrarDAta thrarDAta = new ThrarDAta();
                    thrarDAta.start();
                }
            }
        });

    }

    public class mHander extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:

                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case 1:

                    dataPresenter.getTheodm(idd, ++page);
                    isloading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThrarDAta extends Thread {
        @Override
        public void run() {
            mHander.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message = mHander.obtainMessage(1);
            mHander.sendMessage(message);
            super.run();
        }
    }
//    private void LoadmorData() {

    //     recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//         @Override
//         public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//             super.onScrollStateChanged(recyclerView, newState);
//             if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
//             {
//                 isScrolling = true;
//             }
//         }
//
//         @Override
//         public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//             super.onScrolled(recyclerView, dx, dy);
//             currentItems = manager.getChildCount();
//             totalItems = manager.getItemCount();
//             scrollOutItems = manager.findFirstVisibleItemPosition();
//
//             if(isScrolling && (currentItems + scrollOutItems == totalItems))
//             {
//                 isScrolling = false;
//                 Getdata(page);
//             }
//
//         }
//     });
//     Getdata(page);

//    }
    private void GetData(int idsp, int page) {
        DataClient dataClient = APIUtils.dataClient();
        Call<List<SptheoDM>> listCall = dataClient.getSpDM(idsp, page);
        listCall.enqueue(new Callback<List<SptheoDM>>() {
            @Override
            public void onResponse(Call<List<SptheoDM>> call, Response<List<SptheoDM>> response) {
                Log.e("B", response.code() + "");
                if (response != null && response.toString().length() > 0) {
                    progressBar.setVisibility(View.INVISIBLE);
                    mdArrayList.addAll(response.body());
                    itemDetailAdapter.notifyDataSetChanged();
                } else {
                    limitData = true;
                    progressBar.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(Call<List<SptheoDM>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(int position) {
        post = new SptheoDM();
        post = mdArrayList.get(position);
        int ID = post.getId();
        int Giachitiet = post.getGiasp();
        String Tenchitiet = post.getTensanpham();
        String HinhChiTiet = post.getHinhanh();
        String Mota = post.getMotasp();
        int idsanoham = post.getIdsanpham();
        chitietHang fragment = new chitietHang();
        Bundle bundle = new Bundle();
        bundle.putInt("iduss", idusers);
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
        getActivity().setTitle("Chi tiết sản phẩm");
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

    }

    @Override
    public void Sucessfullsp(List<SptheoDM> sptheoDMS) {
        mdArrayList.addAll(sptheoDMS);
        itemDetailAdapter.notifyDataSetChanged();
    }
}
