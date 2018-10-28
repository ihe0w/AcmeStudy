package com.example.ihe.acmestudy.UI;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.ihe.acmestudy.Cache.QuestionForDB;
import com.example.ihe.acmestudy.Cache.QuestionsPacket;
import com.example.ihe.acmestudy.R;
import com.example.ihe.acmestudy.UI.gapfilling.GapFillingSpanAnswerRange;
import com.example.ihe.acmestudy.UI.gapfilling.GapFillingView;

import java.util.ArrayList;
import java.util.List;

import static com.example.ihe.acmestudy.Compatibility.AcmeContext.getContext;

class PSIPageItemViewLoader {
    private Context context;

    PSIPageItemViewLoader(Context context){      //还是那样的问题，你知道的我不喜欢直接传入context的
        this.context = context;
    }
    void parseQuestionsPacket(QuestionsPacket questionsPacket){

    }
    List<View> loadQuestionIntoViews(){

        List<String> optionContentList =new ArrayList<>();
        String questionStemContent="下列正确的是？这些问题都跟哲学有关。\n" +
                "All these questions relate to philosophy";
        optionContentList.add("1+1=2");
        optionContentList.add("1+1=3");
        optionContentList.add("1+1=4");
        optionContentList.add("1-1=2");

        List<View> itemViewList = new ArrayList<>();
        View singleChoiceView= createAndLoadIntoChoiceView(QuestionForDB.SINGLE_CHOICE,questionStemContent,optionContentList);
        if (singleChoiceView==null)
            Log.d("#", "null singleChoiceView");
        View multipleChoicesView=createAndLoadIntoChoiceView(QuestionForDB.MULTIPLE_CHOICE,questionStemContent,optionContentList);
//kkkkkkkkkkkkkkkkkkk
        String gapFillingQuestionStem = "纷纷扬扬的________下了半尺多厚。天地间________的一片。我顺着________工地走了四十多公里，" +
                "只听见各种机器的吼声，可是看不见人影，也看不见工点。一进灵官峡，我就心里发慌。";

        // 答案范围集合
        List<GapFillingSpanAnswerRange> rangeList = new ArrayList<>();
        rangeList.add(new GapFillingSpanAnswerRange(5, 13));
        rangeList.add(new GapFillingSpanAnswerRange(23, 31));
        rangeList.add(new GapFillingSpanAnswerRange(38, 46));
        View gapFillingView=  createAndLoadIntoGapFillingView(gapFillingQuestionStem,rangeList);
//jjjjjjjjjjjjjjjjjjjjjjjjjj

        itemViewList.add(gapFillingView);
        itemViewList.add(singleChoiceView);
        itemViewList.add(multipleChoicesView);
        return itemViewList;
    }
    private View createAndLoadIntoChoiceView(int type,String questionStemContent,List<String> optionContentList){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.questions_container_layout,null);
        if (view!=null) {
            TextView questionStemView=view.findViewById(R.id.stem_view);
            if (questionStemView!=null){
                questionStemView.setText(questionStemContent);
            }

            RecyclerView recyclerView = view.findViewById(R.id.question_container);
            QuestionsLoader questionsLoader= new QuestionsLoader(type, optionContentList, recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(questionsLoader);}
        return view;
    }
    private View createAndLoadIntoGapFillingView(String gapFillingQuestionStem,List<GapFillingSpanAnswerRange> rangeList){
        View view=  LayoutInflater.from(getContext()).inflate(R.layout.gap_filling_layout,null);
        GapFillingView gapFillingView =view.findViewById(R.id.gap_filling_view);
        gapFillingView.loadQuestionContent(gapFillingQuestionStem, rangeList);
        return gapFillingView;

    }



}
