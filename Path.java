import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Path here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Path extends Actor
{
    public static GreenfootImage transparentImage = new GreenfootImage("Path_0.png");
    private static GreenfootImage redImage = new GreenfootImage("Path_1.png"); 
    
    
    /**
     * Act - do whatever the Path wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //if(isTouching(TankBody.class) || isTouching(Barrier.class)) setImage(redImage);
        //else setImage(transparentImage);
    }
    
    public void changeImage(){
        GreenfootImage f = new GreenfootImage(g + "  " + getF(), 15, Color.WHITE, Color.BLACK);
        setImage(f);
    }
    
    private Point position; // Position of square
    private int parent; // Index of square that is before this one in the route
    private boolean closed = false; // Has not been looked at
    public int g; // Score to get to this position. 10s added for every vertical and horizontal move and 14s for every diagonal move since a diagonal move is a farther move
    private int h; // Score estimate for how many vertical and horizontal moves, regardless of obstacles
    
    public Path(Point position, int parent, int parentG, int thisG, Point worldDestination)
    {
        this.position = position; // Position
        this.parent = parent; // Parent
        changeG(parentG + thisG); // Parent's score plus score took to get from there to here
        Point destination = transform_coordinate(worldDestination);
        h = (int)(Math.abs(position.x - destination.x) + Math.abs(position.y - destination.y)) * 10; // Vertical and horizontal spaces between here and destination
        
    }
    
    public static ArrayList<Path> findPath(Point start_point, Point destination){
        ArrayList<Path> all_path = new ArrayList<Path>();
        int parent = 0;
        Path start_path = new Path(transform_coordinate(start_point), -1, 0, 0, destination);
        start_path.g = 0;
        // System.out.println(start_path.getF());
        all_path.add(start_path);
        boolean target = false;
        int steps = 200;
        for(int c = 0; c < steps && !target; c++){
            Path curr_path = all_path.get(parent);
            Point check_spot = new Point(0, -1);
            //up
            check_spot = new Point(0, -1);            
            if(!check_obstacle(curr_path.position, check_spot)){
                 if (!check(all_path, parent, 10, check_spot)){ // Check if already found this square and see if this route is better than one that the square is a part of
                     all_path.add(new Path(MyMath.add(curr_path.position, check_spot), parent, curr_path.getG(), 10, destination)); // Add the square, takes 10 points to move horizontally or vertically, and know how many points it has taken to get here
                     
                  }
            }
            //down
            check_spot = new Point(0, 1);
            if(!check_obstacle(curr_path.position, check_spot)){
                  if (!check(all_path, parent, 10, check_spot)){ // Check if already found this square and see if this route is better than one that the square is a part of
                     all_path.add(new Path(MyMath.add(curr_path.position, check_spot), parent, curr_path.getG(), 10, destination)); // Add the square, takes 10 points to move horizontally or vertically, and know how many points it has taken to get here
                     
                  }
            }
            //left
            check_spot = new Point(-1, 0);
            if(!check_obstacle(curr_path.position, check_spot)){
                  if (!check(all_path, parent, 10, check_spot)){ // Check if already found this square and see if this route is better than one that the square is a part of
                     all_path.add(new Path(MyMath.add(curr_path.position, check_spot), parent, curr_path.getG(), 10, destination)); // Add the square, takes 10 points to move horizontally or vertically, and know how many points it has taken to get here
                     
                  }
            }
            //right
            check_spot = new Point(1, 0);
            if(!check_obstacle(curr_path.position, check_spot)){
                  if (!check(all_path, parent, 10, check_spot)){ // Check if already found this square and see if this route is better than one that the square is a part of
                    all_path.add(new Path(MyMath.add(curr_path.position, check_spot), parent, curr_path.getG(), 10, destination)); // Add the square, takes 10 points to move horizontally or vertically, and know how many points it has taken to get here
                    
                }
            }
            //up right
            check_spot = new Point(1, -1);            
            if(!check_obstacle(curr_path.position, check_spot)){
                 if (!check(all_path, parent, 14, check_spot)){ // Check if already found this square and see if this route is better than one that the square is a part of
                     all_path.add(new Path(MyMath.add(curr_path.position, check_spot), parent, curr_path.getG(), 14, destination)); // Add the square, takes 10 points to move horizontally or vertically, and know how many points it has taken to get here
                     
                  }
            }
            //up left
            check_spot = new Point(-1, -1);
            if(!check_obstacle(curr_path.position, check_spot)){
                  if (!check(all_path, parent, 14, check_spot)){ // Check if already found this square and see if this route is better than one that the square is a part of
                     all_path.add(new Path(MyMath.add(curr_path.position, check_spot), parent, curr_path.getG(), 14, destination)); // Add the square, takes 10 points to move horizontally or vertically, and know how many points it has taken to get here
                     
                  }
            }
            //down right
            check_spot = new Point(1, 1);
            if(!check_obstacle(curr_path.position, check_spot)){
                  if (!check(all_path, parent, 14, check_spot)){ // Check if already found this square and see if this route is better than one that the square is a part of
                     all_path.add(new Path(MyMath.add(curr_path.position, check_spot), parent, curr_path.getG(), 14, destination)); // Add the square, takes 10 points to move horizontally or vertically, and know how many points it has taken to get here
                     
                  }
            }
            //down left
            check_spot = new Point(-1, 1);
            if(!check_obstacle(curr_path.position, check_spot)){
                  if (!check(all_path, parent, 14, check_spot)){ // Check if already found this square and see if this route is better than one that the square is a part of
                    all_path.add(new Path(MyMath.add(curr_path.position, check_spot), parent, curr_path.getG(), 14, destination)); // Add the square, takes 10 points to move horizontally or vertically, and know how many points it has taken to get here
                    
                }
            }
            for(Path p : all_path){
                // System.out.println(p.getPosition().toString() + " F: " + p.getF());
            }
            //close current path
            all_path.get(parent).close();
            //Choose the path in the open list that have the lowest f
            int lowest = -1; // Index for square that has lowest score
            for (int i = 0; i < all_path.size(); i++) // Get square that has lowest score
            {
                if (!all_path.get(i).isClosed()) // Make sure we have not already looked at
                {
                    if (lowest == -1) // If havn't found one yet
                        lowest = i; // This lowest one found so far
                    else if (all_path.get(i).getF() < all_path.get(lowest).getF()) // Check to see if score of this square is the new lowest
                        lowest = i; // This lowest one found so far
                }
            }
            // System.out.println("lowest: " + lowest + " pos: " + all_path.get(lowest).getPosition().toString());
            if (lowest == -1) // No possible path
            {                
                return null;
            }
            parent = lowest; // Now looking at new lowest
            for (int i = 0; i < all_path.size(); i++) // Loop all squares that have found
            {
                if (all_path.get(i).getPosition().equals(transform_coordinate(destination)) && all_path.get(i).isClosed()) // Check to see if found destination
                {
                    parent = i; // Start at this square to chart back to beginning
                    target = true; // We have found the destination
                }
            }
        
        }
        ArrayList<Path> final_path = new ArrayList<Path>();
        if(target){
            while(parent != -1){
                final_path.add(all_path.get(parent));
                parent = all_path.get(parent).getParent();
            }
        }
        
        return final_path;
        // return all_path;
    }
    
    public static Point transform_coordinate(Point world_coordinate){
        Point new_point = new Point(world_coordinate);
        new_point.x = (int)world_coordinate.x / transparentImage.getWidth();
        new_point.y = (int)world_coordinate.y / transparentImage.getHeight();
        return new_point;
    }
    
    public static Point transform_coordinate_world(Point path_coordinate){
        Point new_point = new Point(path_coordinate);
        new_point.x = (int)path_coordinate.x * transparentImage.getWidth() + transparentImage.getWidth()/2;
        new_point.y = (int)path_coordinate.y * transparentImage.getHeight() + transparentImage.getHeight()/2;
        return new_point;
    }
    
    public Point to_world(){
        return transform_coordinate_world(this.position);
    }
    
    //Check for obstacle and boundary
    public static boolean check_obstacle(Point parent, Point spot){
        
        try{
            if(TankWorld.Map[(int) (parent.y + spot.y)][(int)(parent.x)] != 1 &&
            TankWorld.Map[(int) (parent.y)][(int)(parent.x + spot.x)] != 1 &&
            TankWorld.Map[(int) (parent.y + spot.y)][(int)(parent.x + spot.x)] != 1) return false;
        }
        catch(Exception e){
            return true;
        }
        
        return true;
    }
    private static boolean check(ArrayList<Path> all_path,int parent, int cost, Point spot)
    {
        boolean flag = false;
        
        
        for (int i = 0; i < all_path.size(); i++) // Loop squares found
        {
            if (all_path.get(i).getPosition().equals(MyMath.add(all_path.get(parent).getPosition(), spot))) // Check if already have the square
            {
                flag = true; // Do have this square
                if (!all_path.get(i).isClosed() && all_path.get(parent).getG() + cost < all_path.get(i).getG()) // Check if new route to square is better
                {
                    all_path.get(i).adopt(parent); // Change square's parent
                    all_path.get(i).changeG(all_path.get(parent).getG() + cost); // Change square's score
                }
            }
        }
        return flag; // return if found that already have square
    }
    public Point getPosition()
    {
        return position;
    }
    public int getX()
    {
        return (int)position.x;
    }
    public int getY()
    {
        return (int)position.y;
    }
    public int getParent()
    {
        return parent;
    }
    public void adopt(int parent)
    {
        this.parent = parent;
    }
    public boolean isClosed()
    {
        return closed;
    }
    public void close()
    {
        closed = true;
    }
    public int getG()
    {
        return g;
    }
    public void changeG(int newG)
    {
        g = newG;
        if(TankWorld.Map[(int)position.y][(int)position.x] != 0) g += 500;
    }
    public int getH()
    {
        return h;
    }
    
    public int getF(){
        return g+h;
    }
}