package Model.Stmt;

import Model.ADT.MyIDict;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class VarDeclStmt implements IStmt {
    String name;
    Type type;
    public VarDeclStmt(String s1,Type t1){
        name=s1;
        type=t1;
    }
    public String toString(){
        return type+" "+name;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> tbl=state.getSymTable();
        Value value;
        value=type.defaultValue();
        if(!tbl.exists(name)){
            tbl.add(name,value);
        }
        else throw new MyException("Element exists already with name "+ name);
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        typeEnv.add(name,type);
        return typeEnv;
    }
}