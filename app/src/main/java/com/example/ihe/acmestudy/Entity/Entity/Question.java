package com.example.ihe.acmestudy.Entity.Entity;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Question extends LitePalSupport implements Serializable {

    private String questionStem;


    public String getQuestionStem() {
        return questionStem;
    }

    public void setQuestionStem(String questionStem) {
        this.questionStem = questionStem;
    }
}
