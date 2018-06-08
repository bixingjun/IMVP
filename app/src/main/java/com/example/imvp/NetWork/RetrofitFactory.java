package com.example.imvp.NetWork;
/*
 *  包名: com.example.imvp.NetWork
 * Created by ASUS on 2018/5/31.
 *  描述: TODO
 */

import android.content.Context;
import android.util.Log;

import com.example.imvp.BaseApplication;
import com.example.imvp.utils.NetUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {



    private static final String API_HOST ="你的BaseUrl";

    //访问超时
    private static final long TIMEOUT = 30;

   private static ClearableCookieJar cookieJar =
            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.getAppContext()));


    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .cookieJar(cookieJar)
            //打印接口信息，方便接口调试
            .addInterceptor(getLoggerInterceptor())
            .addNetworkInterceptor(new CacheStrategyInterceptor())

            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)

            .build();


    private static  Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_HOST)
            // 添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .setLenient()
                    .create()
            ))
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build();


    //获得RetrofitService对象
    public static Retrofit getInstance() {
        return retrofit;
    }

    public static HttpLoggingInterceptor getLoggerInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("ApiUrl", "--->" + message);

            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    public static class CacheStrategyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtils.isConnected(BaseApplication.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();

            }
            Response response = chain.proceed(request);

            if (NetUtils.isConnected(BaseApplication.getAppContext())) {
                int maxAge = 1 * 60;//这个是控制缓存的最大生命时间
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    }




}
