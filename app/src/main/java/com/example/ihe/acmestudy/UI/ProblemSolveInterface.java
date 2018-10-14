package com.example.ihe.acmestudy.UI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ihe.acmestudy.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.ihe.acmestudy.Compatibility.AcmeContext.getContext;

public class ProblemSolveInterface extends AppCompatActivity{
    FloatingActionButton like_btn;
    Chronometer recordChronometer;
    Toolbar toolbar_head;
    ProgressBar progressBar;
    boolean IS_SELECTED=false;
    private long recordingTime = 0;// 记录下来的总时间
    List<View> pages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View rootView = View.inflate(this, R.layout.problem_solve_layout, null);
        setContentView(R.layout.problem_solve_layout);

        //当试题到达 试题cache之后，启动此页面


        //获取试题内容
        List<String> optionContentList =new ArrayList<>();
        optionContentList.add("1+1=2");
        optionContentList.add("1+1=3");
        optionContentList.add("1+1=4");
        optionContentList.add("1-1=2");
        String questionStemContent="下列正确的是？这些问题都跟哲学有关。\n" +
                "All these questions relate to philosophy";

        //加载试题
        View view= LayoutInflater.from(getContext()).inflate(R.layout.questions_container_layout,null);
        View view1= LayoutInflater.from(getContext()).inflate(R.layout.multiple_choice_question_layout,null);

        pages= new ArrayList<>();
        if (view!=null) {
            RecyclerView recyclerView = view.findViewById(R.id.question_container);
            QuestionsLoader questionsLoader = new QuestionsLoader(true, optionContentList, recyclerView);
            recyclerView.setAdapter(questionsLoader);
            recyclerView.setLayoutManager(new LinearLayoutManager(ProblemSolveInterface.this));
            TextView questionStemView = findViewById(R.id.stem_view);
            if (questionStemView != null)
                questionStemView.setText(questionStemContent);
            else
                Log.d("#", "onCreate: nothing");

            //加载pagerView item


        }
        else
            Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();

        pages.add(view);
        pages.add(view1);
        PageLoader pageLoader = new PageLoader(pages);
        ViewPager viewPager = findViewById(R.id.card_pager);
        viewPager.setAdapter(pageLoader);





        //加载各部件
         toolbar_head=findViewById(R.id.toolbar_head);
        setSupportActionBar(toolbar_head);
        ActionBar actionBar_head=getSupportActionBar();
        if (actionBar_head!=null){
            actionBar_head.setDisplayHomeAsUpEnabled(true);
            actionBar_head.setDisplayShowTitleEnabled(false);
        }
       like_btn=findViewById(R.id.like_btn);
        progressBar=findViewById(R.id.progressBar);




        //开始计时
         recordChronometer =findViewById(R.id.chronometer);
         onRecordStart();
        //监听各部件
        PSIListener psiListener=new PSIListener();
        like_btn.setOnClickListener(psiListener);
        recordChronometer.setOnClickListener(psiListener);
        viewPager.addOnPageChangeListener(psiListener);

    }
    //从数据获取模块获取相应的试题加载在试题容器（如recycleview，textview等等）
    private void acquireQuestionsLoadIntoViews(){

    }
    /*为计时所准备的函数*/
    public void onRecordStart() {
        recordChronometer.setBase(SystemClock.elapsedRealtime() - recordingTime);// 跳过已经记录了的时间，起到继续计时的作用
        recordChronometer.start();
    }
    public void onRecordPause() {
        recordChronometer.stop();
        recordingTime = SystemClock.elapsedRealtime()- recordChronometer.getBase();// 保存这次记录了的时间
    }
    public void onRecordStop() {
        recordingTime = 0;
        recordChronometer.setBase(SystemClock.elapsedRealtime());
    }
/*一个监听所有控件（chronometer，like_btn,viewpager）的类*/
    class PSIListener implements View.OnClickListener,ViewPager.OnPageChangeListener {
//监听所有点击事件的方法
        @Override
        public void onClick(View v) {
            if (v.getId()==like_btn.getId())
            {
                like_btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                if (!IS_SELECTED){
                like_btn.setImageResource(R.drawable.like_img);
                IS_SELECTED=true;
                }
                else {
                    like_btn.setImageResource(R.drawable.ic_favorite_border);
                    IS_SELECTED=false;
                }
            }
            else if (v.getId()== recordChronometer.getId())
            {
                onRecordPause();
                PSIDialogFragment psiDialogFragment=new PSIDialogFragment();
                AlertDialog dialog = new AlertDialog.Builder(ProblemSolveInterface.this)
                        .setMessage("    休息一下")
                        .setNeutralButton("    继续做题", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                onRecordStart();
                            }


                        }).create();
                dialog.show();
            }
        }
/*监听pageChange事件
* 翻页后，progressbar，答题卡界面，like_btn都要做出相应的改变*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state==ViewPager.SCROLL_STATE_IDLE){

        }
    }
}

}
