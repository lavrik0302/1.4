package com.company;

import java.util.Scanner;

class DoubleLinkList<T> {
    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T elt, Node<T> next) {
            this.prev = prev;
            this.item = elt;
            this.next = next;
        }
    }

    private final Node<T> first;
    private final Node<T> last;
    private int size = 0;

    public DoubleLinkList() {
        first = new Node<>(null, null, null);
        last = new Node<>(null, null, null);
        first.next = last;
        last.prev = first;
    }
    private Node<T> move(int pos) {
        Node<T> n = first;
        for (int i = 0; i <= pos; ++i) {
            n = n.next;
        }
        return n;
    }

    public void add(int pos, T elt) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException(pos + " is out of bounds");
        }
        Node<T> n = move(pos-1);
        Node<T> newNode = new Node<>(null, elt, null);
        newNode.prev = n;
        newNode.next = n.next;
        n.next.prev = newNode;
        n.next = newNode;
        size++;
    }


    public void add(T elt) {
        Node<T> n = last.prev;
        Node<T> newNode = new Node<>(null, elt, null);
        newNode.prev = n;
        newNode.next = n.next;
        n.next.prev = newNode;
        n.next = newNode;
        size++;
    }

    public T remove(int pos){
        Node<T> n = move(pos-1);
        T data = n.next.item;
        n.next.next.prev=n;
        n.next=n.next.next;
        size--;
        return data;
    }

    public T get(int pos) {
        if (pos < 0 || pos > size-1) {
            throw new IndexOutOfBoundsException(pos + " is out of bounds");
        }
        Node<T> n = move(pos);
        return n.item;
    }

    public T set(int pos, T elt) {
        if (pos < 0 || pos > size-1) {
            throw new IndexOutOfBoundsException(pos + " is out of bounds");
        }
        Node<T> n = move(pos);
        T prev = n.item;
        n.item = elt;
        return prev;
    }
    public int size() {
        return size;
    }
}

class Node{
    public String phoneNumber;
    public Node next;
    public Node prev;

    public Node(String number) {
        phoneNumber = number;
        next = null;
        prev = null;
    }
}
class OrderedList{
    private Node first;
    private int size;
    public OrderedList() {
        first = null;
        size = 0;
    }
    public void addNew(Node obj) {
        if (size == 0)
            first = obj;
        else {
            Node curr = first, prev = curr;
            while (curr != null && (curr.phoneNumber.compareTo(obj.phoneNumber) < 0) ) {
                prev = curr;
                curr = curr.next;
            }
            if (curr == first) {
                obj.next = first;
                first = obj;
            } else {
                obj.next = curr;
                prev.next = obj;
            }
        }
        size++;
    }

    public void printList() {
        if (size == 0) System.out.println("Список пуст");
        else {
            int index = 1;
            Node curr = first;
            System.out.printf("|%14s|\n", "Упорядоченный");
            while (curr != null) {
                System.out.println("|--------------|");
                System.out.printf("|%14s|\n", curr.phoneNumber);
                curr = curr.next;
                index++;
            }
        }
        System.out.println("|______________|");
    }
}
public class Main {

    public static void main(String[] args) {
        DoubleLinkList<Special> iter = new DoubleLinkList<>();
        Scanner scn = new Scanner(System.in);
        boolean f = true;
        System.out.println("Введите 7 значный номер(абонент) или 3 значный (спецслужба)");
        String number = "";
        while (f == true) {
            number = scn.nextLine().trim();
// проверка на корректность
            if (Special.checknumber(number) || Special.checkSnumber(number)) {
                f = false;
            } else {
                System.out.println("Некорректный ввод . Попробуте ещё раз ");
            }
            if (!f) {
                iter.add(new Special(number));
                System.out.println("Номер успешно добавлен.Если хотите завершить добавление номеров телефонов введите 0");
            }
            f = true;
            if (number.equals("0")) {
                break;
            }

        }

        System.out.printf("|%14s|\n", "Номер телефона");
        for (int i = 0; i < iter.size(); i++) {
            System.out.println("|--------------|");
            System.out.printf("|%14s|\n", iter.get(i).getnumber());
        }
        System.out.println("|______________|");

        OrderedList oList = new OrderedList();
        String s5 = "";
        for (int i = iter.size() - 1; i >= 0; i--) {
            s5 = iter.get(i).getnumber();
            int d = s5.length();
            if (d == 3) {
                continue;
            }
            oList.addNew(new Node(s5));
        }

        oList.printList();
    }
    }

