package org.ds.dynamicarray;

public class BinarySearchTree<T extends Comparable<T>> {

    private int nodeCount = 0;

    private Node root = null;


    private class Node {
        T data;
        Node left, right;

        public Node(Node left, Node right, T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int size() {
        return nodeCount;
    }

    public boolean add(T element) {

        //Check if element is already present in tree.
        if (contains(element)) {
            return false;
        } else {
            root = add(root, element);
            nodeCount++;
            return true;
        }
    }

    private Node add(Node node, T element) {
        //Base case: found null leaf node
        if (node == null) {
            node = new Node(null, null, element);
        } else {
            if (element.compareTo(node.data) < 0) {
                node.left = add(node.left, element); //place element in lower subtree.
            } else {
                node.right = add(node.right, element); //place element un right subtree.
            }
        }
        return node;
    }


    private boolean contains(T element) {
        return true;
    }
}
