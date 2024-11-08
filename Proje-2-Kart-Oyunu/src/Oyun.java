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
        ArrayList<CardForGraphics> cardsinfo= new  ArrayList<CardForGraphics>();
        cardsinfo.add(new CardForGraphics((short)200, (short)100, (short)612, (short)407, 45, "Visual/djsjdjebfowodbwocbwoc.jpg"));
        BufferedImage[] cards = new BufferedImage[20];
        cards = GraphicalUserInterface.fillImagesInAccordanceToTheirInfo(cardsinfo, cards, (byte)1);





    GraphicalUserInterface window = new GraphicalUserInterface();
    window.MainGraphics(cardsinfo, cards);
    }
}
