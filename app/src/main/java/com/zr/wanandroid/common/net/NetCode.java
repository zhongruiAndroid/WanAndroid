package com.zr.wanandroid.common.net;

public class NetCode {
    public static final String NET_ERROR = "-1";
    /**
     * -3：sign时间加密异常
     * -15:请求解密和token校验远程接口没返回
     * 101：bp||sign为空
     * 102：sxid为空
     * 104：sign加密错误
     * 105：相同请求，多次调用
     * 106：加密异常，抛出异常
     * 107：登录token校验失败
     */
    /**
     * -3：sign时间加密异常
     */
    public static final String ERR_CODE_TS = "-3";
    /**
     * 107：登录token校验失败
     */
    public static final String ERR_CODE_TOKEN = "-1001";
}
