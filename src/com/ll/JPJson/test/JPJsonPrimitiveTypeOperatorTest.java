package com.ll.JPJson.test;

import com.ll.JPJson.lib.parsec.JPJsonPrimitiveTypeOperator;
import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;
import com.ll.JParsec.lib.TextState;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by liuli on 15-12-7.
 */
public class JPJsonPrimitiveTypeOperatorTest {

    @Test
    public void testJPJsonIntOperator() throws Exception {

        Parser IntParser = JPJsonPrimitiveTypeOperator.JPJsonIntOperator();
        State state = new TextState("15512");
        assertEquals(15512, IntParser.parse(state));

        state = new TextState("-4521");
        assertEquals(-4521, IntParser.parse(state));

    }

    @Test
    public void testJPJsonFloatOperator() throws Exception {
        Parser FloatParser = JPJsonPrimitiveTypeOperator.JPJsonFloatOperator();
        State state = new TextState("21.3");

        assertEquals(21.3f, FloatParser.parse(state));

        state = new TextState("-.23");
        assertEquals(-0.23f,FloatParser.parse(state));
    }
}