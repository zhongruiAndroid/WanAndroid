package com.zr.wanandroid.module.officialaccount.model;

import com.github.theokhttp.TheOkHttp;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.common.net.NetUrl;
import com.zr.wanandroid.common.net.bean.BaseResponse;
import com.zr.wanandroid.common.net.callback.HttpCallback;
import com.zr.wanandroid.common.single.SingleClass;
import com.zr.wanandroid.module.officialaccount.bean.OfficialAccountBean;

import java.util.List;

public class OfficialAccountModel extends SingleClass {
    public static OfficialAccountModel getInstance() {
        return getInstance(OfficialAccountModel.class);
    }
    public void OfficialAccountAuthorList(RequestListener<List<OfficialAccountBean>> listener){
        TheOkHttp.startGet(NetUrl.OFFICIAL_ACCOUNT_LIST, new HttpCallback<List<OfficialAccountBean>>() {
            @Override
            public void success(List<OfficialAccountBean> data, BaseResponse server, String result) {
                toSuccess(listener,data);
            }
            @Override
            public void error(String code, String errorMsg) {
                toError(listener,code,errorMsg);
            }
        });
    }
}
