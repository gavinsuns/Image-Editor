import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Generic Button to display text that is clickable. Owned by a World, which controls click
 * capturing.
 * 
 * @author Gavin Sun
 * @version December 2019
 */

public class TextButton extends Actor
{
    // Declare private variables
    private GreenfootImage myImage;
    private String buttonText;
    private int textSize;

    /**
     * Construct a TextButton with a given String at the default size.
     * 
     * @param text  String value to display.
     * 
     */
    public TextButton (String text)
    {
        this(text, 20);
        textSize = 20;
    }

    /**
     * Act() method checks if mouse has moved over the button.
     */       
    public void act(){
         if (Greenfoot.mouseMoved(this)){
             update(Color.BLUE); 
         }
         if(Greenfoot.mouseMoved(getWorld()) || Greenfoot.mouseMoved(Background.getImage())){
             update(Color.WHITE);
         }
    }
    
    /**
     * Construct a TextButton with a given String and a specified size.
     * 
     * @param text  String value to display.
     * @param textSize  size of text, as an integer.
     * 
     */
    public TextButton (String text, int textSize)
    {
        buttonText = text;
        GreenfootImage tempTextImage = new GreenfootImage (text, textSize, Color.BLACK, Color.WHITE);
        myImage = new GreenfootImage (160, tempTextImage.getHeight() + 8);
        myImage.setColor (Color.WHITE);
        myImage.fill();
        myImage.drawImage (tempTextImage, 4, 4);

        myImage.setColor (Color.WHITE);
        myImage.drawRect (0,0,159, tempTextImage.getHeight() + 7);
        setImage(myImage);
    }

    /**
     * Change the text displayed on this Button.
     * 
     * @param   text    String to display.
     * 
     */
    public void update (String text)
    {
        buttonText = text;      
        GreenfootImage tempTextImage = new GreenfootImage (text, 20, Color.BLACK, Color.WHITE);
        myImage = new GreenfootImage (160, tempTextImage.getHeight() + 8);
        myImage.setColor (Color.WHITE);
        myImage.fill();
        myImage.drawImage (tempTextImage, 4, 4);

        myImage.setColor (Color.WHITE);
        myImage.drawRect (0,0,159, tempTextImage.getHeight() + 7);
        setImage(myImage);
    }

    /**
     * Change the colour of this Button.
     * 
     * @param colour The colour to change to.
     * 
     */
    public void update (Color colour){
        GreenfootImage tempTextImage = new GreenfootImage (buttonText, 20, Color.BLACK, colour);

        myImage = new GreenfootImage (160, tempTextImage.getHeight() + 8);
        myImage.setColor (colour);
        myImage.fill();
        myImage.drawImage (tempTextImage, 4, 4);

        myImage.setColor (colour);
        myImage.drawRect (0,0,159, tempTextImage.getHeight() + 7);
        setImage(myImage);
    }
}
