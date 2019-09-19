import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/** 
 * @author   Chris Flodstrom (czf0038@auburn.edu)
 * @version  1 March 2019
 * @param <T> - used
 */
public class RandomList<T> implements RandomizedList<T> {
   private T[] elem;
   private int size;
   private static final int DEFAULT_LENGTH = 1;
   
   /**
   * RandomList obj is created.
   */
   @SuppressWarnings("unchecked")
   public RandomList() {
      size = 0;
      elem = (T[]) new Object[DEFAULT_LENGTH];
   }
   
   /**
   * a list iterator is made.
   * @return - used.
   */
   public Iterator<T> iterator() {
      Iteration iter = new Iteration(elem, size());
      return iter;
   }

   /**
   * the list adds an element.
   * @param element - used 
   */
   public void add(T elmt) {
      if (elmt == null) {
         throw new IllegalArgumentException("elem cannot be null");
      }
      if (size == elem.length) {
         resize(elem.length * 2);
      }
      elem[size()] = elmt;
      size++;
   }

   /**
   * removes the random elmt.
   * @return - used
   */
   public T remove() {
      if (size() == 0) {
         return null;
      } 
      int r = new Random().nextInt(size());
      T del = elem[r];
      elem[r] = elem[size() - 1];
      elem[size() - 1] = null;
      size--;
      if (size() > 0 && size() < elem.length / 4) {
         resize(elem.length / 2);
      }
      return del;
   }

   /**
   * returns random elmt.
   * @return - used
   */
   public T sample() {
      if (size == 0) {
         return null;
      }
      int r = new Random().nextInt(size());
      return elem[r];
   }
   
   /**
   * returns the size
   * @return - used
   */
   public int size() {
      return size;
   }
  
   /**
   * looks to see if the list is empty
   * @return - used
   */
   public boolean isEmpty() {
      return size == 0;
   }
   
   /**
   * resizes element array.
   * @param capacity - used
   */
   @SuppressWarnings("unchecked")
   private void resize(int capacity) {
      T[] arr = (T[]) new Object[capacity];
      for (int i = 0; i < size(); i++) {
         arr[i] = elem[i];
      }
      elem = arr;
   }
      
   /**
   * Defines iterator behavior.
   */
   private class Iteration implements Iterator<T> {
      private T[] itm;
      private int cnt;
      private int curr;
      
      /**
      * creates the special iterator
      */
      Iteration(T[] arr, int amt) {
         itm = arr;
         cnt = amt;
         curr = 0;
      }
   
      /**
      * sees if theres another element.
      * @return  - used.
      */
      public boolean hasNext() {
         return (curr < size());
      }
      
      /**
      * returns the next element
      * @return  - used.
      */
      public T next() {
         if	(!hasNext()) {
            throw new NoSuchElementException();
         }
         return itm[curr++];
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}