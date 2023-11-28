
import java.util.Iterator;

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
class MyLinkedList <T> implements Iterator {
    private Node start = null;
    private Node end = null;
    private Node pointer = null;
    @Override
    public boolean hasNext() {
        if(pointer.next != null) return true;
        return false;
    }
    @Override
    public Node next() {
        if(pointer.next == null) return null;
        pointer = pointer.next;
        return pointer;
    }
    MyLinkedList() {
        start = null;
        end = null;
        pointer = null;
    }
    MyLinkedList(Node node) {
        start = node;
        end = node;
        pointer = node;
    }
    public void add (T added) {
        if(start==null) {
            start = new Node(added,null,null);
            end = start;
            pointer = start;
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
    public void getLast() {
        if(end==null) System.out.println("null");
        System.out.println(end.data);
    }
}
public class Main {
    public static void main(String[] args) {
        Node node1 = new Node("string");
        Node node2 = new Node(true);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        MyLinkedList my = new MyLinkedList(node1);
        my.add(4);
        my.add(3);
        my.add(2);
        my.delete(1);
        my.add(1);
        my.getIndex(0);
        my.getIndex(1);
        my.getIndex(2);
        my.getIndex(3);
    }
}
