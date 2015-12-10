package com.ll.JPJson.test;

import com.ll.JPJson.lib.json.JsonNull;
import com.ll.JPJson.lib.parsec.JPJsonNullOperator;
import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;
import com.ll.JParsec.lib.TextState;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by liuli on 15-12-7.
 */
public class JPJsonNullOperatorTest {

    @Test
    public void testJPJsonNullOperator() throws Exception {
        Parser jpjNull = JPJsonNullOperator.JPJsonNullOperator();
        State state = new TextState("null");
        assertEquals(JsonNull.instance(), jpjNull.parse(state));

        state = new TextState("NULL");
        assertEquals(JsonNull.instance(), jpjNull.parse(state));
    }
}