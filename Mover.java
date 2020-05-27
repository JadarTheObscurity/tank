import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mover here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mover extends Actor
{
    private Point speed = new Point(0, 0);
    private double max_speed = 5;
    private  Point last_pos = new Point(0,0);
    private Point pos;
    /**
     * Act - do whatever the Mover wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Mover(){
        
    }
    
    public Mover(double max_speed){
        this.max_speed = max_speed;
    }
    public void act() 
    {
        move();
        // Add your action code here.
    }   
    
    public void setSpeed(Point speed){
        this.speed = speed;
        if(speed.getLength() > max_speed)
            this.speed.setLength(max_speed);
    }
    
    public Point getSpeed(){
        return speed;
    }
    
    public void move(){
        last_pos.x = getX();
        last_pos.y = getY();
        if(pos == null){
            pos = new Point(getX(), getY());
        }
        else{
            pos.x += speed.x;
            pos.y -= speed.y;
        }
        setLocation((int)pos.x, (int)pos.y);
    }
    
    public void setLocation(Point pos){
        setLocation((int)pos.x, (int)pos.y);
    }
 
    public Point getLocation(){
        return new Point(getX(), getY());
    }
    
    public Point getLastPos(){
        return last_pos;
    }
    
    public void setMaxSpeed(double speed){
        max_speed = speed;
    }
    
    public double getMaxSpeed(){
        return max_speed;
    }
    
}
