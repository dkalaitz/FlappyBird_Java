import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Score extends Actor
{
    
    private int score, scoreMod;
    private Color scoreColor = new Color(252, 136, 3);

    public Score() {
        score = 0;
        scoreMod = 0;
        updateImage();
    }

    public void incrementScore() {
        scoreMod++;
        if (scoreMod % 2 == 0) {
            score++;
        }
        updateImage();
    }

    private void updateImage() {
        GreenfootImage image = new GreenfootImage(200, 50); 
        image.setTransparency(150); 
        image.setColor(scoreColor);
        image.setFont(new Font("Arial", true, false, 32)); 
        image.drawString("Score: " + score, 30, 30); 
        setImage(image);
    }
    
    public int getScore() {
        return score;
    }
}
