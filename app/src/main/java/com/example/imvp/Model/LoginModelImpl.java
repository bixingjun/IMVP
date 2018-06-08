package com.example.imvp.Model;
/*
 *  包名: com.example.imvp.Model
 * Created by ASUS on 2018/5/8.
 *  描述: TODO
 */

import com.example.imvp.NetWork.ApiServcieImpl;
import com.example.imvp.NetWork.BaseResponse;
import com.example.imvp.bean.LoginData;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginModelImpl implements  LoginContract.Model{

    @Override
    public Observable<LoginData> login(HashMap<String, Object> treeMap) {
        return null;
    }
}