package com.example.ihe.acmestudy.Compatibility;

import android.support.v4.view.ViewPager;

public class TransmitterAmongInterfaces {
    ViewPager  viewPagerFromPSI;

    public ViewPager getViewPagerFromPSI() {
        return viewPagerFromPSI;
    }

    public void setViewPagerFromPSI(ViewPager viewPagerFromPSI) {
        this.viewPagerFromPSI = viewPagerFromPSI;
    }

    private static final TransmitterAmongInterfaces ourInstance = new TransmitterAmongInterfaces();

    public static TransmitterAmongInterfaces getInstance() {
        return ourInstance;
    }

    private TransmitterAmongInterfaces() {
    }
}
