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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class openRFile implements IStmt {
    Exp expr;
    public openRFile(Exp exp){
        expr=exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        Value v=expr.eval(state.getSymTable(),state.getHeap());
        MyIDict<StringValue, BufferedReader> tbl=state.getFiletable();
        if(expr.eval(state.getSymTable(),state.getHeap()).getType().equals(new StringType())){
            if(!tbl.exists((StringValue) v))
            {
               String path=expr.eval(state.getSymTable(),state.getHeap()).toString();
               try{
                   BufferedReader buff=new BufferedReader(new FileReader(path));
                   tbl.add((StringValue) v,buff);
                   return state;
               }
               catch (IOException e) {
                   e.printStackTrace();
               }
            }
            else throw new MyException("The StringValue is already in table");
        }
        else throw new MyException("The Value is not a stringValue");
return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if(!expr.typecheck(typeEnv).equals(new StringType()))
            throw new MyException("File name is not a string");
        return typeEnv;
    }

    public String toString(){
        return "openRFile("+expr.toString()+")";
    }
}
