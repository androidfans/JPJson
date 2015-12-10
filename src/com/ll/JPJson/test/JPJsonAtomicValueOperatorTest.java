package com.ll.JPJson.test;

import com.ll.JPJson.lib.json.JsonPrimitive;
import com.ll.JPJson.lib.parsec.JPJsonAtomicValueOperator;
import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;
import com.ll.JParsec.lib.TextState;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by liuli on 15-12-7.
 */
public class JPJsonAtomicValueOperatorTest {

    @Test
    public void testJPJsonIntOperator() throws Exception {

        Parser<JsonPrimitive> IntParser = JPJsonAtomicValueOperator.JPJsonIntOperator();
        State state = new TextState("15512");
        assertEquals(15512, IntParser.parse(state).getAsInteger().intValue());

        state = new TextState("-4521");
        assertEquals(-4521, IntParser.parse(state).getAsInteger().intValue());

    }

    @Test
    public void testJPJsonFloatOperator() throws Exception {
        Parser<JsonPrimitive> FloatParser = JPJsonAtomicValueOperator.JPJsonDoubleOperator();
        State state = new TextState("21.3");

        assertEquals(new Double(21.3), FloatParser.parse(state).getAsDouble());

        state = new TextState("-.23");
        assertEquals(new Double(-0.23), FloatParser.parse(state).getAsDouble());
    }

    @Test
    public void testJPJsonBooleanOperator() throws Exception {
        Parser<JsonPrimitive> booleanParser = JPJsonAtomicValueOperator.JPJbooleanOperator();
        State state = new TextState("false");
        assertEquals(false, booleanParser.parse(state).getAsBoolean());

        state = new TextState("true");
        assertEquals(true, booleanParser.parse(state).getAsBoolean());

        state = new TextState("FALSE");
        assertEquals(false, booleanParser.parse(state).getAsBoolean());

        state = new TextState("TRUE");
        assertEquals(true, booleanParser.parse(state).getAsBoolean());
    }

    @Test
    public void testJPJsonStringOperator() throws Exception {
        Parser par = JPJsonAtomicValueOperator.JPJsonStringOperator();
        State state = new TextState("\"abcdefg \"");

        assertEquals("abcdefg ", par.parse(state));
    }
}