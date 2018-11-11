package com.example.ihe.acmestudy.Cache.Questions;
import com.example.ihe.acmestudy.Cache.QuestionsPacket;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/*管理手机本地的QuestionCache，在本地阶段时通过这个接口输入提取试题，在云阶段时作为试题的缓存
* 本地时：我想只调用*/
public class CacheManager {
    public void loadQuestionClassIntoCache(Question question){
        if (question instanceof SingleChoiceQuestion){
            SingleChoiceQuestion singleChoiceQuestion=(SingleChoiceQuestion)question;
            singleChoiceQuestion.save();
        }
        else if (question instanceof MultipleChoiceQuestion){
            MultipleChoiceQuestion multipleChoiceQuestion=(MultipleChoiceQuestion)question;
            multipleChoiceQuestion.save();
        }
        else if (question instanceof GapFillingQuestion){
            GapFillingQuestion gapFillingQuestion=(GapFillingQuestion)question;
            gapFillingQuestion.save();
        }
    }

    private boolean loadSingleChoiceQuestionIntoCache(){
        return true;
    }
    private boolean loadMultipleChoiceQuestion(){
        return true;
    }
    private boolean loadGapFillingQuestion(){
        return true;
    }
    public Question findQuestionByIdAndType(int questionId, int type){
        if (type==QuestionForDBAgent.SINGLE_CHOICE){
            return LitePal.find(SingleChoiceQuestion.class,questionId);
        }
        else if (type==QuestionForDBAgent.MULTIPLE_CHOICE){
            return LitePal.find(MultipleChoiceQuestion.class,questionId);
        }
        else if (type==QuestionForDBAgent.GAP_FILLING){
            return LitePal.find(GapFillingQuestion.class,questionId);
        }
        return null;
    }
    public QuestionsPacket getQuestionPacket(){
        QuestionsPacket questionsPacket=new QuestionsPacket();
        List<QuestionForDBAgent> questionForDBAgents=new ArrayList<>();

        questionsPacket.addQuestion(QuestionForDBAgent.getQuestionAgentBySingleChoiceQuestion((SingleChoiceQuestion) findQuestionByIdAndType(1,QuestionForDBAgent.SINGLE_CHOICE)));
        questionsPacket.addQuestion(QuestionForDBAgent.getQuestionAgentByMultipleChoiceQuestion((MultipleChoiceQuestion)findQuestionByIdAndType(1,QuestionForDBAgent.MULTIPLE_CHOICE)));
        return questionsPacket;
    }
}
