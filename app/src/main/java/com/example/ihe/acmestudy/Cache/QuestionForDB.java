package com.example.ihe.acmestudy.Cache;

public class QuestionForDB {
    public static final int SINGLE_CHOICE=0;
    public static final int MULTIPLE_CHOICE=1;
    public static final int GAP_FILLING=2;

    private int type;
    private String[] optionContentList;
    private String questionStem;
    public String[] getOptionContentList() {
        return optionContentList;
    }

    public void setOptionContentList(String[] optionContentList) {
        this.optionContentList = optionContentList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
