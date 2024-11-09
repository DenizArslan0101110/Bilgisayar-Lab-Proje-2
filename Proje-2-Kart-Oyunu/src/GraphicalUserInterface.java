import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class GraphicalUserInterface extends JPanel
{

    JFrame window;

    private ArrayList<CardForGraphics> cardsinfo;

    GraphicalUserInterface(ArrayList<CardForGraphics> cardsinfo)
    {
        this.cardsinfo = cardsinfo;
        // JFrame basically our window
        this.window = new JFrame();                               // create frame
        window.setTitle("Kart Oyunu: Savaş Araçları");              // set title
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // x button purpose
        window.setResizable(false);                                 // disable resize
        window.setSize(1366, 876);                                  // set size
        window.setVisible(true);                                    // make window visible

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (CardForGraphics cardsinfoii : cardsinfo) g.drawImage(cardsinfoii.image, cardsinfoii.display_x_pos, cardsinfoii.display_y_pos, null);
    }

    public static ArrayList<CardForGraphics> fillImagesInAccordanceToTheirInfo(ArrayList<CardForGraphics> cardsinfo)
    {
        for (CardForGraphics cardsinfoii : cardsinfo) {
            cardsinfoii.set_Image(loadbimg(cardsinfoii.path));
            cardsinfoii.image = rotate(cardsinfoii.rotation, cardsinfoii);
        }
        return cardsinfo;
    }


    // method for loading a buffered image with a specific jpg file
    public static BufferedImage loadbimg(String path)
    {
        BufferedImage bimg;
        try
        {
            File imgFile = new File(path);
            bimg = ImageIO.read(imgFile);
            // Check if the image was successfully loaded
            if (bimg != null)
            {
                //System.out.println("Image loaded successfully!");
                // code requires us to load all the images every single frame
                // to reduce clutter I give no info when load is successfull
                return bimg;
            }
            else System.out.println("Failed to load the image.");
        }
        catch (IOException e)
        {
            // Handle any I/O exceptions (e.g., file not found, unsupported format)
            System.out.println("Specified image at "+path+" could not be found, it wont be loaded.");
            return null;
        }
        return null;
    }

    // rotates Image, took straight out of StackOverflow :skull:
    public static BufferedImage rotate(Double angle, CardForGraphics cardsinfo)
    {
        double sin = Math.abs(Math.sin(Math.toRadians(angle)));
        double cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = cardsinfo.image.getWidth();
        int h = cardsinfo.image.getHeight();
        int neww = (int) Math.floor(w*cos + h*sin);
        int newh = (int) Math.floor(h*cos + w*sin);

        cardsinfo.display_x_pos = cardsinfo.x_pos;
        cardsinfo.display_y_pos = cardsinfo.y_pos;
        cardsinfo.display_x_pos -= (short)((neww-cardsinfo.width)/2);
        cardsinfo.display_y_pos -= (short)((newh-cardsinfo.height)/2);

        BufferedImage rotated = new BufferedImage(neww, newh, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphic = rotated.createGraphics();

        graphic.setComposite(AlphaComposite.SrcOver.derive(0.0f));  // Fully transparent background
        graphic.fillRect(0, 0, neww, newh);  // Fill the entire image with transparency
        graphic.setComposite(AlphaComposite.SrcOver);

        graphic.translate((neww-w)/2, (newh-h)/2);
        graphic.rotate(Math.toRadians(angle), (float)w/2, (float)h/2);
        graphic.drawRenderedImage(cardsinfo.image, null);
        graphic.dispose();
        return rotated;
    }
}
