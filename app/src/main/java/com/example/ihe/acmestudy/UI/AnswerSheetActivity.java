package com.example.ihe.acmestudy.UI;

import android.content.Intent;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.ihe.acmestudy.Compatibility.TransmitterAmongInterfaces;
import com.example.ihe.acmestudy.R;

public class AnswerSheetActivity extends AppCompatActivity {
    Button returnPSIButton;
    TabItem tabItem;
    TabLayout answerTabGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_sheet_interface);

        returnPSIButton=findViewById(R.id.back_psi);
        tabItem=findViewById(R.id.first_question_tab);
        answerTabGroup=findViewById(R.id.answer_tab_group);
//        answerTabGroup.setupWithViewPager();
//        answerTabGroup.addOnTabSelectedListener();


    }
    class AnswerTabSelectedListener implements TabLayout.OnTabSelectedListener{
        ViewPager viewPager;
        TransmitterAmongInterfaces transmitterAmongInterfaces;
        AnswerTabSelectedListener(){
            viewPager=transmitterAmongInterfaces.getViewPagerFromPSI();
        }
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Intent intent=new Intent();
            intent.putExtra("selected_tab_num",tab.getPosition());
            setResult(RESULT_OK,intent);
            finish();
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

}
