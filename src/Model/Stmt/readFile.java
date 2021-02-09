package Model.Stmt;

import Model.ADT.MyIDict;
import Model.ADT.MyIStack;
import Model.Expression.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt {
    Exp expr;
    String var_name;
    public readFile(Exp exp, String vari){
        expr=exp;
        var_name=vari;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        Value v=expr.eval(state.getSymTable(),state.getHeap());
        int bwoah=0;
        if(state.getSymTable().exists(var_name)){
            Value v1=state.getSymTable().get_elem(var_name);
            if(v1.getType().equals(new IntType())){
                if(v.getType().equals(new StringType())){
                    if(state.getFiletable().exists((StringValue) v)){
                        BufferedReader buff=state.getFiletable().get_elem((StringValue) v);
                        try {
                            String line=buff.readLine();
                            if(line!=null)
                                bwoah= Integer.parseInt(line);
                            state.getSymTable().update(var_name,new IntValue(bwoah));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else throw new MyException("Not existing in FileTable");
                }
                else throw new MyException("Expr not ok");
            }
            else
                throw new MyException("Not an int");
        }
        else
            throw new MyException("Var doesnt exist");
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if(!typeEnv.get_elem(var_name).equals(new IntType()))
            throw new MyException("not an int type");
        if(!expr.typecheck(typeEnv).equals(new StringType()))
            throw new MyException("File name is not a string");
        return typeEnv;
    }

    public String toString(){
        return "readFile("+expr.toString()+","+var_name+")";
    }
}
