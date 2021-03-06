package com.aac.expansion.data;


import android.arch.lifecycle.Observer;
import com.aac.module.ui.AacFragmentPresenter;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  Fragment数据业务
 */

public abstract class AacDataFPresenter<V extends AacDataFragment, M> extends AacFragmentPresenter<V> {


    private Observer<M> observeForever = new Observer<M>() {
        @Override
        public void onChanged(M m) {
            if (m != null) {
                getView().setBaseData(m);
            } else {
                getView().setBaseError(new Throwable(new NullPointerException()));
            }
        }
    };
    /***
     * 可以子线程数据ui线程手动发布数据
     **/
    public void postData(M data) {
        getView().setBaseData(data);
    }
    /**
     *
     * ***/
    public Observer<M> getDataSubscriber() {
        return observeForever;
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad();


    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
     protected void stopLoad() {

    }
}
