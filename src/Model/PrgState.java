package Model;


import Model.ADT.*;
import Model.Stmt.IStmt;
import Model.Value.StringValue;
import Model.Value.Value;


import java.io.*;
import java.util.HashMap;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDict<String, Value> symTable;
    MyIDict<StringValue, BufferedReader> filetable;
    MyIList<Value> out;
    IHeap<Integer, Value> heap;
    IStmt originalProgram;
    ISemaphore Semaphore;
    ILock Lock;
    ICyclicBarrier Barrier;
    private Integer id_thread = 1;
    private Integer last_id = 1;
    private static Integer id;

    public  PrgState(MyIStack<IStmt> stk, MyIDict<String,Value> symtbl, MyIList<Value> ot,MyIDict<StringValue,
            BufferedReader> filetbl,IHeap<Integer, Value> heapy, IStmt prg,int id_, ISemaphore semaphore,ILock lock,ICyclicBarrier barrier) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        filetable = filetbl;
        heap = heapy;
        originalProgram = prg;
        exeStack.push(prg);
        id_thread=id_;
        id=id_;
        Semaphore=semaphore;
        Lock=lock;
        Barrier=barrier;

    }

    public ICyclicBarrier getBarrier() {
        return Barrier;
    }

    public void setBarrier(ICyclicBarrier barrier) {
        Barrier = barrier;
    }

    public ILock getLock() {
        return Lock;
    }

    public void setLock(ILock lock) {
        Lock = lock;
    }

    public ISemaphore getSemaphore() {
        return Semaphore;
    }

    public void setSemaphore(ISemaphore semaphore) {
        Semaphore = semaphore;
    }

    public void setLast_id(Integer last_id) {
        this.last_id = last_id;
    }

    public Integer getLast_id() {
        return last_id;
    }

    private static Object deepCopy(Object object) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(object);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return objInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getId() {
        return id;
    }

    public Integer getId_thread() {
        return id_thread;
    }

    public static void setId(int id) {
        PrgState.id = id;
    }

    public PrgState OneStep() throws MyException {
        IStmt newprog = exeStack.pop();
        return newprog.execute(this);
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDict<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDict<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public String toString() {
        return exeStack.toString() + " " + symTable.toString() + " " + out.toString();
    }

    public MyIDict<StringValue, BufferedReader> getFiletable() {
        return filetable;
    }

    public void setFiletable(MyIDict<StringValue, BufferedReader> filetable) {
        this.filetable = filetable;
    }

    public IHeap<Integer, Value> getHeap() {
        return heap;
    }

    public void setHeap(IHeap<Integer, Value> heap) {
        this.heap = heap;
    }

    public boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if (exeStack.isEmpty()){
            return null;
        }
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }


}