import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Animation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animation extends Actor
{
    public int act_count = 0;
    public int image_num = 0;
    
    public Animation(int num){
        act_count = 0;
        image_num = num;
    }
    
    /**
     * Act - do whatever the Animation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }   

    public boolean end(){
        return act_count >= image_num;
    }
}
