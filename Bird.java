import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.function.Consumer;
import java.util.List;

public class Bird extends Actor
{
    public static boolean canFlap;
    public static int LIVES;
    private static final int GRAVITY = 1;
    private static final int FLAP_STRENGTH = -12;
    private int velocity;
    private Wing wing;
    private int wingX, wingY;
    private boolean isDeadSoundFlag = false;
    private boolean isFlapping = false;
    private boolean isDead = false;
    private GreenfootImage birdImage;

    public Bird() {
        // Load the bird image
        birdImage = new GreenfootImage("bird.png");
        
        // Scale down the image to make it smaller
        birdImage.scale(birdImage.getWidth()/2, birdImage.getHeight()/2); 
        
        // Set the image of the bird
        setImage(birdImage);
        velocity = 0;
        wing = new Wing();
        LIVES = 2;
        canFlap = true;
    }
    
    public void act() {
        checkIfBirdIsDead();
        applyGravity();
        if (canFlap)
            checkFlap();
        move();
        checkCollisionWithPipe();
        checkBirdPosition();
    }
    
    private void move() {
        // Update bird's position
        setLocation(getX(), getY() + velocity);

        // Update wing's position relative to bird's new position
        calculateWingRelativePositionToBird();
        if (wing != null) {
            wing.setLocation(wingX, wingY);
        }
    }

    public void addedToWorld(World world) {
        addWingToWorld();
    }
    
    // Add wing to World
    private void addWingToWorld() {
        if (wing != null && getWorld() != null) {
            getWorld().addObject(wing, getX() - wing.getImage().getWidth() / 2, getY() + wing.getImage().getHeight());
        }
    }
    
    private void calculateWingRelativePositionToBird(){
        if (isFlapping){
            this.wingX = getX() - wing.getImage().getWidth() / 2;
            this.wingY = getY() + wing.getImage().getHeight();
        } else {
            this.wingX = getX() - wing.getImage().getWidth();
            this.wingY = getY() + wing.getImage().getHeight() / 2;            
        }
    }
    
    private void applyGravity() {
        velocity += GRAVITY;
    }
    
    private void checkFlap() {
        if (Greenfoot.isKeyDown("space")) {
            velocity = FLAP_STRENGTH;
            //setRotation(-45);
            //wing.setRotation(-45);
            isFlapping = true;
        } else {
            //setRotation(45);
            //wing.setRotation(45);
            isFlapping = false;
        }
    }
    
    public void checkCollisionWithPipe() {
        Pipe pipe = (Pipe) getOneIntersectingObject(Pipe.class);

        if (pipe != null && LIVES != 0 ) {
            LIVES--;
            resumeState();
            Greenfoot.playSound("flappy-bird-hit-sound-101soundboards.wav");
        }
    }
    
    private void resumeState(){
        List<Pipe> pipes = getWorld().getObjects(Pipe.class);
        
        // If bird is alive and it does not intersect bottom border,
        // remove the first pair of pipes
        if (LIVES != 0 && !intersectsBottomBorder()){
            getWorld().removeObject(pipes.get(0));
            getWorld().removeObject(pipes.get(1));
        } else if ( LIVES != 0 && intersectsBottomBorder()){
            this.setLocation(200, MyWorld.WORLD_HEIGHT/2); // Set bird to the initial location
            calculateWingRelativePositionToBird();
            wing.setLocation(wingX, wingY);
            velocity = 0;
            if (!pipes.isEmpty()){ // Remove first pair of pipes
                getWorld().removeObject(pipes.get(0)); 
                getWorld().removeObject(pipes.get(1));
            }
        }
    }

    private boolean intersectsBottomBorder() {
        // Get the bird's position and dimensions
        int birdY = getY();
        int birdHeight = getImage().getHeight();
        
        // Get the height of the game window or playing area
        int gameHeight = MyWorld.WORLD_HEIGHT;
        // Check if the bird's bottom edge is at or below the bottom border
        return (birdY + (birdHeight / 4) >= gameHeight);
    }
    
    private void checkBirdPosition(){
        if (intersectsBottomBorder()){
            if (LIVES != 0) {
                LIVES--;
                resumeState();
                Greenfoot.playSound("flappy-bird-hit-sound-101soundboards.wav");
            }
        }
    }
    
    private void checkIfBirdIsDead(){
        Pipe pipe = null;
        if (LIVES == 0){
            velocity = 10;
            canFlap = false;
            setRotation(45);
            wing.setRotation(45);
            pipe = (Pipe) getOneIntersectingObject(Pipe.class);
            if (!isDeadSoundFlag){
                Greenfoot.playSound("die-101soundboards.wav");
                isDeadSoundFlag = true;
            }
            if (intersectsBottomBorder() && !isDead){
                GameOver gameOver = new GameOver();
                getWorld().addObject(gameOver, getWorld().getWidth()/2, getWorld().getHeight()/2);
                gameOver.setLocation(getWorld().getWidth()/2, getWorld().getHeight()/2 - 100);
                isDead = true;
                resetText();
            } else if (intersectsBottomBorder() && isDead){
                if (Greenfoot.isKeyDown("space")){
                    getWorld().removeObject(pipe);
                    Greenfoot.setWorld(new MyWorld());
                } 
            }
        }
    }
    
    private void resetText(){
        GreenfootImage resetTextImage = new GreenfootImage("Press \"Space\" to start again!", 30, Color.WHITE, new Color(0, 0, 0, 0));
        getWorld().addObject(new Actor() {
            {
                setImage(resetTextImage);
            }
        }, getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 300);
    }
    
    
    
    

    

}
