package com.example.ihe.acmestudy.Cache.Questions;

import org.litepal.crud.LitePalSupport;

public class Question extends LitePalSupport {

    private String questionStem;

    public String getQuestionStem() {
        return questionStem;
    }

    public void setQuestionStem(String questionStem) {
        this.questionStem = questionStem;
    }
}
