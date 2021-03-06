package com.annimon.ownlang.modules.functional;

import com.annimon.ownlang.exceptions.TypeException;
import com.annimon.ownlang.lib.*;
import java.util.Map;

public final class functional_foreach implements Function {

    @Override
    public Value execute(Value... args) {
        Arguments.check(2, args.length);
        
        if (args[1].type() != Types.FUNCTION) {
            throw new TypeException("Function expected in second arg");
        }
        final Value container = args[0];
        final Function consumer = ((FunctionValue) args[1]).getValue();
        if (container.type() == Types.ARRAY) {
            final ArrayValue array = (ArrayValue) container;
            for (Value element : array) {
                consumer.execute(element);
            }
            return array;
        }
        if (container.type() == Types.MAP) {
            final MapValue map = (MapValue) container;
            for (Map.Entry<Value, Value> element : map) {
                consumer.execute(element.getKey(), element.getValue());
            }
            return map;
        }
        throw new TypeException("Invalid first argument. Array or map expected");
    }
}
