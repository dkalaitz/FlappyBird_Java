import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class StartingPage extends World
{

    private SimpleTimer soundTimer = new SimpleTimer();
    private boolean soundMapFlag = false;
    private GreenfootSound introSound = new GreenfootSound("flappy_bird_intro.wav");

    public StartingPage()
    {    
        super(MyWorld.WORLD_WIDTH, MyWorld.WORLD_HEIGHT, 1);
        generateInitialClouds();
        getBackground().setColor(MyWorld.background_color);
        getBackground().fill();
        
        // Display the logo image
        GreenfootImage logo = new GreenfootImage("flappy_bird_logo.png");
        logo.scale(MyWorld.WORLD_WIDTH/2, MyWorld.WORLD_HEIGHT/2);
        addObject(new Actor() {
            {
                setImage(logo);
            }
        }, getWidth() / 2, getHeight() / 2 - 100);
        
        // Display the text prompting to press Enter
        GreenfootImage pressEnterToStartImage = new GreenfootImage("Press Space to Begin", 40, Color.YELLOW, new Color(0, 0, 0, 0));
        addObject(new Actor() {
            {
                setImage(pressEnterToStartImage);
            }
        }, getWidth() / 2, getHeight() / 2 + 200);

        GreenfootImage instructionsImage = new GreenfootImage("Use \"Space\" to fly. Try to pass through as many pipes as you can!", 20, Color.WHITE, new Color(0, 0, 0, 0));
        addObject(new Actor() {
            {
                setImage(instructionsImage);
            }
        }, getWidth() / 2, getHeight() / 2 + 300);

    }      
    
    public void act() {
        // Check if Enter key is pressed to start the game
        if (Greenfoot.isKeyDown("space")) {
            introSound.stop();
            // Replace StartPage with main game world
            Greenfoot.setWorld(new MyWorld());
        }
        if (!introSound.isPlaying() && soundTimer.millisElapsed() >= 100000){
            soundTimer.mark(); //Restart the timer
            introSound.play();
        } else if (!soundMapFlag){
            introSound.play();
            soundMapFlag = true;
            soundTimer.mark(); // Start the timer
        }
    }
    
    public void started(){
        CloudGenerator cloudGenerator = new CloudGenerator();
        addObject(cloudGenerator, getWidth(), getHeight());
    }
    
    private void generateInitialClouds(){
        for (int i = 0; i < 5; i++) {
            Cloud cloud = new Cloud();
            int randomX = Greenfoot.getRandomNumber(MyWorld.WORLD_WIDTH); // Generate random X coordinate
            int randomY = Greenfoot.getRandomNumber(MyWorld.WORLD_HEIGHT); // Generate random Y coordinate
            addObject(cloud, randomX, randomY);
        }
    }
}
