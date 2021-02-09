package Model.Value;

import Model.Type.RefType;
import Model.Type.Type;

public class RefValue implements Value {
    int address;
    Type locationType;

    public RefValue(int i, Type inner) {
        address = i;
        locationType = inner;
    }

    int getAddr() {
        return address;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return "Ref{" +
                "address=" + address +
                ", locationType=" + locationType +
                '}';
    }

    @Override
    public boolean equals(Value t) {
        RefValue oth = (RefValue) t;
        return this.address == oth.address;
    }

    @Override
    public Value getCopy() {
        return new RefValue(address, locationType);
    }

    public void setAddress(int addr) {
        address = addr;
    }

    public int getAddress() {
        return address;
    }
}