package com.ll.JPJson.lib.parsec;

import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;
import static com.ll.JParsec.lib.TextOperator.*;

/**
 * Created by liuli on 15-12-10.
 */
public class JPJsonArrayOperator {
    public static Parser JPJsonArrayOperator(Parser parser) {
        class JPJsonArrayParser extends Parser {

            @Override
            public Object parse(State state) {
                Parser left = Chr('[');
                Parser right = Chr(']');

                return null;
            }
        }
        return new JPJsonArrayParser();
    }
}
