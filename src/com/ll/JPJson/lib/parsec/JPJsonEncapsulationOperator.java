package com.ll.JPJson.lib.parsec;

import com.ll.JPJson.lib.json.JsonArray;
import com.ll.JPJson.lib.json.JsonElement;
import com.ll.JPJson.lib.json.JsonObject;
import com.ll.JPJson.lib.json.JsonPrimitive;
import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;

import java.util.List;

import static com.ll.JParsec.lib.TextOperator.*;
import static com.ll.JParsec.lib.CombinatorOperator.*;

/**
 * Created by liuli on 15-12-10.
 */
public class JPJsonEncapsulationOperator {
    public static Parser JPJsonArrayOperator() {
        class JPJsonArrayParser extends Parser<JsonArray> {

            @Override
            public JsonArray parse(State state) {
                Parser left = Chr('[');
                Parser right = Chr(']');
                Parser sep = sepBy(between(skip(Try(whiteSpace())), JPJsonAtomicValueOperator.JPJsonValueOperator(), skip(Try(whiteSpace()))), Chr(','));
                JsonArray re = new JsonArray();
                re.setElements((List<JsonElement>) left.then(sep).over(right).parse(state));
                return re;
            }
        }
        return new JPJsonArrayParser();
    }

    public static Parser JPJsonObjectOperator() {
        class JPJsonObjectParser extends Parser<JsonObject> {

            @Override
            public JsonObject parse(State state) {
                JsonObject re = new JsonObject();
                Parser<JsonPrimitive> keyParser = between(skip(Try(whiteSpace())), JPJsonAtomicValueOperator.JPJsonStringOperator(), skip(Try(whiteSpace())));
                Parser<JsonElement> eleParser = between(skip(Try(whiteSpace())), JPJsonAtomicValueOperator.JPJsonStringOperator(), skip(Try(whiteSpace())));
                skip(Try(whiteSpace())).then(Chr('{')).parse(state);
                String key = keyParser.parse(state).getAsString();
                Chr(':').parse(state);
                JsonElement ele = eleParser.parse(state);
                re.add(key, ele);
                return re;
            }
        }
        return new JPJsonObjectParser();
    }
}