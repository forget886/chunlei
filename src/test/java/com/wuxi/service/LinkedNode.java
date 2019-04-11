package com.wuxi.service;

public class LinkedNode<E> {

    public LinkedNode() {
    }

    Node<E> head;
    Node<E> tail;


    public void add(E item) {
        Node<E> newNode = new Node<E>(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public Node<E> remove() {
        if (head == null)
            return null;
        if (head == tail)
            tail = null;
        Node<E> p = head;
        head = p.next;
        p.next = null;
        return p;
    }

    public void print() {
        for (Node<E> p = head; p != null; p = p.next) {
            System.out.println(p.item);
        }
        System.out.println("--------------------");
    }

    private class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

}
