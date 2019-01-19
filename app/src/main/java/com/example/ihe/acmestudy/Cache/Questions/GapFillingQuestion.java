package com.example.ihe.acmestudy.Cache.Questions;

import com.example.ihe.acmestudy.View.gapfilling.GapFillingSpanAnswerRange;

import java.util.List;

class GapFillingQuestion extends Question {
    private List<GapFillingSpanAnswerRange> rangeList;
    private List<String> answerPatterns;
    private static int questionId=0;

    GapFillingQuestion(){
        questionId++;
    }

    public static int getQuestionId() {
        return questionId;
    }

    public static void setQuestionId(int questionId) {
        GapFillingQuestion.questionId = questionId;
    }

    public List<String> getAnswerPatterns() {
        return answerPatterns;
    }

    public void setAnswerPatterns(List<String> answerPatterns) {
        this.answerPatterns = answerPatterns;
    }

    public List<GapFillingSpanAnswerRange> getRangeList() {
        return rangeList;
    }

    public void setRangeList(List<GapFillingSpanAnswerRange> rangeList) {
        this.rangeList = rangeList;
    }
}
