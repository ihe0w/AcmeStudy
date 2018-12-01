package com.example.ihe.acmestudy.UI;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.ihe.acmestudy.Cache.Questions.QuestionForDBAgent;
import com.example.ihe.acmestudy.UI.gapfilling.GapFillingListener;
import com.example.ihe.acmestudy.UI.gapfilling.GapFillingView;

import java.util.List;

public class AnswerValidater {
//    List<View> pages;
//    AnswerValidater(List<View> pages){
//        this.pages=pages;
////    }

    public void validateAnswer(Object listener,int type){
        if (listener instanceof OptionSelectedListener&&type==QuestionForDBAgent.SINGLE_CHOICE){

        }
        else if (listener instanceof OptionSelectedListener&&type==QuestionForDBAgent.MULTIPLE_CHOICE){

        }
        else if (listener instanceof GapFillingListener&&type==QuestionForDBAgent.GAP_FILLING){

        }


    }

//    private void collectUserAnswersFromSingleChoice(){
//
//    }
//    private void collectUserAnswersFromMultipleChoice(){
//
//    }
//    private void collectUserAnswersFromGapFilling(GapFillingView gapFillingView){
//
//    }
    private void updateSingleChoiceCorrecting(){

    }
    private void updateMultipleChoiceCorrecting(){

    }
    private void updateGapFillingCorrecting(GapFillingView gapFillingView){

    }
//    private int[] checkBlankAnswerByRegularExpression(){
//
//    }
//    private UserAnswerPerformanceData generateUserPerformanceData(){
//
//    }
//    private Map<Integer,List<Integer>> singleAnswerList;
//    private Map<Integer,List<Integer[]>> multipleAnswerList;
//    private Map<Integer,List<String>> gapFillingAnswerList;
//
//    public void setSingleAnswerList(Map<Integer, List<Integer>> singleAnswerList) {
//        this.singleAnswerList = singleAnswerList;
//    }
//
//    public void setMultipleAnswerList(Map<Integer, List<Integer[]>> multipleAnswerList) {
//        this.multipleAnswerList = multipleAnswerList;
//    }
//
//    public void setGapFillingAnswerList(Map<Integer, List<String>> gapFillingAnswerList) {
//        this.gapFillingAnswerList = gapFillingAnswerList;
//    }

}
