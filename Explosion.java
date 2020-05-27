import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Animation
{
    String image_path = "Explode_";
    private GreenfootImage image = new GreenfootImage(image_path + 0 + ".png");
    
    public Explosion(){
        super(65);
    }
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        playAnimation();
        // Add your action code here.
    }
    
    public void playAnimation(){
        if(act_count < image_num){
            image = new GreenfootImage(image_path + act_count + ".png");
            setImage(image);
        }
        act_count++;
    }
    
    
}
