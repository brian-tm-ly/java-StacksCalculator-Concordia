public class MyStack<E> {
    private E[] data; // generic array used to store the elements of the stack
    private int top; // index of the top element in the stack
    private static final int CAPACITY = 4; //Default capacity of the stack

    public MyStack(int capacity) {
        data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
        top = -1; // stack is empty when top is -1
    }

    public MyStack() {
        this(CAPACITY);
    }

    public int size() {
        return top + 1; // returns the number of elements in the stack
    }

    public boolean isEmpty() {
        return (top == -1); // returns true if the stack is empty
    }

    public void push(E e) {
        if (size() == data.length) {
            resize();
        }
        data[top++] = e; // increment top before storing new element
    }

    public E top() {
        if (isEmpty()) {
            return null;
        } else {
            return data[top]; // return the top element of the stack

        }
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        } else {
            E result = data[top]; // store the top element in a variable
            data[top] = null; // remove top element;
            top--; // decrement top
            return result;
        }
    }

    public void resize() {
        E[] temp = (E[]) new Object[data.length * 2]; // safe cast; compiler may give warning. Doubling the size of the array
        // Copy the elements of the old array to the new array
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp; // data now refers to the new array
    }
}
