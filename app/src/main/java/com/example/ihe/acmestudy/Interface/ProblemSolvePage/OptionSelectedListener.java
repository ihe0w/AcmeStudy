package com.example.ihe.acmestudy.Interface.ProblemSolvePage;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ihe.acmestudy.common.AcmeApplication;
import com.example.ihe.acmestudy.R;

public class OptionSelectedListener implements View.OnClickListener {

    private boolean IsSingle;
    private int CheckedId =-1;//被选择单选按钮的id
    private boolean CheckedIdsWhenMultiple[];
    private Button checkingButton;
    private Button checkedButton;
    private View loadedView;

    public OptionSelectedListener(boolean IsSingle, View loadedView,int questionNumber){
        this.IsSingle = IsSingle;
        this.loadedView=loadedView;
        CheckedIdsWhenMultiple=new boolean[questionNumber+1];
    }
    public void setButtonState(int buttonId,double state){
         setSelectedState(loadedView.findViewById(buttonId),false,state);
    }

    private void setSelectedState(Button button, boolean selectedState, double state){
        if (state<0) {
            if (selectedState) {
                if (IsSingle)
                    button.setBackgroundResource(R.drawable.circle_pressed);
                else
                    button.setBackgroundResource(R.drawable.multiple_pressed_img);
                button.setTextColor(ContextCompat.getColor(AcmeApplication.getContext(), R.color.colorText));
            } else {
                if (IsSingle)
                    button.setBackgroundResource(R.drawable.circle_unpressed);
                else
                    button.setBackgroundResource(R.drawable.multiple_img);
                button.setTextColor(ContextCompat.getColor(AcmeApplication.getContext(), R.color.colorPrimaryDark));
            }
        }
        else {
            if (IsSingle){
                if (state==AnswerChecker.RIGHT)
                    button.setBackgroundResource(R.drawable.right_option_single);
                else
                    button.setBackgroundResource(R.drawable.wrong_option_single);
            }else {
                if (state==AnswerChecker.RIGHT)
                    button.setBackgroundResource(R.drawable.right_option_multiple);
                else if (state==AnswerChecker.WRONG) {
                    button.setBackgroundResource(R.drawable.wrong_option_multiple);
                }else
                    button.setBackgroundResource(R.drawable.wrong_option_multiple);
                //TODO:I must change the color in future!

            }
        }
    }
    /*我给button，textview，cardview都监听了，无法判断是否点击了重复的按钮,although i doubt that should set so many Listeners?
     */
    private Button getCheckingButton(View v){
        checkingButton=null;
        if (v instanceof Button) {
            checkingButton = (Button) v;
        }
        else if (v instanceof TextView){
            View parent_view=(View)v.getParent();
            checkingButton=parent_view.findViewWithTag("single_button");
        }
        else if (v instanceof CardView) {
            checkingButton=v.findViewWithTag("single_button");
        }
        return checkingButton;

    }
    private void unSelectCheckedButtonWhenSingle(){
        if (CheckedId != -1) {
            checkedButton = loadedView.findViewById(CheckedId);
            if (checkedButton != null) {
                setSelectedState(checkedButton, false,-1 );
            }
            else
                Log.d("#", "onClickEventWhenSingle: ");
        }
    }
    private void onClickEventWhenSingle(){
        unSelectCheckedButtonWhenSingle();
        if (this.checkingButton !=null){
            setSelectedState(this.checkingButton, true, -1);
            CheckedId = checkingButton.getId();
        }
    }
    private void onClickEventWHenMultiple(){
        int checkingId=checkingButton.getId();
        if (CheckedIdsWhenMultiple[checkingId]){
            setSelectedState(this.checkingButton,false,-1 );
            CheckedIdsWhenMultiple[checkingId]=false;
        }
        else {
            setSelectedState(this.checkingButton,true, -1);
            CheckedIdsWhenMultiple[checkingId]=true;
        }
    }
    @Override
    public void onClick(View v) {
        checkingButton=getCheckingButton(v);
        if (IsSingle){
            onClickEventWhenSingle();
        }
        else{
            onClickEventWHenMultiple();
        }
    }

    public int getAnswerId(){
        return CheckedId;
    }
    public boolean[] getMultipleCheckedIds(){
        return CheckedIdsWhenMultiple;
    }
}
