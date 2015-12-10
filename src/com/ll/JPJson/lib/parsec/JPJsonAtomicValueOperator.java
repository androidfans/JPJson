package com.ll.JPJson.lib.parsec;

import com.ll.JPJson.lib.json.JsonPrimitive;
import com.ll.JParsec.lib.CombinatorOperator;
import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;

import java.util.ArrayList;

import static com.ll.JParsec.lib.CombinatorOperator.*;
import static com.ll.JParsec.lib.TextOperator.*;
import static com.ll.JParsec.lib.AtomOperator.*;

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

    public static Parser<String> JPJsonStringOperator() {
        class strParser extends Parser{

            @Override
            public Object parse(State state) {
                Parser par = between(Chr('"'), many(notEqual('"')), Chr('"'));
                ArrayList<Character> arrayList = (ArrayList<Character>) par.parse(state);
                char[] re = new char[arrayList.size()];
                for (int i = 0; i < re.length; i++) {
                    re[i] = arrayList.get(i);
                }
                return new String(re);
            }
        }
        return new strParser();
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
