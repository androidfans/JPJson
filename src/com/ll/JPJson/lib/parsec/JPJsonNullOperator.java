package com.ll.JPJson.lib.parsec;

import com.ll.JPJson.lib.json.JsonNull;
import com.ll.JParsec.lib.Parser;
import com.ll.JParsec.lib.State;

import static com.ll.JParsec.lib.CombinatorOperator.*;
import static com.ll.JParsec.lib.TextOperator.*;
import static com.ll.JParsec.lib.AtomOperator.*;
/**
 * Created by liuli on 15-12-7.
 */
public class JPJsonNullOperator {

    public static Parser JPJsonNullOperator() {
       return choice(Try(Str("null")), Str("NULL")).then(Return(JsonNull.instance()));
    }

}