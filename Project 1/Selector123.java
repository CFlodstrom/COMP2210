import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Chris Flodstrom (czf0038@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 05 Feb 2019
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param <T> - used
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      // if the collection is empty or null, then comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an array of"
            + " one or more integers");
      }
      
      if (coll.isEmpty()) {
         throw new NoSuchElementException("must be a non empty"
            + " collection");
      }
      
      Iterator <T> iter = coll.iterator();
      T minimum = iter.next();
      
      if (iter.hasNext()) {
         for (T elem: coll) {
            if (comp.compare(elem, minimum) < 0) {
               minimum = elem;
            }
         }
      }
      return minimum;
         
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    * @param <T> - used
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      // if the collection is empty or null, then comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an array of"
            + " one or more integers");
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException("must be a non empty"
                  + " collection");
      }
      Iterator<T> iter = coll.iterator();
      T maximum = iter.next();
      if (iter.hasNext()) {
         for (T elem: coll) {
            if (comp.compare(elem, maximum) > 0) {
               maximum = elem;
            }
         }
      }
      return maximum;
                            
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    * @param <T> - used
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      // checks if collection is empty, comp is null
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an array of"
            + " one or more integers");
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException("must be a non empty"
                  + " collection");
      }
      //creates a collection arraylist and sorts
      List<T> cpylst = new ArrayList(coll);
      java.util.Collections.sort(cpylst, comp);
      //checks if k is less than 0 or k is larger than the array length
      if (k > cpylst.size() || k <= 0) {
         throw new NoSuchElementException("k - 1 needs to be within"
            + " the array set");
      }
      //counts how many unique and duplicate numbers and removes duplicates
      int dupCnt = 0;
      int unqTot = 0;
      int orgTot = cpylst.size();
      for (int i = 0; i < cpylst.size() - 1; i++) {
         while (cpylst.size() > 1 && i < cpylst.size() - 1
            && cpylst.get(i) == cpylst.get(i + 1)) {
            cpylst.remove(i);
            dupCnt++;
         }   
      }
      unqTot = orgTot - dupCnt;
      //if k is more than total number of unique numbers
      if (k > unqTot) {
         throw new NoSuchElementException("k must be less"
            + " than the unique count of numbers");
      }
      return cpylst.get(k - 1);        
   }
   
   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    * @param <T> - used
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an array of"
            + " one or more integers");
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException("must be a non empty"
                  + " collection");
      }
      //creates a collection arraylist and sorts it
      List<T> cpylst = new ArrayList(coll);
      java.util.Collections.sort(cpylst, comp);
      
      //checks if k is less than 0 or greater than array length
      if (k > cpylst.size() || k <= 0) {
         throw new NoSuchElementException("k-1 must be a value"
            + " in an array set"); 
      }
      //counts number of unique and duplicate nums and removes duplicates
      int dupCnt = 0;
      int unqTot = 0;
      int orgTot = cpylst.size();
      for (int i = 0; i < cpylst.size() - 1; i++) {
         while (cpylst.size() > 1 && i < cpylst.size() - 1
            && cpylst.get(i) == cpylst.get(i + 1)) {
            cpylst.remove(i);
            dupCnt++;
         }
      }
      unqTot = orgTot - dupCnt;
      //if k is more than total number values
      if (k > unqTot) {
         throw new NoSuchElementException("k has to be less"
            + " than the unique num count");
      }
      return cpylst.get(unqTot - k);              
   }

   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    * @param <T> - used
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an array of"
            + " one or more integers");
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException("must be a non empty"
                  + " collection");
      }
      //makes arraylist of orignal and rangeq
      List<T> cpylst = new ArrayList(coll);
      List<T> range = new ArrayList(coll);
      int j = 0;
      
      for (int i = 0; i < cpylst.size(); i++) {
         if ((comp.compare(cpylst.get(i), low) >= 0)
            && (comp.compare(cpylst.get(i), high) <= 0)) {
            range.set(j, cpylst.get(i));
            j++;
         }
      }
      //if no values are in range
      if (j == 0) {
         throw new NoSuchElementException("the collection must"
            + " be within range");
      }
      //deletes extra values
      for (int i = range.size() - 1; i > j - 1; i--) {
         range.remove(i);
      }
      return range;
   
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    * @param <T> - used
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be an array of"
            + " one or more integers");
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException("must be a non empty"
                  + " collection");
      }
   // finds max
      Iterator<T> iter = coll.iterator();
      T ceiling = iter.next();
      if (iter.hasNext()) {
         for (T elem: coll) {
            if (comp.compare(elem, ceiling) > 0) {
               ceiling = elem;
            }
         }
      }
      int chng = 0;
      //finds lowest value
      for (T elem: coll) {
         if ((comp.compare(elem, key) >= 0)
               && (comp.compare(elem, ceiling) <= 0)) {
            ceiling = elem;
            chng++;
         }
      }
      //if none qualify
      if (chng == 0) {
         throw new NoSuchElementException("there is no qualifying"
            + " value");
      }
      return ceiling;
   
   }
   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    * @param <T> - used
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
     //if a is null or has a length of 0
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("must be"
            + " one or more integers");
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException("can't be empty coll");
      }
      //finds min value
      Iterator<T> iter = coll.iterator();
      T flr = iter.next();
      if (iter.hasNext()) {
         for (T elem: coll) {
            if (comp.compare(elem, flr) < 0) {
               flr = elem;
            }
         }
      }
      int chng = 0;
      //finds the lowest value equal to or above the key
      for (T elem: coll) {
         if ((comp.compare(elem, key) <= 0)
            && (comp.compare(elem, flr) >= 0)) {
            flr = elem;
            chng++;
         }
      }
      //if no qualifying value for ceiling
      if (chng == 0) {
         throw new NoSuchElementException("there is"
            + " no qualifying value");
      }
      return flr;
   }

}   

