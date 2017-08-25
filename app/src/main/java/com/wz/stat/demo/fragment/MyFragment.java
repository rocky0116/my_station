package com.wz.stat.demo.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wz.stat.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/************************************************
 * author:       火中燕.
 * email:        wangzhong0116@foxmail.com.
 * version:      1.0.1.
 * date:         2016/12/9 20:18.
 * description:
 ***********************************************/

public class MyFragment extends Fragment {

    @Bind(R.id.my_about)
    LinearLayout my_about;
    public MyFragment(){super();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.activity_my, container, false);
        ButterKnife.bind(this, myview);
        return myview;
    }

    @OnClick({R.id.my_about})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_about:
                new MaterialDialog.Builder(getActivity())
                        .title(R.string.my_about)
                        .iconRes(R.mipmap.ic_launcher)
                        .content(R.string.about_me)
                        .positiveText(R.string.close)
                        .show();
                break;
        }
    }
}
