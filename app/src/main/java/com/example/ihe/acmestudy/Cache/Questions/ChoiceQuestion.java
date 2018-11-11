package com.example.ihe.acmestudy.Cache.Questions;

import java.util.List;

class ChoiceQuestion extends Question {
    private List<String> optionContentList;
    public List<String> getOptionContentList() {
        return optionContentList;
    }

    public void setOptionContentList(List<String> optionContentList) {
        this.optionContentList = optionContentList;
    }
}
