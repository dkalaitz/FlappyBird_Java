import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class CloudGenerator extends Generator
{
    private static final int SPAWN_DELAY = 100; // Adjust as needed
    private int spawnCounter = 0;
    private static final int CLOUD_SPEED = -2;
    
    
    public CloudGenerator() {
    }
    
    public void act()
    {
        generate();
        move();
    }
    
    public void generate(){
        spawnCounter++;
        if (spawnCounter >= SPAWN_DELAY) {
            spawn();
            spawnCounter = 0;
        }      
    }

    public void spawn() {
        World world = getWorld();
        if (world != null) {
            Cloud cloud = new Cloud();
            int x = MyWorld.WORLD_WIDTH;
            int y = Greenfoot.getRandomNumber(world.getHeight());
            world.addObject(cloud, x, y);
        }
    }
    
    
    public void move() {
        // Get all pipe objects in the world
        List<Cloud> clouds = getWorld().getObjects(Cloud.class);
        
        // Move each pipe to the left
        for (Cloud cloud : clouds) {
            cloud.setLocation(cloud.getX() + CLOUD_SPEED, cloud.getY());
        }
    }
}
