package com.zr.wanandroid;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.zr.wanandroid.utils.HtmlUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void testzip() {
        File file=new File("D:/aaaa","aaaa2.rar");
        try {
            boolean newFile = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.exists());
        Log.i("=====","====="+file.exists());
        FileUtils.unZip(file,"D:\\aaaa2",false);
    }
    @Test
    public void sdd() {
        HtmlUtils.test();
        String testText="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>这里填写标题</title>\n" +
                "<meta name=\"keywords\" content=\"这里填写关键词\" />\n" +
                "<meta name=\"description\" content=\"这里填写说明内容\" />\n" +
                "\n" +
                "<script language=\"JavaScript\" type=\"text/javascript\">\n" +
                "<!--JS代码位置-->\n" +
                "</script>\n" +
                "\n" +
                "<style type=\"text/css\">\n" +
                "<!--CSS样式代码位置-->\n" +
                "</style>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<p>每当大家有在编译期间有修改字节码的需求，第一反应应该就是使用 Transform 吧，我们知道每个 Transform 都有它的输入、输出，问几个问题：</p>\\\\r\\\\n<ol>\\r\\n<li>在编译过程中，有哪些“系统Transform”执行？</li>\\r\\n<li>自定义 Transform和其他系统Transform执行的顺序是怎么样的？</li>\\r\\n<li>Transform和 gradle task的关系是怎么样的？</li>\\r\\n</ol>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
        String s = HtmlUtils.htmlDecode(testText);
        System.out.println("html:====="+s);
        Log.i("=====","html:====="+s);
    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.look.book", appContext.getPackageName());
    }
    @Test
    public void afds() {
        String json="{\"code\":-1,\"msg\":\"参数缺失\",\"data\":null}";
        try {
            JSONObject jsonObject=new JSONObject(json);
            System.out.println("============"+jsonObject.optString("code"));
        } catch (JSONException e) {
            System.out.println("============error");
            e.printStackTrace();
        }
    }
}
