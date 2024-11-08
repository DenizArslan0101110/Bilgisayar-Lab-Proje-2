
public class Oyun
{
    public static void main(String[] args)
    {
        final int NUMBER_OF_CARDS = 6;

        Oyuncu Player = new Oyuncu(1,"Oyuncu",0);
        Oyuncu bilgisayar = new Oyuncu(0,"Bilgisayar",0);

        Player.ShuffleCards(NUMBER_OF_CARDS);
        bilgisayar.ShuffleCards(NUMBER_OF_CARDS);
        System.out.println("Insanlarin kartlari :");
        Player.ShowCards();
        System.out.println("Bilgisayarin kartlari :");
        bilgisayar.ShowCards();


        GraphicalUserInterface window = new GraphicalUserInterface();
        window.MainGraphics();
    }
}
