
import java.util.Iterator;
import java.util.NoSuchElementException;

class Node<T> {
    T data;
    Node prev = null;
    Node next = null;
    Node(T data,Node prev, Node next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
    Node(T data) {
        this.data = data;
    }
}
class MyLinkedList<T> implements Iterable<T> {
    private Node<T> start = null;
    private Node<T> end = null;
    int size = 0;
    public int size() {
        return size;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = start;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    MyLinkedList() {
    }
    MyLinkedList(Node node) {
        start = node;
        end = node;
        size++;
    }
    public void add (T added) {
        if(start==null) {
            start = new Node(added,null,null);
            end = start;
        }
        else {
            Node newNode = new Node(added,end,null);
            end.next = newNode;
            end = newNode;
        }
        size++;
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> pointer = start;
        for(int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.data;
    }
    public void delete(int index) {
        Node pointer = start;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            start = start.next;
        } else {
            Node<T> current = start;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }
    public void peek() {
        if(end==null) System.out.println("null");
        System.out.println(end.data);
    }
    public void pop() {
        System.out.println(end.data);
        if(end.prev==null) {
            start = null;
            end = null;
        }
        else {
            end = end.prev;
        }
    }
}
class MyQueue<T> {
    private MyLinkedList<T> list = new MyLinkedList<>();

    public void enqueue(T data) {
        list.add(data);
    }

    public T dequeue() {
        if (list.size() == 0) {
            throw new NoSuchElementException();
        }
        T data = list.get(0);
        list.delete(0);
        return data;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
class MyStack<T> {
    private MyLinkedList<T> list = new MyLinkedList<>();

    public void push(T data) {
        list.add(data);
    }

    public T pop() {
        if (list.size() == 0) throw new NoSuchElementException();
        T data = list.get(list.size() - 1);
        list.delete(list.size() - 1);
        return data;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}


public class Main {
    public static void main(String[] args) {
        Node node1 = new Node("string");
        Node node2 = new Node(true);
        Node node3 = new Node(10);
        Node node4 = new Node(4);
        MyLinkedList<Integer> my = new MyLinkedList(node3);
        my.add(4);
        my.add(3);
        my.add(2);
        my.delete(1);
        my.add(1);
        System.out.println(my.get(0));
        System.out.println(my.get(1));
        System.out.println(my.get(2));
        System.out.println(my.get(3));
        for(int now : my) {
            System.out.println(now);
        }
        MyQueue<String> queue = new MyQueue<>();
        queue.enqueue("안녕");
        queue.enqueue("하세");
        queue.enqueue("요");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        MyStack<String> stack = new MyStack<>();
        stack.push("스택");
        stack.push("테스트");
        stack.push("321");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
