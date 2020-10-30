package com.zr.wanandroid.module.home.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.github.mydialog.TheDialog;
import com.zr.wanandroid.R;

public class SearchHistoryClearDialog extends TheDialog {
    public SearchHistoryClearDialog(@NonNull Context context) {
        super(context);
    }
    public static void show(Context context){
        SearchHistoryClearDialog dialog=new SearchHistoryClearDialog(context);
        dialog.initView();
        dialog.show();
    }
    public void initView(){
        View inflate = getLayoutInflater().inflate(R.layout.search_histroy_dialog, null);
    }
}
