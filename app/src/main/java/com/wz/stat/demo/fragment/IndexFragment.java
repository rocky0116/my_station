package com.wz.stat.demo.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wz.stat.demo.MainActivity;
import com.wz.stat.demo.R;
import com.wz.stat.demo.adapter.GridViewAdapter;
import com.wz.stat.demo.adapter.ViewPagerAdapter;
import com.wz.stat.demo.model.Model;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/************************************************
 * author:       火中燕.
 * email:        wangzhong0116@foxmail.com.
 * version:      1.0.1.
 * date:         2016/12/9 20:17.
 * description: 首页
 ***********************************************/

public class IndexFragment extends Fragment {

    private String[] titles = {"生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活",
            "生活", "生活"};

    @Bind(R.id.viewpager)
    ViewPager mPager;
    @Bind(R.id.ll_dot)
    LinearLayout mLlDot;

    private List<View> mPagerList;
    private List<Model> mDatas;
    private LayoutInflater inflater;
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 10;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;

    public IndexFragment(){super();}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.activity_index, container, false);
        ButterKnife.bind(this, myview);

        //初始化数据源
        initDatas();
        inflater = LayoutInflater.from(getActivity());
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<View>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
            gridView.setAdapter(new GridViewAdapter(getActivity(), mDatas, i, pageSize));
            mPagerList.add(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + curIndex * pageSize;
                    Toast.makeText(getActivity(), mDatas.get(pos).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        //设置适配器
        mPager.setAdapter(new ViewPagerAdapter(mPagerList));
        //设置圆点
        setOvalLayout();
        return myview;
    }


    /**
     * 初始化数据源
     */
    private void initDatas() {
        mDatas = new ArrayList<Model>();
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getActivity().getPackageName());
            mDatas.add(new Model(titles[i], R.mipmap.ic_launcher));
        }
    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
}
