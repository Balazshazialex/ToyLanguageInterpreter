package Model.Stmt;

import Model.ADT.MyIDict;
import Model.MyException;
import Model.PrgState;
import Model.Type.Type;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;        //which is the execution method for a statement.
    MyIDict<String, Type> typecheck(MyIDict<String,Type> typeEnv) throws MyException;
 }