package com.example.ihe.acmestudy.Interface.ProblemSolvePage;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.ihe.acmestudy.R;

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private int count = ProblemSolveActivity.packetSize;
    private int[] mIds = new int[count];

    GridViewAdapter(Context context) {
        this.mContext = context;
        for (int i = 0; i < count; i++) {
            mIds[i] = i + 1;
        }
    }

    @Override
    public int getCount() {
        return mIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Log.d("#", "getView: ");
        TextView tv = new TextView(mContext);
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(new GridView.LayoutParams(70, 70));
        tv.setPadding(8, 8, 8, 8);
        tv.setTextSize(18);
        tv.setTextColor(Color.WHITE);

        tv.setText(mIds[position] + "");
        tv.setBackgroundResource(R.drawable.circle_pressed);
//        Log.d("#", "getView: end ");
        return tv;
    }
}
