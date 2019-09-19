import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Chris Flodstrom (czf0038@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2-21-2019
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) {
      try {
         Scanner sc = new Scanner(new File(filename));
         int sze = sc.nextInt();
         points = new Point[sze];
         int x;
         int y;
         for (int i = 0; i < sze; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            points[i] = new Point(x, y);
         }
      }
      catch (java.io.IOException e) {
         System.err.println("File cannot be found");
      }
      catch (Exception e) {
         System.err.println("err");
      }
         
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      Line temp = new Line();
      lines = new TreeSet<Line>();
      for (int i = 0; i < points.length; i++) { // point 1
         for (int j = i + 1; j < points.length; j++) { //point 2
            for (int k = j + 1; k < points.length; k++) { //point 3
               for (int l = k + 1; l < points.length; l++) { //point 4
                  temp.add(points[i]);
                  temp.add(points[j]);
                  if (temp.add(points[k]) && temp.add(points[l]) && temp.length() == 4) {
                     lines.add(temp);
                  }
                  temp = new Line();
               }
            }
         }
      }
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      Point[] srtd = Arrays.copyOf(points, points.length);
      lines = new TreeSet<Line>();
      Line tmp = new Line();
      boolean canAdd = true;
      
      
      for (int i = 0; i < points.length; i++) {
         Arrays.sort(srtd, points[i].slopeOrder);
         for (int j = 0; j < points.length; j++) {
            tmp.add(srtd[0]);
            canAdd = tmp.add(srtd[j]);
            if (!canAdd) {
               if (tmp.length() >= 4) {
                  lines.add(tmp);
               }
               tmp = new Line();
               tmp.add(srtd[j]);
            }
         }
      }
      return lines;
   }
   
}
