package com.example.imvp.Activity;
/*
 *  包名: com.example.imvp
 * Created by ASUS on 2018/5/6.
 *  描述: TODO
 */

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.imvp.Presenter.BasePresenter;
import com.example.imvp.View.BaseView;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView{

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(attachLayoutRes());

        ButterKnife.bind(this);

        //initInjector();

        initViews();

        mPresenter = createPresenter();

   //     mPresenter.attachView((V) this);

        updateViews();
    }

    protected abstract void updateViews();

    protected abstract void initViews();

    protected abstract void initInjector();

    protected abstract int attachLayoutRes();


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detechView();  //解除关联
    }

    protected abstract T createPresenter();
}
