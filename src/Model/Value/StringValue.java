package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value {
    String val;

    public StringValue(String v) {
        val = v;
    }

    public String getVal() {
        return val;
    }

    public String toString() {
        return val + "";
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Value t) {
        StringValue oth = (StringValue) t;
        return this.val == oth.val;
    }

    @Override
    public Value getCopy() {
        return new StringValue(val);
    }

}


