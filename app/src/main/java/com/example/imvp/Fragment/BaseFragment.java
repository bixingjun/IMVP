package com.example.imvp.Fragment;
/*
 *  包名: com.example.imvp.Fragment
 * Created by ASUS on 2018/5/6.
 *  描述: TODO
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imvp.Presenter.BasePresenter;
import com.example.imvp.View.BaseView;

import butterknife.ButterKnife;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView{

    private View mRootView;

    protected boolean isVisible;

    protected boolean mIsMulti;

    protected boolean mHasLoadedData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);

            ButterKnife.bind(this, mRootView);

            initInjector();
            initViews();


        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }

        return mRootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews();
        }
    }

    /**Fragment 显示或隐藏 时被调用*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    protected abstract void updateViews();

    protected abstract void initData();

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
}
