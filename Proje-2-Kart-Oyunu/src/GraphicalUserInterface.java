import javax.swing.*;
//import javax.swing.BorderFactory;
//import javax.swing.border.Border;
//import java.awt.*;
//import java.awt.geom.*;
//import java.awt.image.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class GraphicalUserInterface
{
    BufferedImage fumobuffer;
    public void MainGraphics(ArrayList<CardForGraphics> cardsinfo, BufferedImage[] cards)
    {


        // load buffered image (this can rotate)
        //BufferedImage jpeg = new BufferedImage(100, 150, BufferedImage.TYPE_INT_ARGB);
        //fumobuffer = loadbimg(jpeg, "Visual/djsjdjebfowodbwocbwoc.jpg");
        //fumobuffer = ImageRotator.rotate(fumobuffer, 24.0);


        // JPanel used to draw the buffered image
        JPanel cards_panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for(short i=0; i<cardsinfo.size() ;i++) g.drawImage(cards[i], cardsinfo.get(i).x_pos, cardsinfo.get(i).y_pos, null);  // Draw the image at coordinates (0, 0)
            }
        };

        // JFrame basically our window
        JFrame window = new JFrame();                               // create frame
            window.setTitle("Kart Oyunu: Savaş Araçları");              // set title
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // x button purpose
            window.setResizable(false);                                 // disable resize
            window.setSize(1366, 876);                                  // set size
            window.setVisible(true);                                    // make window visible

            window.add(cards_panel);                                    // will be used to draw cards (I hope)


    }

    // fills in the info for cards(the image versions), by looking at cardsinfo(the struct one)
    public static BufferedImage[] fillImagesInAccordanceToTheirInfo(ArrayList<CardForGraphics> cardsinfo, BufferedImage[] cards, byte arraysize)
    {
        for(short i=0; i<arraysize ;i++)
        {
            cards[i] = new BufferedImage(cardsinfo.get(i).width, cardsinfo.get(i).height, BufferedImage.TYPE_INT_ARGB);
            cards[i] = loadbimg(cards[i], cardsinfo.get(i).path);
            cards[i] = ImageRotator.rotate(cards[i], cardsinfo.get(i).rotation);
        }
        return cards;
    }


    // method for loading a buffered image with a specific jpg file
    public static BufferedImage loadbimg(BufferedImage bimg, String path)
    {
        try
        {
            File imgFile = new File(path);
            bimg = ImageIO.read(imgFile);
            // Check if the image was successfully loaded
            if (bimg != null)
            {
                System.out.println("Image loaded successfully!");
                return bimg;
            }
            else System.out.println("Failed to load the image.");
        }
        catch (IOException e)
        {
            // Handle any I/O exceptions (e.g., file not found, unsupported format)
            System.out.println("Specified image at "+path+" could not be found, it wont be loaded.");
            return bimg;
        }
        return bimg;
    }
}
