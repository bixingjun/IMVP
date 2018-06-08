package com.example.imvp.NetWork;
/*
 *  包名: com.example.imvp.NetWork
 * Created by ASUS on 2018/5/8.
 *  描述: TODO
 */

public class ApiServcieImpl {

    private static APIService apiService;

    public static APIService getInstance() {

        if(apiService==null){
            synchronized (APIService.class){
                if(apiService==null){
                    apiService=  createAPIService.apiService;
                }
            }
        }
        return apiService;
    }

    private static class createAPIService {
        //Retrofit会根据传入的接口类.生成实例对象.
        //
        private static final APIService apiService = RetrofitUtil.getRetrofit().create(APIService.class);

        private static final APIService sapiService = RetrofitFactory.getInstance().create(APIService.class);
    }



}
