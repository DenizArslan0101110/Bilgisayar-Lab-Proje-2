import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Oyun
{
    public static void main(String[] args)
    {
        final int NUMBER_OF_CARDS = 6;

        ArrayList<CardForGraphics> cardsinfo = new ArrayList<>();
        GraphicalUserInterface gui = new GraphicalUserInterface(cardsinfo);
        gui.window.add(gui);

        Oyuncu Player = new Oyuncu(1,"Oyuncu",0);
        Oyuncu bilgisayar = new Oyuncu(0,"Bilgisayar",0);

        Player.ShuffleCards(NUMBER_OF_CARDS);
        bilgisayar.ShuffleCards(NUMBER_OF_CARDS);
        System.out.println("Insanlarin kartlari :");
        Player.ShowCards(cardsinfo);
        System.out.println("Bilgisayarin kartlari :");
        bilgisayar.ShowCards(cardsinfo);

        // DO NOT REPLACE WITH LAMBDA PLEASE I DONT FUCKING KNOW WHAT A LAMBDA IS, IGNORE THE WARNING
        ActionListener screen_refresher = new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                ReloadImages(cardsinfo);
                gui.repaint();

            }
        };

        Timer screen_refresh_timer = new Timer(1000/60, screen_refresher);
        screen_refresh_timer.start();




    }
    public static void ReloadImages(ArrayList<CardForGraphics> cardsinfo)
    {
        for (CardForGraphics cardsinfoii : cardsinfo)
        {
            cardsinfoii.image = GraphicalUserInterface.loadbimg(cardsinfoii.path);
            cardsinfoii.image = GraphicalUserInterface.rotate(cardsinfoii.rotation, cardsinfoii);
        }
    }
}
