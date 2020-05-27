import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Tank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TankBody extends Mover
{
    public TankTurret m_turret;
    private GreenfootImage MyBodyImage = new GreenfootImage("M_Tank.png");
    private GreenfootImage EnemyBodyImage = new GreenfootImage("E_Tank.png"); 
    public boolean me = false;
    private Point destination;
    private ArrayList<Path> m_path;
    private static Point player_pos;
    
    /**
     * Act - do whatever the Tank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public TankBody(boolean me){
        super(3);
        
        m_turret = new TankTurret(this, me);
        if(me) {
            setImage(MyBodyImage);
        }
        else {
            setImage(EnemyBodyImage);
            setMaxSpeed(4);
        }
        
        this.me = me;
    }
    public void act() 
    {
        // Add your action code here.
        
        if(me){
            playerControl();
            player_pos = new Point(getX(), getY());
        }
        else{
            enemyControl();
        }
        
    } 
    
    public void playerControl(){
        keyBoardControl();
        if(Greenfoot.isKeyDown("x")){
            destination = new Point(500, 700);
            List<Path> rp = getWorld().getObjects(Path.class);
            for(Path p : rp){
                getWorld().removeObject(p);
            }
            m_path = Path.findPath(new Point(getX(), getY()), destination);
            for(Path p : m_path){
                getWorld().addObject(p,(int)p.to_world().x, (int)p.to_world().y); 
                p.changeImage();
                //System.out.println(p.getPosition().toString());
            }
            Path dest = new Path(Path.transform_coordinate(destination), 0, 0, 0, destination); 
            getWorld().addObject(dest,(int)dest.to_world().x, (int)dest.to_world().y); 
            dest.changeImage();
            //System.out.println(m_path.size());
        }
        
    }
    
    public void enemyControl(){
        double to_destination = 0;
        double refresh_rate = 10;
        if(to_destination > 15) refresh_rate = (int)to_destination * 3;
        //find path to player
        if(TankWorld.timer % refresh_rate == 1){
            m_path = Path.findPath(new Point(getX(), getY()), player_pos);
                // if(m_path != null){
                    // List<Path> rp = getWorld().getObjects(Path.class); 
                    // for(Path p : rp){
                        // getWorld().removeObject(p);
                    // }
                                
                    // for(Path p : m_path){
                        // getWorld().addObject(p,(int)p.to_world().x, (int)p.to_world().y); 
                        // p.changeImage();
                        // //System.out.println(p.getPosition().toString());
                    // }
                // }
        }
        if(m_path != null){
           
            for(int i = 1; i < m_path.size(); i++){
                Point p = m_path.get(i).getPosition();
                
                if(Path.transform_coordinate(new Point(getX(), getY())).equals(p)){
                    
                    double ratio = 0.7;
                    destination = MyMath.add(m_path.get(i-1).to_world().scale(ratio), m_path.get(i).to_world().scale(1-ratio));
                    to_destination = i;
                }   
            }
        }
        // System.out.println(destination.toString());
        // moveToPoint(player_pos);
        if(destination != null){
            if(to_destination > 2 || true)
                moveToPoint(destination, to_destination / 5);
            // System.out.println(getSpeed().getLength());
        }
    }  
    

    
    public void keyBoardControl(){
        Point m_speed = new Point(0, 0);
        double basic_speed = 10;

        
        if(Greenfoot.isKeyDown("w")) {
            m_speed.y += basic_speed;
        }
        if(Greenfoot.isKeyDown("a")) {
            m_speed.x -= basic_speed;
        }
        if(Greenfoot.isKeyDown("s")) {
            m_speed.y -= basic_speed;
        }   
        if(Greenfoot.isKeyDown("d")) {
            m_speed.x += basic_speed;
        }
        setSpeed(m_speed);
        tankMove();
    }
    
    public void tankMove(){
        Point m_speed = getSpeed();
        if(!testMove()){
            setSpeed(new Point(m_speed.x, 0));
            if(!testMove()){
                setSpeed(new Point(0, m_speed.y));
                testMove();
            }
        }
        // //check collide
        // if(isTouching(Barrier.class) || isTouching(TankBody.class)) {
            // setLocation(getLastPos());
            // setSpeed(new Point(getSpeed().x, 0));
            // move();
        // }
        // if(isTouching(Barrier.class) || isTouching(TankBody.class)) {
            // setLocation(getLastPos());
            // setSpeed(new Point(0, getSpeed().y));
            // move();
        // }
        // if(isTouching(Barrier.class) || isTouching(TankBody.class)) {
            // setLocation(getLastPos());
        // }
    }
    
    public boolean testMove(){
        if(getSpeed().getLength() != 0){
            double r = MyMath.angleInRange(Math.toDegrees(Math.atan2(getSpeed().y, getSpeed().x)));
            setRotation(360 - (int)r);
        }
        //if(getSpeed().getLength() < 2) setSpeed(getSpeed().setLength(2));
        if(getOneObjectAtOffset((int)getSpeed().x * 15, -(int)getSpeed().y * 15 , Barrier.class) == null && 
        getOneObjectAtOffset((int)getSpeed().x * 15, -(int)getSpeed().y * 15 , TankBody.class) == null){
            move();
            return true;
        }
        
        // if(isTouching(Barrier.class) || isTouching(TankBody.class)) {
            // setLocation(getLastPos());
            // return false;
        // }
        return false;
    }
    
    public void moveToPoint(Point p){
        Point relative_pos = new Point(p.x - getX(), -p.y + getY());
        relative_pos.setLength(getMaxSpeed());
        setSpeed(relative_pos);
        tankMove();
    }
    public void moveToPoint(Point p, double speed){
        Point relative_pos = new Point(p.x - getX(), -p.y + getY());
        relative_pos.setLength(speed);
        setSpeed(relative_pos);
        tankMove();
    }
    
}
