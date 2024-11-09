import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Oyun
{
    public static void main(String[] args)
    {
        Oyuncu bilgisayar = new Oyuncu();
        Oyuncu Player = new Oyuncu();
        System.out.println("sa");

        //CardForGraphics[] cardsinfo = new CardForGraphics[20];
        ArrayList<CardForGraphics> cardsinfo = new ArrayList<>();
        cardsinfo.add(new CardForGraphics((short)300, (short)200, (short)612, (short)407, 45, "Visual/djsjdjebfowodbwocbwoc.jpg"));
        //ArrayList<BufferedImage> cards = new ArrayList<BufferedImage>();
        ArrayList<BufferedImage> cards = GraphicalUserInterface.fillImagesInAccordanceToTheirInfo(cardsinfo, new ArrayList<>(), (byte)cardsinfo.size());

        GraphicalUserInterface gui = new GraphicalUserInterface(cardsinfo, cards);
        gui.window.add(gui);

        ActionListener screen_refresher = new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                //window.MainGraphics(cardsinfo, cards);
                cards.set(0, GraphicalUserInterface.loadbimg(cards.get(0), cardsinfo.get(0).path));
                cards.set(0, GraphicalUserInterface.rotate(cards.get(0), cardsinfo.get(0).rotation, cardsinfo.get(0)));
                gui.repaint();
                cardsinfo.get(0).AddToRotation(5);
                System.out.println("card 1 angle "+cardsinfo.get(0).rotation);
            }
        };

        Timer screen_refresh_timer = new Timer(1000/60, screen_refresher);
        screen_refresh_timer.start();




    }
}
