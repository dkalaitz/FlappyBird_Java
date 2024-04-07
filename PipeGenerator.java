import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;


public class PipeGenerator extends Generator
{
    private static int PIPE_WIDTH = 80;
    private int pipeGap = 280; 
    private int pipe_move_speed = -3; 
    private static final int MIN_HEIGHT = 120;
    private int delay = 130;
    private int spawnCounter = 0;
    private Score score;
    private int minDelay = 40, maxSpeed = -10, minPipeGap = 200;
    
    public PipeGenerator(Score score) {
        this.score = score;
    }

    
    public void act()
    {   
        if (checkBirdsCondition()){
            spawn();
            move(); 
        }
    }
    
    // Spawn pipes
    public void spawn(){
        spawnCounter++;
        if (spawnCounter >= delay){
            generate();
            spawnCounter = 0;
            // Gradually increasing difficulty
            increaseDifficulty();
        }
    }
    
    /**
     * This method generates a pair of pipes for the game. It calculates the maximum height that the top pipe can have 
     * and generates a random height for it within the constraints. If the calculated height for either the top or bottom pipe
     * is below the specified minimum height, it is adjusted accordingly.
     * The top and bottom pipes are then created with their respective heights and added to the game world.
     */
    public void generate() {
        // Calculate the maximum height that the top pipe can have
        int maxTopPipeHeight = MyWorld.WORLD_HEIGHT - MIN_HEIGHT - pipeGap;
    
        // Generate random height for the top pipe within the constraints
        int topPipeHeight = Greenfoot.getRandomNumber(maxTopPipeHeight);
    
        // Ensure that the top pipe height is not below the minimum height
        if (topPipeHeight < MIN_HEIGHT) {
            topPipeHeight = MIN_HEIGHT;
        }
    
        // Calculate the height of the bottom pipe
        int bottomPipeHeight = MyWorld.WORLD_HEIGHT - topPipeHeight - pipeGap;
    
        // Ensure that the bottom pipe height is not below the minimum height
        if (bottomPipeHeight < MIN_HEIGHT) {
            bottomPipeHeight = MIN_HEIGHT;
        }
    
        // Create top pipe
        Pipe topPipe = new Pipe();
        topPipe.getImage().scale(PIPE_WIDTH, topPipeHeight);
        getWorld().addObject(topPipe, MyWorld.WORLD_WIDTH, topPipeHeight / 2);
    
        // Create bottom pipe
        Pipe bottomPipe = new Pipe();
        bottomPipe.getImage().scale(PIPE_WIDTH, bottomPipeHeight);
        bottomPipe.setRotation(180);
        getWorld().addObject(bottomPipe, MyWorld.WORLD_WIDTH, MyWorld.WORLD_HEIGHT - bottomPipeHeight / 2);
    }
    
    private void increaseDifficulty(){
        // Increase speed every time user pass through 3 pair of pipes
        if (score.getScore() % 3 == 0 && score.getScore() != 0){
            if (pipe_move_speed >= maxSpeed){
                pipe_move_speed -= 1;
            }
            // Reduce delay in the pipes' spawning
            if (delay >= minDelay){
                  delay -= 10;
            }
            // Reduce the gap between the bottom and top pipe. when delay and move speed reach their minimum and maximum accordingly
            if (pipe_move_speed == maxSpeed && delay == minDelay && minPipeGap < pipeGap){
                pipeGap -= 10;
            }
        }
    }

    
    public void move() {
        // Get all pipe objects in the world
        List<Pipe> pipes = getWorld().getObjects(Pipe.class);
        
        // Move each pipe to the left
        for (Pipe pipe : pipes) {
            pipe.setLocation(pipe.getX() + pipe_move_speed, pipe.getY());
        }
    }    
    
    private boolean checkBirdsCondition(){
        // Check if bird is alive or not
        if (Bird.LIVES == 0){
            return false;
        } else {
            return true;
        }
    }
    
}
