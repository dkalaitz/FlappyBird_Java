import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Cloud extends Actor
{

    public Cloud() {
        // Load the cloud image
        GreenfootImage cloudImage = new GreenfootImage("cloud.png");

        // Scale down the image to make it smaller
        cloudImage.scale(cloudImage.getWidth() / 2, cloudImage.getHeight() / 2); 

        // Adjust the transparency of the image to make it more transparent
        cloudImage.setTransparency(130); 

        // Set the image of the actor
        setImage(cloudImage);
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
