package com.example.ihe.acmestudy.Cache.Questions;

import com.example.ihe.acmestudy.UI.gapfilling.GapFillingSpanAnswerRange;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class QuestionForDBAgent extends LitePalSupport {
    public static final int SINGLE_CHOICE=0;
    public static final int MULTIPLE_CHOICE=1;
    public static final int GAP_FILLING=2;

    private int type;

//    private List<String> optionContentList;
//    private String questionStem;
    private static int questionId=0;
//    private List<GapFillingSpanAnswerRange> rangeList;
    //answer and answerDetail 是完全依赖于stem和optionContentList的
    private SingleChoiceQuestion singleChoiceQuestion;
    private MultipleChoiceQuestion multipleChoiceQuestion;
    private GapFillingQuestion gapFillingQuestion;
    private Question question;


    public static SingleChoiceQuestion getSingleChoiceQuestion(){
//        this.setType(QuestionForDBAgent.SINGLE_CHOICE);
        return new SingleChoiceQuestion();
    }
    public static MultipleChoiceQuestion getMultipleChoiceQuestion(){
        return new MultipleChoiceQuestion();
    }
    public static GapFillingQuestion getGapFillingQuestion(){
        return new GapFillingQuestion();
    }

    public static QuestionForDBAgent getQuestionForDBInstance(){
        return new QuestionForDBAgent();
    }



    private QuestionForDBAgent() {
        questionId++;
    }
/*作为question包内调用的构造函数*/
    public static QuestionForDBAgent getQuestionAgentBySingleChoiceQuestion(SingleChoiceQuestion singleChoiceQuestion){
        QuestionForDBAgent questionForDBAgent=new QuestionForDBAgent();
        questionForDBAgent.setType(QuestionForDBAgent.SINGLE_CHOICE);
        questionForDBAgent.setQuestionStem(singleChoiceQuestion.getQuestionStem());
        questionForDBAgent.setOptionContentList(singleChoiceQuestion.getOptionContentList());
        return questionForDBAgent;
    }
    public static QuestionForDBAgent getQuestionAgentByMultipleChoiceQuestion(MultipleChoiceQuestion multipleChoiceQuestion){
        QuestionForDBAgent questionForDBAgent=new QuestionForDBAgent();
        questionForDBAgent.setType(QuestionForDBAgent.MULTIPLE_CHOICE);
        questionForDBAgent.setQuestionStem(multipleChoiceQuestion.getQuestionStem());
        questionForDBAgent.setOptionContentList(multipleChoiceQuestion.getOptionContentList());
        return questionForDBAgent;
    }
    public static QuestionForDBAgent getQuestionAgentByGapFIllingQuestion(GapFillingQuestion gapFillingQuestion){
        QuestionForDBAgent questionForDBAgent=new QuestionForDBAgent();
        questionForDBAgent.setType(QuestionForDBAgent.GAP_FILLING);
        questionForDBAgent.setQuestionStem(gapFillingQuestion.getQuestionStem());
        questionForDBAgent.setRangeList(gapFillingQuestion.getRangeList());
        return questionForDBAgent;
    }
    public static int getQuestionId() {
        return questionId;
    }
    /*向外封装的函数*/
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        if (type==QuestionForDBAgent.SINGLE_CHOICE){
            question=new SingleChoiceQuestion();

        }
        else if (type==QuestionForDBAgent.MULTIPLE_CHOICE){
            question =new MultipleChoiceQuestion();

        }
        else if (type==QuestionForDBAgent.GAP_FILLING) {
            question = new GapFillingQuestion();
        }

    }

    public List<String> getOptionContentList() {
        if (question instanceof ChoiceQuestion)
            return ((ChoiceQuestion) question).getOptionContentList();
        else
            return null;
    }

    public void setOptionContentList(List<String> optionContentList) {
        if (question instanceof ChoiceQuestion)
            ((ChoiceQuestion) question).setOptionContentList(optionContentList);
//        this.optionContentList = optionContentList;
    }

    public String getQuestionStem() {

        return question.getQuestionStem();
    }

    public void setQuestionStem(String questionStem) {
        question.setQuestionStem(questionStem);
//        this.questionStem = questionStem;
    }
    public List<GapFillingSpanAnswerRange> getRangeList() {
        if (question instanceof GapFillingQuestion)
            return ((GapFillingQuestion) question).getRangeList();
        else
            return null;
    }

    public void setRangeList(List<GapFillingSpanAnswerRange> rangeList) {
        if (question instanceof GapFillingQuestion)
            ((GapFillingQuestion) question).setRangeList(rangeList);
//        this.rangeList = rangeList;
    }


}

