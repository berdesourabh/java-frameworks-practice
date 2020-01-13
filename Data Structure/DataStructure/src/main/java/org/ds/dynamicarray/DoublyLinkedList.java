package org.ds.dynamicarray;

import org.junit.Assert;

public class DoublyLinkedList<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node<T> {
        T data;
        Node<T> previous, next;

        public Node(T data, Node<T> previous, Node<T> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;

        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    //clear() //loop through and make all null and head and tail null
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T element) {
        addLast(element);  //appending element to the last.
    }

    public void addFirst(T element) {
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        } else {
            head.previous = new Node<T>(element, null, head);
            head = head.previous;
        }

        size++;
    }

    private void addLast(T element) {
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        } else {
            tail.next = new Node<T>(element, tail, null);
            tail = tail.next;
        }

        size++;
    }

    //T peekFirst(); // getting value of 1st node.
    // T removeFirst();

    private T remove(Node<T> rm_node) {
        //if(node.previous == null) return removeFirst(); //If its a first node to remove.
        //if(node.next == null) return removeLast(); //If it's last node to remove.

        rm_node.next.previous = rm_node.previous; //pointing deleting nodes next element to deleting nodes prev.     3   <--- 4 --->   5
        rm_node.previous.next = rm_node.next; //pointing deleting nodes prev element to deleting nodes next.              deleting 4

        T data = rm_node.data; //to return deleted node data.

        rm_node.data = null;
        rm_node = rm_node.previous = rm_node.next = null; //Clearing node
        --size;

        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();

        int i;
        Node<T> trav;

        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++) {
                trav = trav.next;
            }
        } else {
            for (i = size - 1, trav = tail; i != index; i--) {
                trav = trav.previous;
            }
        }
        return remove(trav);
    }


    public static void main(String[] args) {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
        dl.add(1);
        dl.add(2);
        dl.add(3);

//        dl.removeAt(2);

        Assert.assertEquals(3, dl.size);
    }
}
