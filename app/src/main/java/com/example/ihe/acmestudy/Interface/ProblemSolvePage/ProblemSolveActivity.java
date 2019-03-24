package com.example.ihe.acmestudy.Interface.ProblemSolvePage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
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

import com.example.ihe.acmestudy.Entity.Entity.UserQuestionMapper;
import com.example.ihe.acmestudy.Interface.ProblemSolvePage.gapfilling.GapFillingSpanAnswerRange;
import com.example.ihe.acmestudy.Entity.Entity.GapFillingQuestion;
import com.example.ihe.acmestudy.Entity.Entity.MultipleChoiceQuestion;
import com.example.ihe.acmestudy.Entity.Entity.QuestionInfo;
import com.example.ihe.acmestudy.Entity.Entity.SingleChoiceQuestion;
import com.example.ihe.acmestudy.R;
import com.example.ihe.acmestudy.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProblemSolveActivity extends BaseActivity implements QuestionItemFragment.OnFragmentInteractionListener, AnswerSheetFragment.OnFragmentInteractionListener {
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
    public static List<QuestionInfo> questionInfos;
    public static List<UserQuestionMapper> userQuestionMappers;
    ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    VIewPagerAnalyzeFragmentAdapter vIewPagerAnalyzeFragmentAdapter;
    AnswerSheetFragment answerSheetFragment;
    int pagePosition;
    public static  final int PSA=0;
    public static final int PAA=1;
    private static int whichActivity=PSA;
    AnswerChecker answerChecker;
    public static int packetSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBroadcast();
        Log.d("#", "onCreate:  "+this);
//        handleIntentMsg();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("#", "ohhhhhhhnCreate:  "+this);

    }
    @Override
    protected void initData() {
        super.initData();
        initInstances();
        acquireQuestions();
    }
    private void acquireQuestions(){
       if (whichActivity==PSA){
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

           String gapFillingQuestionStem = "纷纷扬扬的________下了半尺多厚。天地间________的一片。我顺着________工地走了四十多公里，" +
                   "只听见各种机器的吼声，可是看不见人影，也看不见工点。一进灵官峡，我就心里发慌。";

           // 答案范围集合
           List<GapFillingSpanAnswerRange> rangeList = new ArrayList<>();
           rangeList.add(new GapFillingSpanAnswerRange(5, 13));
           rangeList.add(new GapFillingSpanAnswerRange(23, 31));
           rangeList.add(new GapFillingSpanAnswerRange(38, 46));
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
           isLikeSet =new boolean[packetSize];
           //TODO:here must have userquestionmapper
           userQuestionMappers=new ArrayList<>();
           UserQuestionMapper userQuestionMapper=new UserQuestionMapper();
           userQuestionMapper.setQuestionId(0);
           UserQuestionMapper userQuestionMapper1=new UserQuestionMapper();
           userQuestionMapper1.setQuestionId(1);
           UserQuestionMapper userQuestionMapper2=new UserQuestionMapper();
           userQuestionMapper2.setQuestionId(2);
           answerChecker.setUserQuestionMappers(userQuestionMappers);
           answerChecker.setQuestionInfos(questionInfos);
       }
       else {
           questionInfos=answerChecker.getQuestionInfos();
           packetSize=questionInfos.size();
           userQuestionMappers=answerChecker.getUserQuestionMappers();
       }


    }
    private void initInstances(){
        psiListener=new PSIListener();
//        psiPageItemViewLoader=new PSIPageItemViewLoader(ProblemSolveActivity.this);
        if (whichActivity==PSA)
            viewPagerFragmentAdapter=new ViewPagerFragmentAdapter(getSupportFragmentManager());
        else
            vIewPagerAnalyzeFragmentAdapter=new VIewPagerAnalyzeFragmentAdapter(getSupportFragmentManager());
        answerSheetFragment=new AnswerSheetFragment();
        answerChecker=AnswerChecker.getInstance();
    }
    @Override
    protected void initView() {
        super.initView();
        adaptSystemStatusBar();
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
            } else if (intent.getAction().equals("com.ihe.checkanswer")){
                checkAnswer();
            }

        }
    };
    private void adaptSystemStatusBar(){
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
    }
    private void initAppBar(){
        setSupportActionBar(toolbar_head);
        ActionBar actionBar_head=getSupportActionBar();
        if (actionBar_head!=null){
            actionBar_head.setDisplayHomeAsUpEnabled(true);
            actionBar_head.setDisplayShowTitleEnabled(false);
        }
        toggleAnswerSheetButton.setOnClickListener(psiListener);
        if (whichActivity==PSA)
            initChronometer();



    }
    private void initFloatButton(){
        like_btn.setOnClickListener(psiListener);
    }
    private void initViewPager(){
        if (whichActivity==PSA) {
            viewPager.setAdapter(viewPagerFragmentAdapter);
            viewPager.addOnPageChangeListener(psiListener);
        }
        else
            viewPager.setAdapter(vIewPagerAnalyzeFragmentAdapter);
    }
    private void initBroadcast(){
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ihe.jumptonext");
        filter.addAction("com.ihe.jumptopage");
        filter.addAction("com.ihe.checkanswer");
        lbm.registerReceiver(mMessageReceiver, filter);
    }

    private void initChronometer(){
        onRecordStart();
        recordChronometer.setOnClickListener(psiListener);
    }
//    private void handleIntentMsg(){
//        if (pagePosition!=-1){
//            jumpToPage(pagePosition);
//            viewPagerFragmentAdapter.setNEED_ANALYZE_VIEW(true);
//        }
//    }
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
        Log.d("#", "jumpToPage: "+index);
        viewPager.setCurrentItem(index);
    }
    public void checkAnswer(){
        viewPagerFragmentAdapter.setUserAnswer();
        answerChecker.setUserQuestionMappers(userQuestionMappers);
        answerChecker.checkAnswer();
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
            jumpToPage(packetSize);
//            getFragmentManager().beginTransaction().replace(R.layout.activity_problem_solve_interface,(Fragment)answerSheetFragment).commit();
//            Intent PSIandASIintent=new Intent(ProblemSolveActivity.this,AnswerSheetActivity.class);
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
//        return ProblemSolveActivity.this;
//    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
    public static int getWhichActivity(){
        return whichActivity;
    }
}
