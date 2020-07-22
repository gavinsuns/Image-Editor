import greenfoot.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

/**
 * Class that processes images.
 * This class manipulates Java BufferedImages with multiple different methods.
 * Class contains methods to flip, rotate and add photo effects to the image.
 * 
 * @author Gavin Sun
 * @version December 2019
 */

public class Processor  
{
    private static ArrayList<BufferedImage> listOfVersions = new ArrayList<BufferedImage>();  
    
    /**
     * This method will increase the blue value while reducing the red and green values.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */
    public static void blueify (BufferedImage bi)
    {
        listOfVersions.add(deepCopy(bi));
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);
                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (blue < 254)
                    blue += 2;
                if (red >= 50)
                    red--;
                if (green >= 50)
                    green--;

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }

    }

    /**
     * This method will flip the image vertically.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */    
    public static void flipVertical (BufferedImage bi)
    {
        listOfVersions.add(deepCopy(bi));
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);

        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                newBi.setRGB (x, ySize - y - 1, rgb);
                
            }
        }
        
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = newBi.getRGB(x, y);
                bi.setRGB (x, y, rgb);
            }
        }        
    }
    
    /**
     * This method will flip the image horizontally.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */    
    public static void flipHorizontal (BufferedImage bi)
    {
        listOfVersions.add(deepCopy(bi));
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                newBi.setRGB (xSize - x - 1, y, rgb);
                
            }
        }
        
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = newBi.getRGB(x, y);
                bi.setRGB (x, y, rgb);
            }
        }
    }

    /**
     * This method will change the red, green, and blue values of each pixel to make the image grey instead of coloured.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */    
    public static void greyScale(BufferedImage bi){
        listOfVersions.add(deepCopy(bi));
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Temp image, to store pixels as we reverse everything
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                double grey = ( (0.3 * red) + (0.59 * green) + (0.11 * blue) );
                int newColour = packagePixel ((int) grey, (int) grey, (int) grey, alpha);
                bi.setRGB (x, y, newColour);            
            }
        }
    }

    /**
     * This method will inverse the red, green, and blue values to make the colour of the pixels inversed.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */    
    public static void negative(BufferedImage bi){   
        listOfVersions.add(deepCopy(bi));
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Temp image, to store pixels as we reverse everything
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                
                int[] rgbValues = unpackPixel (rgb);
                
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                int newColour = packagePixel (255 - red, 255 - green, 255 - blue, alpha);
                bi.setRGB (x, y, newColour);            
            }
        }       
    }

    /**
     * This method will rotate the image 180°.
     * 
     * @param bi    The BufferedImage (passed by reference) to rotate.
     */    
    public static void rotate (BufferedImage bi)
    {
        listOfVersions.add(deepCopy(bi));
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);

        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                newBi.setRGB (xSize - x - 1, ySize - y - 1, rgb);
                
            }
        }     
        
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = newBi.getRGB(x, y);
                bi.setRGB (x, y, rgb);
            }
        }        
    }

    /**
     * This method will rotate the image 90° clockwise.
     * 
     * @param bi    The BufferedImage (passed by reference) to turn.
     * 
     * @return BufferedImage The new buffered image which is bi rotated clockwise.
     */    
    public static GreenfootImage turn (BufferedImage bi)
    {
        listOfVersions.add(deepCopy(bi));
        int ySize = bi.getWidth();
        int xSize = bi.getHeight();

        // Temp image, to store pixels as we reverse everything
        BufferedImage newBi = new BufferedImage (xSize, ySize, 3);
        
        for (int x = 0; x < ySize; x++)
        {
            for (int y = 0; y < xSize; y++)
            {
                int rgb = bi.getRGB(x, y);
                newBi.setRGB (xSize - y - 1, x, rgb);
            }
        }
        GreenfootImage newImage = createGreenfootImageFromBI (newBi);
        return newImage;
    }

    /**
     * This method will increase the red value while reducing the green and blue values.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */  
    public static void redify(BufferedImage bi){
        listOfVersions.add(deepCopy(bi));
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Temp image, to store pixels as we reverse everything
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                if (blue >= 50)
                    blue --;
                if (red < 254)
                    red += 2;
                if (green >= 50)
                    green--;
                
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);            
            }
        }
    }    

    /**
     * This method will increase the red and green value, which makes the image more warm/yellow.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */    
    public static void warm(BufferedImage bi)
    {
        listOfVersions.add(deepCopy(bi));

        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgb = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic Warmer
                if (red <= 255){
                    red += 6;
                }
                if (green <= 255){
                    green += 6;
                }

                if (red > 255){
                    red = 255;
                }
                if (green > 255){
                    green = 255;
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    /**
     * This method will increase the brightness of the image.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */    
    public static void bright(BufferedImage bi){
        listOfVersions.add(deepCopy(bi));
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Temp image, to store pixels as we reverse everything
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                double r = 255*(Math.pow((red/255.0), 0.66));
                double g = 255*(Math.pow((green/255.0), 0.66));
                double b = 255*(Math.pow((blue/255.0), 0.66));
                int newColour = packagePixel ((int) r, (int) g, (int) b, alpha);
                bi.setRGB (x, y, newColour);            
            }
        }
    }    

    /**
     * This method will increase the green value while reducing the red and blue values.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */    
    public static void greenify(BufferedImage bi){
        listOfVersions.add(deepCopy(bi));
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        // Temp image, to store pixels as we reverse everything
        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                if (blue >= 50)
                    blue --;
                if (red >= 50)
                    red--;
                if (green < 254)
                    green += 2;
                
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);            
            }
        }
    }    

    /**
     * This method will darken/reduce the brightness of the image by reducing the red, green, and blue values.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */    
        public static void darken(BufferedImage bi)
    {
        listOfVersions.add(deepCopy(bi)); 
        
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        for (int x = 0; x < xSize; x++)
        {
            for (int y = 0; y < ySize; y++)
            {
                int rgb = bi.getRGB(x, y);

                int[] rgbValues = unpackPixel (rgb);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (red-10 > 35)
                {
                    red-=10;
                }
                if (green-10 > 35)
                {
                    green-=10;
                }
                if (blue-10 > 35)
                {
                    blue-=10;
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }       
    }

    /**
     * This method will undo the changes made to the original version.
     * Each click makes the image go back one change.
     * 
     * @param bi    The BufferedImage (passed by reference) to undo.
     * 
     * @return BufferedImage The previous version of the buffered image.
     */        
    public static GreenfootImage undo(BufferedImage bi){
        int old = listOfVersions.size() - 1;
        if (old >= 0){
            BufferedImage newBi = listOfVersions.get(old);
            GreenfootImage newImage = createGreenfootImageFromBI (newBi);
            listOfVersions.remove(old);
            return newImage;
        }
        else{
            GreenfootImage image = createGreenfootImageFromBI (bi);
            return image;
        }    
    }    
    
    /**
     * Takes in a BufferedImage and returns a GreenfootImage.
     *
     * @param newBi The BufferedImage to convert.
     *
     * @return GreenfootImage A GreenfootImage built from the BufferedImage provided.
     */
    public static GreenfootImage createGreenfootImageFromBI (BufferedImage newBi)
    {
        GreenfootImage returnImage = new GreenfootImage (newBi.getWidth(), newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);
        return returnImage;
    }

    /**
     * This method will make a copy of the buffered image.
     * 
     * @param bi    The BufferedImage (passed by reference) to copy.
     * 
     * @return BufferedImage A copy of the buffered image.
     */        
    public static BufferedImage deepCopy(BufferedImage bi){    
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultip = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultip, null);    
    }
    
    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation.
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }

    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer.
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }    
}
