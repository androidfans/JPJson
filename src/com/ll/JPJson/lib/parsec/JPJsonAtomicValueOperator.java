package com.ll.JPJson.lib.parsec;

import com.ll.JPJson.lib.json.JsonPrimitive;
import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;

import static com.ll.JParsec.lib.CombinatorOperator.Try;
import static com.ll.JParsec.lib.CombinatorOperator.choice;
import static com.ll.JParsec.lib.TextOperator.Float;
import static com.ll.JParsec.lib.TextOperator.Int;
import static com.ll.JParsec.lib.TextOperator.Str;

/**
 * Created by liuli on 15-12-11.
 */
public class JPJsonAtomicValueOperator {
    public static Parser JPJsonIntOperator() {
        class JPJIntParser extends Parser<JsonPrimitive>{

            @Override
            public JsonPrimitive parse(State state) {
                Parser<String> intParser = Int();
                String data = intParser.parse(state);
                int iData = Integer.parseInt(data);
                return new JsonPrimitive(iData);
            }
        }
        return new JPJIntParser();
    }

    public static Parser JPJsonDoubleOperator() {
        class JPJFloatParser extends Parser<JsonPrimitive> {

            @Override
            public JsonPrimitive parse(State state) {
                Parser<String> floatParser = Float();
                String data = floatParser.parse(state);
                double dData = Double.parseDouble(data);
                return new JsonPrimitive(dData);
            }
        }
        return new JPJFloatParser();
    }

    public static Parser JPJbooleanOperator() {
        class JPJbooleanParser extends Parser<JsonPrimitive>{

            @Override
            public JsonPrimitive  parse(State state) {
                Parser trueParser = choice(Try(Str("true")), Str("TRUE"));
                Parser falseParser = choice(Try(Str("false")), Str("FALSE"));
                String data = (String) choice(Try(trueParser), falseParser).parse(state);
                boolean bData = Boolean.parseBoolean(data);
                return new JsonPrimitive(bData);
            }
        }
        return new JPJbooleanParser();
    }
    public static Parser JPJsonValueOperator() {
        class JPJsonValueParser extends Parser {

            @Override
            public Object parse(State state) {
                Parser ch = choice(Try(JPJbooleanOperator()), Try(JPJsonDoubleOperator()), Try(JPJsonIntOperator()));
                return ch.parse(state);
            }
        }
        return new JPJsonValueParser();
    }
}
