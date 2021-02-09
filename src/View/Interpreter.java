package View;

import Controller.Controller;
import Model.ADT.*;
import Model.Expression.*;
import Model.MyException;
import Model.PrgState;
import Model.Stmt.*;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.IRepo;
import Repository.Repo;

import java.beans.IntrospectionException;
import java.util.HashMap;

public class Interpreter {
    public static IStmt Ex1() {
        IStmt ex1 = new CmpStmt(
                new VarDeclStmt("v", new IntType()),
                new CmpStmt(
                        new VarDeclStmt("a", new RefType(new IntType())),
                        new CmpStmt(
                                new AssignStmt("v", new ValExpr(new IntValue(10))),
                                new CmpStmt(
                                        new newStmt("a", new ValExpr(new IntValue(22))),
                                        new CmpStmt(
                                                new forkStmt(
                                                        new CmpStmt(
                                                                new wH("a", new ValExpr(new IntValue(30))),
                                                                new CmpStmt(
                                                                        new AssignStmt("v", new ValExpr(new IntValue(32))),
                                                                        new CmpStmt(
                                                                                new PrintStmt(new VarExpr("v")),
                                                                                new PrintStmt(new rH(new VarExpr("a")))
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CmpStmt(
                                                        new PrintStmt(new VarExpr("v")),
                                                        new PrintStmt(new rH(new VarExpr("a")))
                                                )
                                        )
                                )
                        )
                )
        );
        return ex1;
    }

    public static IStmt Ex2() {
        /*
        Ref int v;
        new(v,20);
        Ref Ref int a;
        new(a,v);
        new(v,30);
        print(rH(rH(a))) */
        IStmt ex6 = new CmpStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CmpStmt(
                        new newStmt("v", new ValExpr(new IntValue(20))),
                        new CmpStmt(
                                new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CmpStmt(
                                        new newStmt("a", new VarExpr("v")),
                                        new CmpStmt(
                                                new newStmt("v", new ValExpr(new IntValue(30))),
                                                new PrintStmt(new rH(new rH(new VarExpr("a"))))
                                        )
                                ))
                )
        );
        return ex6;
    }

    public static IStmt Ex3() {
        IStmt ex3 = new CmpStmt(
                new VarDeclStmt("a", new IntType()),
                new CmpStmt(
                        new VarDeclStmt("v", new IntType()),
                        new CmpStmt(
                                new AssignStmt("a", new ValExpr(new IntValue(3))),
                                new CmpStmt(
                                        new IfStmt(new RelationalExpr(new VarExpr("a"), new ValExpr(new IntValue(2)), 5), new AssignStmt("v", new ValExpr(new IntValue(2))), new AssignStmt("v", new ValExpr(new IntValue(3)))),
                                        new PrintStmt(new VarExpr("v"))
                                )
                        )
                )
        );
        return ex3;
    }

    public static IStmt Ex4() {
        IStmt ex4 = new CmpStmt(
                new VarDeclStmt("varf", new StringType()),
                new CmpStmt(
                        new AssignStmt("varf", new ValExpr(new StringValue("test1.txt"))),
                        new CmpStmt(
                                new VarDeclStmt("varc", new IntType()),
                                new CmpStmt(
                                        new openRFile(new VarExpr("varf")),
                                        new CmpStmt(
                                                new readFile(new VarExpr("varf"), "varc"),
                                                new CmpStmt(
                                                        new PrintStmt(new VarExpr("varc")),
                                                        new CmpStmt(
                                                                new readFile(new VarExpr("varf"), "varc"),
                                                                new CmpStmt(
                                                                        new PrintStmt(new VarExpr("varc")),
                                                                        new CmpStmt(new closeRFile(new VarExpr("varf")),
                                                                                new PrintStmt(new VarExpr("varc"))
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )

                        )
                )
        );
        return ex4;
    }

    public static IStmt Ex5() {
        IStmt ex5 = new CmpStmt(new VarDeclStmt("b", new BoolType()),
                new CmpStmt(
                        new VarDeclStmt("c", new IntType()),
                        new CmpStmt(
                                new AssignStmt("b", new ValExpr(new BoolValue(true))),
                                new CmpStmt(
                                        new ConditionalAssign("c", new VarExpr("b"), new ValExpr(new IntValue(100)), new ValExpr(new IntValue(200))),
                                        new CmpStmt(
                                                new PrintStmt(new VarExpr("c")),
                                                new CmpStmt(
                                                        new ConditionalAssign("c", new ValExpr(new BoolValue(false)), new ValExpr(new IntValue(100)), new ValExpr(new IntValue(200))),
                                                        new PrintStmt(new VarExpr("c"))
                                                )
                                        )
                                )
                        )
                ));
        return ex5;
    }

    public static IStmt Ex6() {
        IStmt ex6 = new CmpStmt(
                new VarDeclStmt("a", new RefType(new IntType())),
                new CmpStmt(
                        new newStmt("a", new ValExpr(new IntValue(20))),
                        new CmpStmt(
                                new ForStmt("v", new ValExpr(new IntValue(0)), new ValExpr(new IntValue(3)),
                                        new ArithExpr(1, new VarExpr("v"), new ValExpr(new IntValue(1))),
                                        new forkStmt(
                                                new CmpStmt(
                                                        new PrintStmt(new VarExpr("v")),
                                                        new AssignStmt("v", new rH(new VarExpr("a")))
                                                )
                                        )),
                                new PrintStmt(new rH(new VarExpr("a")))
                        )
                )
        );
        return ex6;
    }

    public static IStmt Ex7() {
        IStmt ex7 = new CmpStmt(new VarDeclStmt("a", new IntType()),
                new CmpStmt(
                        new VarDeclStmt("b", new IntType()),
                        new CmpStmt(
                                new VarDeclStmt("c", new IntType()),
                                new CmpStmt(
                                        new AssignStmt("a", new ValExpr(new IntValue(1))),
                                        new CmpStmt(
                                                new AssignStmt("b", new ValExpr(new IntValue(2))),
                                                new CmpStmt(
                                                        new AssignStmt("c", new ValExpr(new IntValue(5))),
                                                        new CmpStmt(
                                                                new SwitchStmt(new ArithExpr(3, new VarExpr("a"), new ValExpr(new IntValue(10))),
                                                                        new ArithExpr(3, new VarExpr("b"), new VarExpr("c")),
                                                                        new CmpStmt(new PrintStmt(new VarExpr("a")), new PrintStmt(new VarExpr("b"))),
                                                                        new ValExpr(new IntValue(10)),
                                                                        new CmpStmt(new PrintStmt(new ValExpr(new IntValue(100))), new PrintStmt(new ValExpr(new IntValue(200)))),
                                                                        new PrintStmt(new ValExpr(new IntValue(300)))
                                                                ),
                                                                new PrintStmt(new ValExpr(new IntValue(300)))
                                                        )
                                                )
                                        )
                                )
                        )
                ));
        return ex7;
    }

    public static IStmt Ex8() {
        IStmt ex8 = new CmpStmt(
                new VarDeclStmt("v", new IntType()),
                new CmpStmt(
                        new AssignStmt("v", new ValExpr(new IntValue(0))),
                        new CmpStmt(
                                new RepeatStmt(
                                        new CmpStmt(
                                                new forkStmt(new CmpStmt(new PrintStmt(new VarExpr("v")),
                                                        new AssignStmt("v", new ArithExpr(2, new VarExpr("v"), new ValExpr(new IntValue(1)))))),
                                                new AssignStmt("v", new ArithExpr(1, new VarExpr("v"), new ValExpr(new IntValue(1))))
                                        ),
                                        new RelationalExpr(new VarExpr("v"), new ValExpr(new IntValue(3)), 3)
                                ),
                                new CmpStmt(
                                        new AssignStmt("v", new ValExpr(new IntValue(3))),
                                        new PrintStmt(new ArithExpr(3, new VarExpr("v"), new ValExpr(new IntValue(10))))
                                )
                        )
                )
        );
        return ex8;
    }

    public static IStmt Ex9() {
        IStmt ex9 = new CmpStmt(
                new VarDeclStmt("v", new IntType()),
                new CmpStmt(
                        new AssignStmt("v", new ValExpr(new IntValue(20))),
                        new CmpStmt(
                                new SleepStmt(3),
                                new PrintStmt(new VarExpr("v"))
                        )
                )
        );
        return ex9;
    }

   /* Ref int v1; int cnt;
new(v1,1);createSemaphore(cnt,rH(v1));
    fork(acquire(cnt);wh(v1,rh(v1)*10));print(rh(v1));release(cnt));
    fork(acquire(cnt);wh(v1,rh(v1)*10));wh(v1,rh(v1)*2));print(rh(v1));release(cnt));
    acquire(cnt);
    print(rh(v1)-1);
    release(cnt)*/
    public static IStmt Ex10() {
        IStmt ex10 = new CmpStmt(
                new VarDeclStmt("v1", new RefType(new IntType())),
                new CmpStmt(
                        new VarDeclStmt("cnt", new IntType()),
                        new CmpStmt(
                                new newStmt("v1", new ValExpr(new IntValue(1))),
                                new CmpStmt(
                                        new CreateSemaphore("cnt", new rH(new VarExpr("v1"))),
                                        new CmpStmt(
                                                new forkStmt(new CmpStmt(
                                                        new Acquire("cnt"),
                                                        new CmpStmt(
                                                                new wH("v1", new ArithExpr(3, new rH(new VarExpr("v1")), new ValExpr(new IntValue(10)))),
                                                                new CmpStmt(
                                                                        new PrintStmt(new rH(new VarExpr("v1"))),
                                                                        new Release("cnt")
                                                                )
                                                        )

                                                )),
                                                new CmpStmt(
                                                        new forkStmt(
                                                                new CmpStmt(
                                                                        new Acquire("cnt"),
                                                                        new CmpStmt(
                                                                                new wH("v1", new ArithExpr(3, new rH(new VarExpr("v1")), new ValExpr(new IntValue(10)))),
                                                                                new CmpStmt(
                                                                                        new wH("v1", new ArithExpr(3, new rH(new VarExpr("v1")), new ValExpr(new IntValue(2)))),
                                                                                        new CmpStmt(
                                                                                                new PrintStmt(new rH(new VarExpr("v1"))),
                                                                                                new Release("cnt")
                                                                                        )
                                                                                )
                                                                        )
                                                                )),
                                                        new CmpStmt(
                                                                new Acquire("cnt"),
                                                                new CmpStmt(
                                                                        new PrintStmt(new ArithExpr(2, new rH(new VarExpr("v1")), new ValExpr(new IntValue(1)))),
                                                                        new Release("cnt")
                                                                )
                                                        )


                                                )
                                        )


                                )
                        )
                )
        );
        return ex10;
    }

    public static IStmt Ex11() {
        IStmt ex11 = new CmpStmt(
                new VarDeclStmt("v1", new RefType(new IntType())),
                new CmpStmt(
                        new VarDeclStmt("x", new IntType()),
                        new CmpStmt(
                                new newStmt("v1", new ValExpr(new IntValue(20))),
                                new CmpStmt(
                                        new NewLock("x"),
                                        new CmpStmt(new forkStmt(
                                                new CmpStmt(
                                                        new forkStmt(
                                                                new CmpStmt(new LockStmt("x"),
                                                                        new CmpStmt(new wH("v1", new ArithExpr(2, new rH(new VarExpr("v1")), new ValExpr(new IntValue(1)))),
                                                                                new UnlockStmt("x")
                                                                        )
                                                                )
                                                        ),
                                                        new CmpStmt(new LockStmt("x"),
                                                                new CmpStmt(new wH("v1", new ArithExpr(3, new rH(new VarExpr("v1")), new ValExpr(new IntValue(10)))),
                                                                        new UnlockStmt("x")
                                                                )
                                                        )
                                                )
                                        ),
                                                new CmpStmt(new NopStmt(),
                                                        new CmpStmt(new NopStmt(),
                                                                new CmpStmt(new NopStmt(),
                                                                        new CmpStmt(new NopStmt(),
                                                                                new CmpStmt(new LockStmt("x"),
                                                                                        new CmpStmt(
                                                                                                new PrintStmt(new rH(new VarExpr("v1"))),
                                                                                                new UnlockStmt("x")
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        return ex11;
    }

    public static void main(String[] args) {
        IStmt ex1 = Ex1();
        try {
            ex1.typecheck(new MyDict<String, Type>());
        } catch (MyException e) {
            e.printStackTrace();
            return;
        }
        PrgState prg1 = new PrgState(new MyStack<>(), new MyDict<>(), new MyList<>(), new MyDict<>(), new Heap<>(new HashMap<>()), ex1, 1, new Semaphore(), new Lock(),new CyclicBarrier());
        IRepo repo1 = new Repo(prg1, "log2.txt");
        Controller ctr1 = new Controller(repo1);

        IStmt ex2 = Ex2();
        try {
            ex2.typecheck(new MyDict<String, Type>());
        } catch (MyException e) {
            e.printStackTrace();
            return;
        }
        PrgState prg2 = new PrgState(new MyStack<>(), new MyDict<>(), new MyList<>(), new MyDict<>(), new Heap<>(new HashMap<>()), ex2, 2, new Semaphore(), new Lock(),new CyclicBarrier());
        IRepo repo2 = new Repo(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);
        IStmt ex3 = Ex4();
        try {
            ex3.typecheck(new MyDict<String, Type>());
        } catch (MyException e) {
            e.printStackTrace();
            return;
        }
        PrgState prg3 = new PrgState(new MyStack<>(), new MyDict<>(), new MyList<>(), new MyDict<>(), new Heap<>(new HashMap<>()), ex3, 3, new Semaphore(), new Lock(),new CyclicBarrier());
        IRepo repo3 = new Repo(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);

        IStmt ex5 = Ex5();
        PrgState prg5 = new PrgState(new MyStack<>(), new MyDict<>(), new MyList<>(), new MyDict<>(), new Heap<>(new HashMap<>()), ex5, 5, new Semaphore(), new Lock(),new CyclicBarrier());
        IRepo repo5 = new Repo(prg5, "log2.txt");
        Controller ctr5 = new Controller(repo5);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.show();
    }
}

