package com.example.shoponline.Retrofit2;

public class APIUtils {
    public static DataClient dataClient() {
        return RetrofitClient.getInstance().create(DataClient.class);
    }
}
