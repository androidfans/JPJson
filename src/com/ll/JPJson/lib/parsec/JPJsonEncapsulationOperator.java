package com.ll.JPJson.lib.parsec;


import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;
import static com.ll.JParsec.lib.TextOperator.*;
import static com.ll.JParsec.lib.CombinatorOperator.*;

/**
 * Created by liuli on 15-12-10.
 */
public class JPJsonEncapsulationOperator {
    public static Parser JPJsonArrayOperator() {
        class JPJsonArrayParser extends Parser {

            @Override
            public Object parse(State state) {
                Parser left = Chr('[');
                Parser right = Chr(']');
                Parser sep = sepBy(between(skip(Try(whiteSpace())), JPJsonAtomicValueOperator.JPJsonValueOperator(),skip(Try(whiteSpace()))),Chr(','));
                return left.then(sep).over(right).parse(state);
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
