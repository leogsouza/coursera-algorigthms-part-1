import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private Node<Item> head = null;
  private Node<Item> tail = null;
  private int size = 0;
  

  // construct an empty deque
  public Deque() {}

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() { 
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("item cannot be null");
    }

    Node<Item> newNode = new Node<>(item);
    if (size == 0) {
      head = newNode;
      tail = newNode;
    } else {
      head.prev = newNode;
      newNode.next = head;
      head = newNode;
    }
    size++;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null)  {
      throw new IllegalArgumentException("item cannot be null");
    }
    Node<Item> newNode = new Node<>(item);
    if (size == 0) {
      head = newNode;
      tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
    size++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (size == 0) {
      throw new NoSuchElementException("Unable to removeFirst: queue is empty");
    }
    size--;
    Item removedItem = tail.node;
    head = head.next;
    if (head != null) {
      head.prev = null;
    } else {
      tail = null;
    }

    return removedItem;

  }

  // remove and return the item from the back
  public Item removeLast() {
    if (size == 0) {
      throw new NoSuchElementException("que is empty");
    }
    size--;
    Item removedItem = tail.node;
    tail = tail.prev;
    if (tail != null) {
      tail.next = null;
    } else {
      head = null;
    }

    return removedItem;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new Iterator<Item>() {
      Node<Item> currNode = head;

      @Override
      public boolean hasNext() { return currNode != null; }

      @Override
      public Item next() {
          if (!hasNext()) {
              throw new NoSuchElementException("no more elements");
          }
          Item i = currNode.node;
          currNode = currNode.next;
          return i;
      }

      @Override
      public void remove() { throw new UnsupportedOperationException(); }
    };
    
  }

  private static class Node<T> {
    private final T node;
    private Node<T> next;
    private Node<T> prev;

    public Node(T node) {
      this.node = node;
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<>();
    
  }

}