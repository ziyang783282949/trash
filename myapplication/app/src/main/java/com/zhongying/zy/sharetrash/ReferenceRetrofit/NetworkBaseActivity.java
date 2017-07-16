package com.zhongying.zy.sharetrash.ReferenceRetrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.zhongying.zy.sharetrash.R;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NetworkBaseActivity extends AppCompatActivity implements View.OnClickListener {
    //private RelativeLayout mLayoutTitleBar;
    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button mForwardButton;
    private FrameLayout mContentLayout;

    public ProgressDialog pd;
    public ObservableTransformer<Observable, Observable> composeFunction;
    private final long RETRY_TIMES = 1;
    private boolean showLoading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setupViews();   //加载 activity_title 布局 ，并获取标题及两侧按钮
    }

    private void init() {

        pd = new ProgressDialog(this);
        composeFunction = new ObservableTransformer<Observable, Observable>() {
            @Override
            public Observable<Observable> apply(Observable<Observable> upstream) {
                return upstream.retry(0)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (NetworkUtil.isNetworkAvailable(NetworkBaseActivity.this)) {
                                    if (showLoading) {
                                        if (pd != null && !pd.isShowing()) {
                                            pd.show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(NetworkBaseActivity.this, "网络连接异常，请检查网络", Toast.LENGTH_LONG).show();

                                }
                            }
                        }).observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    public void setLoadingFlag(boolean show) {
        showLoading = show;
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

    private void setupViews() {
        super.setContentView(R.layout.activity_title);
        mTitleTextView = (TextView) findViewById(R.id.text_title);
        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);
        mBackwardbButton = (Button) findViewById(R.id.button_backward);
        mForwardButton = (Button) findViewById(R.id.button_forward);
    }

    /**
     * 是否显示返回按钮
     * @param backwardResid  文字
     * @param show  true则显示
     */
    protected void showBackwardView(int backwardResid, boolean show) {
        if (mBackwardbButton != null) {
            if (show) {
                mBackwardbButton.setText(backwardResid);
                mBackwardbButton.setVisibility(View.VISIBLE);
            } else {
                mBackwardbButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    /**
     * 提供是否显示提交按钮
     * @param forwardResId  文字
     * @param show  true则显示
     */
    protected void showForwardView(int forwardResId, boolean show) {
        if (mForwardButton != null) {
            if (show) {
                mForwardButton.setVisibility(View.VISIBLE);
                mForwardButton.setText(forwardResId);
            } else {
                mForwardButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    /**
     * 返回按钮点击后触发
     * @param backwardView
     */
    protected void onBackward(View backwardView) {
        Toast.makeText(this, "点击返回，可在此处调用finish()", Toast.LENGTH_LONG).show();
        //finish();
    }

    /**
     * 提交按钮点击后触发
     * @param forwardView
     */
    protected void onForward(View forwardView) {
        Toast.makeText(this, "点击提交", Toast.LENGTH_LONG).show();
    }


    //设置标题内容
    @Override
    public void setTitle(int titleId) {
        mTitleTextView.setText(titleId);
    }

    //设置标题内容
    @Override
    public void setTitle(CharSequence title) {
        mTitleTextView.setText(title);
    }

    //设置标题文字颜色
    @Override
    public void setTitleColor(int textColor) {
        mTitleTextView.setTextColor(textColor);
    }


    //取出FrameLayout并调用父类removeAllViews()方法
    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }


    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     * 按钮点击调用的方法
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_backward:
                onBackward(v);
                break;

            case R.id.button_forward:
                onForward(v);
                break;

            default:
                break;
        }
    }

}
