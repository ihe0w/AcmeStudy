package com.example.ihe.acmestudy.Interface.ProblemSolvePage.gapfilling;

import android.content.Context;
import android.graphics.drawable.PaintDrawable;
import android.support.annotation.NonNull;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.ihe.acmestudy.Interface.ProblemSolvePage.ProblemSolveActivity;
import com.example.ihe.acmestudy.R;

import java.util.ArrayList;
import java.util.List;

public class GapFillingListener {
    private GapFillingView gapFillingView;
    private Context context;
    private List<String> answerList;
    private int blankSize;
    public GapFillingListener(GapFillingView gapFillingView){
        this.gapFillingView = gapFillingView;
        context= gapFillingView.context;
        answerList = new ArrayList<>();
        blankSize=gapFillingView.rangeList.size();
        if (ProblemSolveActivity.getWhichActivity()==ProblemSolveActivity.PSA) {
            for (int i = 0; i < blankSize; i++) {
                answerList.add("");
            }
        }


    }
    BlankClickableSpan getBlankClickableSpan(int position){
        return new BlankClickableSpan(position);
    }
    public void setUserAnswers(List<String> userAnswers){
        answerList=userAnswers;
    }

    class BlankClickableSpan extends ClickableSpan {

        private int position;

        BlankClickableSpan(int position) {
            this.position = position;
        }

        @Override
        public void onClick(@NonNull final View widget) {
            //加载输入答案view
            View view = LayoutInflater.from(context).inflate(R.layout.input_gap_filling_answer_view, null);
            final EditText etInput = (EditText) view.findViewById(R.id.et_answer);
            Button btnFillBlank = (Button) view.findViewById(R.id.btn_fill_blank);

            // 显示原有答案
            showOrignalAnswer(etInput);
            if (ProblemSolveActivity.getWhichActivity()==ProblemSolveActivity.PSA) {
                //弹出键盘让用户输入答案
                final PopupWindow popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, gapFillingView.dp2px(40));
                // 获取焦点
                popupWindow.setFocusable(true);
                // 为了防止弹出菜单获取焦点之后，点击Activity的其他组件没有响应
                popupWindow.setBackgroundDrawable(new PaintDrawable());
                // 设置PopupWindow在软键盘的上方
                popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                // 弹出PopupWindow
                popupWindow.showAtLocation(gapFillingView, Gravity.BOTTOM, 0, 0);

                btnFillBlank.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 填写答案
                        String answer = etInput.getText().toString();
                        fillAnswer(answer, position);
                        popupWindow.dismiss();
                    }
                });

                // 显示软键盘
                InputMethodManager inputMethodManager =
                        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethodManager != null;
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }else {
                //TODO:I need a window to show answer and analyze;besides that,I also should have to indicate whether wrong or right!
            }
        }

        private void showOrignalAnswer(EditText etInput){
            String oldAnswer = answerList.get(position);
            if (!TextUtils.isEmpty(oldAnswer)) {
                etInput.setText(oldAnswer);
                etInput.setSelection(oldAnswer.length());
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            // 不显示下划线
            ds.setUnderlineText(false);
        }
        /**
         * 填写答案
         *
         * @param answer   当前填空处答案
         * @param position 填空位置
         */
        private void fillAnswer(String answer, int position) {
            answer = " " + answer + " ";

            // 替换答案
            GapFillingSpanAnswerRange range = gapFillingView.rangeList.get(position);
            gapFillingView.gapFillingQuestionContent.replace(range.start, range.end, answer);

            // 更新当前的答案范围
            GapFillingSpanAnswerRange currentRange = new GapFillingSpanAnswerRange(range.start, range.start + answer.length());
            gapFillingView.rangeList.set(position, currentRange);

            // 答案设置下划线
            gapFillingView.gapFillingQuestionContent.setSpan(new UnderlineSpan(),
                    currentRange.start, currentRange.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // 将答案添加到集合中
            answerList.set(position, answer.replace(" ", ""));

            // 更新内容
            gapFillingView.gapFillingStemView.setText(gapFillingView.gapFillingQuestionContent);

            for (int i = 0; i < gapFillingView.rangeList.size(); i++) {
                if (i > position) {
                    // 获取下一个答案原来的范围
                    GapFillingSpanAnswerRange oldNextRange = gapFillingView.rangeList.get(i);
                    int oldNextAmount = oldNextRange.end - oldNextRange.start;
                    // 计算新旧答案字数的差值
                    int difference = currentRange.end - range.end;

                    // 更新下一个答案的范围
                    GapFillingSpanAnswerRange nextRange = new GapFillingSpanAnswerRange(oldNextRange.start + difference,
                            oldNextRange.start + difference + oldNextAmount);
                    gapFillingView.rangeList.set(i, nextRange);
                }
            }
        }

    }

    public List<String> getAnswerList() {
        return answerList;
    }
}

