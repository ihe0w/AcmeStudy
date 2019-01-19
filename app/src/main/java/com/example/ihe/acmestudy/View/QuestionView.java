package com.example.ihe.acmestudy.View;

public interface QuestionView {
   //state unSelected is 0,select is 1,right is 2,wrong is 3
    void updateComponentsState(int state,int componentId);
    UserAnswer getUserAnswer();
}
