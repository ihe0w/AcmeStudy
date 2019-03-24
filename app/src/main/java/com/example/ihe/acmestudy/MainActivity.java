package com.example.ihe.acmestudy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ihe.acmestudy.Interface.ProblemSolvePage.ProblemSolveActivity;
import com.example.ihe.acmestudy.common.BaseActivity;

/**
 * 判断是否登录，登录了就跳转首页，若是没有则跳转到登陆注册界面，*/
public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("#", "initView: AAa");
        Intent intent1=new Intent(MainActivity.this, ProblemSolveActivity.class);
        startActivity(intent1);
//        Intent intent=new Intent(MainActivity.this,HomePageActivity.class);
//        Intent intent1=new Intent(MainActivity.this, ProblemSolveActivity.class);
//        Log.d("#", "initViewend: ");
////        startActivity(intent);        //I don't understand why ???
//        startActivity(intent1);
    }



    @Override
    protected void initView() {
        super.initView();
        Log.d("#", "initView: ");
//        Intent intent=new Intent(MainActivity.this,HomePageActivity.class);
//        startActivity(intent);
    }
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }
}
//                Button dbButton=findViewById(R.id.button3);
//        final TextView textView=findViewById(R.id.textView);
//        dbButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                LitePal.getDatabase();
//                Log.d("#", "db suessful");
////                QuestionForDBAgent questionForDB=QuestionForDBAgent.getQuestionForDBInstance();
////                questionForDB.setQuestionStem("hello,world");
////                List<String> optionList=new ArrayList<>(1);
////                optionList.add("this option content");
////                questionForDB.setOptionContentList( optionList);
////                questionForDB.save();
////                List<SingleChoiceQuestion> singleChoiceQuestions=LitePal.select("questionStem").find(SingleChoiceQuestion.class);
////                List<QuestionForDBAgent> questionForDBS=LitePal.select("questionStem").find(QuestionForDBAgent.class);
////                for (SingleChoiceQuestion questionForDB1:singleChoiceQuestions){
////                    Log.d("#", ""+questionForDB1.getQuestionStem());
////                    textView.setText(questionForDB1.getQuestionStem());
////                    textView.setText(questionForDB1.getOptionContentList().get(0));
////                }
////            }
////        });
//Button button=findViewById(R.id.button2);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this, ProblemSolveActivity.class);
//                startActivity(intent);
//            }
//        });
//        Button button1=findViewById(R.id.send);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        Button button2=findViewById(R.id.send_btn);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        returnHomeBtn=findViewById(R.id.home);
//        returnHomeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,HomePageActivity.class);
//                startActivity(intent);
//            }
//        });