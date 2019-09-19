import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
 * This is the double end list class   
 * @author Chris Flodstrom (czf0038@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 1 March 2019
 */
public class DoubleEndList<T> implements DoubleEndedList<T> {
   private Node fr, lst;
   private int sz;
   public DoubleEndList() {
      fr = null;
      sz = 0;
   }
   
    /**
    * Adds element to the front of the list.
    * Will throw an IllegalArgumentException if it is null
    */
   public void addFirst(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      Node n = new Node(element);
      if (size() == 0) {
         fr = n;
         lst = n;
      }
      else {
         n.next = fr;
         fr = n;
      }
      sz++;  
   }
   
    /**
    * adds elem to end of the list. If elem is null,
    * it throws an IllegalArgumentException.
    */
   public void addLast(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      Node n = new Node(element);
      n.element = element;
      if (sz == 0) {
         fr = n;
         lst = n;
      }
      else {
         lst.next = n;
         lst = n;
      }
      sz++;
   }

 /**
    * Delete the element to the front of the list.
    * If it is empty the method is null.
    */
   public T removeFirst() {
      if (sz == 0) {
         return null;
      }
      T deleted = fr.element;
      fr = fr.next;
      sz--;
      return deleted;
   }
   
    /**
    * Deletes the element at the end of the list.
    * method is null if list is empty.
    */
   public T removeLast() {
      if (sz == 0) {
         return null;
      }
      else if (sz == 1) {
         T deleted = fr.element;
         fr = null;
         lst = null;
         sz--;
         return deleted;
      }
      
      else {
         Node n = fr;
         while (n.next.next != null) {
            n = n.next;
         }
         T deleted = n.next.element;
         n.next = null;
         lst = n;
         sz--;
         return deleted;
      }
      
   }
   
    /**
    * Returns the number of elements.
    */
   public int size() {
      return sz;
   }

   public Iterator<T> iterator() {
      return new Iteration();
   }
   
    /**
    * Returns true if this list is empty or false.
    */
   public boolean isEmpty() {
      return sz == 0;
   }
   
   /**
   * Nested helper class to allow implementation of a Node in top level
   * level class.
   */ 
   private class Node {
      private T element;
      private Node next;
      public Node(T t) {
         element = t;
      }
      public Node(T t, Node n) {
         element = t;
         next = n;
      }
   }
   
    /**
    * Creates and returns an iterator of the elements.
    * The order of the elements returned by this iterator is in order
    * from the beginning to the end of the list.
    */
   private class Iteration implements Iterator<T> {
      private Node current = fr;
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         T result = current.element;
         current = current.next;
         return result;
      }
      public boolean hasNext() {
         return current != null;
      }
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}