package com.example.ihe.acmestudy.UI;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
//填充每个页面，根据传输的数据，决定在什么位置，加载多少的选择题，填空题页面......
/*
* 加载所有view到viewpager里，但不做初始化工作*/

public class PageLoader extends PagerAdapter {
    private List<View> pageItemsList;

    public PageLoader(List<View> pageItemsList) {
        super();
        this.pageItemsList=pageItemsList;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d("#", "instantiateItem:  the parent is "+pageItemsList.get(position).getParent()+" it is "+pageItemsList.get(position));
        container.addView(pageItemsList.get(position));
        return pageItemsList.get(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @Override
    public int getCount() {
        return pageItemsList.size();
    }

}
