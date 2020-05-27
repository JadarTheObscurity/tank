import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TankWorld extends World
{
    // int enemyNum = 3;
    public static double timer = 0;
    public static int[][] Map;
    int[][] emptyMap = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public TankWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
        createTank(true, getWidth()/2, getHeight()/2);
   
        // for (int i = 0; i < enemyNum; i++){
            // int x = Greenfoot.getRandomNumber(getWidth());
            // int y = Greenfoot.getRandomNumber(getHeight());
            // while (Math.abs(x - getWidth()/2) < 200 && Math.abs(y - getHeight()/2) < 200) {
                // x = Greenfoot.getRandomNumber(getWidth());
                // y = Greenfoot.getRandomNumber(getHeight());
            // }
            // createTank(false, x, y);
        // }
        generateMap();
        //generatePath();
    }
    
    public void createTank(boolean me, int x, int y){
        TankBody m = new TankBody(me);
        addObject(m, x, y);
        addObject(m.m_turret, x, y); 
    }
    
    public void generateMap(){
        // 1 => barrier
        // 11 => enemy tank
        // int[][] map = {
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        // };
        int[][] map1 = {
            {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,11},
            { 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            { 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0},
            { 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            { 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            { 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            { 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0},
            { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
            { 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0},
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0},
            { 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0},
            { 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,11},
        };
        Map = map1;
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 20; j++){
                int n = Map[i][j];
                Point p = Path.transform_coordinate_world(new Point(j, i));
                if(n == 1){
                    
                    addObject(new Wall(), (int)p.x, (int)p.y);
                }
                if(n == 11){
                    createTank(false, (int)p.x, (int)p.y);
                    
                }
            }
        }
        // for(int i = 0; i < 10; i++){
            // int x = Greenfoot.getRandomNumber(getWidth());
            // int y = Greenfoot.getRandomNumber(getHeight());
            // addObject(new Wall(), x, y);
        // }
    }
    
    public void updateMap(){
        //reset map
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 20; j++){
               Map[i][j] = 0;
            }
        }
        List<TankBody> tanks = getObjects(TankBody.class);
        for (TankBody t : tanks){
            if(t.me == false){
                Point p = Path.transform_coordinate(new Point(t.getX(), t.getY()));
                Map[(int)p.y][(int)p.x] = 11;
                System.out.println(p.toString());
            }
        }
        List<Wall> walls = getObjects(Wall.class);
        for (Wall w : walls){
            Point p = Path.transform_coordinate(new Point(w.getX(), w.getY()));
            Map[(int)p.y][(int)p.x] = 1;
        }
        
        
        // List<Path> rp = getObjects(Path.class); 
        // for(Path p : rp){
            // removeObject(p);
        // }
        // for(int i = 0; i < 16; i++){
            // for(int j = 0; j < 20; j++){
                // Path p = new Path(new Point(j, i), 0, Map[i][j], 0, new Point(0, 0));
                // addObject(p,(int)p.to_world().x, (int)p.to_world().y); 
                // p.changeImage();
            // }
        // }
        
                                
       
    }
    
    public void act(){
        checkBulletCollision();
        removeAnimation();
        updateMap();
        timer++;
    }
    
    void checkBulletCollision(){
        List<Bullet> all_bullet = getObjects(Bullet.class);
        for (Bullet b : all_bullet){
            //at edge
            try{
                if(b.isAtEdge()) {
                    
                    removeBullet(b);
                }
                else if(b.hitMover()){
                    b.removeTouching();
                    removeBullet(b);
                }
                else if(b.hitBarrier()){
                    removeBullet(b);
                }
            }
            catch(Exception e){}
            
        }
    }
    
    public void spawnBullet(Point init_pos, Point init_speed, double direction){
        Bullet b = new Bullet(init_pos, init_speed, direction);
        addObject(b, (int)init_pos.x, (int)init_pos.y);
    }
    
    public void removeBullet(Bullet b){
        addObject(new Explosion(), b.getX(), b.getY());
        removeObject(b);
    }
    
    public void removeAnimation(){
        List<Animation> animations = getObjects(Animation.class);
        for(Animation a : animations){
            if(a.end()) removeObject(a);
        }
    }
    
    public static boolean hi(Path p){
        // getObjectsAt(0,0,Actor.class);
        return false;
    }
    
    
}
