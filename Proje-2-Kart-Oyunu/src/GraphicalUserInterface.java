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
    int CardsNumber;
    JFrame window;
    private ArrayList<SavasAraclari> PlayingCards;
    private ArrayList<CardForGraphics> cardsinfo;
    private ArrayList<CardForGraphics> framesonmap;
    private ArrayList<CardForGraphics> safecardsinfo;
    private ArrayList<String> stringer;

    GraphicalUserInterface(ArrayList<CardForGraphics> cardsinfo, ArrayList<CardForGraphics> framesonmap, ArrayList<CardForGraphics> safecardsinfo, ArrayList<SavasAraclari> PlayingCards, ArrayList<String> stringass) throws InterruptedException
    {
        this.PlayingCards = PlayingCards;
        this.cardsinfo = cardsinfo;
        this.framesonmap = framesonmap;
        this.safecardsinfo = safecardsinfo;
        this.stringer = stringass;
        this.window = new JFrame();
        window.setBackground(Color.BLACK);
        window.setTitle("Kart Oyunu: Savaş Araçları");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(1366, 876);
        window.addMouseListener
        ( new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) { Oyun.NumbersClicked++; }
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        }
        );
        window.addMouseWheelListener
        ( new MouseWheelListener()
        {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e)
            {
                CardsNumber = 0;
                for(SavasAraclari card :PlayingCards) { CardsNumber++; }
                if(e.getWheelRotation() < 0)
                {
                    if(Oyun.selected_card < CardsNumber - 1)
                    {
                        Oyun.selected_card++;
                        while(PlayingCards.get(Oyun.selected_card).is_used)
                        {
                            if(Oyun.selected_card < CardsNumber - 1) Oyun.selected_card++;
                            else Oyun.selected_card = 0;
                        }
                    }
                    else
                    {
                        Oyun.selected_card = 0;
                        while (PlayingCards.get(Oyun.selected_card).is_used)
                        {
                            if (Oyun.selected_card < CardsNumber - 1) Oyun.selected_card++;
                        }
                    }
                    System.out.println(Oyun.selected_card);
                }
                else if(e.getWheelRotation() > 0)
                {
                    if(Oyun.selected_card > 0)
                    {
                        Oyun.selected_card--;
                        while(PlayingCards.get(Oyun.selected_card).is_used)
                        {
                            if(Oyun.selected_card > 0) Oyun.selected_card--;
                            else Oyun.selected_card = CardsNumber - 1;
                        }
                    }
                    else
                    {
                        Oyun.selected_card = CardsNumber - 1;
                        while (PlayingCards.get(Oyun.selected_card).is_used)
                        {
                            if (Oyun.selected_card > 0) Oyun.selected_card--;
                        }
                    }
                    System.out.println(Oyun.selected_card);
                }
            }
        }
        );
        window.setVisible(true);                                    // make window visible
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (CardForGraphics fr : framesonmap) g.drawImage(fr.image, fr.display_x_pos, fr.display_y_pos, null);
        for (CardForGraphics card : safecardsinfo) g.drawImage(card.image, card.display_x_pos, card.display_y_pos, null);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 34));
        g.setColor(Color.WHITE);
        g.drawString(stringer.get(0), 60, 330);
        g.drawString(stringer.get(1), 60, 390);
        g.drawString(stringer.get(2), 60, 450);
        g.drawString(stringer.get(3), 60, 510);
        Oyuncu.SkorGoster(g, stringer);
        g.setColor(Color.RED);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));
        g.drawString(stringer.get(6), 265, 450);
    }

    // verilen path aracılıgıyla resmi yukleyip return eder
    public static BufferedImage loadbimg(String path)
    {
        BufferedImage bimg;
        try
        {
            File imgFile = new File(path);
            bimg = ImageIO.read(imgFile);
            if (bimg != null)
            {
                return bimg;
            }
            else System.out.println("Resim yuklenemedi.");
        }
        catch (IOException e)
        {
            System.out.println(path+" adresindeki resim bulunamadı, yüklenmeyecek.");
            return null;
        }
        return null;
    }

    // resmi dönderiyor
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
        graphic.setComposite(AlphaComposite.SrcOver.derive(0.0f));
        graphic.fillRect(0, 0, neww, newh);
        graphic.setComposite(AlphaComposite.SrcOver);
        graphic.translate((neww-w)/2, (newh-h)/2);
        graphic.rotate(Math.toRadians(angle), (float)w/2, (float)h/2);
        graphic.drawRenderedImage(cardsinfo.image, null);
        graphic.dispose();
        return rotated;
    }

    // resmi saydamlastiriyor
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

    // resmi verilen boyutlarar büyütüyor
    public static BufferedImage scale(BufferedImage src, int w, int h)
    {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int x, y;
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (y = 0; y < h; y++) ys[y] = y * hh / h;
        for (x = 0; x < w; x++)
        {
            int newX = x * ww / w;
            for (y = 0; y < h; y++)
            {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }
}
