import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Generic gray button to display text that is clickable. Owned by a World, which controls click
 * capturing.
 * 
 * @author Gavin Sun
 * @version December 2019
 */

public class GrayTextBox extends Actor
{
    // Declare private variables
    private GreenfootImage myImage;
    private String buttonText;
    private int textSize;

    /**
     * Construct a TextButton that is gray with a given String at the default size.
     * 
     * @param text  String value to display.
     * 
     */
    public GrayTextBox (String text)
    {
        this(text, 20);
    }

    /**
     * Act() method checks if mouse has moved over the button.
     */    
    public void act(){
         if (Greenfoot.mouseMoved(this)){
             update(Color.DARK_GRAY); 
         }
         if(Greenfoot.mouseMoved(getWorld()) || Greenfoot.mouseMoved(Background.getImage())){
             update(Color.GRAY);
         }
    }
    
    /**
     * Construct a TextButton that is gray with a given String and a specified size.
     * 
     * @param text  String value to display.
     * @param textSize  size of text, as an integer.
     * 
     */
    public GrayTextBox (String text, int textSize)
    {
        buttonText = text;
        GreenfootImage tempTextImage = new GreenfootImage (text, textSize, Color.WHITE, Color.GRAY);
        myImage = new GreenfootImage (160, tempTextImage.getHeight() + 8);
        myImage.setColor (Color.GRAY);
        myImage.fill();
        myImage.drawImage (tempTextImage, 4, 4);

        myImage.setColor (Color.GRAY);
        myImage.drawRect (0,0,159, tempTextImage.getHeight() + 7);
        setImage(myImage);
    }
    
    /**
     * Change the colour of this Button.
     * 
     * @param colour The colour to use.
     * 
     */    
    public void update (Color colour){
        GreenfootImage tempTextImage = new GreenfootImage (buttonText, 20, Color.WHITE, colour);

        myImage = new GreenfootImage (160, tempTextImage.getHeight() + 8);
        myImage.setColor (colour);
        myImage.fill();
        myImage.drawImage (tempTextImage, 4, 4);

        myImage.setColor (colour);
        myImage.drawRect (0,0,159, tempTextImage.getHeight() + 7);
        setImage(myImage);
    }
}
