/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlesolver;

/**
 *
 * @author Grant
 */
public class Utils {

   public static double distance(Point a, Point b)
   {
      return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
   }
   
   public static double manhattanDistance(Point a, Point b)
   {
      return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
   }

   
   
}
