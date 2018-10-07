package com.example.ihe.acmestudy.UI;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ihe.acmestudy.Compatibility.AcmeContext;
import com.example.ihe.acmestudy.R;

import java.util.ArrayList;
import java.util.List;

//根据PageLoader传来的试题类型信息，与cache传来的题目，加载相应的试题项
public class QuestionsLoader extends RecyclerView.Adapter<QuestionsLoader.QuestionHolder> {
    private List<String> optionContentList = new ArrayList<>(); //选择题的list
    private static int i=0;
    private View loadedView;
    private boolean IsSingle;
    private char option_letter='A';
    private OptionRadioGroup optionRadioGroup;
    QuestionsLoader(boolean IsSingle, List<String> optionContentList, RecyclerView recyclerView) {
        this.IsSingle = IsSingle;
        this.optionContentList =optionContentList;
        loadedView=recyclerView;     //?我很不想把RV直接传进去，但我现在不知道还有什么更好的办法没有
        if (IsSingle){
             optionRadioGroup =new OptionRadioGroup();
        }
    }

    static class QuestionHolder extends RecyclerView.ViewHolder {
        TextView optionContentView;
        Button button;
        CardView itemOptionCard;
        QuestionHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.option);
            optionContentView = itemView.findViewById(R.id.option_content);
            itemOptionCard=itemView.findViewById(R.id.single_choice_card);
            Log.d("#", "QuestionHolder: " + itemView+itemView.getId());
            Log.d("#", "QuestionHolder: " + itemOptionCard+itemOptionCard.getId());

//            if (itemView instanceof CardView) {
//                itemOptionCard = (CardView) itemView;
//
////            if (itemView instanceof CardView) {
////
//                if (itemOptionCard == null) {
//                    Log.d("#", " find cardView is null");
//                }
////                i++;
////                Log.d("#", "QuestionHolder: " + i);
////            }
//            }
        }
    }
    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_choice_question_layout,parent,false);
        QuestionHolder questionHolder=new QuestionHolder(view);
        return questionHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        String optionContent=optionContentList.get(position);
        holder.button.setText(Character.toString(option_letter));          //for radio question
        holder.optionContentView.setText(optionContent);
        if (holder.button !=null)
        { optionRadioGroup.addRadio(holder.button);}
        else
            Log.d("#", "addRadio: cardview is null");

//        Log.d("#", "#onBindViewHolder: "+holder.itemView);
//        if (holder.itemOptionCard !=null)
//        { optionRadioGroup.addRadio(holder.itemOptionCard);}
//        else
//            Log.d("#", "addRadio: cardview is null");
        option_letter++;
    }

    @Override
    public int getItemCount() {
        return optionContentList.size();
    }

    private class OptionRadioGroup {
        private int CheckedId =-1;//被选择按钮的id
        private  int optionId =1; //单选按钮id
        private SelectedListener selectedListener=new SelectedListener(true);
        OptionRadioGroup(){
            selectedListener.setCheckedId(CheckedId);
        }
        void addRadio(Button optionButton){    //为button设置单选功能

                optionButton.setId(optionId);
//                optionButton.findViewWithTag("single_button").setId(optionId);
                optionButton.setOnClickListener(selectedListener);
                Log.d("#", "#addRadio: " + optionId + "the getId is " + optionButton.getId());
                optionId++;

//            if (button.isChecked()) {
//                mProtectFromCheckedChange = true;
//                if (CheckedId != -1) {
//                    setCheckedStateForButton(CheckedId, false);
//                }
//                mProtectFromCheckedChange = false;
//                setCheckedId(button.getId());
//            }
        }
//        private void setCheckedStateForButton(int optionId, boolean checked) {
//            View checkedView = findViewById(optionId);
//            if (checkedView != null && checkedView instanceof Button) {
//                ((Button) checkedView).setChecked(checked);
//            }
//
//
//
//    }
    }

    private void setSelectedState(Button button, boolean selectedState){
        if (selectedState){
            button.setBackgroundResource(R.drawable.circle);
            button.setTextColor(ContextCompat.getColor(AcmeContext.getContext(),R.color.colorText));
        }
        else {
            button.setBackgroundResource(R.drawable.circle__pressed);
            button.setTextColor(ContextCompat.getColor(AcmeContext.getContext(),R.color.colorPrimaryDark));
        }
    }
    private class SelectedListener implements OnClickListener{

        boolean IsSingle;
        int CheckedId;
        SelectedListener(boolean IsSingle){
            this.IsSingle = IsSingle;
        }
        //如果是单选按钮
        void setCheckedId(int checkedId) {
            CheckedId = checkedId;
        }
        @Override
        public void onClick(View v) {

            Log.d("#", "#onClick: "+CheckedId);
            if (IsSingle){
//                if (v instanceof CardView) {
                    if (CheckedId != -1) {
                        Button checkedButton = loadedView.findViewById(CheckedId);
                        if (checkedButton != null) {
                            setSelectedState(checkedButton, false);
                        } else
                            Log.d("#", "onClick: checkedButton is null");

                    }
                    Log.d("#", "onClick: checked over"+CheckedId);
                Button checkingButton;
                    if (v instanceof Button) {
                        checkingButton = (Button) v;
                        setSelectedState(checkingButton, true);
                        CheckedId = checkingButton.getId();
                    }
//                    Button checkingButton =  v.findViewWithTag("single_button");

                    Log.d("#", "onClick: checrckedId"+CheckedId+"hello"+i);
                    i++;

                }
//            }
        }
    }

}
