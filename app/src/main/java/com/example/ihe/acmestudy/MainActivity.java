package com.example.ihe.acmestudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ihe.acmestudy.Cache.Questions.QuestionForDBAgent;
import com.example.ihe.acmestudy.Cache.Questions.SingleChoiceQuestion;
import com.example.ihe.acmestudy.UI.ProblemSolveInterface;

import org.litepal.LitePal;

import java.util.List;

/*本软件致力于服务所有应试教育下的考试，提供电子试题与纸质试题的无尽交融，并以多媒体的方式提供多种解析方式*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ProblemSolveInterface.class);
                startActivity(intent);
            }
        });
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
    }
}
