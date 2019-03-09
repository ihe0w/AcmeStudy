package com.example.ihe.acmestudy.Interface.ProblemSolvePage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.ihe.acmestudy.Interface.ProblemSolvePage.ProblemSolveInterface;
import com.example.ihe.acmestudy.Interface.ProblemSolvePage.AnswerSheetFragment;
import com.example.ihe.acmestudy.Interface.ProblemSolvePage.QuestionItemFragment;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        Log.d("#", "ViewPagerFragmentAdapter: ");
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("#", "getItem: ");
        if (position== ProblemSolveInterface.packetSize)
            return new AnswerSheetFragment();
        Log.d("#", "before fragment");
        return QuestionItemFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return ProblemSolveInterface.packetSize+1;
    }
}
