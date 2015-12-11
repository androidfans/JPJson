package com.ll.JPJson.lib.parsec;


import com.ll.JPJson.lib.json.JsonArray;
import com.ll.JPJson.lib.json.JsonElement;
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

    public static Parser JPJsonObjectOperator(Parser parser) {
        class JPJsonObjectOperator extends Parser {

            @Override
            public Object parse(State state) {
                return null;
            }
        }
        return new JPJsonObjectOperator();
    }
}
