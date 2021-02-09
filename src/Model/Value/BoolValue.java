package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class  BoolValue implements Value {
    boolean val;

    public BoolValue(boolean v) {
        val = v;
    }

    public boolean getVal() {
        return val;
    }

    public String toString() {
        return val+"";
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Value t) {
        BoolValue oth=(BoolValue)t;
        return this.val==oth.val;
    }

    @Override
    public Value getCopy() {
        return new BoolValue(val);
    }


}
