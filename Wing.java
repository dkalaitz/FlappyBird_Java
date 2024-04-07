import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Wing extends Actor {
    
    private GreenfootImage wingUpImage;
    private GreenfootImage wingDownImage;
    private boolean isWingsUp;

    public Wing() {
        wingUpImage = new GreenfootImage("wing_up.png");
        wingDownImage = new GreenfootImage("wing_down.png");
        
        wingUpImage.scale(wingUpImage.getWidth() / 2, wingUpImage.getHeight() / 2); 
        wingDownImage.scale(wingDownImage.getWidth() / 2, wingDownImage.getHeight() / 2);

        setImage(wingUpImage);
        isWingsUp = true;
    }
    
    public void act() { 
        setImage(wingUpImage);
        if (Greenfoot.isKeyDown("Space") && Bird.canFlap){
            flap();
        }
    }
    
    public void flap() {
        if (isWingsUp) {
            setImage(wingDownImage);
        } else {
            setImage(wingUpImage);
        }
        Greenfoot.playSound("swoosh-101soundboards.wav");
        isWingsUp = !isWingsUp;
    }
    
}
