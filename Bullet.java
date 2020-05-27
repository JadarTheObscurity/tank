import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Mover
{
    Point m_speed;
    Point init_pos;
    int act_count = 1;
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Bullet(Point init_pos, Point init_speed, double direction){
        super(6);
        setLocation(init_pos);
        setSpeed(init_speed, direction);
        this.init_pos = init_pos;
        act_count = 1;
    }
    

    
    public void act() 
    {
        move();
        act_count++;
        // Add your action code here.
    }
    public void move(){
        Point displacement = new Point(m_speed);
        displacement.scale(act_count);
        displacement.y *= -1;//reverse y axis to match the stupid coordinate
        setLocation(MyMath.add(init_pos, displacement));
    }
    public void setSpeed(Point init_speed, double direction){
        
        setRotation((int)MyMath.angleInRange(-direction));
        //direction = Math.toRadians(direction);
        direction *= 0.0175;
        m_speed = new Point(Math.cos(direction), Math.sin(direction));
        
        m_speed.setLength(getMaxSpeed());
        m_speed = MyMath.add(init_speed, m_speed);
        //setRotation((int)MyMath.angleInRange(-m_speed.getTheta()));       
    }
    
    
    
    public boolean hitMover(){
        return isTouching(Mover.class);
    }
    
    public boolean hitBarrier(){
        return isTouching(Barrier.class);
    }
    
    public void removeTouching(){
        List<TankBody> allTouched = getIntersectingObjects(TankBody.class);
        for(TankBody t : allTouched){
            ((TankWorld)getWorld()).removeObject(t.m_turret);
            ((TankWorld)getWorld()).removeObject(t);
        }
        removeTouching(Mover.class);
    }
    
   
    }
    
