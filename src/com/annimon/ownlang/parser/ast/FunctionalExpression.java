package com.annimon.ownlang.parser.ast;

import com.annimon.ownlang.lib.Functions;
import com.annimon.ownlang.lib.Value;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public final class FunctionalExpression implements Expression {
    
    private final String name;
    private final List<Expression> arguments;
    
    public FunctionalExpression(String name) {
        this.name = name;
        arguments = new ArrayList<>();
    }
    
    public FunctionalExpression(String name, List<Expression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }
    
    public void addArgument(Expression arg) {
        arguments.add(arg);
    }

    @Override
    public Value eval() {
        final int size = arguments.size();
        final Value[] values = new Value[size];
        for (int i = 0; i < size; i++) {
            values[i] = arguments.get(i).eval();
        }
        return Functions.get(name).execute(values);
    }

    @Override
    public String toString() {
        return name + "(" + arguments.toString() + ")";
    }
}