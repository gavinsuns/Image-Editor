import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Color;

/**
 * The class Processor contains all of the code to actually perform
 * transformation. The rest of the classes serve to support that
 * capability. This World allows the user to choose transformations
 * and open files.
 * 
 * @author Gavin Sun
 * @version December 2019
 */

public class Background extends World
{
    // Constants:
    private final String STARTING_FILE = "flower.jpg";

    // Objects and Variables:
    private static ImageHolder image;

    private TextButton blueButton;
    private TextButton hRevButton;
    private TextButton vRevButton;
    private TextButton greyScaleButton;
    private TextButton negButton;
    private TextButton rotate;
    private TextButton turn;
    private TextButton undo;
    private TextButton redButton;
    private TextButton greenButton;
    private TextButton warm;
    private TextButton bright;
    private TextButton darken;
    private TextButton openFile;
    private TextButton saveAsPNG;
    private TextButton saveAsJPG;
    private GrayTextBox colourChange;
    private GrayTextBox additionalChange;
    private GrayTextBox file;
    
    private int counterC = 0;
    private int counterA = 0;
    private int counterF = 0;
    private String fileName;
    private Logo logo;

    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        // Initialize buttons and the image
        image = new ImageHolder(STARTING_FILE);
        logo = new Logo();
        blueButton = new TextButton("Blue++");
        greenButton = new TextButton("Green++");
        hRevButton = new TextButton("Horizontal Flip");
        vRevButton = new TextButton("Vertical Flip");
        greyScaleButton = new TextButton("Grey Scale");
        negButton = new TextButton("Negative");      
        rotate = new TextButton("Rotate 180°");
        turn = new TextButton("Turn 90°");
        undo = new TextButton("Undo");
        redButton = new TextButton("Red++");
        warm = new TextButton("Warm++");
        bright = new TextButton("Bright++");
        darken = new TextButton("Dark++");
        openFile = new TextButton("File: " + STARTING_FILE);
        saveAsPNG = new TextButton("Save as PNG");
        saveAsJPG = new TextButton("Save as JPG");
        colourChange = new GrayTextBox("Colour Functions");
        additionalChange = new GrayTextBox("Special Functions");
        file = new GrayTextBox("                File");
        
        // Add objects to the screen
        addObject (image, 400, 350);
        addObject (logo, 770, 570); 
        addObject (colourChange, 700, 20);
        
        addObject (file, 400, 20);
        
        addObject (additionalChange, 100, 20);   
    }

    /**
     * getImage() method gets the image for other classes to access.
     * 
     * @return image returns the current image.
     */
    public static ImageHolder getImage()
    {
        return image;
    }
    
    /**
     * Act() method just checks for mouse input.
     */
    public void act ()
    {
        checkMouse();
    }
    
    /**
     * Check for user clicking on a button.
     */
    private void checkMouse ()
    {
        // Avoid excess mouse checks - only check mouse if somethething is clicked.
        if (Greenfoot.mouseClicked(null))
        {
            if (Greenfoot.mouseClicked(colourChange)){
                counterC++;
                if (counterC % 2 == 1){
                        addObject (redButton, 700, 60);
                        addObject (greenButton, 700, 100);
                        addObject (blueButton, 700, 140);
                        addObject (greyScaleButton, 700, 180);
                        addObject (negButton, 700, 220);
                        addObject (bright, 700, 260);
                        addObject (darken, 700, 300);
                        addObject (warm, 700, 340);
                }
                else{
                        removeObject (redButton);
                        removeObject (greenButton);
                        removeObject (blueButton);
                        removeObject (greyScaleButton);
                        removeObject (negButton);
                        removeObject (bright);
                        removeObject (darken);
                        removeObject (warm);                    
                }
            }
            if(Greenfoot.mouseClicked(additionalChange)){
                counterA++;
                if (counterA % 2 == 1){  
                    addObject (undo, 100, 60); 
                    addObject (hRevButton, 100, 100);
                    addObject (vRevButton, 100, 140);
                    addObject (rotate, 100, 180);
                    addObject (turn, 100, 220); 
                }
                else{ 
                    removeObject (undo); 
                    removeObject (vRevButton);
                    removeObject (rotate);
                    removeObject (turn);
                    removeObject (hRevButton);                       
                }
            }
            if (Greenfoot.mouseClicked(file)){
                counterF++;
                if (counterF % 2 == 1){
                    addObject (openFile, 400, 60);      
                    addObject (saveAsPNG, 400, 100);
                    addObject (saveAsJPG, 400, 140);            
                }
                else{
                    removeObject(saveAsPNG);
                    removeObject(saveAsJPG);
                    removeObject(openFile);
                }
            }
            if (Greenfoot.mouseClicked(blueButton)){
                Processor.blueify(image.getBufferedImage());
            }
            else if (Greenfoot.mouseClicked(hRevButton)){
                Processor.flipHorizontal(image.getBufferedImage());
            }
            else if (Greenfoot.mouseClicked(vRevButton)){
                Processor.flipVertical(image.getBufferedImage());
            }
            else if (Greenfoot.mouseClicked(greyScaleButton)){
                Processor.greyScale(image.getBufferedImage());
            }      
            else if (Greenfoot.mouseClicked(negButton)){
                Processor.negative(image.getBufferedImage());
            }
            else if (Greenfoot.mouseClicked(rotate)){
                Processor.rotate(image.getBufferedImage());
            }
            else if (Greenfoot.mouseClicked(turn)){
                GreenfootImage rotate = Processor.turn(image.getBufferedImage());
                image.setImage(rotate);
            }
            else if (Greenfoot.mouseClicked(redButton)){
                Processor.redify(image.getBufferedImage());
            }
            else if (Greenfoot.mouseClicked(warm)){
                Processor.warm(image.getBufferedImage());
            }
            else if(Greenfoot.mouseClicked(undo)){
                GreenfootImage undo = Processor.undo(image.getBufferedImage());
                image.setImage(undo);
            }
            else if(Greenfoot.mouseClicked(greenButton)){
                Processor.greenify(image.getBufferedImage());
            }
            else if (Greenfoot.mouseClicked(bright)){
                Processor.bright(image.getBufferedImage());
            }
            else if (Greenfoot.mouseClicked(darken)){
                Processor.darken(image.getBufferedImage());
            }
            else if (Greenfoot.mouseClicked(openFile))
            {
                openFile ();
            }
            else if (Greenfoot.mouseClicked(saveAsPNG))
            {
                saveAsPNG ();
            }
            else if (Greenfoot.mouseClicked(saveAsJPG))
            {
                saveAsJPG ();
            }
        }
    }

    /**
     * Allows the user to open a new image file.
     */
    private void openFile ()
    {
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Please input a file name with extension");

        // If the file opening operation is successful, update the text in the open file button
        if (image.openFile (fileName))
        {
            String display = "File: " + fileName;
            openFile.update (display);
        }
        else{
            JOptionPane.showMessageDialog (null, "Open Unsuccessful!");
        }

    }    
    
    /**
     * Allows the user to save a file as a PNG.
     */    
    private void saveAsPNG ()
    {
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Input file name (No extension)");
        try{
        if (fileName.equals ("")){
            fileName += "image";
            JOptionPane.showMessageDialog (null, "No file name entered, file name set to: image");
        }
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog (null, "Cancelled!");   
        }    
        
        if (fileName != null){
            fileName += ".png";
            File f = new File (fileName);    
            try{
                ImageIO.write(image.getImage().getAwtImage(), "png", f );
            }
            catch(IOException e) {
                JOptionPane.showMessageDialog (null, "Save unsuccessful!");
            }
            JOptionPane.showMessageDialog (null, "Successfully saved as " + fileName + "!");
        }
    }
    
    /**
     * Allows user to save a file as a JPG.
     */    
    private void saveAsJPG(){
        // Use a JOptionPane to get file name from user
        String fileName = JOptionPane.showInputDialog("Please input a file name to save as. (No extension)");
        try{
            if (fileName.equals ("")){
                fileName += "image";
                JOptionPane.showMessageDialog (null, "No file name entered, file name set to: image");
            }
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog (null, "Cancelled!");
        }
        
        if (fileName != null)
        {
            fileName += ".jpg";
            File f = new File (fileName);     
        
            BufferedImage pngImage = image.getBufferedImage();
            try{
                BufferedImage newBufferedImage = new BufferedImage (pngImage.getWidth(), pngImage.getHeight(), pngImage.TYPE_INT_RGB);
                newBufferedImage.createGraphics().drawImage(pngImage,0,0,Color.WHITE,null);
                ImageIO.write(newBufferedImage, "jpg", f );
            }
            catch(IOException e) {
                JOptionPane.showMessageDialog (null, "Save unsuccessful!");
            }
    
            JOptionPane.showMessageDialog (null, "Successfully saved as " + fileName + "!");
        }
    }
}

