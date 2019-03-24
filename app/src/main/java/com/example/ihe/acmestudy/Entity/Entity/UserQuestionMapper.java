package com.example.ihe.acmestudy.Entity.Entity;
import java.sql.Timestamp;
import java.util.List;

public class UserQuestionMapper {
    private int questionId;
    private int userId;
    private int relation;
    private double accuracy;
    private boolean isLike;
    private int frequency;
    private Timestamp lastTimeDone;
    private boolean isError;
    private int questionType;
    private int userAnswerId;
    private boolean[] userAnswerIds;
    private List<String> userAnswer;
    //TODO:what should I do in db?

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Timestamp getLastTimeDone() {
        return lastTimeDone;
    }

    public void setLastTimeDone(Timestamp lastTimeDone) {
        this.lastTimeDone = lastTimeDone;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public boolean[] getUserAnswerIds() {
        return userAnswerIds;
    }

    public void setUserAnswerIds(boolean[] userAnswerIds) {
        this.userAnswerIds = userAnswerIds;
    }

    public int getUserAnswerId() {
        return userAnswerId;
    }

    public void setUserAnswerId(int userAnswerId) {
        this.userAnswerId = userAnswerId;
    }

    public List<String> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(List<String> userAnswer) {
        this.userAnswer = userAnswer;
    }
}

