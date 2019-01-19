package com.example.ihe.acmestudy.View;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.ihe.acmestudy.Compatibility.TransmitterAmongInterfaces;
import com.example.ihe.acmestudy.DataAcquire.QuestionSetJsonParser;
import com.example.ihe.acmestudy.R;

import java.util.List;

public class ProblemSolveActivity extends AppCompatActivity{
    FloatingActionButton like_btn;
    Chronometer recordChronometer;
    Toolbar toolbar_head;
    ProgressBar progressBar;
    ImageButton toggleAnswerSheetButton;
    ViewPager viewPager;
    QuestionSetJsonParser questionSetJsonParser;

    private long recordingTime = 0;// 记录下来的总时间
    List<View> pages;
    boolean[] isLikeSet;
    PSIPageItemViewLoader psiPageItemViewLoader;
    private int totalNumberOfItemView=5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_solve_layout);
        adaptSystemStatusBar();

        //解析并获取试题内容
        psiPageItemViewLoader=new PSIPageItemViewLoader(ProblemSolveActivity.this);
        questionSetJsonParser=new QuestionSetJsonParser();

        pages=psiPageItemViewLoader.loadQuestionIntoViews(questionSetJsonParser.getQuestionPacket());

        isLikeSet =new boolean[pages.size()];
        //加载pagerView item
        PageLoader pageLoader = new PageLoader(pages);
        viewPager = findViewById(R.id.card_pager);
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
        toggleAnswerSheetButton=findViewById(R.id.toggle_answer_sheet_button);
        recordChronometer =findViewById(R.id.chronometer);
        //开始计时

         onRecordStart();
        //监听各部件
        PSIListener psiListener=new PSIListener();
        like_btn.setOnClickListener(psiListener);
        recordChronometer.setOnClickListener(psiListener);
        viewPager.addOnPageChangeListener(psiListener);
        toggleAnswerSheetButton.setOnClickListener(psiListener);

        TransmitterAmongInterfaces transmitterAmongInterfaces=TransmitterAmongInterfaces.getInstance();
        transmitterAmongInterfaces.setViewPagerFromPSI(viewPager);

    }
    private void adaptSystemStatusBar(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }
    private void switchToSeletedPage(){

    }
    private void updateCheckedAnswerStates(){
        for (View view:pages){

        }
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
                toggleLikedButton();
            else if (v.getId()== recordChronometer.getId())
                updateChronometerState();
            else if (v.getId()==toggleAnswerSheetButton.getId())
                switchAnswerSheetInterface();

        }
        private void toggleLikedButton(){
            int currentPageNumber=viewPager.getCurrentItem();
            if (isLikeSet[currentPageNumber]){
                like_btn.setImageResource(R.drawable.ic_favorite_border);
                isLikeSet[viewPager.getCurrentItem()]=false;
            }
            else {
                like_btn.setImageResource(R.drawable.like_img);
                isLikeSet[currentPageNumber]=true;
            }
        }
        private void updateChronometerState(){
            onRecordPause();
            PSIDialogFragment psiDialogFragment=new PSIDialogFragment();
            AlertDialog dialog = new AlertDialog.Builder(ProblemSolveActivity.this)
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
        private void switchAnswerSheetInterface(){
            Intent PSIandASIintent=new Intent(ProblemSolveActivity.this,AnswerSheetActivity.class);
            startActivityForResult(PSIandASIintent,1);
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
            int currentPageNumber=viewPager.getCurrentItem();
            Toast.makeText(ProblemSolveActivity.this,"now page is"+viewPager.getCurrentItem()+"   State is"+isLikeSet[currentPageNumber],Toast.LENGTH_SHORT).show();
            if (isLikeSet[currentPageNumber]){
                like_btn.setImageResource(R.drawable.like_img);
            }
            else {
                like_btn.setImageResource(R.drawable.ic_favorite_border);
            }

        }
    }
}

}
