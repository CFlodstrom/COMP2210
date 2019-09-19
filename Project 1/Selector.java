import java.util.Arrays;

/**
 *  Defines a library of selection methods
 *  on arrays of ints.
 *
 *  @author   Chris Flodstrom (czf0038@auburn.edu)
 *  @author   Dean Hendrix (dh@auburn.edu)
 *  @version  24 Jan 19
 *
 */
public final class Selector {
   /**
    *  Can't instantiate this class.
    *
    *    D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    *
    */
   private Selector() { }
    
   /**
    *  Selects the minimum value from the array a. This method
    *  throws IllegalArgumentException if a is null or has zero
    *  length. The array a is not changed by this method.
    *  @param a -used
    *  @return min.
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int min = a[0];
      for (int i = 1; i < a.length; i++ ) {
         if (a[i] < min) {
            min = a[i];
         }
      }
      return min;
   }

   /**
    *  Selects the maximum value from the array a. This method
    *  throws IllegalArgumentException if a is null or has zero
    *  length. The array a is not changed by this method.
    *  @param a -used
    *  @return max
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      } 
      int max = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] > max) {
            max = a[i];
         }
      }
      return max;     
   }
   
   /**
    *  The kmin method selects the kth minimum value from a given array.
    *  Throws an IllegalArgumentException if the array is null,
    *  has zero length, or there is no kth minimum value.
    *  @param a - used
    *  @param k - used
    *  @return -used
    */ 
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      //copies array to avoid changing original
      int[] b = new int[a.length];
      for (int i = 0; i < a.length; i++) {
         b[i] = a[i];
      }
      //Sorts
      Arrays.sort(b);
      int distinct = 1;
      int temp = b[0];
      int kmin = 0;
      if (k == 1) {
         kmin = b[0];
         return kmin;
      }
      //Checks array for distinct elements
      for (int i = 1; i < a.length; i++) {
         if (b[i] != temp) {
            distinct++;
            if (distinct == k) {
               kmin = b[i];
            }
         }
         temp = b[i];
      }
      //If k is bigger there is no kmin.
      if (k > distinct) {
         throw new IllegalArgumentException();
      }
      return kmin;
   }
   
   /**
    *  The kmax method selects the kth maximum value from a given array.
    *  Throws an IllegalArgumentException if the array is null,
    *  has zero length, or there is no kth maximum value.
    *  @param a -used
    *  @param k -used
    *  @return -used
    */    
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      //copies array
      int[] b = new int[a.length];
      for (int i = 0; i < a.length; i ++) {
         b[i] = a[i];
      }
      //Sorts 
      Arrays.sort(b);
      int distinct = 1;
      int temp = b[b.length - 1];
      int kmax = 0;
      if (k == 1) {
         kmax = b[b.length - 1];
         return kmax;
      }
      //Checks array for distinct elements
      for (int i = b.length - 1; i >= 0; i--) {
         if (b[i] != temp) {
            distinct++;
            if (distinct == k) {
               kmax = b[i];
            }
         }
         temp = b[i];
      }
      //If k is greater than
      if (k > distinct) {
         throw new IllegalArgumentException();
      }      
      return kmax;
   }
        
   /**
    *  Returns an array containing all the values in a in the
    *  range [low..high]; that is, all the values that are greater
    *  than or equal to low and less than or equal to high,
    *  including duplicate values. The length of the returned array
    *  is the same as the number of values in the range [low..high].
    *  If there are no qualifying values, this method returns a
    *  zero-length array. Note that low and high do not have
    *  to be actual values in a. This method throws an
    *  IllegalArgumentException if a is null or has zero length.
    *  The array a is not changed by this method.
    *  @param a - used
    *  @param low - used
    *  @param high - used
    *  @return - used
    */
   public static int[] range(int[] a, int low, int high) {
   
      //if a is null or 0
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("must be an"
                + " array of one integer or more");
      }
   
      //This is a copy
      int[] b = Arrays.copyOf(a, a.length);
      int j = 0;
   
   //values within the range are listed first in the array
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            b[j] = b[i];
            j++;
         }
      }
   
      //when no values are in the range
      if (j == 0) {
         int[] c = {};
         return c;
      }
      //if values are in the range
      else {
         int[] d = Arrays.copyOf(b, j);
         return d;
      }
   }

    /**
    *  Returns the smallest value in a that is greater than or equal to
    *  the given key. This method throws an IllegalArgumentException if
    *  a is null or has zero length, or if there is no qualifying
    *  value. Note that key does not have to be an actual value in a.
    *  The array a is not changed by this method.
    *  @param a -used
    *  @param key -used
    *  @return -used
    */
   public static int ceiling(int[] a, int key) {
    
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int ceiling = 0;
      boolean found = false; //Tels if first possible ceiling found
      for (int i = 0; i < a.length; i ++) {
      
      //if no ceiling.
         if (!found && a[i] >= key) {
            ceiling = a[i];
            found = true;
         }
         //if ceiling.
         else if (a[i] >= key && a[i] <= ceiling) {
            ceiling = a[i];
         }
      }
      //If not ceiling, throw an exception.
      if (!found) {
         throw new IllegalArgumentException();
      }
      return ceiling;
   }

   /**
    *  Returns the largest value in a that is less than or equal to
    *  the given key. This method throws an IllegalArgumentException if
    *  a is null or has zero length, or if there is no qualifying
    *  value. Note that key does not have to be an actual value in a.
    *  The array a is not changed by this method.
    *  @param a -used
    *  @param key -used
    *  @return -used
    */
   public static int floor(int[] a, int key) {
   
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int floor = 0;
      boolean found = false; 
      for (int i = 0; i < a.length; i ++) {
      //only compare to key if not first floor.
         if (!found && a[i] <= key) {
            floor = a[i];
            found = true;
         }
         //If floor found, compare to key.
         else if (a[i] <= key && a[i] >= floor) {
            floor = a[i];
         }
      }
      //If floor never found, throw an exception.
      if (!found) {
         throw new IllegalArgumentException();
      }
      return floor;
   }
}
