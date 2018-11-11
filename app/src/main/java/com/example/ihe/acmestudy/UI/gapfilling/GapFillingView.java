package com.example.ihe.acmestudy.UI.gapfilling;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ihe.acmestudy.R;

import java.util.ArrayList;
import java.util.List;

public class GapFillingView extends RelativeLayout {

    TextView gapFillingStemView;
    Context context;
    // 答案集合
    private List<String> answerList;
    // 答案范围集合
    List<GapFillingSpanAnswerRange> rangeList;
    // 填空题内容
    SpannableStringBuilder gapFillingQuestionContent;
    GapFillingListener gapFillingListener;

    public GapFillingView(Context context) {
        this(context, null);
    }

    public GapFillingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GapFillingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.gap_filling_question_view, this);
        gapFillingStemView = (TextView) findViewById(R.id.gap_filling_stem_view);

    }

    /**
     * 设置数据
     *
     * @param originContent   源数据
     * @param gapFillingSpanAnswerRangeList 答案范围集合
     */
    public void loadQuestionContent(String originContent, List<GapFillingSpanAnswerRange> gapFillingSpanAnswerRangeList) {
        if (TextUtils.isEmpty(originContent) || gapFillingSpanAnswerRangeList == null
                || gapFillingSpanAnswerRangeList.isEmpty()) {
            return;
        }

        // 获取课文内容
        gapFillingQuestionContent = new SpannableStringBuilder(originContent);
        // 答案范围集合
        rangeList = gapFillingSpanAnswerRangeList;

        // 设置下划线颜色
        for (GapFillingSpanAnswerRange range : rangeList) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#4DB6AC"));
            gapFillingQuestionContent.setSpan(colorSpan, range.start, range.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // 初始化答案集合
//        answerList = new ArrayList<>();
//        for (int i = 0; i < rangeList.size(); i++) {
//            answerList.add("");
//        }
        gapFillingListener=new GapFillingListener(this);

        // 设置填空处点击事件及设置gapFillingQuestionContent的span
        for (int i = 0; i < rangeList.size(); i++) {
            GapFillingSpanAnswerRange range = rangeList.get(i);
//            GapFillingListener.BlankClickableSpan blankClickableSpan = new GapFillingListener.BlankClickableSpan(i);
            GapFillingListener.BlankClickableSpan blankClickableSpan=gapFillingListener.getBlankClickableSpan(i);
            gapFillingQuestionContent.setSpan(blankClickableSpan, range.start, range.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // 设置此方法后，点击事件才能生效
        gapFillingStemView.setMovementMethod(LinkMovementMethod.getInstance());
        gapFillingStemView.setText(gapFillingQuestionContent);
    }

    /**
     * 空格的点击事件class
     */
//    class BlankClickableSpan extends ClickableSpan {
//
//        private int position;
//
//        BlankClickableSpan(int position) {
//            this.position = position;
//        }
//
//        @Override
//        public void onClick(@NonNull final View widget) {
//            //加载输入答案view
//            View view = LayoutInflater.from(context).inflate(R.layout.input_gap_filling_answer_view, null);
//            final EditText etInput = (EditText) view.findViewById(R.id.et_answer);
//            Button btnFillBlank = (Button) view.findViewById(R.id.btn_fill_blank);
//
//            // 显示原有答案
//            String oldAnswer = answerList.get(position);
//            if (!TextUtils.isEmpty(oldAnswer)) {
//                etInput.setText(oldAnswer);
//                etInput.setSelection(oldAnswer.length());
//            }
//            //弹出键盘让用户输入答案
//            final PopupWindow popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT, dp2px(40));
//            // 获取焦点
//            popupWindow.setFocusable(true);
//            // 为了防止弹出菜单获取焦点之后，点击Activity的其他组件没有响应
//            popupWindow.setBackgroundDrawable(new PaintDrawable());
//            // 设置PopupWindow在软键盘的上方
//            popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//            // 弹出PopupWindow
//            popupWindow.showAtLocation(gapFillingStemView, Gravity.BOTTOM, 0, 0);
//
//            btnFillBlank.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // 填写答案
//                    String answer = etInput.getText().toString();
//                    fillAnswer(answer, position);
//                    for (String tempAnswer:getAnswerList()){
//                        Log.d("#", "loadQuestionIntoViews: "+tempAnswer);
//                    }
//                    popupWindow.dismiss();
//                }
//            });
//
//            // 显示软键盘
//            InputMethodManager inputMethodManager =
//                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//            assert inputMethodManager != null;
//            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//
//        @Override
//        public void updateDrawState(TextPaint ds) {
//            // 不显示下划线
//            ds.setUnderlineText(false);
//        }
//    }

    /**
     * 填写答案
     *
     * @param answer   当前填空处答案
     * @param position 填空位置
     */
//    private void fillAnswer(String answer, int position) {
//        answer = " " + answer + " ";
//
//        // 替换答案
//        GapFillingSpanAnswerRange range = rangeList.get(position);
//        gapFillingQuestionContent.replace(range.start, range.end, answer);
//
//        // 更新当前的答案范围
//        GapFillingSpanAnswerRange currentRange = new GapFillingSpanAnswerRange(range.start, range.start + answer.length());
//        rangeList.set(position, currentRange);
//
//        // 答案设置下划线
//        gapFillingQuestionContent.setSpan(new UnderlineSpan(),
//                currentRange.start, currentRange.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        // 将答案添加到集合中
//        answerList.set(position, answer.replace(" ", ""));
//
//        // 更新内容
//        gapFillingStemView.setText(gapFillingQuestionContent);
//
//        for (int i = 0; i < rangeList.size(); i++) {
//            if (i > position) {
//                // 获取下一个答案原来的范围
//                GapFillingSpanAnswerRange oldNextRange = rangeList.get(i);
//                int oldNextAmount = oldNextRange.end - oldNextRange.start;
//                // 计算新旧答案字数的差值
//                int difference = currentRange.end - range.end;
//
//                // 更新下一个答案的范围
//                GapFillingSpanAnswerRange nextRange = new GapFillingSpanAnswerRange(oldNextRange.start + difference,
//                        oldNextRange.start + difference + oldNextAmount);
//                rangeList.set(i, nextRange);
//            }
//        }
//    }

    /**
     * 获取答案列表
     *
     * @return 答案列表
     */
    public List<String> getAnswerList() {
        return answerList;
    }

    /**
     * dp转px
     *
     * @param dp dp值
     * @return px值
     */
    int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}


