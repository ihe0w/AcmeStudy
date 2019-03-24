package com.example.ihe.acmestudy.Interface.ProblemSolvePage;

import android.util.SparseArray;
import android.util.SparseIntArray;

import com.example.ihe.acmestudy.Entity.Entity.ChoiceQuestion;
import com.example.ihe.acmestudy.Entity.Entity.GapFillingQuestion;
import com.example.ihe.acmestudy.Entity.Entity.MultipleChoiceQuestion;
import com.example.ihe.acmestudy.Entity.Entity.Question;
import com.example.ihe.acmestudy.Entity.Entity.QuestionInfo;
import com.example.ihe.acmestudy.Entity.Entity.SingleChoiceQuestion;
import com.example.ihe.acmestudy.Entity.Entity.UserQuestionMapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerChecker {
    private List<SparseIntArray> userSingleChoiceAnswers;
    private List<SparseArray<Integer[]>> userMultipleChoiceAnswers;
    private List<SparseArray<String[]>> userGapFillingAnswers;
    private List<QuestionInfo> questionInfos;
    private List<UserQuestionMapper> userQuestionMappers;

    public static final double RIGHT=1;
    public static final double WRONG=0;
    public static final double HALF_RIGHT=0.5;
    private static AnswerChecker INSTANCE=new AnswerChecker();
    private AnswerChecker(){
        userSingleChoiceAnswers=new ArrayList<>();
        userMultipleChoiceAnswers=new ArrayList<>();
        userGapFillingAnswers=new ArrayList<>();
        questionInfos=new ArrayList<>();
    }
    public static AnswerChecker getInstance() {
        return INSTANCE;
    }

    public List<QuestionInfo> getQuestionInfos() {
        return questionInfos;
    }

    public void setQuestionInfos(List<QuestionInfo> questionInfos) {
        this.questionInfos = questionInfos;
    }

    public void checkAnswer(){
        for (int i=0;i<questionInfos.size();i++) {
            QuestionInfo questionInfo=questionInfos.get(i);
            UserQuestionMapper userQuestionMapper=userQuestionMappers.get(i);
            if (questionInfo.getType()==QuestionInfo.SINGLE_CHOICE) {
                SingleChoiceQuestion singleChoiceQuestion=(SingleChoiceQuestion)questionInfo.getRealQuestion();
                checkSingleQuestion(userQuestionMapper.getUserAnswerId(), singleChoiceQuestion.getAnswerId());
            }
            else if (questionInfo.getType()==QuestionInfo.MULTIPLE_CHOICE){
                MultipleChoiceQuestion multipleChoiceQuestion=(MultipleChoiceQuestion)questionInfo.getRealQuestion();
                checkMultipleQuestion(userQuestionMapper.getUserAnswerIds(),multipleChoiceQuestion.getCheckedIdsWhenMultiple());
            }
            else if (questionInfo.getType()== QuestionInfo.GAP_FILLING){
                GapFillingQuestion gapFillingQuestion=(GapFillingQuestion)questionInfo.getRealQuestion();
                checkGapfillingQuestion(userQuestionMapper.getUserAnswer(),gapFillingQuestion.getAnswerPatterns());
            }
        }
    }
    private double checkSingleQuestion(int userAnswer,int realAnswer){
        if (userAnswer==realAnswer)
            return RIGHT;
        else
            return WRONG;
    }
    private double checkMultipleQuestion(boolean[] userAnswers,boolean[] realAnswers){
        int flag=0;
        for (int i=0;i<realAnswers.length;i++){
            if (userAnswers[i] && !realAnswers[i])
                return WRONG;
            if (!userAnswers[i]&&realAnswers[i])
                flag=1;

        }
        if (flag==0)
            return RIGHT;
        else
            return HALF_RIGHT;

    }
    private double checkGapfillingQuestion(List<String> userAnswers,List<String> realAnswerPattern){
        //TODO:provide basic service that's mean
        for (int i=0;i<realAnswerPattern.size();i++)
            if (!userAnswers.get(i).equals(realAnswerPattern.get(i)))
                return WRONG;
        return RIGHT;
    }

    public List<UserQuestionMapper> getUserQuestionMappers() {
        return userQuestionMappers;
    }

    public void setUserQuestionMappers(List<UserQuestionMapper> userQuestionMappers) {
        this.userQuestionMappers = userQuestionMappers;
    }

    public void updateMapperIsLike(int position,boolean isLike){
        UserQuestionMapper userQuestionMapper=userQuestionMappers.get(position);

    }
    public void updateSingleChoiceUserAnswers(int position,int answerId){
        UserQuestionMapper userQuestionMapper=userQuestionMappers.get(position);
        userQuestionMapper.setUserAnswerId(answerId);
    }
    public void updateMultipleChoiceUserAnswers(int position,boolean[] answerIds){
        UserQuestionMapper userQuestionMapper=userQuestionMappers.get(position);
        userQuestionMapper.setUserAnswerIds(answerIds);
    }
    public void updateGapfillingUserAnswers(int position,List<String> userAnswers){
        UserQuestionMapper userQuestionMapper=userQuestionMappers.get(position);
        userQuestionMapper.setUserAnswer(userAnswers);
    }
    //    public void setUserSingleChoiceAnswer(int position,int userAnswer){
//        SparseIntArray userAnswerMap=new SparseIntArray();
//        userAnswerMap.put(position,userAnswer);
//        userSingleChoiceAnswers.add(userAnswerMap);
//    }
//    public void setUserMultipleChoiceAnswers(int position,int[] userAnswers){
//        Integer[] userAnswerArray= Arrays.stream(userAnswers).boxed().toArray(Integer[]::new);
//        SparseArray<Integer[]> userAnswerMap=new SparseArray<>();
//        userAnswerMap.put(position,userAnswerArray);
//        userMultipleChoiceAnswers.add(userAnswerMap);
//    }
//    public void setUserGapFillingAnswers(int position,String[] userAnswers){
//        SparseArray<String[]> userAnswerMap=new SparseArray<>();
//        userAnswerMap.put(position,userAnswers);
//        userGapFillingAnswers.add(userAnswerMap);
//    }

}
