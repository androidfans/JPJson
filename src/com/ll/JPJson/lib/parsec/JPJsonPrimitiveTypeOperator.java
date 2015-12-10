package com.ll.JPJson.lib.parsec;

import com.ll.JPJson.lib.json.JsonNull;
import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;

import static com.ll.JParsec.lib.TextOperator.*;
import static com.ll.JParsec.lib.AtomOperator.*;
import static com.ll.JParsec.lib.CombinatorOperator.*;
/**
 * Created by liuli on 15-12-7.
 */
public class JPJsonPrimitiveTypeOperator {
    public static Parser JPJsonIntOperator() {
        class JPJIntParser extends Parser{

            @Override
            public Object parse(State state) {
                Parser<String> intParser = Int();
                String data = intParser.parse(state);
                return Integer.parseInt(data);
            }
        }
        return new JPJIntParser();
    }

    public static Parser JPJsonFloatOperator() {
        class JPJFloatParser extends Parser {

            @Override
            public Object parse(State state) {
                Parser<String> floatParser = Float();
                String data = floatParser.parse(state);
                return Float.parseFloat(data);
            }
        }
        return new JPJFloatParser();
    }

    public static Parser JPJbooleanOperator() {
        class JPJbooleanParser extends Parser{

            @Override
            public Object parse(State state) {
                return choice(Try(Str("null")), Str("NULL")).then(Return(JsonNull.instance()));
            }
        }
        return new JPJbooleanParser();
    }
}
