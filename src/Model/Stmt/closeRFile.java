package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.Expression.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    Exp expr;
    public closeRFile(Exp exp){
        expr=exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        Value v=expr.eval(state.getSymTable(),state.getHeap());
        if(v.getType().equals(new StringType()))
        {
            if(state.getFiletable().exists((StringValue) v))
            {
                BufferedReader buff=state.getFiletable().get_elem((StringValue) v);
                try {
                    buff.close();
                    state.getFiletable().remove((StringValue) v);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else throw new MyException("File doesnt exist");
        }
        else
            throw new MyException("Not a stringType");
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if(!expr.typecheck(typeEnv).equals(new StringType()))
            throw new MyException("expr value is not string");
        return typeEnv;
    }

    public String toString(){
        return "closeRFile("+expr.toString()+")";
    }
}
