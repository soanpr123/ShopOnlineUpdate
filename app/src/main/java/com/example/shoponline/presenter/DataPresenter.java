package com.example.shoponline.presenter;

import android.content.Context;
import android.util.Log;

import com.example.shoponline.Retrofit2.RetrofitClient;
import com.example.shoponline.modelgson.LoaiSp;
import com.example.shoponline.modelgson.SanphamNew;
import com.example.shoponline.Retrofit2.APIUtils;
import com.example.shoponline.Retrofit2.DataClient;
import com.example.shoponline.fragmentscreen.TrangChinh;
import com.example.shoponline.interfac.LoginView;
import com.example.shoponline.modelgson.SptheoDM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPresenter {
    private LoginView loginView;
    private Context context;
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
    private TrangChinh trangChinh;
    private List<SanphamNew> sanphamNewss;

    public DataPresenter(LoginView loginView, Context context) {
        this.loginView = loginView;
        this.context = context;


    }

    public void GetspNew() {
        DataClient dataClient = APIUtils.dataClient();
        Call<List<SanphamNew>> listCall = dataClient.getAllSanpham();

        listCall.enqueue(new Callback<List<SanphamNew>>() {
            @Override
            public void onResponse(Call<List<SanphamNew>> call, Response<List<SanphamNew>> response) {
Log.e("A",response.code()+"");
               loginView.Sucessfull(response.body());
//                ArrayList<SanphamNew> sanphamNews = (ArrayList<SanphamNew>) response.body();
//                if (sanphamNews.size() > 0) {
//                    for (int i = 0; i < sanphamNews.size(); i++) {
//                        Log.e("A", sanphamNews.get(i).getTensanpham());
//                        Log.e("A", sanphamNews.get(i).getGiasp() + "");
//                        Log.e("A", sanphamNews.get(i).getHinhanh());
//                        Log.e("A", sanphamNews.get(i).getMotasp());
//                        Log.e("A", sanphamNews.get(i).getIdsanpham() + "");
//                        Log.e("A", sanphamNews.get(i).getSoluong() + "");
//                        Log.e("A", sanphamNews.get(i).getId() + "");
//
//                    }
//                }
            }

            @Override
            public void onFailure(Call<List<SanphamNew>> call, Throwable t) {

            }
        });
    }
    public void getLoaisp(){
        DataClient dataClient=APIUtils.dataClient();
        Call<List<LoaiSp>>listCall=dataClient.getLoaisp();
        listCall.enqueue(new Callback<List<LoaiSp>>() {
            @Override
            public void onResponse(Call<List<LoaiSp>> call, Response<List<LoaiSp>> response) {
                Log.e("A", response.code()+"");
               loginView.SucessfullLoaisp(response.body());

            }

            @Override
            public void onFailure(Call<List<LoaiSp>> call, Throwable t) {

            }
        });
    }
    public void getTheodm(int idsp,int page){

DataClient dataClient=APIUtils.dataClient();
Call<List<SptheoDM>>listCall=dataClient.getSpDM(idsp,page);
listCall.enqueue(new Callback<List<SptheoDM>>() {
    @Override
    public void onResponse(Call<List<SptheoDM>> call, Response<List<SptheoDM>> response) {
        Log.e("B", response.code()+"");

                loginView.Sucessfullsp(response.body());
    }

    @Override
    public void onFailure(Call<List<SptheoDM>> call, Throwable t) {
loginView.LoginFail();
    }
});
    }
}
