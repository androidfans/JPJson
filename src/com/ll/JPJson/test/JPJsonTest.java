package com.ll.JPJson.test;

import com.ll.JPJson.lib.JPJson;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by liuli on 15-12-13.
 */
public class JPJsonTest {

    @Test
    public void testFromJson() throws Exception {
        String json = "{\"mingzi\":{\"zhi\":\"liuli\"}}";
        JPJson jpJson = new JPJson();
        Person d  =jpJson.fromJson(json, Person.class);
    }
}