package com.example.imvp.Model;
/*
 *  包名: com.example.imvp.Model
 * Created by ASUS on 2018/5/8.
 *  描述: TODO
 */

import com.example.imvp.bean.LoginData;

import java.util.HashMap;

import io.reactivex.Observable;

public class LoginContract {
    public interface Model {
        /** * 获取登陆数据 * @return Observable<LoginData> */
        Observable<LoginData> login(HashMap<String, Object> treeMap);
    }

}
