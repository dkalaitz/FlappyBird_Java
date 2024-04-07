import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Heart extends Actor {
    private GreenfootImage fullHeart = new GreenfootImage("heart_full.png");
    private GreenfootImage emptyHeart = new GreenfootImage("heart_empty.png");
    
    public Heart() {
        fullHeart.scale(fullHeart.getWidth() / 6, fullHeart.getHeight() / 6);
        emptyHeart.scale(emptyHeart.getWidth() / 6, emptyHeart.getHeight() / 6);
        setImage(fullHeart);
    }
    
    public void act(){
        displayLives();
    }
    
    
    private void displayLives(){
        if (Bird.LIVES == 1){
            MyWorld.SECOND_HEART.setImage(emptyHeart);
        } else if (Bird.LIVES == 0){
            MyWorld.FIRST_HEART.setImage(emptyHeart);
        }
    }
    
}
