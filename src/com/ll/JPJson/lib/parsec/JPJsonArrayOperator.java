package com.ll.JPJson.lib.parsec;

import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;

/**
 * Created by liuli on 15-12-10.
 */
public class JPJsonArrayOperator {
    public static Parser JPJsonArrayOperator() {
        class JPJsonOperatorParser extends Parser {

            @Override
            public Object parse(State state) {

                return null;
            }
        }
        return new JPJsonOperatorParser();
    }
}
