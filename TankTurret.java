import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class TankTurret here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TankTurret extends Mover
{
    TankBody m_tankBody;
    boolean me;
    int reload_complete = 100;
    int reload = 0;
    private GreenfootImage MyTurretImage = new GreenfootImage("M_Turret.png");
    private GreenfootImage EnemyTurretImage = new GreenfootImage("E_Turret.png"); 
    public TankTurret(TankBody tankBody, boolean me){
        m_tankBody = tankBody;
        this.me = me;
        if(me){
            setImage(MyTurretImage);
            reload_complete = 50;
        }
        else {
            setImage(EnemyTurretImage);
            reload_complete = 70;
            reload = Greenfoot.getRandomNumber(15);
        }
    }
    /**
     * Act - do whatever the TankTurret wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        followBody();
        reload++;
        if(me) playerControl();
        else enemyControl();
        // Add your action code here.
    }    
    
    void playerControl(){
        followMouse();
        if(Greenfoot.mouseClicked(null))shootBullet();
    }
    void enemyControl(){
        if(aimMe()){
            shootBullet();
        }
    }
    
    void followBody(){
        setSpeed(m_tankBody.getSpeed());
        setLocation(m_tankBody.getX(), m_tankBody.getY());
    }
    
    public void followMouse(){
        try{
            MouseInfo mouse = Greenfoot.getMouseInfo();
            double angle = Math.atan2(mouse.getY() - getY(), mouse.getX() - getX());
            angle = Math.toDegrees(angle);
            setRotation((int)MyMath.angleInRange(angle));
        }
        catch(Exception e){}
    }
    
    public boolean aimMe(){
        List<TankBody> movers = getObjectsInRange(10000, TankBody.class);
        for (TankBody m : movers){
            if(m != null){
                if(m.me == true){
                    double angle = new Point(m.getX() - getX(), m.getY() - getY()).getTheta();
                    setRotation((int)MyMath.angleInRange(angle));
                    if(willShootAlly(new Point(m.getX(), m.getY()))) return false;
                    return true;
                }
            }
        }
        return false;
    }
    
     public void shootBullet(){
        if(reload > reload_complete){
            reload = 0;
            int angle_bias = 5 - Greenfoot.getRandomNumber(10);
            int shoot_angle = getRotation() + angle_bias;
            double degree = Math.toRadians(shoot_angle);
            Point offset = new Point(Math.cos(degree), Math.sin(degree));
            offset.scale(41);               
            ((TankWorld)getWorld()).spawnBullet(MyMath.add(getLocation(), offset), getSpeed(), MyMath.angleInRange(-(double)shoot_angle));
        }
    }
    
    boolean willShootAlly(Point enemyPos){
        //compute the trajectory equation
        double a = enemyPos.y - getY();
        double b = enemyPos.x - getX();
        double c = -a * getX() - b * getY();
        //System.out.println(a + " " + b + " " + c);
        List<TankBody> allys = getObjectsInRange(10000, TankBody.class);
        for (TankBody ally : allys){
            if(ally != null){
                if(ally.me == false && ally != m_tankBody){
                    double dis_to_trajectory = Math.abs(a * ally.getX() + b * ally.getY() + c) / Math.hypot(a, b);
                    //System.out.println(dis_to_trajectory);
                    if(dis_to_trajectory < 150)
                        if(MyMath.dot(new Point(ally.getX() - getX(), ally.getY() - getY()),
                        new Point(ally.getX() - enemyPos.x, ally.getY() - enemyPos.y)) < 0)
                            return true;
                        
                }
            }
        }
        return false;
    }
}
