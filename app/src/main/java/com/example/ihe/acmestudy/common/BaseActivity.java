package com.example.ihe.acmestudy.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ihe.acmestudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class BaseActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);


        initData();
        initView();

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    protected void toastShort(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

         protected void initData() {

        }

        protected void initView() {

    }

    protected abstract int getContentViewId();
}
