package com.zr.wanandroid;

import android.util.Log;

import com.zr.wanandroid.utils.HtmlUtils;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    public void dd() {
        System.out.printf("100的一半是：%d", 100 / 2);
    }

    @Test
    public void sdd() {
        HtmlUtils.test();
    }

    @Test
    public void s() {
        Exception exception = new Exception();
        if (exception instanceof SocketTimeoutException) {
            System.out.println("22222222222");
        } else {
            System.out.println("1111111111");
        }
    }

    public void testType(Object objs) {
        System.out.println("instanceof byte[]?" + (objs instanceof byte[]));
        System.out.println("isArray?" + objs.getClass().isArray());
        if (objs.getClass().isArray()) {
            System.out.println("getClass.getComponentType:" + objs.getClass().getComponentType());
        }
    }

    @Test
    public void sd() {
        System.out.println(3 << 1);
        System.out.println(3 << 2);
        System.out.println(3 << 3);
    }

    @Test
    public void sddd() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.remove("c");
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.println(s);
        }
    }

    @Test
    public void sdddd() {
        System.out.println(String.valueOf(System.currentTimeMillis() / 1000L));
        Map<String, String> appCommonParamMap = getAppCommonParamMap();
        tea(appCommonParamMap,0);
    }

    public void tea(Map map, int num) {
        if (num == 5) {
            return;
        }
        System.out.println(map.get("ts"));
        tea(map, num + 1);
    }

    public static Map<String, String> getAppCommonParamMap() {
        Map<String, String> map = new HashMap();
        map.put("imei", "asfdasfdfsd");
        map.put("ts", getTs());
        return map;
    }

    public static String getTs() {
        return String.valueOf(System.currentTimeMillis() / 1000L);
    }

    @Test
    public void ads() {
        Map<String,String>map=new ConcurrentHashMap<>();
        map.remove(null);
    }

    @Test
    public void testzip() {

        File file=new File("D:/aaaa","test_img1.png");
        File needFile=new File("D:/aaaa/test.png");
        System.out.println("====needFile="+needFile.exists());
        if(true){
            return;
        }
        try {
            boolean newFile = file.createNewFile();
            InputStream is = new FileInputStream(needFile);
            FileOutputStream fos = new FileOutputStream(file);
            int len;
            byte[] buf = new byte[1024 * 2];
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            // 关流顺序，先打开的后关闭
            fos.close();
            is.close();
            System.out.println(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(file.exists());
//        FileUtils.unZip(file,"D:/aaaa2",false);
    }
}