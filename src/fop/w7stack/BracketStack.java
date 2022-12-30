package fop.w7stack;

public class BracketStack {
    private char[] stack;
    private int stackpointer;
    public BracketStack(int maxSize) {
        stack = new char[maxSize];
    }
    public void push(char value){
        stack[stackpointer++] = value;
    }
    public char pop(){
        return stack[--stackpointer];
    }
    public char peek(){
        return stack[stackpointer -1];
    }
    public boolean isEmpty(){
        return stackpointer == 0;
    }
    public static boolean checkBraces(String input) {
        BracketStack stack = new BracketStack(input.length());
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current == '(' || current == '[' || current == '{')
                stack.push(current);
            else if ((current == ')' || current == ']' || current == '}')
                    && (stack.isEmpty() || !matches(stack.pop(), current)))
                return false;
        }
        return stack.isEmpty();
    }
    private static boolean matches(char open, char close) {
        return open == '(' && close == ')' || open == '[' && close == ']' || open == '{' && close == '}';
    }
}
