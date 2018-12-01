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
import com.example.ihe.acmestudy.UI.QuestionView;
import com.example.ihe.acmestudy.UI.UserAnswer;

import java.util.ArrayList;
import java.util.List;

public class GapFillingView extends RelativeLayout implements QuestionView {
    static final int WRONG_STATE=3;
    static final int RIGHT_STATE=2;

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
    public List<String> getAnswerList() {
        return answerList;
    }
    int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


    @Override
    public void updateComponentsState(int state, int componentId) {

    }

    @Override
    public UserAnswer getUserAnswer() {
        return null;
    }
}


