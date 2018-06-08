package com.example.imvp.NetWork;
/*
 *  包名: com.example.imvp.NetWork
 * Created by ASUS on 2018/5/8.
 *  描述: TODO
 */

import com.example.imvp.bean.LoginData;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface APIService {
    @GET("login地址")
    Observable<BaseResponse<LoginData>> login(@QueryMap HashMap<String, Object> params);
}
