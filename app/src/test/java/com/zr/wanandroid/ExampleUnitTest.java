package com.zr.wanandroid;

import com.zr.wanandroid.utils.HtmlUtils;

import org.junit.Test;

import java.net.SocketTimeoutException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void sdd() {
        HtmlUtils.test();
    }
    @Test
    public void s() {
        Exception exception=new Exception();
        if(exception instanceof SocketTimeoutException){
            System.out.println("22222222222");
        }else{
            System.out.println("1111111111");
        }
    }
    public void testType(Object objs){
        System.out.println("instanceof byte[]?"+(objs instanceof byte[]));
        System.out.println("isArray?"+objs.getClass().isArray());
        if (objs.getClass().isArray()){
            System.out.println("getClass.getComponentType:"+objs.getClass().getComponentType());
        }
    }
    @Test
    public void sd() {
        System.out.println(3<<1);
        System.out.println(3<<2);
        System.out.println(3<<3);
    }
}