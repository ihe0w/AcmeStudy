package com.example.ihe.acmestudy.Entity.Entity;

import java.io.Serializable;
import java.sql.Time;

public class QuestionInfo implements Serializable {
    public static final int SINGLE_CHOICE=0;
    public static final int MULTIPLE_CHOICE=1;
    public static final int GAP_FILLING=2;
    private int type;
    private int question_id;
    private String scope;
    private String tag;
    private double accuracy;
    private Time averageCostTime;

    private Question realQuestion;
    private int doQuestionUsersCount;


    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public Time getAverageCostTime() {
        return averageCostTime;
    }

    public void setAverageCostTime(Time averageCostTime) {
        this.averageCostTime = averageCostTime;
    }

    public Question getRealQuestion() {
        return realQuestion;
    }

    public void setRealQuestion(Question realQuestion) {
        this.realQuestion = realQuestion;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getDoQuestionUsersCount() {
        return doQuestionUsersCount;
    }

    public void setDoQuestionUsersCount(int doQuestionUsersCount) {
        this.doQuestionUsersCount = doQuestionUsersCount;
    }

//    public static final Parcelable.Creator<QuestionInfo> CREATOR= new Creator<QuestionInfo>() {
//        @Override
//        public QuestionInfo createFromParcel(Parcel source) {
//            QuestionInfo questionInfo=new QuestionInfo();
//            questionInfo.type=source.readInt();
//        }
//
//        @Override
//        public QuestionInfo[] newArray(int size) {
//            return new QuestionInfo[size];
//        }
//    }
//    @Override
//    public int describeContents() {
//        return 0;
//    }
////    private int type;
////    private int question_id;
////    private String scope;
////    private String tag;
////    private double accuracy;
////    private Time averageCostTime;
////
////    private Question realQuestion;
////    private int doQuestionUsersCount;
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(type);
//        dest.writeInt(question_id);
//        dest.writeString(scope);
//        dest.writeString(tag);
//        dest.writeDouble(accuracy);
////        dest.writeTypedObject();
//        dest.writeValue(averageCostTime);
//
//        //how to write Question and Time?
//
//    }

}
