package org.ds.dynamicarray;

import org.junit.Assert;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {

    private int nodeCount = 0;

    private Node root = null;


    enum TreeTraversalIterator {
        PRE_ORDER,
        IN_ORDER,
        POST_ORDER,
        LEVEL_ORDER
    }

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

    public boolean remove(T element) {
        if (contains(element)) {
            remove(root, element);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T element) {
        if (node == null) return null;

        int cmp = element.compareTo(node.data);

        if (cmp < 0) {
            node.left = remove(node.left, element);
        } else if (cmp > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                Node rightChild = node.right;

                node.data = null;
                node = null;

                return rightChild;
            } else if (node.right == null) {
                Node leftChild = node.left;

                node.data = null;
                node = null;

                return leftChild;
            }
        }
        //handle both tree present case
        return null;
    }

    private boolean contains(T element) {
        return contains(root, element);
    }


    private boolean contains(Node node, T element) {
        if (node == null) return false;

        int cmp = element.compareTo(node.data);

        if (cmp < 0) {
            return contains(node.left, element);
        } else if (cmp > 0) {
            return contains(node.right, element);
        } else return true;
    }

    public Iterator<T> traverse(TreeTraversalIterator order) {
        switch (order) {
            case PRE_ORDER:
                return preOrderTraversal();
            case IN_ORDER:
                return inOrderTraversal();
           /* case POST_ORDER:
                return postOrderTraversal();
            case LEVEL_ORDER:
                return levelOrderTraversal();*/
            default:
                return null;
        }
    }

    private Iterator<T> preOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }
        };
    }

    private Iterator<T> inOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {
            Node trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();

            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();

                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.data;

            }
        };
    }
/*
    private Iterator<T> postOrderTraversal() {
    }

    private Iterator<T> levelOrderTraversal() {
    }*/


    public static void main(String[] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        t.add(5);
        t.add(10);
        t.add(11);
        t.add(2);

        Iterator it = t.inOrderTraversal();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
        Assert.assertTrue(t.contains(2));
    }
}

