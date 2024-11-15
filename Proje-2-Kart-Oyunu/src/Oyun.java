import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Oyun
{
    public static void main(String[] args)
    {
        final int NUMBER_OF_CARDS = 6;
        int Turn_number = 0;

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



        for (; Turn_number < 1 ; Turn_number++) {

            SaldiriHesapla(Player,bilgisayar,1,2,3);
            Player.SkorGöster();
            bilgisayar.SkorGöster();

            if(Player.Playing_Cards.size() < 3 && Player.Playing_Cards.size() != 0){
                Player.ShuffleCards(3-Player.Playing_Cards.size());
                break;
            }
            else
                Player.ShuffleCards(1);


            if(bilgisayar.Playing_Cards.size() < 3 && Player.Playing_Cards.size() != 0){
                bilgisayar.ShuffleCards(3-bilgisayar.Playing_Cards.size());
                break;
            }
            else
                bilgisayar.ShuffleCards(1);
        }






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

    public static void SaldiriHesapla(Oyuncu Player,Oyuncu bilgisayar,int kart1 ,int kart2 ,int kart3){
        ArrayList<Integer> Cards = new ArrayList<>();           ///Hangi sayilari seçtiğimi tutmak için liste
        ArrayList<Integer> CardsForComputer = new ArrayList<>();        ///Üstteki ama bilgisayarlı versiyonu

        Cards = Player.kartSec(kart1,kart2,kart3);          ///Seçilen x,y,z kartlarini kart seç ile seç.
        CardsForComputer = bilgisayar.kartSec();            ///Üsttekinin aynısı ama bilgisayar hali bilgisayar random atama yapıyor.



        for (int i = 0; i < 3; i++) {       /// Her kartin karşısandakiyle savaşması için 3 kere dönüyor (sanirim)(sanırım değil ama yazması komik).

            int damageForPlayer = Player.Playing_Cards.get(Cards.get(i)).vurus;
            int damageForComputer = bilgisayar.Playing_Cards.get(Cards.get(i)).vurus;

            Player.Playing_Cards.get(Cards.get(i)).isUsed = true;
            bilgisayar.Playing_Cards.get(CardsForComputer.get(i)).isUsed = true;

            ///Damage for player

            if(Player.Playing_Cards.get(Cards.get(i)) instanceof Siha tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof DenizAraclari) damageForComputer += (tempt).denizVurusAvantaji;
            else if(Player.Playing_Cards.get(Cards.get(i)) instanceof Siha tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof KaraAraclari) damageForComputer += tempt.karaVurusAvantaji;
            else if(Player.Playing_Cards.get(Cards.get(i)) instanceof Ucak tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof KaraAraclari) damageForComputer += tempt.karaVurusAvantaji;

            if(Player.Playing_Cards.get(Cards.get(i)) instanceof KFS tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof DenizAraclari) damageForComputer += tempt.denizVurusAvantaji;
            else if (Player.Playing_Cards.get(Cards.get(i)) instanceof KFS tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof HavaAraclari) damageForComputer += tempt.havaVurusAvantaji;
            else if(Player.Playing_Cards.get(Cards.get(i)) instanceof Obus tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof DenizAraclari) damageForComputer += tempt.denizVurusAvantaji;

            if(Player.Playing_Cards.get(Cards.get(i)) instanceof Sida tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof KaraAraclari) damageForComputer += tempt.karaVurusAvantaji;
            else if (Player.Playing_Cards.get(Cards.get(i)) instanceof Sida tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof HavaAraclari) damageForComputer += tempt.havaVurusAvantaji;
            else if(Player.Playing_Cards.get(Cards.get(i)) instanceof Firkateyn tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof HavaAraclari) damageForComputer += tempt.havaVurusAvantaji;

            ///Damage for computer

            if(bilgisayar.Playing_Cards.get(Cards.get(i)) instanceof Siha tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof DenizAraclari) damageForComputer += (tempt).denizVurusAvantaji;
            else if(bilgisayar.Playing_Cards.get(Cards.get(i)) instanceof Siha tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof KaraAraclari) damageForComputer += tempt.karaVurusAvantaji;
            else if(bilgisayar.Playing_Cards.get(Cards.get(i)) instanceof Ucak tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof KaraAraclari) damageForComputer += tempt.karaVurusAvantaji;

            if(bilgisayar.Playing_Cards.get(Cards.get(i)) instanceof KFS tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof DenizAraclari) damageForComputer += tempt.denizVurusAvantaji;
            else if (bilgisayar.Playing_Cards.get(Cards.get(i)) instanceof KFS tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof HavaAraclari) damageForComputer += tempt.havaVurusAvantaji;
            else if(bilgisayar.Playing_Cards.get(Cards.get(i)) instanceof Obus tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof DenizAraclari) damageForComputer += tempt.denizVurusAvantaji;

            if(bilgisayar.Playing_Cards.get(Cards.get(i)) instanceof Sida tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof KaraAraclari) damageForComputer += tempt.karaVurusAvantaji;
            else if (bilgisayar.Playing_Cards.get(Cards.get(i)) instanceof Sida tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof HavaAraclari) damageForComputer += tempt.havaVurusAvantaji;
            else if(bilgisayar.Playing_Cards.get(Cards.get(i)) instanceof Firkateyn tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof HavaAraclari) damageForComputer += tempt.havaVurusAvantaji;


            int real_XP = bilgisayar.Playing_Cards.get(Cards.get(i)).seviyePuani;
            int real_XP1 = Player.Playing_Cards.get(Cards.get(i)).seviyePuani;


            int isDead = Player.Playing_Cards.get(Cards.get(i)).DurumGuncelle(damageForComputer,real_XP);        ///Xp ve hasar yolla
            int isDead1 = bilgisayar.Playing_Cards.get(Cards.get(i)).DurumGuncelle(damageForPlayer,real_XP1);         ///Xp ve hasar yolla

            if(isDead == -1){
                Player.Playing_Cards.remove(i);

            }
            if(isDead1 == -1){
                bilgisayar.Playing_Cards.remove(i);
            }

        }
    }
}
