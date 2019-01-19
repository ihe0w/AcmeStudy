package com.example.ihe.acmestudy.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Button;

public class ChoiceQuestionView extends RecyclerView implements QuestionView{
    static final int WRONG_STATE=3;
    static final int SELECTED_STATE=1;
    static final int UNSELECTED_STATE=0;
    static final int RIGHT_STATE=2;
    static final int CHECK_STATE_SCOPE=2;
    int type;

    public void setType(int type) {
        this.type = type;
    }

    public ChoiceQuestionView(Context context) {
        super(context);
    }

    public ChoiceQuestionView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ChoiceQuestionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void updateComponentsState(int state, int componentId) {
        if (state>=CHECK_STATE_SCOPE){
            Button button=this.findViewById(componentId);
            if (state==2){

            }else if (state==3){

            }
        }

    }


    @Override
    public UserAnswer getUserAnswer() {
        return null;
    }
}
