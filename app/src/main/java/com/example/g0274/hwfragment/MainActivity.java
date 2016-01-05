package com.example.g0274.hwfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity implements MasterFragment.OnItemSelectedListener {

    private int position;//紀錄目前所選擇的項目編號

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        會自動依照裝置直立或橫豎的方向，載入相對應的畫面配置資源
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            //            如果畫面為橫豎
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                FragmentManager manager = getFragmentManager();
                //                取得DetailFragment物件
                DetailFragment detailFragment = (DetailFragment) manager.findFragmentById(R.id.detail_fragment);
                //                讀取儲存的項目編號
                position = savedInstanceState.getInt("position", 0);
                //                更新畫面內容
                detailFragment.updateDetail(position);
            }
        }
    }

    /**
     * 此方法會在Activity元件結束前呼叫一次
     *
     * @param outState Activity元件結束時要儲存的資料
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //        儲存項目編號
        outState.putInt("position", position);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onItemSelected(int position) {
        //        使用所接收的參數來設定項目編號
        this.position = position;

        //        如果裝置的方向是橫豎
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //            取得FragmentManager物件
            FragmentManager manager = getFragmentManager();
            //            取得DetailFragment物件
            DetailFragment detailFragment = (DetailFragment) manager.findFragmentById(R.id.detail_fragment);
            //            更新畫面內容
            detailFragment.updateDetail(position);
        } else {//如果裝置的方向是直立
            //            建立起動明細資料的intent物件
            Intent intent = new Intent(this, DetailActivity.class);
            //            加入項目編號資料
            intent.putExtra("position", position);
            //            啟動intent，這裡指的是DetailActivity
            startActivity(intent);
        }
    }
}
