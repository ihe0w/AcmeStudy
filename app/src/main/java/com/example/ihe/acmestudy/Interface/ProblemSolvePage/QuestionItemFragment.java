package com.example.ihe.acmestudy.Interface.ProblemSolvePage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ihe.acmestudy.Interface.ProblemSolvePage.gapfilling.GapFillingSpanAnswerRange;
import com.example.ihe.acmestudy.Interface.ProblemSolvePage.gapfilling.GapFillingView;
import com.example.ihe.acmestudy.Entity.Entity.GapFillingQuestion;
import com.example.ihe.acmestudy.Entity.Entity.MultipleChoiceQuestion;
import com.example.ihe.acmestudy.Entity.Entity.QuestionInfo;
import com.example.ihe.acmestudy.Entity.Entity.SingleChoiceQuestion;
import com.example.ihe.acmestudy.R;
import com.example.ihe.acmestudy.common.AcmeApplication;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INDEX = "index";

    // TODO: Rename and change types of parameters
    private int index;
    private QuestionInfo questionInfo;
    private int type;

    private OnFragmentInteractionListener mListener;
    private  View rootView;
    ChoiceQuestionsAdapter choiceQuestionsAdapter;
    GapFillingView gapFillingView;

    public QuestionItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param index Parameter 2.
     * @return A new instance of fragment QuestionItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionItemFragment newInstance(int index) {
        QuestionItemFragment fragment = new QuestionItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }
    private void initData(){
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
            questionInfo= ProblemSolveActivity.questionInfos.get(index);
            type=questionInfo.getType();
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflaterRootView(inflater,container);
        ConstraintLayout constraintLayout=rootView.findViewById(R.id.question_item_fragment_layout);
        constraintLayout.addView(initQuestionView());
        return rootView;
    }
    private View initQuestionView(){
        View questionView=null;

        if (questionInfo.getType()==QuestionInfo.SINGLE_CHOICE){
            SingleChoiceQuestion singleChoiceQuestion=(SingleChoiceQuestion) questionInfo.getRealQuestion();
            questionView=createAndLoadIntoChoiceView(QuestionInfo.SINGLE_CHOICE,singleChoiceQuestion.getQuestionStem(),singleChoiceQuestion.getOptionContentList());
        }
        else if (questionInfo.getType()==QuestionInfo.MULTIPLE_CHOICE){
            MultipleChoiceQuestion multipleChoiceQuestion=(MultipleChoiceQuestion)questionInfo.getRealQuestion();
            questionView=createAndLoadIntoChoiceView(QuestionInfo.MULTIPLE_CHOICE,multipleChoiceQuestion.getQuestionStem(),multipleChoiceQuestion.getOptionContentList());
        }
        else  if (questionInfo.getType()==QuestionInfo.GAP_FILLING){
            GapFillingQuestion gapFillingQuestion=(GapFillingQuestion)questionInfo.getRealQuestion();
            questionView=createAndLoadIntoGapFillingView(gapFillingQuestion.getQuestionStem(),gapFillingQuestion.getRangeList());
        }
        return questionView;
    }
    protected void inflaterRootView(@NonNull LayoutInflater inflater, ViewGroup container){
        rootView = inflater.inflate(R.layout.fragment_question_item,
                container, false);
    }

    private View createAndLoadIntoChoiceView(int type, String questionStemContent, List<String> optionContentList){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.choice_questions_container_layout,null);
        if (view!=null) {
            TextView questionStemView=view.findViewById(R.id.stem_view);
            if (questionStemView!=null){
                questionStemView.setText(questionStemContent);
            }

            ChoiceQuestionView choiceQuestionView = view.findViewById(R.id.choice_question_view);
            choiceQuestionView.setType(type);
            choiceQuestionsAdapter = new ChoiceQuestionsAdapter(type, optionContentList, choiceQuestionView);
            //TODO:setLayoutManager
            choiceQuestionView.setLayoutManager(new LinearLayoutManager(AcmeApplication.getContext()));
            choiceQuestionView.setAdapter(choiceQuestionsAdapter);}
        return view;
    }
    private View createAndLoadIntoGapFillingView(String gapFillingQuestionStem,List<GapFillingSpanAnswerRange> rangeList){
        View view=  LayoutInflater.from(getContext()).inflate(R.layout.gap_filling_layout,null);
        gapFillingView=view.findViewById(R.id.gap_filling_view);
        gapFillingView.loadQuestionContent(gapFillingQuestionStem, rangeList);
        return view;

    }

    public int getType() {
        return type;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public int getAnswerId(){
        return choiceQuestionsAdapter.getAnswerId();
    }
    public boolean[] getMultipleCheckedIds(){
        return choiceQuestionsAdapter.getMultipleCheckedIds();
    }
    public List<String> getAnswerList() {
        return gapFillingView.getAnswerList();
    }

}
