
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
    }
    public void getIndex(int index) {
        if(start==null) System.out.println("null");
        Node pointer = start;
        for(int i = 0; i < index; i++) {
            if(pointer.next == null) System.out.println("null");
            pointer = pointer.next;
        }
        System.out.println(pointer.data);
    }
    public void delete(int index) {
        Node pointer = start;
        for(int i = 0; i < index; i++) {
            if(pointer.next==null) System.out.println("인덱스가 존재하지 않음");
            pointer = pointer.next;
        }
        // 첫 번째 원소일 경우
        if(pointer.prev==null) {
            if(pointer.next==null) {
                start=null;
                end=null;
                return;
            }
            else {
                start = pointer.next;
                pointer = pointer.next;
            }
        }
        // 마지막 원소일 경우
        if(pointer.next==null) {
            pointer.prev.next = null;
            end = pointer.prev;
            return;
        }
        //중간일 경우
        pointer.prev.next = pointer.next;
        pointer.next.prev = pointer.prev;
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
        my.getIndex(0);
        my.getIndex(1);
        my.getIndex(2);
        my.getIndex(3);
        for(int now : my) {
            System.out.println(now);
        }
    }
}
