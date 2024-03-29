import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Deque;
import java.util.Queue;
/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Chris Flodstrom (czf0038@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 10 April 2019
 */
public class Doublets implements WordLadderGame {

   List<String> EMPTY_LADDER = new ArrayList<>();
   TreeSet<String> lexicon;
   int wrdCnt;
   private HashSet<String> vist;
   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
            
               
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toLowerCase());
            wrdCnt++;
            s.nextLine();
            
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   
    /**
    * Returns the Hamming distance between 2 strings. If the two strings
    * have a different length it is undefined and returns a -1.
    * @param  str1 - used
    * @param  str2 - used
    * @return - used
    */  
   public int getHammingDistance(String str1, String str2) {
   
      int outcome = 0;
      if (str1.length() != str2.length()) {
         return -1;
      }   
      char[] string1 = str1.toCharArray();
      char[] string2 = str2.toCharArray();
      for (int i = 0; i < str1.length(); i++) {
         if (string1[i] != string2[i]) {
            outcome++;
         }
      }
      return outcome;
   }

   /**
   * Returns a minimum-length word from start to finish. 
   * Returns empty if no word exists. Breadth-first search is used.
   * @param  start - used
   * @param  end - used
   * @return - used
   */
   public List<String> getMinLadder(String start, String end) {
      vist = new HashSet<String>();
      return bfs(start, end);
   }

   public List<String> bfs(String start, String end) {
      Deque<Node> deq = new ArrayDeque<>();
      List<String> nebor = new ArrayList<String>();
      deq.addLast(new Node(start, null));
      while (!deq.isEmpty()) {
         Node tmp = deq.removeFirst();
         if (tmp.word.equals(end)) {
            Node tmp2 = tmp;
            while (tmp2.n != null) {
               nebor.add(tmp2.word);
               tmp2 = tmp2.n;
            }
            nebor.add(start);
            Collections.reverse(nebor);
            return nebor;
         }
         for (String i : getNeighbors(tmp.word)) {
            if (!vist.contains(i)) {
               vist.add(i);
               deq.addLast(new Node(i, tmp));
            }
         }
      }
      return nebor;
   }   
   
    /**
    * Returns all numbers with a hamming distance of one
    * @param  - used
    * @return - used
    */
   public List<String> getNeighbors(String word) {
      List<String> neigh = new ArrayList<String>();
      Iterator<String> itr = lexicon.iterator();
      while (itr.hasNext()) {
         String wrd2 = itr.next();
         if (getHammingDistance(word, wrd2) == 1) {
            neigh.add(wrd2);
         }
      }
      return neigh;
   }
   
    /**
    * Returns total number of words.
    * @return - used
    */
   public int getWordCount() {
      return lexicon.size();
   }
   
    /**
    * Checks to see there is a word in the string.
    * @param  str - used
    * @return - used
    */
   public boolean isWord(String str) {
      if (lexicon.contains(str)) {
         return true;
      }
      return false;
   }
   
    /**
    * Checks to see if there is a valid word ladder.
    * @param  sequence - used
    * @return - used
    */
   public boolean isWordLadder(List<String> sequence) {
      if (sequence.isEmpty()) {
         return false;
      }
      for (int i = 0; i < sequence.size() - 1; i++) {
         if (getHammingDistance(sequence.get(i), sequence.get(i + 1)) != 1) {
            return false;
         }
      }
      for (int i = 0; i < sequence.size(); i++) {
         if (!lexicon.contains(sequence.get(i))) {
            return false;
         }
      }
      return true;  
   }
   
   private class Node {
      String word;
      int prev;
      Node n;
   
      public Node(String word, Node node){
         this.word = word;
         n = node;
      }
   }

}