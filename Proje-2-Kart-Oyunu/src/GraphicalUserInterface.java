import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class GraphicalUserInterface extends JPanel
{
    int Player_card_number;
    int CardsNumber;
    JFrame window;


    private ArrayList<CardForGraphics> cardsinfo;
    private ArrayList<CardForGraphics> framesonmap;
    private ArrayList<CardForGraphics> safecardsinfo;

    GraphicalUserInterface(ArrayList<CardForGraphics> cardsinfo, ArrayList<CardForGraphics> framesonmap, ArrayList<CardForGraphics> safecardsinfo)
    {
        this.cardsinfo = cardsinfo;
        this.framesonmap = framesonmap;
        this.safecardsinfo = safecardsinfo;
        // JFrame basically our window
        this.window = new JFrame();                               // create frame
        window.setBackground(Color.BLACK);
        window.setTitle("Kart Oyunu: Savaş Araçları");              // set title
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // x button purpose
        window.setResizable(false);                                 // disable resize
        window.setSize(1366, 876);                                  // set size
        window.setVisible(true);                                    // make window visible

        window.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override public void mousePressed(MouseEvent e) {
                Oyun.NumbersClicked++;
                //System.out.println("AAAAAAAAAAAAA; " + Oyun.NumbersClicked);

            }
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
        window.addMouseWheelListener(new MouseWheelListener() {
            int k = 0;

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if(k == 0){
                    for(CardForGraphics i :cardsinfo){
                        if(i.owners_ID == true){
                            CardsNumber++;
                        }
                    }
                    k++;
                }
                if(e.getWheelRotation() < 0){
                    if(Oyun.selected_card < CardsNumber - 1)
                        Oyun.selected_card++;
                    System.out.println(Oyun.selected_card);
                }
                else{
                    if(Oyun.selected_card > 0)
                        Oyun.selected_card--;
                    System.out.println(Oyun.selected_card);
                }
            }
        });


    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (CardForGraphics card : safecardsinfo) g.drawImage(card.image, card.display_x_pos, card.display_y_pos, null);

        for (CardForGraphics fr : framesonmap) g.drawImage(fr.image, fr.display_x_pos, fr.display_y_pos, null);
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

    public static BufferedImage ApplyTransparency(CardForGraphics card, float ghosting_val)
    {
        BufferedImage dest = new BufferedImage(card.image.getWidth(), card.image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphic = dest.createGraphics();
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_OVER, ghosting_val);
        graphic.setComposite(ac);
        graphic.drawImage(card.image, 0, 0, null);
        graphic.dispose();
        return dest;
    }
}
