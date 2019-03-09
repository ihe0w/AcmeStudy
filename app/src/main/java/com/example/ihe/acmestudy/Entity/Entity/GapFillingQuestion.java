package com.example.ihe.acmestudy.Entity.Entity;

import com.example.ihe.acmestudy.Interface.gapfilling.GapFillingSpanAnswerRange;

import java.util.List;

public class GapFillingQuestion extends Question {
    private List<GapFillingSpanAnswerRange> rangeList;
    private List<String> answerPatterns;




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
