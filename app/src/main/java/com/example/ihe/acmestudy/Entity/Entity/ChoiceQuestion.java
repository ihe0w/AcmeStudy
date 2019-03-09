package com.example.ihe.acmestudy.Entity.Entity;

import java.util.List;

public class ChoiceQuestion extends Question {
    private List<String> optionContentList;
    public List<String> getOptionContentList() {
        return optionContentList;
    }

    public void setOptionContentList(List<String> optionContentList) {
        this.optionContentList = optionContentList;
    }
}
