package com.example.ihe.acmestudy.Cache;

import com.example.ihe.acmestudy.Cache.Questions.QuestionForDBAgent;

import java.util.ArrayList;
import java.util.List;

public class QuestionsPacket {
    private int[] questionPacketHead;
    private List<QuestionForDBAgent> questionForDBAgentList;
    public QuestionsPacket(){
        questionForDBAgentList =new ArrayList<>();
    }

    public void setQuestionForDBAgentList(List<QuestionForDBAgent> questionForDBAgentList) {
        this.questionForDBAgentList = questionForDBAgentList;
    }

    public List<QuestionForDBAgent> getQuestionForDBAgentList() {      //这个试题包必须严格按顺序
        return questionForDBAgentList;
    }

    public void addQuestion(QuestionForDBAgent questionForDBAgent) {
        questionForDBAgentList.add(questionForDBAgent);
    }
    public int[] getQuestionPacketHead() {
        return questionPacketHead;
    }

    public void setQuestionPacketHead(int[] questionPacketHead) {
        this.questionPacketHead = questionPacketHead;
    }
}
