package Model.Expression;

import Model.ADT.IHeap;
import Model.ADT.MyIDict;
import Model.MyException;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class NotExpression implements Exp{
    private Exp expression;

    public NotExpression(Exp expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, IHeap<Integer, Value> hp) throws MyException {
        return expression.eval(tbl, hp).equals(new BoolValue(false)) ? new BoolValue(true) : new BoolValue(false);
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return null;
    }
}
