package com.example.imvp.Presenter;
/*
 *  包名: com.example.imvp.Presenter
 * Created by ASUS on 2018/5/6.
 *  描述: TODO
 */

import com.example.imvp.View.BaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class BasePresenter<T extends BaseView> {

    protected Reference<T> mViewRef;  //View接口类型的弱引用



    protected T mView;

    public BasePresenter(T view) {
        this.mViewRef = new WeakReference<T>(view);

    }



    public void attachView(T view){
        mViewRef = new WeakReference<T>(view);
    }

    /**
     * 获取View
     * @return
     */
    protected T getView(){
        return mViewRef.get();
    }

    /**
     * 判断是否与View建立关联
     * @return
     */
    public boolean isViewAttach(){
        return mViewRef != null && mViewRef.get() != null;

    }

    /**
     * 解除关联
     */
    public void detechView(){
        if(mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
