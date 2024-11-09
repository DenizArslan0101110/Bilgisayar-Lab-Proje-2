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

        Oyuncu Player = new Oyuncu(true,"Oyuncu",0);
        Oyuncu bilgisayar = new Oyuncu(false,"Bilgisayar",0);

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
                OrderCardsIntoADeck(cardsinfo);
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

    public static void OrderCardsIntoADeck(ArrayList<CardForGraphics> cardsinfo)
    {
        byte players_deck_size = 0;
        byte dealers_deck_size = 0;
        byte iforplayer = 0;
        byte ifordealer = 0;
        for(byte i=0; i<cardsinfo.size() ;i++)
        {
            if(cardsinfo.get(i).owners_ID) players_deck_size++;
            else dealers_deck_size++;
        }
        byte angle_increment_player = (byte)(90/players_deck_size);
        byte angle_increment_dealer = (byte)(90/dealers_deck_size);
        for(byte i=0; i<cardsinfo.size() ;i++)
        {
            if(cardsinfo.get(i).owners_ID)
            {
                cardsinfo.get(i).SetRotation(-45+((iforplayer+0.5)*angle_increment_player));
                cardsinfo.get(i).SetXPos((short)(653 - (30*players_deck_size)+(iforplayer*60)));
                cardsinfo.get(i).SetYPos((short)(638 + (Math.pow(Math.abs(cardsinfo.get(i).rotation/3), 1.7) ) ) );
                iforplayer++;
            }
            else
            {
                cardsinfo.get(i).SetRotation(-45+180+(ifordealer+0.5)*angle_increment_dealer);
                cardsinfo.get(i).SetXPos((short)(603 + (30*dealers_deck_size)-(ifordealer*60)));
                cardsinfo.get(i).SetYPos((short)(50 - (Math.pow(Math.abs((180-cardsinfo.get(i).rotation)/3), 1.7) ) ) );
                ifordealer++;
            }
        }
    }
}
