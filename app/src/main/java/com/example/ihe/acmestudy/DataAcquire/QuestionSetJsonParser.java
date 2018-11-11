package com.example.ihe.acmestudy.DataAcquire;

import com.example.ihe.acmestudy.Cache.Questions.QuestionForDBAgent;
import com.example.ihe.acmestudy.Cache.QuestionsPacket;
import com.example.ihe.acmestudy.DataProcess.tempBuildQuestionManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class QuestionSetJsonParser {
    private Gson gson;
    List<QuestionForDBAgent> questionForDBAgentList;
    private void parseQuestionSet(){
                                          //网络异常处理以及解码
    }
//    public QuestionsPacket getParsedQuestionPacket(){
//        parseQuestionSet();
//
//    }
    private void parseQuestion(String jsonQuestion ){
        gson=new Gson();
        List<QuestionForDBAgent> questionForDBAgentList =gson.fromJson(jsonQuestion,new TypeToken<List<QuestionForDBAgent>>(){}.getType());

    }
    public QuestionsPacket getQuestionPacket(){
        tempBuildQuestionManager tempBuildQuestionManager=new tempBuildQuestionManager();
        return tempBuildQuestionManager.getQuestionsPacket();
    }
}
