package com.example.ihe.acmestudy.DataProcess;

import com.example.ihe.acmestudy.Cache.Questions.QuestionForDBAgent;
import com.example.ihe.acmestudy.Cache.QuestionsPacket;
import com.example.ihe.acmestudy.View.gapfilling.GapFillingSpanAnswerRange;

import java.util.ArrayList;
import java.util.List;

public class tempBuildQuestionManager {
    private QuestionsPacket questionsPacket;
    public tempBuildQuestionManager(){
        questionsPacket=new QuestionsPacket();

        QuestionForDBAgent singleChoiceQuestion=QuestionForDBAgent.getQuestionForDBInstance();
        singleChoiceQuestion.setType(QuestionForDBAgent.SINGLE_CHOICE);
        QuestionForDBAgent mutipleChioceQuestion=QuestionForDBAgent.getQuestionForDBInstance();
        mutipleChioceQuestion.setType(QuestionForDBAgent.MULTIPLE_CHOICE);
        QuestionForDBAgent gapFillingQuestion=QuestionForDBAgent.getQuestionForDBInstance();
        gapFillingQuestion.setType(QuestionForDBAgent.GAP_FILLING);

        List<String> optionContentList =new ArrayList<>();
        String questionStemContent="下列正确的是？这些问题都跟哲学有关。\n" +
                "All these questions relate to philosophy";
        optionContentList.add("1+1=2");
        optionContentList.add("1+1=3");
        optionContentList.add("1+1=4");
        optionContentList.add("1-1=2");
        singleChoiceQuestion.setOptionContentList(optionContentList);
        singleChoiceQuestion.setQuestionStem(questionStemContent);

        mutipleChioceQuestion.setQuestionStem(questionStemContent);
        mutipleChioceQuestion.setOptionContentList(optionContentList);

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
        questionsPacket.addQuestion(singleChoiceQuestion);
        questionsPacket.addQuestion(mutipleChioceQuestion);
        questionsPacket.addQuestion(gapFillingQuestion);
    }
    public QuestionsPacket getQuestionsPacket() {
        return questionsPacket;
    }

    public void setQuestionsPacket(QuestionsPacket questionsPacket) {
        this.questionsPacket = questionsPacket;
    }
}