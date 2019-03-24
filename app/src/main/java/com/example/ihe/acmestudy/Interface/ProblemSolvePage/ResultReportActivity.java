package com.example.ihe.acmestudy.Interface.ProblemSolvePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.ihe.acmestudy.R;
import com.example.ihe.acmestudy.common.BaseActivity;

import butterknife.BindView;

public class ResultReportActivity extends BaseActivity {
    @BindView(R.id.grid_view)
    NoScrollGridView noScrollGridView;

    GridViewAdapter gridViewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        initNoScrollGridView();
    }
    private void initNoScrollGridView(){
        noScrollGridView.setAdapter(gridViewAdapter);
        noScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO:跳转到相应的viewpager 页面
                Intent intent = new Intent(ResultReportActivity.this, ProblemSolveActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });


    }


    @Override
    protected void initData() {
        super.initData();
        initInstances();

    }
    private void initInstances(){
        gridViewAdapter=new GridViewAdapter(ResultReportActivity.this);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_result_report;
    }
}
