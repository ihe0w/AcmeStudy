package com.example.ihe.acmestudy.Cache.Questions;

public class SingleChoiceQuestion extends ChoiceQuestion {
     private int answerId;
     private static int questionId=0;

     SingleChoiceQuestion(){
         questionId++;
     }
     public static int getQuestionId() {
         return questionId;
     }

     public static void setQuestionId(int questionId) {
         SingleChoiceQuestion.questionId = questionId;
     }

     public int getAnswerId() {
        return answerId;
    }

     public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
}
