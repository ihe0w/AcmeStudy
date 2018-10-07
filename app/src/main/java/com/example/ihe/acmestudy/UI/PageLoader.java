package com.example.ihe.acmestudy.UI;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
//填充每个页面，根据传输的数据，决定在什么位置，加载多少的选择题，填空题页面......

public class PageLoader extends PagerAdapter {
    private List<View> pageItemsList;

    public PageLoader(List<View> pageItemsList) {
        super();
        this.pageItemsList=pageItemsList;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
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
