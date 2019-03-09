package com.example.ihe.acmestudy.Entity.Entity;

public class MultipleChoiceQuestion extends ChoiceQuestion {
    private boolean[] CheckedIdsWhenMultiple;



    public boolean[] getCheckedIdsWhenMultiple() {
        return CheckedIdsWhenMultiple;
    }
    public void setCheckedIdsWhenMultiple(boolean[] checkedIdsWhenMultiple) {
        this.CheckedIdsWhenMultiple = checkedIdsWhenMultiple;
    }
}
