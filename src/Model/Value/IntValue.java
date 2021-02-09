package Model.Value;

import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value {
    int val;

    public IntValue(int v) {
        val = v;
    }

    public int getVal() {
        return val;
    }

    public String toString() {
        return val+"";
    }
    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Value t) {
        IntValue oth=(IntValue)t;
        return this.val==oth.val;
    }

    @Override
    public Value getCopy() {
        return new IntValue(val);
    }


}