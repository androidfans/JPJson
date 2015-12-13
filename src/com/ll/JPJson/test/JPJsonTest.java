package com.ll.JPJson.test;

import com.ll.JPJson.lib.JPJson;
import org.junit.Test;

/**
 * Created by liuli on 15-12-13.
 */
public class JPJsonTest {

    @Test
    public void testFromJson() throws Exception {
        String json = "{\"names\":\"怡美家园\",\"data\":[{\"name\":\"haha\"}],\"times\":[10,11,13,13,21,23]}";
        JPJson jpJson = new JPJson();
        Person d  =jpJson.fromJson(json, Person.class);
    }
}