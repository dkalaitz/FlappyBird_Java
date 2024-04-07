import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Pipe extends Actor
{

    private GreenfootImage pipeImage;
    
    public Pipe() {
        // Set the initial image for the pipe
        pipeImage = new GreenfootImage("pipe.png");
        setImage(pipeImage);
    }
    
    public void act()
    {
        removeIfReachesLeftBorder();
    }
    
    public void removeIfReachesLeftBorder(){
        if (getX() == MyWorld.LEFT_BORDER){
            getWorld().removeObject(this);
        }
    }
}
