package com.android.basecore;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
    public void sd() {
        Map<String,String> map=new HashMap<String,String>();
        map.put(null,"1");
        System.out.println(map.remove(null));
        map.remove(null);
        System.out.println(map.remove(null));
    }
}