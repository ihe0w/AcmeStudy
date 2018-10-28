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

import com.example.ihe.acmestudy.Cache.QuestionForDB;
import com.example.ihe.acmestudy.Compatibility.AcmeContext;
import com.example.ihe.acmestudy.R;

import java.util.ArrayList;
import java.util.List;

//根据PageLoader传来的试题类型信息，与cache传来的题目，加载相应的试题项
public class QuestionsLoader extends RecyclerView.Adapter<QuestionsLoader.QuestionHolder> {
    private List<String> optionContentList; //选择题的list
    private View loadedView; //使用这个QuestionLoader的view
    private int type;
    private char option_letter='A';
    private  int optionId =1; //单选按钮id
    private OptionSelectedListener optionSelectedListener;
    QuestionsLoader(int type, List<String> optionContentList, RecyclerView recyclerView) {
        Log.d("#", "QL start");
        this.type = type;
        this.optionContentList =optionContentList;
        loadedView=recyclerView;     //?我很不想把RV直接传进去，但我现在不知道还有什么更好的办法没有

        if (type==QuestionForDB.SINGLE_CHOICE){
            optionSelectedListener=new OptionSelectedListener(true,loadedView,getItemCount());
        }
        else if (type==QuestionForDB.MULTIPLE_CHOICE){
            optionSelectedListener=new OptionSelectedListener(false,loadedView,getItemCount());
        }
        else if (type==QuestionForDB.GAP_FILLING){

        }
        Log.d("#", "QL end");

    }

    static class QuestionHolder extends RecyclerView.ViewHolder {
        TextView optionContentView;
        Button button;
        CardView itemOptionCard;
        QuestionHolder(View itemView) {
            super(itemView);
            Log.d("#", "QH start");
            button = itemView.findViewById(R.id.option);
            optionContentView = itemView.findViewById(R.id.option_content);
            itemOptionCard=itemView.findViewById(R.id.single_choice_card);
            Log.d("#", "QH end");
        }
    }
    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("#", "onCreateViewHolder: ");
        View view=null;
        if (type==QuestionForDB.SINGLE_CHOICE) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_choice_question_layout, parent, false);
        }
        else if (type==QuestionForDB.MULTIPLE_CHOICE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_choice_question_layout, parent, false);
        }
        if (view==null)
            Log.d("#", "onCreateViewHolder: null view");
        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        if (type==QuestionForDB.GAP_FILLING){

        }else{
            loadEachButton(holder.button,holder.optionContentView,holder.itemOptionCard,position);
        }
    }

    @Override
    public int getItemCount() {
        return optionContentList.size();
    }
        //为button设置单选功能
        private void loadEachButton(Button optionButton, TextView optionContentView, CardView itemOptionCard, int position){
                String optionContent=optionContentList.get(position);

                optionButton.setId(optionId);
                optionButton.setText(Character.toString(option_letter));
                optionContentView.setText(optionContent);
                optionId++;
                option_letter++;

            optionButton.setOnClickListener(optionSelectedListener);
            optionContentView.setOnClickListener(optionSelectedListener);
            itemOptionCard.setOnClickListener(optionSelectedListener);
        }




}
