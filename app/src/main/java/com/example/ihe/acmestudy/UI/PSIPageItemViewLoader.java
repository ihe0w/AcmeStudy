package com.example.ihe.acmestudy.UI;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.ihe.acmestudy.Cache.Questions.QuestionForDBAgent;
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
    List<View> loadQuestionIntoViews(QuestionsPacket questionsPacket){
        View questionView=null;
        List<View> itemViewList = new ArrayList<>();
        for (QuestionForDBAgent question:questionsPacket.getQuestionForDBAgentList()) {
            if (question.getType()==QuestionForDBAgent.SINGLE_CHOICE){
                questionView=createAndLoadIntoChoiceView(QuestionForDBAgent.SINGLE_CHOICE,question.getQuestionStem(),question.getOptionContentList());
                questionView.setTag(QuestionForDBAgent.SINGLE_CHOICE);
            }
            else if (question.getType()==QuestionForDBAgent.MULTIPLE_CHOICE){
                questionView=createAndLoadIntoChoiceView(QuestionForDBAgent.MULTIPLE_CHOICE,question.getQuestionStem(),question.getOptionContentList());
                questionView.setTag(QuestionForDBAgent.MULTIPLE_CHOICE);
            }
            else if (question.getType()==QuestionForDBAgent.GAP_FILLING){
                questionView=createAndLoadIntoGapFillingView(question.getQuestionStem(),question.getRangeList());
                questionView.setTag(QuestionForDBAgent.GAP_FILLING);
            }
            itemViewList.add(questionView);
        }
        return itemViewList;
    }
    private View createAndLoadIntoChoiceView(int type,String questionStemContent,List<String> optionContentList){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.questions_container_layout,null);
        if (view!=null) {
            TextView questionStemView=view.findViewById(R.id.stem_view);
            if (questionStemView!=null){
                questionStemView.setText(questionStemContent);
            }

            ChoiceQuestionView choiceQuestionView = view.findViewById(R.id.question_container);
            choiceQuestionView.setType(type);
            ChoiceQuestionsLoader choiceQuestionsLoader = new ChoiceQuestionsLoader(type, optionContentList, choiceQuestionView);
            choiceQuestionView.setLayoutManager(new LinearLayoutManager(context));
            choiceQuestionView.setAdapter(choiceQuestionsLoader);}
        return view;
    }
    private View createAndLoadIntoGapFillingView(String gapFillingQuestionStem,List<GapFillingSpanAnswerRange> rangeList){
        View view=  LayoutInflater.from(getContext()).inflate(R.layout.gap_filling_layout,null);
        GapFillingView gapFillingView =view.findViewById(R.id.gap_filling_view);
        gapFillingView.loadQuestionContent(gapFillingQuestionStem, rangeList);
        return view;

    }



}
