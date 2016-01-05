package com.example.g0274.hwfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by g0274 on 2016/1/4.
 */
public class DetailActivity extends Activity {
    //    負責顯示的Fragment
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //        如果裝置的畫面是橫豎
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //            結束後回到主畫面
            finish();
            return;
        }

        processViews();
    }

    private void processViews() {
        //        取得FragmentManager物件
        FragmentManager manager = getFragmentManager();
        //        取得FragmentTransaction物件
        FragmentTransaction transaction = manager.beginTransaction();
        //        建立DetailFragment
        detailFragment = new DetailFragment();
        //        將DetailFragment加入指定的畫面元件
        transaction.add(R.id.detail_container, detailFragment);
        //        確認執行
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int position = getIntent().getIntExtra("position", -1);
        if (position != -1) {
            setTitle(DataSet.names[position]);
            detailFragment.updateDetail(position);
        }
    }
}
