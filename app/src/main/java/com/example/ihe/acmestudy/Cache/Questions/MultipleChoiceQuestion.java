package com.example.ihe.acmestudy.Cache.Questions;

class MultipleChoiceQuestion extends ChoiceQuestion {
    private boolean[] CheckedIdsWhenMultiple;
    private static int questionId=0;
    MultipleChoiceQuestion(){
        questionId++;
    }
    public static int getQuestionId() {
        return questionId;
    }

    public static void setQuestionId(int questionId) {
        MultipleChoiceQuestion.questionId = questionId;
    }

    public boolean[] getCheckedIdsWhenMultiple() {
        return CheckedIdsWhenMultiple;
    }
    public void setCheckedIdsWhenMultiple(boolean[] checkedIdsWhenMultiple) {
        this.CheckedIdsWhenMultiple = checkedIdsWhenMultiple;
    }
}
