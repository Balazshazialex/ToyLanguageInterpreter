package Model.ADT;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;
    StringBuilder message = new StringBuilder();
    public MyStack(){
        stack=new Stack<T>();
    }
    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public List<T> getAll() {
        return stack;
    }
}
