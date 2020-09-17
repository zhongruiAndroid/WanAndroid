package com.look.book;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
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
