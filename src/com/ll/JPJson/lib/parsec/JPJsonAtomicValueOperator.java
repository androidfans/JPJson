package com.ll.JPJson.lib.parsec;

import com.ll.JPJson.lib.json.JsonNull;
import com.ll.JPJson.lib.json.JsonPrimitive;
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
    public static Parser JPJsonNumberOperator() {
        class JPJIntParser extends Parser<JsonPrimitive>{

            @Override
            public JsonPrimitive parse(State state) {
                Parser<String> intParser = Int();
                String data = intParser.parse(state);
                long iData = Long.parseLong(data);
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



    public static Parser JPJsonEscapeCharSOperator() {
        class JPJsonEscapeCharSParser extends Parser {

            @Override
            public Object parse(State state) {
                Chr('\\').parse(state);
                char data = (char) charOf("nrt\\\"").parse(state);
                switch (data) {
                    case 'n':
                        return '\n';
                    case 'r':
                        return '\r';
                    case 't':
                        return '\t';
                    case '\\':
                        return '\\';
                    case '"':
                        return '"';
                    default:
                        return fail("unknown escape char").parse(state);
                }
            }
        }
        return new JPJsonEscapeCharSParser();
    }

    public static Parser JPJsonEscapeCharCOperator() {
        class JPJsonEscapeCharCParser extends Parser {

            @Override
            public Object parse(State state) {
                //TODO:这里改成使用查表法
                Chr('\\').parse(state);
                char data = (char) charOf("nrt'\\").parse(state);
                switch (data) {
                    case 'n':
                        return '\n';
                    case 'r':
                        return '\r';
                    case 't':
                        return '\t';
                    case '\\':
                        return '\\';
                    case '\'':
                        return '\'';
                    default:
                        return fail("unknown escape char").parse(state);
                }
            }
        }
        return new JPJsonEscapeCharCParser();
    }

    public static Parser<String> JPJsonStringOperator() {
        class strParser extends Parser{

            @Override
            public Object parse(State state) {
                Parser par = between(Chr('"'), many(choice(Try(JPJsonEscapeCharSOperator()),Try(notEqual('"')))), Chr('"'));
                ArrayList<Character> arrayList = (ArrayList<Character>) par.parse(state);
                char[] re = new char[arrayList.size()];
                for (int i = 0; i < re.length; i++) {
                    re[i] = arrayList.get(i);
                }
                return new JsonPrimitive(new String(re));
            }
        }
        return new strParser();
    }

    public static Parser JPJsonValueOperator() {
        return choice(Try(JPJsonStringOperator()),Try(JPJbooleanOperator()), Try(JPJsonDoubleOperator()),Try(JPJsonNullOperator()),Try(JPJsonNumberOperator()),Try(JPJsonEncapsulationOperator.JPJsonArrayOperator()),JPJsonEncapsulationOperator.JPJsonObjectOperator());
    }

    public static Parser JPJsonNullOperator() {
       return choice(Try(Str("null")), Str("NULL")).then(Return(JsonNull.instance()));
    }
}
