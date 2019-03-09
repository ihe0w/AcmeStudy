package com.example.ihe.acmestudy.Interface.ProblemSolvePage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.ihe.acmestudy.Interface.gapfilling.GapFillingSpanAnswerRange;
import com.example.ihe.acmestudy.Entity.Entity.GapFillingQuestion;
import com.example.ihe.acmestudy.Entity.Entity.MultipleChoiceQuestion;
import com.example.ihe.acmestudy.Entity.Entity.QuestionInfo;
import com.example.ihe.acmestudy.Entity.Entity.SingleChoiceQuestion;
import com.example.ihe.acmestudy.R;
import com.example.ihe.acmestudy.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProblemSolveInterface extends BaseActivity implements QuestionItemFragment.OnFragmentInteractionListener, AnswerSheetFragment.OnFragmentInteractionListener {
    @BindView(R.id.like_btn)
    FloatingActionButton like_btn;
    @BindView(R.id.chronometer)
    Chronometer recordChronometer;
    @BindView(R.id.toolbar_head)
    Toolbar toolbar_head;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.toggle_answer_sheet_button)
    ImageButton toggleAnswerSheetButton;
    @BindView(R.id.card_pager)
    ViewPager viewPager;

    private long recordingTime = 0;// 记录下来的总时间
    boolean[] isLikeSet;
    PSIListener psiListener;
//    List<View> pages;
    public static List<QuestionInfo> questionInfos;
    ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    AnswerSheetFragment answerSheetFragment;

    public static int packetSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();


    }
    @Override
    protected void initData() {
        super.initData();
        initInstances();
        acquireQuestions();

    }
    private void acquireQuestions(){
        SingleChoiceQuestion singleChoiceQuestion=new SingleChoiceQuestion();
        MultipleChoiceQuestion multipleChoiceQuestion=new MultipleChoiceQuestion();
        GapFillingQuestion gapFillingQuestion=new GapFillingQuestion();


        List<String> optionContentList =new ArrayList<>();
        String questionStemContent="下列正确的是？这些问题都跟哲学有关。\n" +
                "All these questions relate to philosophy";
        optionContentList.add("1+1=2");
        optionContentList.add("1+1=3");
        optionContentList.add("1+1=4");
        optionContentList.add("1-1=2");
        singleChoiceQuestion.setOptionContentList(optionContentList);
        singleChoiceQuestion.setQuestionStem(questionStemContent);

        multipleChoiceQuestion.setQuestionStem(questionStemContent);
        multipleChoiceQuestion.setOptionContentList(optionContentList);

//        View singleChoiceView= createAndLoadIntoChoiceView(QuestionForDBAgent.SINGLE_CHOICE,questionStemContent,optionContentList);
//        if (singleChoiceView==null)
//            Log.d("#", "null singleChoiceView");
//        View multipleChoicesView=createAndLoadIntoChoiceView(QuestionForDBAgent.MULTIPLE_CHOICE,questionStemContent,optionContentList);

        String gapFillingQuestionStem = "纷纷扬扬的________下了半尺多厚。天地间________的一片。我顺着________工地走了四十多公里，" +
                "只听见各种机器的吼声，可是看不见人影，也看不见工点。一进灵官峡，我就心里发慌。";

        // 答案范围集合
        List<GapFillingSpanAnswerRange> rangeList = new ArrayList<>();
        rangeList.add(new GapFillingSpanAnswerRange(5, 13));
        rangeList.add(new GapFillingSpanAnswerRange(23, 31));
        rangeList.add(new GapFillingSpanAnswerRange(38, 46));
//        View gapFillingView=  createAndLoadIntoGapFillingView(gapFillingQuestionStem,rangeList);

        gapFillingQuestion.setQuestionStem(gapFillingQuestionStem);
        gapFillingQuestion.setRangeList(rangeList);

        QuestionInfo questionInfo=new QuestionInfo();
        QuestionInfo questionInfo1=new QuestionInfo();
        QuestionInfo questionInfo2=new QuestionInfo();
        questionInfo.setType(QuestionInfo.SINGLE_CHOICE);
        questionInfo.setQuestion_id(0);
        questionInfo.setRealQuestion(singleChoiceQuestion);
        questionInfo1.setType(QuestionInfo.MULTIPLE_CHOICE);
        questionInfo1.setRealQuestion(multipleChoiceQuestion);
        questionInfo1.setQuestion_id(1);
        questionInfo2.setType(QuestionInfo.GAP_FILLING);
        questionInfo2.setQuestion_id(2);
        questionInfo2.setRealQuestion(gapFillingQuestion);

        questionInfos=new ArrayList<>();
        questionInfos.add(questionInfo);
        questionInfos.add(questionInfo1);
        questionInfos.add(questionInfo2);
        packetSize=questionInfos.size();
        Log.d("#", "acquireQuestions: ");

//
//        if (psiPageItemViewLoader.loadQuestionIntoViews(questionSetJsonParser.getQuestionPacket())==null)
//            Log.d("#", "acquireQuestions:    null");
//        else
//            Log.d("#", "acquireQuestions: not NULL");
//        packetSize=questionSetJsonParser.getQuestionPacket().getSize();
//        pages=psiPageItemViewLoader.loadQuestionIntoViews(questionSetJsonParser.getQuestionPacket());
        isLikeSet =new boolean[packetSize];

    }
    private void initInstances(){
        psiListener=new PSIListener();
//        psiPageItemViewLoader=new PSIPageItemViewLoader(ProblemSolveInterface.this);
        viewPagerFragmentAdapter=new ViewPagerFragmentAdapter(getSupportFragmentManager());
        answerSheetFragment=new AnswerSheetFragment();
    }
    @Override
    protected void initView() {
        super.initView();
        adaptSystemStatusBar();
        Log.d("#", "Statusbar");
        initAppBar();
        initViewPager();
        initFloatButton();


    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.ihe.jumptonext")) {
                jumpToNext() ;
            } else if (intent.getAction().equals("com.ihe.jumptopage")) {
                int index = intent.getIntExtra("index", 0);
                jumpToPage(index);
            }
        }
    };
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
    private void initAppBar(){
        setSupportActionBar(toolbar_head);
        ActionBar actionBar_head=getSupportActionBar();
        if (actionBar_head!=null){
            actionBar_head.setDisplayHomeAsUpEnabled(true);
            actionBar_head.setDisplayShowTitleEnabled(false);
        }
        toggleAnswerSheetButton.setOnClickListener(psiListener);
        initChronometer();



    }
    private void initFloatButton(){
        like_btn.setOnClickListener(psiListener);
    }
    private void initViewPager(){
//        for (View view:pages){
//            Log.d("#", "initViewPager: "+view);
//        }
//        PageLoader pageLoader = new PageLoader(pages);
        Log.d("#", "initViewPager: before");

        viewPager.setAdapter(viewPagerFragmentAdapter);
        viewPager.addOnPageChangeListener(psiListener);

    }
    private void initFragment(){
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ihe.jumptonext");
        filter.addAction("com.ihe.jumptopage");
        lbm.registerReceiver(mMessageReceiver, filter);
    }
    private void initChronometer(){
        onRecordStart();
        recordChronometer.setOnClickListener(psiListener);
    }

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
    public void jumpToNext() {
        int position = viewPager.getCurrentItem();
        viewPager.setCurrentItem(position + 1);

    }
    public void jumpToPage(int index) {
        viewPager.setCurrentItem(index);
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
        private void switchAnswerSheetInterface(){
            jumpToPage(packetSize);
//            getFragmentManager().beginTransaction().replace(R.layout.activity_problem_solve_interface,(Fragment)answerSheetFragment).commit();
//            Intent PSIandASIintent=new Intent(ProblemSolveInterface.this,AnswerSheetActivity.class);
//            startActivityForResult(PSIandASIintent,1);
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
            int currentPageNumber=viewPager.getCurrentItem();
            if (currentPageNumber==packetSize){
                like_btn.hide();
                toggleAnswerSheetButton.setVisibility(View.INVISIBLE);

            }
            else {
                like_btn.show();
                toggleAnswerSheetButton.setVisibility(View.VISIBLE);
                if (state==ViewPager.SCROLL_STATE_IDLE){
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

    @Override
    protected int getContentViewId() {
        return R.layout.activity_problem_solve_interface;
    }
//    public static Context getContext(){
//        return ProblemSolveInterface.this;
//    }
@Override
public void onFragmentInteraction(Uri uri) {

}
}
