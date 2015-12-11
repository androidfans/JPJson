package com.ll.JPJson.test;

import com.ll.JPJson.lib.json.JsonArray;
import com.ll.JPJson.lib.parsec.JPJsonEncapsulationOperator;
import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;
import com.ll.JParsec.lib.TextState;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by liuli on 15-12-11.
 */
public class JPJsonEncapsulationOperatorTest {

    @Test
    public void testJPJsonArrayOperator() throws Exception {
        State state = new TextState("[\"he\\n\",45]");
        Parser arrPar = JPJsonEncapsulationOperator.JPJsonArrayOperator();
        JsonArray re = (JsonArray) arrPar.parse(state);
        assertEquals(2, re.size());
    }

    @Test
    public void testJPJsonObjectOperator() throws Exception {

    }
}