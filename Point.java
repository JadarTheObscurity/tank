/**
 * Write a description of class Point here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Point  
{
    // instance variables - replace the example below with your own
    public double x;
    public double y;

    /**
     * Constructor for objects of class Point
     */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Point(Point p){
        this.x = p.x;
        this.y = p.y;
    }
    
    public Point scale(double c){
        x *= c;
        y *= c;
        return new Point(x, y);
    }
    
    public Point setLength(double length){
        if(length == 0 || getLength() == 0){
            x = 0;
            y = 0;
            
        }
        else{
           scale(length / getLength());
        }
        return new Point(x, y);
    }
    
    public double getLength(){
        return Math.hypot(x, y);
    }
    
    public double getTheta(){
        return MyMath.angleInRange(Math.toDegrees(Math.atan2(y, x)));
    }
    
    public String toString(){
        return "x: " + x + " y:" + y;
    }
    
    public boolean equals(Point p){
        return this.x == p.x && this.y == p.y;
    }
    
   
}
