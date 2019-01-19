package com.example.ihe.acmestudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ihe.acmestudy.View.ProblemSolveActivity;
import com.example.ihe.acmestudy.View.homepage.HomePageActivity;

import butterknife.BindView;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.home)
    Button returnHomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ProblemSolveActivity.class);
                startActivity(intent);
            }
        });
        Button button1=findViewById(R.id.send);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button button2=findViewById(R.id.send_btn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        returnHomeBtn=findViewById(R.id.home);
        returnHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });
        initView();
    }
    private void initView(){
        Intent intent=new Intent(MainActivity.this,HomePageActivity.class);
        startActivity(intent);
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