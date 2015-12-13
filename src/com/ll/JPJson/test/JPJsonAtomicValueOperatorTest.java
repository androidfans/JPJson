package com.ll.JPJson.test;

import com.ll.JPJson.lib.json.JsonNull;
import com.ll.JPJson.lib.json.JsonPrimitive;
import com.ll.JPJson.lib.parsec.JPJsonAtomicValueOperator;
import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;
import com.ll.JParsec.lib.TextState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by liuli on 15-12-7.
 */
public class JPJsonAtomicValueOperatorTest {

    @Test
    public void testJPJsonIntOperator() throws Exception {

        Parser<JsonPrimitive> IntParser = JPJsonAtomicValueOperator.JPJsonNumberOperator();
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
    public void testJPJsonEscapeCharacterOperator() throws Exception {
        State state = new TextState("\\n\\\"");
        Parser CharS = JPJsonAtomicValueOperator.JPJsonEscapeCharSOperator();
        assertEquals('\n', CharS.parse(state));
        assertEquals('\"', CharS.parse(state));


        state = new TextState("\\n\\\'");
        Parser CharC = JPJsonAtomicValueOperator.JPJsonEscapeCharCOperator();
        assertEquals('\n', CharC.parse(state));
        assertEquals('\'', CharC.parse(state));
    }

    @Test
    public void testJPJsonStringOperator() throws Exception {
        Parser par = JPJsonAtomicValueOperator.JPJsonStringOperator();
        State state = new TextState("\"a\\\"\"");

        assertEquals("a\"", par.parse(state));
    }

    @Test
    public void testJPJsonNullOperator() throws Exception {
        Parser jpjNull = JPJsonAtomicValueOperator.JPJsonNullOperator();
        State state = new TextState("null");
        assertEquals(JsonNull.instance(), jpjNull.parse(state));

        state = new TextState("NULL");
        assertEquals(JsonNull.instance(), jpjNull.parse(state));
    }

    @Test
    public void testJPJsonValueOperator() throws Exception {
        Parser valueP = JPJsonAtomicValueOperator.JPJsonValueOperator();
        State state = new TextState("\"dfs\\\"\"");
        assertEquals("dfs\"", valueP.parse(state));


        state = new TextState("25646");
        assertEquals(25646, ((JsonPrimitive)valueP.parse(state)).getAsInteger().intValue());


        state = new TextState("-0.12");
        assertEquals(new Double(-0.12), ((JsonPrimitive) valueP.parse(state)).getAsDouble());

        state = new TextState("TRUE");
        assertEquals(true, ((JsonPrimitive) valueP.parse(state)).getAsBoolean());

        state = new TextState("NULL");
        assertEquals(JsonNull.instance(),valueP.parse(state));
    }
}