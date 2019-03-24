package com.example.ihe.acmestudy.Interface.ProblemSolvePage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ihe.acmestudy.Entity.Entity.GapFillingQuestion;
import com.example.ihe.acmestudy.Entity.Entity.MultipleChoiceQuestion;
import com.example.ihe.acmestudy.Entity.Entity.QuestionInfo;
import com.example.ihe.acmestudy.Entity.Entity.SingleChoiceQuestion;
import com.example.ihe.acmestudy.Entity.Entity.UserQuestionMapper;

import java.util.ArrayList;
import java.util.List;
/**adapter 实际上也充当了QuestionItemFragment的管理器，它管理着所有的QIF，并且充当PSI向fragment传递信息的中介*/
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private static List<QuestionItemFragment> questionItemFragments=new ArrayList<>();

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
            if (position== ProblemSolveActivity.packetSize)
                return new AnswerSheetFragment();
            if (questionItemFragments.size()<=position)
                questionItemFragments.add(position,QuestionItemFragment.newInstance(position));
            return questionItemFragments.get(position);
    }
    public void setUserAnswer(){
        for (int i=0;i<questionItemFragments.size();i++) {
            QuestionItemFragment questionItemFragment=questionItemFragments.get(i);
            UserQuestionMapper userQuestionMapper=ProblemSolveActivity.userQuestionMappers.get(i);
            if (questionItemFragment.getType()== QuestionInfo.SINGLE_CHOICE) {
                userQuestionMapper.setUserAnswerId(questionItemFragment.getAnswerId());
            }
            else if (questionItemFragment.getType()==QuestionInfo.MULTIPLE_CHOICE){
                userQuestionMapper.setUserAnswerIds(questionItemFragment.getMultipleCheckedIds());
            }
            else if (questionItemFragment.getType()== QuestionInfo.GAP_FILLING){
                userQuestionMapper.setUserAnswer(questionItemFragment.getAnswerList());
            }
        }
    }

    @Override
    public int getCount() {
        return ProblemSolveActivity.packetSize+1;
    }

}
