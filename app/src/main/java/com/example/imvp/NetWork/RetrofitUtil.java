package com.example.imvp.NetWork;
/*
 *  包名: com.example.imvp.NetWork
 * Created by ASUS on 2018/5/6.
 *  描述: TODO
 */

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static final String API_HOST ="你的BaseUrl";
    private RetrofitUtil() {

    }
    public static Retrofit getRetrofit() {
        return Instanace.retrofit;
    } //静态内部类,保证单例并在调用getRetrofit方法的时候才去创建.
    private static class Instanace {

        private static Retrofit retrofit = getInstanace();

        private static Retrofit getInstanace() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger(){
                @Override
                public void log(String message) {

                }
            });

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(API_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit;
        }
    }



}
