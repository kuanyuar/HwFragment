package com.example.g0274.hwfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by g0274 on 2016/1/4.
 */
public class DetailFragment extends Fragment {
    private TextView name_text;//顯示姓名
    private TextView date_text;//顯示日期
    private TextView reason_text;//顯示事由

    public DetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //載入指定的畫面配置資源
        View result = inflater.inflate(R.layout.fragment_detail, container, false);

        //        讀取並設定畫面元件
        name_text = (TextView) result.findViewById(R.id.NM);
        date_text = (TextView) result.findViewById(R.id.Leave);
        reason_text = (TextView) result.findViewById(R.id.Reason);
        return result;
    }

    /**
     * 提供給其他元件呼叫，執行更新畫面元件內容的方法
     *
     * @param position 項目的編號
     */
    public void updateDetail(int position) {
        name_text.setText(DataSet.names[position]);
        date_text.setText(DataSet.dates[position]);
        reason_text.setText(DataSet.reasons[position]);
    }

}
