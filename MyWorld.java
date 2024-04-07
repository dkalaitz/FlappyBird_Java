import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

public class MyWorld extends World
{
    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 800;
    public static final int LEFT_BORDER = 0;
    private static final int NUM_CLOUDS = 5;
    public static final Color background_color = new Color(97, 163, 255);
    private Score score = new Score();
    private Bird bird = new Bird();
    private boolean lastPipePassed = false;
    private boolean isBetweenLastFrame = false;
    public static Heart FIRST_HEART, SECOND_HEART;
    private static final int FIRST_HEART_STARTING_WIDTH = 850;
    private static final int SECOND_HEART_STARTING_WIDTH = 910;
    private static final int HEARTS_STARTING_HEIGHT = 50;
    private SimpleTimer soundTimer = new SimpleTimer();
    private boolean soundMapFlag = false;
    
    public MyWorld()
    {    
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        setPaintOrder(Actor.class, Score.class, Heart.class, GameOver.class,  Wing.class, Bird.class, Pipe.class, Cloud.class);
        prepareInitialMap();
        prepareGenerators();
    }

    public void act(){
        updateScore();
        if (soundTimer.millisElapsed() >= 100000){
            soundTimer.mark(); //Restart the timer
            Greenfoot.playSound("strong_wind.wav");
        } else if (!soundMapFlag){
            Greenfoot.playSound("strong_wind.wav");
            soundMapFlag = true;
            soundTimer.mark(); // Start the timer
        }
    }

    private void prepareInitialMap(){
        getBackground().setColor(background_color);
        getBackground().fill();
        
        addObject(bird, 200, getHeight()/2);
        addObject(score, 100, 50);
        prepareHealth();
        generateInitialClouds();
    }
    
    private void generateInitialClouds(){
        for (int i = 0; i < NUM_CLOUDS; i++) {
            Cloud cloud = new Cloud();
            int randomX = Greenfoot.getRandomNumber(WORLD_WIDTH); // Generate random X coordinate
            int randomY = Greenfoot.getRandomNumber(WORLD_HEIGHT); // Generate random Y coordinate
            addObject(cloud, randomX, randomY);
        }
    }
    
    private void prepareHealth(){
        FIRST_HEART = new Heart();
        SECOND_HEART = new Heart();
        addObject(FIRST_HEART, FIRST_HEART_STARTING_WIDTH, HEARTS_STARTING_HEIGHT);
        addObject(SECOND_HEART, SECOND_HEART_STARTING_WIDTH, HEARTS_STARTING_HEIGHT);
    }
    
    private void prepareGenerators() {
        
        CloudGenerator cloudGenerator = new CloudGenerator();
        PipeGenerator pipeGenerator = new PipeGenerator(score);
        
        List<Generator> generators = new ArrayList();
        generators.add(cloudGenerator);
        generators.add(pipeGenerator);
        
        for (Generator generator : generators){
            addObject(cloudGenerator, getWidth(), getHeight());
            addObject(pipeGenerator, getWidth(), getHeight());           
        }

    }
    
    
        /**
     * This method updates the score based on the position of the bird relative to the pipes.
     * It retrieves the horizontal position of the bird and iterates through each pipe to check if the bird's position
     * is beyond the center of the pipe. If the bird has moved past a pipe center since the last frame,
     * the score is incremented and a point sound is played. The method also keeps track of whether the bird
     * was between pipes in the last frame to avoid counting the same pipe multiple times.
     */
    private void updateScore(){
        int birdX = bird.getX();
        boolean isBetweenCurrentFrame = false;  
        
        for (Pipe pipe: getObjects(Pipe.class)){
            
            int pipeCenterX = pipe.getX();
            if (birdX >= pipeCenterX) {
                isBetweenCurrentFrame = true;
                if (!isBetweenLastFrame){
                    score.incrementScore();
                    Greenfoot.playSound("point-101soundboards.wav");
                }
            }            
        }
        isBetweenLastFrame = isBetweenCurrentFrame;
    }
    
    

}
