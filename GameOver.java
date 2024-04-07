import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOver extends Actor
{
    public GameOver() {
        GreenfootImage game_over_image = new GreenfootImage("game_over.png");
        game_over_image.scale(MyWorld.WORLD_WIDTH/2, MyWorld.WORLD_HEIGHT/2);
        setImage(game_over_image);
        Greenfoot.playSound("game_over_sound.wav");
    }
    
}
