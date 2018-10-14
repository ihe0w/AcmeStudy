package com.example.ihe.acmestudy.DataAcquire;

public class JsonProblem {
    private int type;
    private String[] contentList;

    public String[] getContentList() {
        return contentList;
    }

    public void setContentList(String[] contentList) {
        this.contentList = contentList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
