package com.example.ihe.acmestudy.common;

import com.example.ihe.acmestudy.Interface.ProblemSolvePage.UserAnswer;

public interface QuestionView {
   //state unSelected is 0,select is 1,right is 2,wrong is 3
    void updateComponentsState(int state,int componentId);
    UserAnswer getUserAnswer();
}
