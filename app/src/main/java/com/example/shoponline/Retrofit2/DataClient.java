package com.example.shoponline.Retrofit2;

import com.example.shoponline.modelgson.LoaiSp;
import com.example.shoponline.modelgson.SanphamNew;
import com.example.shoponline.modelgson.SptheoDM;
import com.example.shoponline.modelgson.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface DataClient {
@FormUrlEncoded
    @POST("login.php")
    Call<List<User>>getLogin(@Field("username") String username,
                             @Field("password") String password);

    @GET("getsanpham.php")
    Call<List<SanphamNew>> getAllSanpham();

    @GET("getloaisp.php")
    Call<List<LoaiSp>>getLoaisp();
    @GET("getsptheodanhmuc.php")
    Call<List<SptheoDM>>getSpDM(@Query("idsanpham") int idsanpham,
                                @Query("page") int page);
}
