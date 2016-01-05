package com.example.g0274.hwfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by g0274 on 2016/1/4.
 */
public class MasterFragment extends Fragment {
    private ListView leave_list;

    public MasterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //        載入指定的畫面配置資源
        View result = inflater.inflate(R.layout.fragment_master, container, false);

        //        讀取並設定畫面元件
        leave_list = (ListView) result.findViewById(R.id.leave_list);

        return result;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //        建立一個ListView元件用的Adapter物件

        List<HashMap<String, String>> list = new ArrayList<>();
        //使用List存入HashMap，用來顯示ListView上面的文字。
        for (int i = 0; i < DataSet.names.length; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("names", DataSet.names[i]);
            hashMap.put("dates", DataSet.dates[i]);
            hashMap.put("reasons", DataSet.reasons[i]);
            //把title , text存入HashMap之中
            list.add(hashMap);
            //把HashMap存入list之中
        }
        //ArrayAdapter無法傳入多值
        ListAdapter listAdapter = new SimpleAdapter(
                getActivity(),
                list,
                R.layout.list_item,
                new String[]{"names", "dates", "reasons"},
                new int[]{R.id.title_nm, R.id.title_date, R.id.title_reason});
        // 5個參數 : context , List , layout , key1 & key2 , text1 & text2

        leave_list.setAdapter(listAdapter);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, DataSet.names);
        //        設定ListView使用的Adapter物件
        //leave_list.setAdapter(adapter);
        //        註冊項目點擊監聽事件
        leave_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //                取得使用這個Fragment的Activity元件，這邊指的是MainActivity
                MainActivity mainActivity = (MainActivity) getActivity();

                mainActivity.onItemSelected(position);
            }
        });
    }

    /**
     * 給Activity元件實作的介面
     */
    public interface OnItemSelectedListener {
        /**
         * 呼叫這個方法，讓Activity元件執行點擊項目後所執行的工作
         *
         * @param position 項目編號
         */
        public void onItemSelected(int position);
    }
}

