package double_linked_list;

import java.util.Objects;

public class DoubleLinkedList<T> {
    private ListItem<T> head;
    private ListItem<T> tail;
    private int size;

    public ListItem<T> popHeadElement() {
        if (head == null) return null;
        ListItem<T> result = new ListItem<>(head.getData());
        removeHeadElement();
        return result;
    }

    public ListItem<T> popTailElement() {
        if (tail == null) return null;
        ListItem<T> result = new ListItem<>(tail.getData());
        removeTailElement();
        return result;
    }

    public void removeHeadElement() {
        if (head == null) return;
        head = head.next;
        if (head == null) tail = null;
        size--;
    }

    public void removeTailElement() {
        if (tail == null) return;
        tail = tail.prev;
        if (tail == null) head = null;
        size--;
    }

    public void addToHead(T data) {
        ListItem<T> newItem = new ListItem<>(data);
        if (tail == null) tail = newItem;
        if (head == null) {
            head = newItem;
        } else {
            ListItem<T> tempItem = head;
            head = newItem;
            head.next = tempItem;
            tempItem.prev = head;
        }
        size++;
    }

    public void addToTail(T data) {
        ListItem<T> newItem = new ListItem<>(data);
        if (head == null) head = newItem;
        if (tail == null) {
            tail = newItem;
        } else {
            ListItem<T> tempItem = tail;
            tail = newItem;
            tail.prev = tempItem;
            tempItem.next = tail;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public ListItem<T> getHeadElement() {
        return head;
    }

    public ListItem<T> getTailElement() {
        return tail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleLinkedList<T> that = (DoubleLinkedList<T>) o;
        return Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }

    @Override
    public String toString() {
        if (head == null) {
            return "DoubleLinkedList is empty size = " + size;
        }

        StringBuilder stringBuilder = new StringBuilder(head.toString());
        ListItem<T> item = head;
        while (item.next != null) {
            if (item.next.prev == item) {
                stringBuilder.append("<-");
            }

            stringBuilder.append(" -> ").append(item.next);
            item = item.next;
        }

        return "DoubleLinkedList{size=" + size + "\n" + stringBuilder.toString() + "}";
    }
}