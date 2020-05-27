/**
 * Write a description of class MyMath here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyMath  
{
   public static Point add(Point a, Point b){
       return new Point(a.x + b.x, a.y + b.y);
   }
   
   public static double dot(Point a, Point b){
       return a.x * b.x + a.y * b.y;
    }
   
   public static double angleInRange(double angle){
       while(angle > 360) angle-=360;
       while(angle < 0) angle += 360;
       return angle;
   }

}
